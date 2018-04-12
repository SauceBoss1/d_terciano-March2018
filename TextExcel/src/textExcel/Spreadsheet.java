package textExcel;


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


	public String processCommand(String command) {
		String[] userCommand = command.split(" ", 3);
		if (userCommand.length == 3) {// might make method of assigning all values
			cellAssignment(userCommand[0], userCommand[2]);
			return getGridText();
		} else if (userCommand[0].toLowerCase().equals("clear")) {
			if (userCommand.length >= 2) {
				clear(userCommand[1]);
				return getGridText();
			} else {
				clear();
				return getGridText();
			}
		} else if (userCommand.length == 1) {
			return contentsOfCell(userCommand[0]);
		} else {
			return "command unknown";
		}
	}

	public void cellAssignment(String loc, String content) {
		SpreadsheetLocation location = new SpreadsheetLocation(loc.toLowerCase());
		if (content.contains("\"")) {
			grid[location.getRow()][location.getCol()] = new TextCell(content.trim());
		} else if (content.contains("%")) {
			grid[location.getRow()][location.getCol()] = new PercentCell(content);
		} else if (content.contains("(")) {
			grid[location.getRow()][location.getCol()] = new FormulaCell(content, this);
		} else {
			grid[location.getRow()][location.getCol()] = new ValueCell(content);
		}
	}

	public void clear() {
		for (int row = 0; row < grid.length; row++) {
			for (int col = 0; col < grid[row].length; col++) {
				grid[row][col] = new EmptyCell();
			}
		}
	}

	public void clear(String location) {
		SpreadsheetLocation loca = new SpreadsheetLocation(location);
		grid[loca.getRow()][loca.getCol()] = new EmptyCell();
	}


	public int getRows() {
		return rowLength;
	}


	public int getCols() {
		return columnLength;
	}


	public Cell getCell(Location loc) {
		return grid[loc.getRow()][loc.getCol()];
	}
	
	public Cell[][] getGrid() {
		return this.grid;
	}


	public String getGridText() {
		String gridOutput = "";
		gridOutput += "   |";
		for (char i = 'A'; i <= 'L'; i++) {
			gridOutput += i + "         |";
		}
		gridOutput += "\n";
		for (int i = 0; i < grid.length; i++) {
			gridOutput += (1 + i);
			if (i < 9) {
				gridOutput += "  |";
				for (int j = 0; j < grid[i].length; j++) {
					gridOutput += grid[i][j].abbreviatedCellText() + "|";
				}
				gridOutput += "\n";
			} else {
				gridOutput += " |";
				for (int j = 0; j < grid[i].length; j++) {
					gridOutput += grid[i][j].abbreviatedCellText() + "|";
				}
				gridOutput += "\n";
			}
		}
		return gridOutput;
	}

	public String contentsOfCell(String location) {
		SpreadsheetLocation loca = new SpreadsheetLocation(location);
		return getCell(loca).fullCellText();
	}

}
