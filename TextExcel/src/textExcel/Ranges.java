package textExcel;

/**
 * This class finds the ranges of given ranges depending on the type of values
 * in the spreadsheet and then converts them into one array for easy manipulation
 * 
 * @author Derfel Terciano
 * @version 1
 */
public class Ranges {
	private Spreadsheet spreadsheet;
	private String rang1, rang2;

	public Ranges(Spreadsheet sheet, String rang1, String rang2) {
		spreadsheet = sheet;
		this.rang1=rang1;
		this.rang2=rang2;
	}
	
	/*
	 * NOTE:
	 * 
	 *		I would have made this less redundant, however, the methods return different types of arrays
	 *		Thus I was left with no choice but to create two sepreate methods that do exactly the same thing.
	 */

	/**
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
				result[arrTracker] = (RealCell)spreadsheet.getCell(new SpreadsheetLocation(currentLoc));// finds RealCell at this location
				arrTracker += 1;// helps to keep track of position of where to put the resulting
								// RealCell() in the resulting array
			}
		}
		return result;
	}

	/**
	 * This range method puts all the cell within the given range (i.e. all cells
	 * within a1-k20) into one long array.
	 * 
	 * The main purpose of this is so that the manipulation and sorting of the array would become
	 * ultimately easier
	 * 
	 * returns an array of textCells
	 */
	public TextCell[] rangeOfStrings() {
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
		TextCell[] result = new TextCell[(charTest - charIterator + 1) * (intTest - intIterator + 1)];

		int arrTracker = 0;// keeps track of what element goes into the resulting array
		for (int i = intIterator; i <= intTest; i++) {
			for (char j = charIterator; j <= charTest; j++) {
				String currentLoc = "" + j + i;// this variable keeps track of the current
												// cell within the current iteration
												// this also gives us the cell within the given ranges
				result[arrTracker] = (TextCell)spreadsheet.getCell(new SpreadsheetLocation(currentLoc));// finds the TextCell at this location
				arrTracker += 1;// helps to keep track of position of where to put the resulting
								// TextCell in the resulting array
			}
		}
		return result;
	}


	public void setRanges(String r1, String r2) {
		rang1 = r1;
		rang2 = r2;
	}
	
	/**
	 * once the array has been sorted or manipulated, it is ready to be put back into the grid of the
	 * spreadsheet.
	 * The same concept applies like the range methods, however, this methods manipulates the grid
	 * instead of copying elements of the grid. This is mainly possible through the setCell method
	 * 
	 * Since it does not matter what we put back into the grid/spreadsheet, it is ok to just accept
	 * any Cell where as the methods before depend on what kind of array is in the output.
	 * @param arr
	 */
	public void setGridOfCells(Cell[] arr) {
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
	
	/**
	 * 
	 * The same situation like the ranges method occurred here, I could have made this less redundant, 
	 * however, since the compare methods for the RealCell and textCell are completely different,
	 * I was left with no choice but to create two compareTo methods in their respective class.
	 * 
	 * Sorting uses the selectionSort algorithm since it was the least complicated algorithm to implement.
	 * This particular method of selectionSort sorts only REALCELLS in ascending order
	 * due the problems as aforementioned.
	 * 
	 * @param sort
	 * @param typeOfSort
	 * @return RealCell
	 */
	public RealCell[] selectionSortRealCells(RealCell[] sort, String typeOfSort) {
		int sortType=0; //determines whether the algorithm should be ascending or descending order
		if (typeOfSort.toLowerCase().equals("ascending")) {
			sortType=-1;
		}else {
			sortType=1;
		}
		for(int j=0;j<sort.length-1;j++){
			int min=j;
			for(int k = j+1;k<sort.length;k++) {
				if(sort[k].compareTo(sort[min]) == sortType) {//uses only the realCell object
					min=k;
				}
			}
			RealCell temp = sort[j];
			sort[j]=sort[min];
			sort[min]=temp;
		}
		return sort;
	}
	
	/**
	 * 
	 * The same situation like the ranges method occurred here, I could have made this less redundant, 
	 * however, since the compare methods for the RealCell and textCell are completely different,
	 * I was left with no choice but to create two compareTo methods in their respective class.
	 * 
	 * Sorting uses the selectionSort algorithm since it was the least complicated algorithm to implement.
	 * This particular method of selectionSort sorts only TEXTCELLS in ascending order
	 * due the problems as aforementioned.
	 * 
	 * @param sort
	 * @param typeOfSort
	 * @return RealCell
	 */
	public TextCell[] selectionSortTextCells(TextCell[] arr, String typeOfSort) {
		int sortType=0; //determines whether the algorithm should be ascending or descending order
		if (typeOfSort.toLowerCase().equals("ascending")) {
			sortType=-1;
		}else {
			sortType=1;
		}
		for(int j=0;j<arr.length-1;j++){
			int min=j;
			for(int k = j+1;k<arr.length;k++) {
				if(arr[k].compareTo(arr[min]) == sortType) {//compareTo only uses the TextCell object
					min=k;
				}
			}
			TextCell temp = arr[j];
			arr[j]=arr[min];
			arr[min]=temp;
		}
		return arr;
	}

}
