package eg.edu.alexu.csd.datastructure.hangman.cs33.classes;
import java.util.*;
public class Game {
	public static void main(String [] args) throws Exception {
		Scanner input=new Scanner(System.in);
		hangman hang =new hangman();
		System.out.print("Enter number of wrong guesses :");
		hang.setMaxWrongGuesses(input.nextInt());
		hang.setDictionary(hangman.dictionary);
		String secret=hang.selectRandomSecretWord();
		String word=hangman.playingword;
		char c;
		for(;;) {
			if (word!=null&&!word.equalsIgnoreCase(secret)) {
			System.out.printf("Remaining guesses : %d\nThe word :     %s   \nEnter a character:", (hang.maxwrong-hangman.wrong),word);
			c=input.next().charAt(0);
			word=hang.guess(c);
			}
			else if(word==null) {
				System.out.printf("Remaining guesses : %d\nThe Right word :     %s   \nHard luck,try again next time.", (hang.maxwrong-hangman.wrong),secret);
				break;
			}
			else if(word.equalsIgnoreCase(secret)) {
				System.out.printf("Remaining guesses : %d\nThe Right word :     %s   \nCongratulations,You won !!!!", (hang.maxwrong-hangman.wrong),word);
				break;
			}
		}
	}
}

