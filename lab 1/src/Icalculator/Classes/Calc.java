package Icalculator.Classes;
import Icalculator.Interfaces.ICalculator;
public class Calc implements ICalculator {
	public int add(int x,int y) {
		return x+y;
	}
		public float divide(int x,int y)throws RuntimeException  {
		if(y==0) 
			throw new ArithmeticException("divide by zero is invalid ");
		return (float) x/y;
	}
}
