package textExcel;

/**
 * THis class handles the percent values
 * @author Derfel Terciano
 * @version 1
 */
public class PercentCell extends RealCell {
	//constructor
	public PercentCell(String userInput) {
		super.setUserInput(userInput);
	}

	/*
	 * returns the double value of a given string
	 */
	public double getDoubleValue() {
		double inputHolder = Double.parseDouble(super.fullCellText());
		return inputHolder;
	}

}
