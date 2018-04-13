package textExcel;

/**
 * 
 * @author Derfel Terciano
 * @version 1
 */
public abstract class RealCell implements Cell {

	private String userInput;//field to store the user's input

	abstract double getDoubleValue();//required abstrac method that all sub-classes must implment

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

}
