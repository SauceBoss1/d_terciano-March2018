package textExcel;

public class TextCell implements Cell{
	private String text;


	public String abbreviatedCellText() {
		// TODO Auto-generated method stub
		return text.substring(0,9);
	}


	public String fullCellText() {
		
		return "\""+text+"\"";
	}
	
	public void setCell(String text) {
		this.text=text;
	}

}
