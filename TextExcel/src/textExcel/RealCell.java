package textExcel;

/**
 * 
 * @author Derfel Terciano
 * @version 1
 */
public abstract class RealCell implements Cell, Comparable<RealCell> {
	private Spreadsheet spreadsheet;

	private String userInput;//field to store the user's input

	abstract double getDoubleValue();//required abstract method that all sub-classes must implment

	public String getUserInput() {//getter
		return this.userInput;
	}

	public void setUserInput(String input) {//setter
		userInput = input;
	}

	/*
	 * this method parses the user's input so that
	 * it can fit the string into 10 spaces
	 */
	public String abbreviatedCellText() {
		String dummyInput = getUserInput();
		if (dummyInput.contains("%")) {//checks if the input is a percent
			//truncates the "%" where ever the dot or period  is located
			//TODO fix Error
			double inputAsDouble = Double.parseDouble(fullCellText());
			dummyInput = (inputAsDouble*100) +"";
			dummyInput = dummyInput.replace(dummyInput.substring(dummyInput.indexOf(".")), "%");
		} else {
			dummyInput = Double.parseDouble(dummyInput) + "";//makes all integers a double
		}

		if (dummyInput.length() > 10) {//checks if the user input is greater than 10 spaces
			dummyInput = dummyInput.substring(0, 10);//if so then just get the first 10 charcters
		} else {
			while (dummyInput.length() < 10) {//if truncate spaces to the end until the total spacing reaches 10
				dummyInput += " ";//adds required spaces
			}
		}
		return dummyInput;
	}

	@Override 
	public String fullCellText() {
		String dummyInput = userInput;
		/*
		 * if the entire input is a percentage then this will convert
		 * it into decimal form
		 */
		if (dummyInput.contains("%")) {
			double inputAsDouble = Double.parseDouble(dummyInput.replace("%", "")); // removes the percent sign
			dummyInput = (inputAsDouble * 0.01) + ""; // moves the decimal place 2 places to the right
		}
		return dummyInput;
	}
	
	/**
	 * method compares the getDouble methods of each object
	 * follows the returning format for the String.compareTo(anotherString) method
	 * 
	 * 			Consider the following situation of 2 RealCell objects:
	 * 						a.compareTo(b)
	 * 
	 * returns 1 if a>b
	 * returns 0 if a==b
	 * returns -1 if a<b
	 */
	public int compareTo(RealCell obj) {
		double doub1 = getDoubleValue();
		double doub2 = obj.getDoubleValue();
		if (doub1>doub2) {
			return 1;
		} else if(doub1==doub2) {
			return 0;
		}else {
			return -1;
		}
	}
	
	public Double[] convDouble(RealCell[] arr) {
		Double[] result = new Double[arr.length];
		int tracker=0;
		for(RealCell element:arr) {
			result[tracker]=element.getDoubleValue();
			tracker++;
		}
		return result;
		
	}
}
	
	
