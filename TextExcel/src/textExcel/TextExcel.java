package textExcel;

import java.io.FileNotFoundException;
import java.util.Scanner;

// Update this file with your own code.
/**
 * @author Derfel Terciano
 * @version 1
 */
public class TextExcel {

	public static void main(String[] args) {
		Spreadsheet spreadsheet = new Spreadsheet();
		Scanner userInput = new Scanner(System.in);
		boolean endLoop = true;
		String userAction="";
		while (endLoop) {
			if (userAction.equals("quit")) {
				endLoop = false;
				System.out.println("<Spreadsheet terminated>");
			} else {
				System.out.print("Enter commands: ");
				userAction = userInput.nextLine();
				System.out.println(spreadsheet.processCommand(userAction));
			}
		}
	}
}
