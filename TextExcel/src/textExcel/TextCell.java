package textExcel;

public class TextCell implements Cell, Comparable<Cell>{
	private String text;
	
	
	/*
	 * abbreviates text to 10 space
	 */
	public String abbreviatedCellText() {
		String abbreviatedText="";
		if(text.substring(1,text.length()-1).length()<10) {
			abbreviatedText=text.substring(1,text.length()-1);
			while(abbreviatedText.length()<10) {
				abbreviatedText+=" ";
			}
		}else{
			return text.substring(1,11);
		}
		return abbreviatedText;
	}
	
	/**
	 * returns the same numbers as the String.compareTo() method
	 * compares two String objects
	 * 
	 * due to the unpredictability of what the String.compareTo() method does except for
	 * the description given in the AP reference sheet, I have taken safety measures and made anything 
	 * greater than 0 equal to one, equal to zero equal to 0, and anything less than 0 to be equal to
	 * -1
	 */
	public int compareTo(Cell obj) {
		if(obj instanceof TextCell) {
			if (obj.fullCellText()==null) {
				throw new IllegalArgumentException("TextCell obj must have a valid string");
			} else if (fullCellText().compareTo(obj.fullCellText()) > 0){
				return 1;
			}else if(fullCellText().compareTo(obj.fullCellText()) < 0) {
				return -1;
			}else {
				return 0;
			}
		}else {
			throw new IllegalArgumentException("compareTo needs to be of type TextCell");
		}
		
	}


	public String fullCellText() {//like a getter method
		
		return text;
	}
	
	public TextCell(String text) {
		this.text=text;
	}
	
	

}
