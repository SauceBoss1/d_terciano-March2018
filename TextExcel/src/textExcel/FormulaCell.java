package textExcel;

public class FormulaCell extends RealCell {
	private Spreadsheet spreadsheet;

	public FormulaCell(String input, Spreadsheet spreadsheet) {
		super.setUserInput(input);
		this.spreadsheet = spreadsheet;
	}

	@Override
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
		// formulas
		char[] userFormula1 = toChar(userFormula);
		boolean isNumeric = true;
		for (int i = 0; i < userFormula.length; i += 2) {// checks if the entire array is using all numeric values
			if (!Character.isDigit(userFormula1[i])) {
				isNumeric = false;
			}
		}
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

	public char[] toChar(String[] input) {
		char[] result = new char[input.length];
		for (int i = 0; i < input.length; i++) {
			result[i] = input[i].charAt(0);
		}
		return result;
	}

	public double sum(String range1, String range2) {
		double[] userRange = range(range1, range2);
		double result = 0;
		for (int i = 0; i < userRange.length; i++) {
			result += userRange[i];
		}
		return result;
	}

	public double avg(String range1, String range2) {
		double userSum = sum(range1, range2);
		double lengthOfArray = range(range1, range2).length;
		return userSum / lengthOfArray;
	}

	public double calculationOfArray(String[] arr) {
		double result = Double.parseDouble(arr[0]);
		for (int i = 1; i < arr.length; i += 2) {
			result = operation(result, Double.parseDouble(arr[i + 1]), arr[i]);
		}
		return result;
	}

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

	public double[] range(String range1, String range2) {// creates an array to be calculated from the first coordinate
															// to the last coordinate
		int intIterator = Integer.parseInt(range1.toLowerCase().substring(1));
		int intTest = Integer.parseInt(range2.toLowerCase().substring(1));

		double[] result = new double[(range2.toLowerCase().charAt(0) - range1.toLowerCase().charAt(0) + 1) * (intTest - intIterator + 1)];

		int arrTracker = 0;// keeps track of what element goes into the resulting array
		for (int i = intIterator; i <= intTest; i++) {
			for (char j = range1.toLowerCase().charAt(0); j <= range2.toLowerCase().charAt(0); j++) {

				String currentLoc = "" + j + i;
				SpreadsheetLocation loc = new SpreadsheetLocation(currentLoc);
				Cell cellType = spreadsheet.getCell(loc);
				result[arrTracker] = ((RealCell) cellType).getDoubleValue();
				arrTracker += 1;
			}
		}
		return result;
	}

}
