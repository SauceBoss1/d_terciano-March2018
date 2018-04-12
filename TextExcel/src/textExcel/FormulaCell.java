package textExcel;

/**
 * 
 * @author Derfel Terciano
 * @version 1
 */
public class FormulaCell extends RealCell {
	private Spreadsheet spreadsheet;

	/*
	 * takes in a formula string and a spreadsheet
	 */
	public FormulaCell(String input, Spreadsheet spreadsheet) {
		super.setUserInput(input);
		this.spreadsheet = spreadsheet;
	}

	@Override
	/*
	 * abbreviates the cell
	 */
	public String abbreviatedCellText() {
		String result = getDoubleValue() + "";
		if (result.length() > 10) {
			return result.substring(0, 10);
		} else {
			while (result.length() < 10) {
				result += " ";
			}
			return result;
		}
	}

	public double getDoubleValue() {
		String[] userFormula = super.getUserInput().substring(2, getUserInput().length() - 2).trim().split(" ");// trims
																												// the
		// formula
		// so that
		// we can
		// parse
		// operations
		// into
		// formulas that are easy to calculate
		char[] userFormula1 = toChar(userFormula);// converts the first character of a string array into a char array
		boolean isNumeric = true;
		for (int i = 0; i < userFormula.length; i += 2) {// checks if the entire array is using all numeric values
			if (!Character.isDigit(userFormula1[i])) {
				isNumeric = false;// if there is a letter w/in the array then hat means its a cell reference and
									// returns false
			}
		}
		/*
		 * checks if the user formula has a sum or average method
		 * 
		 * if not then it checks if the user formula has cell reference and then calculates those
		 * if there is no cell references then just calculate the array
		 */
		if (userFormula[0].toLowerCase().equals("sum")) {
			String[] ranges = userFormula[1].split("-");
			return sum(ranges[0], ranges[1]);
		} else if (userFormula[0].toLowerCase().equals("avg")) {
			String[] ranges = userFormula[1].split("-");
			return avg(ranges[0], ranges[1]);
		} else if (isNumeric) {// for now this is a setup for checkpoint 5 dont worry about this for now
			return calculationOfArray(userFormula);
		} else {
			for (int i = 0; i < userFormula.length; i++) {
				if (userFormula[i].toLowerCase().charAt(0) >= 'a' && userFormula[i].toLowerCase().charAt(0) <= 'l') {
					SpreadsheetLocation location = new SpreadsheetLocation(userFormula[i]);
					Cell cellType = spreadsheet.getCell(location);
					if (cellType instanceof RealCell) {
						userFormula[i] = ((RealCell) cellType).getDoubleValue() + "";
					} else {
						userFormula[i] = "";
					}
				}
			}
			return calculationOfArray(userFormula);
		}

	}

	/*
	 * converts the first character of a given string array into a char array
	 * 
	 * this is useful for determining if a given formula has cell references or are
	 * all numeric (number) values
	 */
	public char[] toChar(String[] input) {
		char[] result = new char[input.length];
		for (int i = 0; i < input.length; i++) {
			result[i] = input[i].charAt(0);
		}
		return result;
	}

	/*
	 * calculates the sum of the given range of cells
	 */
	public double sum(String range1, String range2) {
		double[] userRange = range(range1, range2);
		double result = 0;
		for (int i = 0; i < userRange.length; i++) {
			result += userRange[i];
		}
		return result;
	}

	/*
	 * calculates the average of the given range of cells
	 */
	public double avg(String range1, String range2) {
		double userSum = sum(range1, range2);
		double lengthOfArray = range(range1, range2).length;
		return userSum / lengthOfArray;
	}

	/*
	 * calculates all of the operands and operations in an array
	 */
	public double calculationOfArray(String[] arr) {
		double result = Double.parseDouble(arr[0]);
		for (int i = 1; i < arr.length; i += 2) {
			result = operation(result, Double.parseDouble(arr[i + 1]), arr[i]);
		}
		return result;
	}

	/*
	 * This method is extremely helpful as it does the calculation for the array
	 * with all operands and operations
	 */
	public double operation(double operand1, double operand2, String operation) {
		double result = operand1;
		if (operation.equals("+")) {
			result = operand1 + operand2;
		} else if (operation.equals("-")) {
			result = operand1 - operand2;
		} else if (operation.equals("*")) {
			result = operand1 * operand2;
		} else if (operation.equals("/")) {
			result = operand1 / operand2;
		}
		return result;
	}

	/*
	 * This range method puts all the cell within the given range (i.e. all cells
	 * within a1-k20) into one long array.
	 * 
	 * The main purpose of this is so that the calculation of the array would become
	 * ultimately easier
	 */
	public double[] range(String range1, String range2) {
		int intIterator = Integer.parseInt(range1.toLowerCase().substring(1));// iterator for the below for-loop(rows)
																				// This is
																				// the first number of the row
		int intTest = Integer.parseInt(range2.toLowerCase().substring(1));// the test case for the below for loops(rows)
																			// This
																			// is the last number of the row
		char charIterator = range1.toLowerCase().charAt(0);// iterator for the below for-loop(columns) This is the
															// first letter of the column
		char charTest = range2.toLowerCase().charAt(0);// iterator for the below for-loop(columns) This is the last
														// letter of the column
		/*
		 * size of the resulting array is determined by: multiplying the length and
		 * width of the grid ranges selected
		 * 
		 * this is why I am trying to find the the length and width of the selected
		 * ranges below
		 */
		double[] result = new double[(charTest - charIterator + 1) * (intTest - intIterator + 1)];

		int arrTracker = 0;// keeps track of what element goes into the resulting array
		for (int i = intIterator; i <= intTest; i++) {
			for (char j = charIterator; j <= charTest; j++) {
				String currentLoc = "" + j + i;// this variable keeps track of the current
												// cell within the current iteration
												// this also gives us the cell within the given ranges

				SpreadsheetLocation loc = new SpreadsheetLocation(currentLoc);
				Cell cellType = spreadsheet.getCell(loc);// helps find the actual cell at current location of
															// the loop's current iteration
				result[arrTracker] = ((RealCell) cellType).getDoubleValue();// casts the cell into a RealCell in order
																			// to get its double value
				arrTracker += 1;// helps to keep track of position of where to put the resulting
								// getDoubleValue() in the resulting array
			}
		}
		return result;
	}

}
