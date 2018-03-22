package textExcel;

public class FormulaCell extends RealCell {
	
	public FormulaCell(String input) {
		super.setUserInput(input);
	}
	
	
	public double getDoubleValue() {
		String[] userFormula = super.getUserInput().substring(2, getUserInput().length()-2).split(" ");
		if(userFormula[0].isDigit())
		return 0;
	}
	
	public char[] toChar(String[] input) {
		for(int i ; i < input.length; i++) {
			
		}
		return '0';
	}
	
	

}
