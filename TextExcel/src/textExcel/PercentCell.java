package textExcel;

public class PercentCell extends RealCell {
	
	public PercentCell(String userInput) {
		super.setUserInput(userInput);
	}


	@Override
	public double getDoubleValue() {
		double inputHolder = Double.parseDouble(super.fullCellText());
		return inputHolder;
	}

}
