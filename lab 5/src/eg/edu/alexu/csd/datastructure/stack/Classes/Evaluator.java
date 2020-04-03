package eg.edu.alexu.csd.datastructure.stack.Classes;
import java.util.InputMismatchException;
import java.util.regex.Pattern;
import eg.edu.alexu.csd.datastructure.stack.Interfaces.IExpressionEvaluator;
/**
 * This class craeted with two main methods 
 * the first one to convert from infix expression to postfix
 * the second one to evaluate a given postfix expression
 * @author seifgneedy
 * @version 1.0
 */
public class Evaluator implements IExpressionEvaluator {
	/**
	 * this method is used to check the parentheses in the expression and some other conditions like empty one ()
	 * @param expression the expression to check
	 * @return true if the paretheses in the expression is good and false otherwise
	 * 
	 */
	public static boolean parencheck(String expression) {
		int n=expression.length();
		Stack par = new Stack();
		for(int i=0;i<n;i++) {
			if(expression.charAt(i)=='(') {
				if(i+1<n) {
					char ch=expression.charAt(i+1);
					if(ch==')')
						return false;
					if (ch!='('&&(ch=='+'||ch=='-'||ch=='*'||ch=='/'))
						return false;
				}
				if(i-1>=0) {
					char ch=expression.charAt(i-1);
					if (ch!='('&&ch!='+'&&ch!='-'&&ch!='*'&&ch!='/')
						return false;
				}
				par.push('(');
				}
			else if(expression.charAt(i)==')') {
				if(par.isEmpty())
					return false;
				if(i+1<n) {
					char ch=expression.charAt(i+1);
					if(ch=='(')
						return false;
					if (ch!=')'&&ch!='+'&&ch!='-'&&ch!='*'&&ch!='/')
						return false;
				}
				if(i-1>=0) {
					char ch=expression.charAt(i-1);
					if (ch!=')'&&(ch=='+'||ch=='-'||ch=='*'||ch=='/'))
						return false;
				}
				par.pop();
			}
		}
		if (par.isEmpty())
			return true;
		else
			return false;
	}
	/**
	 * this method is used to check the operators and make sure that this is not any invalid input in it like 5+++5 or the things like that
	 * @param expression the expression to check
	 * @return true if the operators is correct and in their right way and false if not
	 */
	public static boolean operatorcheck(String expression) {
		int n=expression.length();
		if(expression.charAt(0)=='+'||expression.charAt(0)=='*'||expression.charAt(0)=='/') 
			return false;
		for(int i=0;i<n;i++) {
			char ch=expression.charAt(i);
			if(ch=='+'||ch=='-'||ch=='*'||ch=='/') {
				if (i-1>=0) {
					char left=expression.charAt(i-1);
					if(left=='+'||left=='-'||left=='*'||left=='/')
						return false;
				}
				if (i+1<n) {
					char right=expression.charAt(i+1);
					if(right=='+'||right=='-'||right=='*'||right=='/')
						return false;
				}
			}
		}
		return true;
	}
	/**
	 * the main goal of this method is to add dummy zeros in case of negative input 
	 * 	this method is split into 2 parts, the first one as we assume that the entered expression don't have any spaces
	 * so it put spaces in their right place to prepare it to the split method in the second part 
	 * and in the second part we take the negative input (assume"-a") and add dummy zero ("(0-a)"
	 * @param ex the expression to add dummy
	 * @return input string after adding dummy zero 
	 */
	public static String addDummy(String ex) {
		for(int i=0;i<ex.length();i++) {
			char temp=ex.charAt(i);
			if(temp=='-'&&i-2>=0&&(ex.charAt(i-2)!='+'&&ex.charAt(i-2)!='*'&&ex.charAt(i-2)!='/'&&ex.charAt(i-2)!='-'&&ex.charAt(i-2)!='('))
				ex=ex.substring(0,i+1)+" "+ex.substring(i+1);
			if((temp=='('||temp=='+'||temp=='*'||temp=='/')&&i+1<ex.length()) {
				ex=ex.substring(0,i+1)+" "+ex.substring(i+1);
			}
			if(temp==')'||temp=='+'||temp=='*'||temp=='/'||temp=='-') {
				String stemp=ex.substring(i);
				ex=ex.substring(0,i)+" "+stemp;
				i++;
			}
		}
		String[] str = ex.trim().split("\\s+");
		String result="";
		for(int i=0;i<str.length;i++) {
			if(str[i].charAt(0)=='-'&&str[i].length()>1) 
				str[i]="(0-"+str[i].substring(1)+")";
			result+=str[i];
		}
		return result;
		}
	public static int precedence(char ch) {
		if (ch=='+'||ch=='-')
			return 1;
		else if(ch=='*'||ch=='/')
			return 2;
		return 0;
	}
	@Override
	public String infixToPostfix(String expression) {
		expression=addDummy(expression);
		if (!parencheck(expression)||!operatorcheck(expression)||expression.length()<3)
			throw new InputMismatchException("Invalid input");
		Stack operator = new Stack();
		String result="";
		int n=expression.length();
		for(int i=0;i<n;i++) {
			char ch=expression.charAt(i);
			if (precedence(ch)>0) {
				result+=" ";
				while(!operator.isEmpty()&&precedence((char)operator.peek())>=precedence(ch)) {
					result+=operator.pop()+" ";
				}
				operator.push(ch);
			}
			else if(ch=='(')
				operator.push(ch);
			else if(ch==')') {
				char top=(char)operator.pop();
				while(top!='(') {
					result+=" "+top;
					top=(char)operator.pop();
				}
			}
			else
				result+=ch;
		}
		while(!operator.isEmpty()) 
			result+=" "+operator.pop();
		return result;
	}
	@Override
	public int evaluate(String expression) {
		Stack nums=new Stack();
		String[] str = expression.trim().split("\\s+");
		if(!checkpostfix(str))
			throw new InputMismatchException("Invalid input");
		for(int i=0;i<str.length;i++) {
			char ch=str[i].charAt(0);
			if(str[i].length()==1&&(ch=='+'||ch=='-'||ch=='*'||ch=='/')) {
				if(nums.size()<2)
					throw new InputMismatchException("Invalid input");
				float num2=(float)nums.pop();
				float num1 =(float)nums.pop();
				if(ch=='+') nums.push(num1+num2);
				if(ch=='-') nums.push(num1-num2);
				if(ch=='*') nums.push(num1*num2);
				if(ch=='/') {
					if(num2==0)
						throw new RuntimeException("Dividing by zero not allowed");
					nums.push(num1/num2);
				}
			}
			else
				nums.push(Float.parseFloat(str[i]));
		}
		return Math.round((float) nums.pop());
	}
	/**
	 * this method is used to check the postfix expression inputed in evaluate method and make sure that number of operators is bigger than number of numbers by 1 .
	 * @param str the terms of the expression stored in array of Strings
	 * @return true if the expression is correct and false other wise
	 */
	public static boolean checkpostfix(String [] str) {
		int numcount=0,opcount=0;
		Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
		for(int i=0;i<str.length;i++) {
			if(pattern.matcher(str[i]).matches())
				numcount++;
			else {
				if(str[i].length()>1)
					return false;
				char ch=str[i].charAt(0);
				if(!(ch=='+'||ch=='-'||ch=='*'||ch=='/'))
					return false;
				opcount++;
			}
		}
		if(opcount==numcount-1)
			return true;
		return false;
	}
}
