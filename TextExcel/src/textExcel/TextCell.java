package textExcel;

public class TextCell implements Cell, Comparable<TextCell>{
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
	 */
	public int compareTo(TextCell obj) {
		if (obj.fullCellText()==null) {
			throw new IllegalArgumentException("TextCell obj must have a valid string");
		} else {
			return fullCellText().compareTo(obj.fullCellText());
		}
		
	}


	public String fullCellText() {//like a getter method
		
		return text;
	}
	
	public TextCell(String text) {
		this.text=text;
	}
	
	

}
