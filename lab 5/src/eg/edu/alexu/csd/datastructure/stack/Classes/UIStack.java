package eg.edu.alexu.csd.datastructure.stack.Classes;
import java.util.*;
public class UIStack {
	public static void main(String [] args) {
		Scanner input = new Scanner(System.in);
		Stack stack =new Stack();
		while(true) {
		System.out.println("------------------------------------------------------------------------\n" +
				"		Choose an action:\n" +
				"		1- Push.\n"+
				"		2- Pop.\n"+
				"		3- Peek.\n"+
				"		4- Get size.\n"+
				"		5- Check if empty.\n"+
				"		6- Exit.");
		
		byte choice=input.nextByte();
		while(choice<1||choice>6) {
			System.out.println("Invalid input enter again");
			choice=input.nextByte();
			
		}
		if (choice == 6)
			break;
		switch(choice) {
		case 1 :
			try {
			stack.push(input.nextInt());
			}
			catch(RuntimeException ex) {
				System.out.println("Stack is empty");
			}
			break;
		case 2 :
			try {
				System.out.printf("%d Poped\n",stack.pop());
				}
				catch(RuntimeException ex) {
					System.out.println("Stack is empty");
				}
				break;
		case 3 :
			try {
				System.out.printf("%d\n",stack.peek());
				}
				catch(RuntimeException ex) {
					System.out.println("Stack is empty");
				}
				break;
		case 4 :
			System.out.printf("The size of the stack is %d\n",stack.size());
			break;
		case 5 :
			if (stack.isEmpty())
				System.out.printf("The stack is empty\n");
			else
				System.out.printf("The stack is not empty\n");
			break;
		}
		}
		System.out.println("Exit succesfully");
	}
}
