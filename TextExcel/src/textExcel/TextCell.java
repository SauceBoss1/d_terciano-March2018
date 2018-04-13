package textExcel;

public class TextCell implements Cell{
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


	public String fullCellText() {//like a getter method
		
		return text;
	}
	
	public TextCell(String text) {
		this.text=text;
	}

}
