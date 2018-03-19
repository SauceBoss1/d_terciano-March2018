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
			System.out.print("Enter commands: ");
			userAction = userInput.nextLine();
			if (userAction.equals("quit")) {
				endLoop = false;
				System.out.println("<TextExcel terminated>");
			} else {
				System.out.println(spreadsheet.processCommand(userAction));
			}
		}
		
	}
}
