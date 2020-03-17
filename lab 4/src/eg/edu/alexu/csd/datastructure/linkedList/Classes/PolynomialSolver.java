package eg.edu.alexu.csd.datastructure.linkedList.Classes;
import java.awt.*;
import eg.edu.alexu.csd.datastructure.linkedList.Interfaces.IPolynomialSolver;
public class PolynomialSolver implements IPolynomialSolver {
	Slinkedlist A = new Slinkedlist();
	Slinkedlist B = new Slinkedlist();
	Slinkedlist C = new Slinkedlist();
	Slinkedlist R = new Slinkedlist();
	public Slinkedlist arrayToLinkedList(int [][] input) {
		Slinkedlist result = new Slinkedlist();
		for (int i=0;i<input.length;i++) {
			result.add(new Point(input[i][0],input[i][1]));
		}
		return result;
	}
	public int[][] LinkedListtoArray(Slinkedlist input){
		int[][] result = new int[input.size()][2];
		for(int i=0;i<input.size();i++) {
			result[i][0]= ((Point)input.get(i)).x;
			result[i][1]= ((Point)input.get(i)).y;
		}
		return result;
	}
	@Override
	public void setPolynomial(char poly, int[][] terms) {	
		if (poly=='A') {
			if (!A.isEmpty())
				A.clear();
			A=arrayToLinkedList(terms);
		}
		else if (poly=='B') {
			if (!B.isEmpty())
				B.clear();
			B=arrayToLinkedList(terms);
		}
		else if (poly=='C'){
			if (!C.isEmpty())
				C.clear();
			C=arrayToLinkedList(terms);
		}
	}
	@Override
	public String print(char poly) {
		return null;
	}

	@Override
	public void clearPolynomial(char poly) {	
		if (poly=='A')
			A.clear();
		else if (poly=='B')
			B.clear();
		else if (poly=='C')
			C.clear();
		else if(poly=='R')
			R.clear();
	}

	@Override
	public float evaluatePolynomial(char poly, float value) {
		return 0;
	}

	@Override
	public int[][] add(char poly1, char poly2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[][] subtract(char poly1, char poly2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[][] multiply(char poly1, char poly2) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
