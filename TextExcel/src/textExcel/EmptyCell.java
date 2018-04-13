package textExcel;
/**
 * Pretty self-explanatory class
 * 
 * @author Derfel Terciano
 * @version 1
 */
public class EmptyCell implements Cell{
	
	// text for spreadsheet cell display, must be exactly length
	public String abbreviatedCellText() {
		return "          ";
	}
	
	// text for individual cell inspection, not truncated or padded
	@Override
	public String fullCellText() {
		return "";
	}

}
