package textExcel;

public class FormulaCell extends RealCell {
	private Spreadsheet spreadsheet;

	public FormulaCell(String input, Spreadsheet spreadsheet) {
		super.setUserInput(input);
		this.spreadsheet= spreadsheet;
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
		String[] userFormula = super.getUserInput().substring(2, getUserInput().length() - 2).trim().split(" ");// trims the
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
		if (isNumeric) {// for now this is a setup for checkpoint 5 dont worry about this for now
						//TODO finish other possibilities of formula
			return calculationOfArray(userFormula);
		} else {
			for(int i = 0; i < userFormula.length;i++) {
				if(userFormula[i].toLowerCase().charAt(0) >= 'a' && userFormula[i].toLowerCase().charAt(0) <= 'l') {
					SpreadsheetLocation location = new SpreadsheetLocation(userFormula[i]);
					Cell cellType = spreadsheet.getCell(location);
					if ( cellType instanceof RealCell) {
						userFormula[i]= ((RealCell)cellType).getDoubleValue() + "";
					} else {
						userFormula[i]= "";
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

	public double calculationOfArray(String[] arr) {
		double result = Double.parseDouble(arr[0]);
		for (int i = 1; i < arr.length; i+=2) {
			result = operation(result, Double.parseDouble(arr[i+1]), arr[i]);
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

}
