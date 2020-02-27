package eg.edu.alexu.csd.datastructure.hangman.cs33.classes;
import eg.edu.alexu.csd.datastructure.hangman.cs33.Interfaces.IHangman;
import java.io.*;
import java.util.*;
public class hangman implements IHangman {
	static String[] dictionary;
	private static int size;
	static String playingword="";
	int maxwrong;
	static String chosen="";
	static int wrong;
	String secretword;
	//set dictionary method that read from file and put it in an array of strings
	public static String[] readfromfile(String[] words) {
		String s;
		size=numberoflines();
		words=new String[size];
	try {
		FileReader fr=new FileReader("src\\eg\\edu\\alexu\\csd\\datastructure\\hangman\\cs33\\Dictionary.txt");
		BufferedReader in = new BufferedReader(fr);
		int i=0;
		while((s=in.readLine())!=null)
			words[i++]=s;
	} catch (IOException e) {
		e.printStackTrace();
	}
	return words;
	}
	public void setDictionary(String[] words) {
	dictionary=readfromfile(words);
	}

	@Override
	public String selectRandomSecretWord() {
		Random rand=new Random();
		int index=rand.nextInt(size);
		secretword=dictionary[index];
		if (secretword!=null) {
			for(int i=0;i<secretword.length();i++) 
				playingword+='-';
			return secretword;
		}
		else
		return null;
	}

	@Override
	public String guess(Character c) throws Exception {
		boolean found=false;
		if(!(secretword.matches("^[a-zA-Z]*$")) || secretword==null){
			throw new Exception ("Buggy Secret Word");
		}
		if(c == null || !(c.toString()).matches("^[a-zA-Z]")){
			System.out.println("Enter A Character From A-Z Or a-z");
			return playingword;
		}
		else {
			if (chosen.contains(String.valueOf(c))) {
				System.out.println("you entered this character before,enter a new one");
				return playingword;
			}
			chosen+=c;
			for(int i=0;i<secretword.length();i++) {
				if(c==secretword.charAt(i)) {
					found=true;
					playingword=playingword.substring(0,i)+secretword.charAt(i)+playingword.substring(i+1);
				}
			}
			if (found==false)
				wrong++;
			if(wrong==maxwrong)
				return null;
			return playingword;
		}
	}

	@Override
	public void setMaxWrongGuesses(Integer max) {
		maxwrong=max;
	}
	public static int numberoflines() {
		int noline=0;
		try {
			FileReader fr=new FileReader("src\\eg\\edu\\alexu\\csd\\datastructure\\hangman\\cs33\\Dictionary.txt");
			BufferedReader in = new BufferedReader(fr);
			while((in.readLine())!=null)
				noline++;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return noline;
	}
}
