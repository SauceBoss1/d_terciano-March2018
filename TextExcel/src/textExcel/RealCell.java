package textExcel;

public abstract class RealCell implements Cell {

	private String userInput;

	abstract double getDoubleValue();

	public String getUserInput() {
		return this.userInput;
	}

	public void setUserInput(String input) {
		userInput = input;
	}

	@Override
	public String abbreviatedCellText() {
		String dummyInput = getUserInput();
		if(dummyInput.contains("%") && dummyInput.contains(".")) {
			dummyInput = dummyInput.replace(dummyInput.substring(dummyInput.indexOf(".")),"%");
		}else {
			dummyInput = Double.parseDouble(dummyInput)+"";
		}
		
		if(dummyInput.length()>10) {
			dummyInput = dummyInput.substring(0,10);
		} else {
			while(dummyInput.length()<10) {
				dummyInput+=" ";
			}
		}
		return dummyInput;
	}

	@Override
	public String fullCellText() {
		String dummyInput = userInput;
		if (dummyInput.contains("%")) {
			double inputAsDouble = Double.parseDouble(dummyInput.replace("%","")); //removes the percent sign
			dummyInput = (inputAsDouble*0.01)+""; //moves the decimal place 2 places to the right
		}
		return dummyInput;
	}

}
