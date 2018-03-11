package textExcel;

import java.util.ArrayList;
/**
 * @author Derfel Terciano
 * @version 1
 */
//Update this file with your own code.

public class SpreadsheetLocation implements Location {
	private String cellName;
	@Override
	public int getRow() {
		if(Character.isDigit(cellName.charAt(1))) {
			int rowNum = Integer.parseInt(cellName.substring(1));
			return rowNum-1;
		}else {
			throw new IllegalArgumentException("Row # must come after the Column Letter");
		}
		
	}

	@Override
	public int getCol() {
		char columnLetter = cellName.charAt(0);
		if(Character.isDigit(columnLetter)) {
			throw new IllegalArgumentException("Letter of column must always go first");
		} else {
			return (int)Character.toLowerCase(columnLetter)-'a';
		}
	}

	public SpreadsheetLocation(String cellName) {
		this.cellName=cellName;
	}

}
