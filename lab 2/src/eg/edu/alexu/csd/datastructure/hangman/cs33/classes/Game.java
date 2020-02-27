package eg.edu.alexu.csd.datastructure.hangman.cs33.classes;
import java.util.*;
public class Game {
	public static void main(String [] args) throws Exception {
		Scanner input=new Scanner(System.in);
		Hangman hang =new Hangman();
		System.out.print("Enter number of wrong guesses :");
		hang.setMaxWrongGuesses(input.nextInt());
		hang.setDictionary(Hangman.dictionary);
		String secret=hang.selectRandomSecretWord();
		String word=Hangman.playingword;
		char c;
		for(;;) {
			if (word!=null&&!word.equalsIgnoreCase(secret)) {
			System.out.printf("Remaining guesses : %d\nThe word :     %s   \nEnter a character:", (hang.maxwrong-Hangman.wrong),word);
			c=input.next().charAt(0);
			word=hang.guess(c);
			}
			else if(word==null) {
				System.out.printf("Remaining guesses : %d\nThe Right word :     %s   \nHard luck,try again next time.", (hang.maxwrong-Hangman.wrong),secret);
				break;
			}
			else if(word.equalsIgnoreCase(secret)) {
				System.out.printf("Remaining guesses : %d\nThe Right word :     %s   \nCongratulations,You won !!!!", (hang.maxwrong-Hangman.wrong),word);
				break;
			}
		}
	}
}

