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
		for (int row = 0; row < grid.length; row++) {
			for (int col = 0; col < grid[row].length; col++) {
				grid[row][col] = new EmptyCell();
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
		return rowLength;
	}

	@Override
	public int getCols() {
		return columnLength;
	}

	@Override
	public Cell getCell(Location loc) {
		return grid[loc.getRow()][loc.getCol()];
	}

	@Override
	public String getGridText() {
		String gridOutput="";
		for(char i='A'; i<'L';i++) {
			gridOutput+="|"+i+"         |";
		}
		gridOutput+="\n";
		for(int i=0;i<grid.length;i++) {
			gridOutput+=(i+1);
			for(int j=0;i<grid[i].length;j++) {
				
			}
		}
		return null;
	}

}
