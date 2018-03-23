package textExcel;

public class FormulaCell extends RealCell {
	
	public FormulaCell(String input) {
		super.setUserInput(input);
	}
	
	
	public double getDoubleValue() {
		String[] userFormula = super.getUserInput().substring(2, getUserInput().length()-2).split(" ");
		char[] userFormula1 = toChar(userFormula);
		for(int i = 0 ; i<userFormula1.length; i++) {
			if(Character.isDigit(userFormula1[i])) {
				
			}
		}
		return 0;
	}
	
	public char[] toChar(String[] input) {
		char[] result = new char[input.length];
		for(int i = 0; i < input.length; i++) {
			result[i]= input[i].charAt(0);
		}
		return result;
	}
	
	

}
