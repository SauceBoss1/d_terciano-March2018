package textExcel;

/**
 * This class finds the ranges of given ranges depending on the type of values
 * in the spreadsheet
 * 
 * @author Derfel Terciano
 * @version 1
 */
public class Ranges {
	private Spreadsheet spreadsheet;
	private String rang1, rang2;

	public Ranges(Spreadsheet sheet) {
		spreadsheet = sheet;
	}

	/*
	 * This range method puts all the cell within the given range (i.e. all cells
	 * within a1-k20) into one long array.
	 * 
	 * The main purpose of this is so that the calculation of the array would become
	 * ultimately easier
	 */
	public RealCell[] rangeOfDoubles() {
		int intIterator = Integer.parseInt(rang1.toLowerCase().substring(1));// iterator for the below for-loop(rows)
																				// This is
																				// the first number of the row
		int intTest = Integer.parseInt(rang2.toLowerCase().substring(1));// the test case for the below for loops(rows)
																			// This
																			// is the last number of the row
		char charIterator = rang1.toLowerCase().charAt(0);// iterator for the below for-loop(columns) This is the
															// first letter of the column
		char charTest = rang2.toLowerCase().charAt(0);// iterator for the below for-loop(columns) This is the last
														// letter of the column
		/*
		 * size of the resulting array is determined by: multiplying the length and
		 * width of the grid ranges selected
		 * 
		 * this is why I am trying to find the the length and width of the selected
		 * ranges below
		 */
		RealCell[] result = new RealCell[(charTest - charIterator + 1) * (intTest - intIterator + 1)];

		int arrTracker = 0;// keeps track of what element goes into the resulting array
		for (int i = intIterator; i <= intTest; i++) {
			for (char j = charIterator; j <= charTest; j++) {
				String currentLoc = "" + j + i;// this variable keeps track of the current
												// cell within the current iteration
												// this also gives us the cell within the given ranges
				SpreadsheetLocation loc = new SpreadsheetLocation(currentLoc);
				result[arrTracker] = (RealCell)spreadsheet.getCell(loc);// finds double at this location
				arrTracker += 1;// helps to keep track of position of where to put the resulting
								// getDoubleValue() in the resulting array
			}
		}
		return result;
	}

	/*
	 * ValueOfCell helps find the double at the given cell location
	 */
	public double doubleValueOfCell(String location) {
		SpreadsheetLocation loc = new SpreadsheetLocation(location);
		Cell cellType = spreadsheet.getCell(loc);// helps find the actual cell at current location of
													// the loop's current iteration
		return ((RealCell) cellType).getDoubleValue();// casts the cell into a RealCell in order
														// to get its double value
	}

	public String[] rangeOfStrings() {
		int intIterator = Integer.parseInt(rang1.toLowerCase().substring(1));// iterator for the below for-loop(rows)
																				// This is
																				// the first number of the row
		int intTest = Integer.parseInt(rang2.toLowerCase().substring(1));// the test case for the below for loops(rows)
																			// This
																			// is the last number of the row
		char charIterator = rang1.toLowerCase().charAt(0);// iterator for the below for-loop(columns) This is the
															// first letter of the column
		char charTest = rang2.toLowerCase().charAt(0);// iterator for the below for-loop(columns) This is the last
														// letter of the column
		String[] result = new String[(charTest - charIterator + 1) * (intTest - intIterator + 1)];

		int arrTracker = 0;// keeps track of what element goes into the resulting array
		for (int i = intIterator; i <= intTest; i++) {
			for (char j = charIterator; j <= charTest; j++) {
				String currentLoc = "" + j + i;// this variable keeps track of the current
												// cell within the current iteration
												// this also gives us the cell within the given ranges
				result[arrTracker] = stringValueOfCell(currentLoc);// finds double at this location
				arrTracker += 1;// helps to keep track of position of where to put the resulting
								// getDoubleValue() in the resulting array
			}
		}
		return result;
	}

	public String stringValueOfCell(String currentLoc) {
		SpreadsheetLocation loc = new SpreadsheetLocation(currentLoc);
		Cell cellType = spreadsheet.getCell(loc);
		return ((TextCell) cellType).fullCellText();
	}

	public void setRanges(String r1, String r2) {
		rang1 = r1;
		rang2 = r2;
	}

	public void setGridOfDoubles(RealCell[] arr) {
		int intIterator = Integer.parseInt(rang1.toLowerCase().substring(1));// iterator for the below for-loop(rows)
																					// This is
																					// the first number of the row
		int intTest = Integer.parseInt(rang2.toLowerCase().substring(1));// the test case for the below for loops(rows)
																				// This
																				// is the last number of the row
		char charIterator = rang1.toLowerCase().charAt(0);// iterator for the below for-loop(columns) This is the
															// first letter of the column
		char charTest = rang2.toLowerCase().charAt(0);// iterator for the below for-loop(columns) This is the last
															// letter of the column
		int tracker = 0;
		for (int i = intIterator; i <= intTest; i++) {
			for (char j = charIterator; j <= charTest; j++) {
				String currentLoc = ""+j+i;
				SpreadsheetLocation loc = new SpreadsheetLocation(currentLoc);
				spreadsheet.setCell(loc,(arr[tracker]));
				tracker++;
				
			}
		}
	}

}
