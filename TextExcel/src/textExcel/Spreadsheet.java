package textExcel;


/**
 * @author Derfel Terciano
 * @version 1
 */
public class Spreadsheet implements Grid {
	private final int rowLength = 20;//# of rows can never be changed
	private final int columnLength = 12;//# of columns can never be changed
	private Cell[][] grid = new Cell[rowLength][columnLength];//creates a 2d array

	/*
	 * initializes the spreadsheet to be filled with empty cells
	 */
	public Spreadsheet() {
		for (int row = 0; row < grid.length; row++) {
			for (int col = 0; col < grid[row].length; col++) {
				grid[row][col] = new EmptyCell();
			}
		}
	}
	
	
	/*
	 * handles the the user's type command
	 */
	public String processCommand(String command) {
		String[] userCommand = command.split(" ", 3);//splits the spaces only 3 times
		if (userCommand.length == 3) {//if length 3 then assign it to the corresponding cell
			cellAssignment(userCommand[0], userCommand[2]);
			return getGridText();
		} else if (userCommand[0].toLowerCase().equals("clear")) {
			if (userCommand.length >= 2) {
				clear(userCommand[1]);//clears a specific cell
				return getGridText();
			} else {
				clear();//clears the entire spreadsheet
				return getGridText();
			}
		}else if (userCommand.length==2 && userCommand[0].toLowerCase().equals("sorta")) {
			String[] ranges = userCommand[1].split("-");
			sortA(ranges[0],ranges[1]);
			return getGridText();
		}else if (userCommand.length==2 && userCommand[0].toLowerCase().equals("sortd")) {
			String[] ranges = userCommand[1].split("-");
			sortD(ranges[0],ranges[1]);
			return getGridText();
		} else if (userCommand.length == 1) {//checks if user wants fullCellText returned
			return contentsOfCell(userCommand[0]);
		} else {
			return "command unknown";
		}
	}
	
	/*
	 * checks what the input's appropriate cell type should be
	 * then it sets that particular cell into the appropriate cell type
	 */
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
	
	//clears the entire cell
	public void clear() {
		for (int row = 0; row < grid.length; row++) {
			for (int col = 0; col < grid[row].length; col++) {
				grid[row][col] = new EmptyCell();
			}
		}
	}
	
	//clears a particular cell
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
	
	public void setCell(Location loc, Cell cell) {
		grid[loc.getRow()][loc.getCol()]= cell;
	}
	

	
	/*
	 * one of the project's most FRUSTRATING task and method
	 * 
	 * method creates the visual spreadsheet and 
	 * formats the spreadsheet correctly
	 */
	public String getGridText() {
		String gridOutput = "";
		
		//sets up the top row and fills it with the letter
		gridOutput += "   |";
		for (char i = 'A'; i <= 'L'; i++) {
			gridOutput += i + "         |";
		}
		gridOutput += "\n";
		
		//sets up the rest of the spreadsheet
		for (int i = 0; i < grid.length; i++) {
			gridOutput += (1 + i);//sets up the row # on the side
			if (i < 9) {//helps format the pipes correctly
				gridOutput += "  |";//adds pipes
				for (int j = 0; j < grid[i].length; j++) {
					gridOutput += grid[i][j].abbreviatedCellText() + "|";//adds the abbreviated cell at specific location
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

	public String contentsOfCell(String location) {//returns the fullCelltext of the input
		SpreadsheetLocation loca = new SpreadsheetLocation(location);
		return getCell(loca).fullCellText();
	}
	public Double[] convDouble(RealCell[] arr) {
		Double[] result = new Double[arr.length];
		int tracker=0;
		for(RealCell element:arr) {
			result[tracker]=element.getDoubleValue();
			tracker++;
		}
		return result;
		
	}
	public void sortA(String rang1, String rang2) {
		SpreadsheetLocation loc = new SpreadsheetLocation(rang1);
		Ranges range = new Ranges(this,rang1, rang2);
		if(getCell(loc) instanceof RealCell) {
			RealCell[] sort = range.rangeOfDoubles();
			range.selectionSortRealCells(sort, "ascending");
			range.setGridOfCells(sort);
		}else {
			TextCell[] sort = range.rangeOfStrings();
			range.selectionSortTextCells(sort, "ascending");
			range.setGridOfCells(sort);
		}
		
	}
	public void sortD(String rang1, String rang2) {
		SpreadsheetLocation loc = new SpreadsheetLocation(rang1);
		Ranges range = new Ranges(this,rang1, rang2);
		if(getCell(loc) instanceof RealCell) {
			RealCell[] sort = range.rangeOfDoubles();
			range.selectionSortRealCells(sort, "descending");
			range.setGridOfCells(sort);
		}else {
			TextCell[] sort = range.rangeOfStrings();
			range.selectionSortTextCells(sort, "descending");
			range.setGridOfCells(sort);
		}
	}

}
