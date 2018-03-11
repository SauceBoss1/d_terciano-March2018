package textExcel;

// Update this file with your own code.

/**
 * @author Derfel Terciano
 * @version 1
 */
public class Spreadsheet implements Grid {
	private final int rowLength = 20;
	private final int columnLength = 12;
	private Cell[][] grid = new Cell[rowLength][columnLength];
	
	public Spreadsheet() {
		for(int row=0 ; row<grid.length;row++) {
			for(int col=0;col<grid[row].length;col++) {
				grid[row][col]= new EmptyCell();
			}
		}
	}
	
	@Override
	public String processCommand(String command) {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	public int getRows() {
		// TODO Auto-generated method stub
		return rowLength;
	}

	@Override
	public int getCols() {
		// TODO Auto-generated method stub
		return columnLength;
	}

	@Override
	public Cell getCell(Location loc) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getGridText() {
		// TODO Auto-generated method stub
		return null;
	}

}
