package textExcel;

public abstract class RealCell implements Cell{

	private String userInput;
	
	abstract double getDoubleValue();
	
	public String getUserInput() {
		return this.userInput;
	}
	@Override
	public String abbreviatedCellText() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String fullCellText() {
		// TODO Auto-generated method stub
		return null;
	}

}
