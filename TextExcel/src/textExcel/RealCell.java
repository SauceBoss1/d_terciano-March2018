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
		if (dummyInput.contains("%") && dummyInput.contains(".")) {//checks if the input is a percent
			//truncates the "%" where ever the dot or period  is located
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
		Double doub1 = new Double(getDoubleValue());
		Double doub2 = new Double(obj.getDoubleValue());
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
	
	public void selectionSort(RealCell[] sort, String typeOfSort) {
		int sortType=0; //determines whether the algorithm should be ascending or descending order
		Double[] arr = convDouble(sort);		
		//Double[] arr = convDouble(sort);
		if (typeOfSort.toLowerCase().equals("ascending")) {
			sortType=-1;
		}else {
			sortType=1;
		}
		for(int j=0;j<arr.length-1;j++){
			int min=j;
			for(int k = j+1;k<arr.length;k++) {
				if(arr[k].compareTo(arr[min]) == sortType) {//compareTo only uses the Double object
					min=k;
				}
			}
			double temp = arr[j];
			arr[j]=arr[min];
			arr[min]=temp;
		}
	}
	
	public void sortA(String rang1, String rang2) {
		Ranges range = new Ranges(spreadsheet);
		range.setRanges(rang1, rang2);
		RealCell[] sort = range.rangeOfDoubles();
		selectionSort(sort, "ascending");
		range.setGridOfDoubles(sort);
		
	}
	public void sortD(String rang1, String rang2) {
		Ranges range = new Ranges(spreadsheet);
		range.setRanges(rang1, rang2);
		RealCell[] sort = range.rangeOfDoubles();
		selectionSort(sort, "descending");
		range.setGridOfDoubles(sort);
	}
}
	
	
