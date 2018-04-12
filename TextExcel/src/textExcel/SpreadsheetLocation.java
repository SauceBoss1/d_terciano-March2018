package textExcel;

import java.util.ArrayList;
/**
 * @author Derfel Terciano
 * @version 1
 */

public class SpreadsheetLocation implements Location {
	private String cellName;
	/*
	 * allows us to get the row of the given cellName
	 * this throws an exception if given row is not in a correct format
	 */
	public int getRow() {
		if(Character.isDigit(cellName.charAt(1))) {
			int rowNum = Integer.parseInt(cellName.substring(1));
			return rowNum-1;
		}else {
			throw new IllegalArgumentException("Row # must come after the Column Letter");
		}
		
	}

	/*
	 * allows us to get the column of the given cellName
	 * this throws an exception if given column is not in a correct format
	 */
	public int getCol() {
		char columnLetter = cellName.charAt(0);
		if(Character.isDigit(columnLetter)) {
			throw new IllegalArgumentException("Letter of column must always go first");
		} else {
			return (int)Character.toLowerCase(columnLetter)-'a';
		}
	}
	
	/*
	 * constructor
	 */
	public SpreadsheetLocation(String cellName) {
		this.cellName=cellName;
	}

}
