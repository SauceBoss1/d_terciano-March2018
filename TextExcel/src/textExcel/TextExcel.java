package textExcel;

import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author Derfel Terciano
 * @version 1
 */
public class TextExcel {

	public static void main(String[] args) {
		Spreadsheet spreadsheet = new Spreadsheet();// creates new spreadsheet
		Scanner userInput = new Scanner(System.in);
		boolean endLoop = true;// ends loop
		String userAction = "";
		while (endLoop) {
			System.out.print("Enter commands: ");
			userAction = userInput.nextLine();// takes in user commands
			if (userAction.equals("quit")) {// ends loop if user types quit
				endLoop = false;
				System.out.println("<TextExcel terminated>");
			} else {
				System.out.println(spreadsheet.processCommand(userAction));// else it inputs command to process command
			}
		}

	}
}
