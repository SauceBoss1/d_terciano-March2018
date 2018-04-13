package textExcel;
/**
 * 
 * @author Derfel Terciano
 * @version 1
 */
public class ValueCell extends RealCell{

	public ValueCell(String userInput) {
		super.setUserInput(userInput);
	}

	public double getDoubleValue() {//parses string into a double
		double strInput = Double.parseDouble(super.fullCellText());
		return strInput;
		
	}

}
