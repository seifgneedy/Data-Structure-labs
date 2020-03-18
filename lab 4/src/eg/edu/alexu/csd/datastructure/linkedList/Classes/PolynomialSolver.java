package eg.edu.alexu.csd.datastructure.linkedList.Classes;
import java.awt.*;
import eg.edu.alexu.csd.datastructure.linkedList.Interfaces.IPolynomialSolver;
public class PolynomialSolver implements IPolynomialSolver {
	Slinkedlist A = new Slinkedlist();
	Slinkedlist B = new Slinkedlist();
	Slinkedlist C = new Slinkedlist();
	Slinkedlist R = new Slinkedlist();
	boolean seta=false,setb=false,setc=false,setr=false;
	//set means it has values
	public Slinkedlist arrayToLinkedList(int [][] input) {
		Slinkedlist result = new Slinkedlist();
		for (int i=0;i<input.length;i++) {
			result.add(new Point(input[i][0],input[i][1]));
		}
		return result;
	}
	public int[][] LinkedListToArray(Slinkedlist input){
		int[][] result = new int[input.size()][2];
		for(int i=0;i<input.size();i++) {
			result[i][0]= ((Point)input.get(i)).x;
			result[i][1]= ((Point)input.get(i)).y;
		}
		return result;
	}
	//array of [coefficients][exponents]
	@Override
	public void setPolynomial(char poly, int[][] terms) {	
		if (poly=='A') {
			if (!A.isEmpty())
				A.clear();
			A=arrayToLinkedList(terms);
			seta=true;
		}
		else if (poly=='B') {
			if (!B.isEmpty())
				B.clear();
			B=arrayToLinkedList(terms);
			setb=true;
		}
		else if (poly=='C'){
			if (!C.isEmpty())
				C.clear();
			C=arrayToLinkedList(terms);
			setc=true;
		}
	}
	
	@Override
	public String print(char poly) {
		Slinkedlist show = new Slinkedlist();
		if (poly=='A'&&seta) 
			show=A;
		else if (poly=='B'&&setb)
			show=B;
		else if(poly=='C'&&setc)
			show=C;
		else if(poly=='R')
			show=R;
		if (show.isEmpty())
			return null;
		String result="";
		for (int i=0;i<show.size();i++) {
			if(((Point)show.get(i)).x!=1)
				result+=Integer.toString(((Point)show.get(i)).x);
			if(((Point)show.get(i)).y==1)
				result+="X";
			else if(((Point)show.get(i)).y>1||((Point)show.get(i)).y<0)
				result+="X^"+Integer.toString(((Point)show.get(i)).y);
			else {/*do nothing */}
			if(i+1<show.size()&&((Point)show.get(i+1)).x>1)
				result+="+";
		}
		if (result.length()==0)
			result="0";
		return result;
	}

	@Override 
	public void clearPolynomial(char poly) {	
		if (poly=='A') {
			A.clear();
			seta=false;}
		else if (poly=='B') {
			B.clear();
			setb=false;}
		else if (poly=='C') {
			C.clear();
			setc=false;}
		else if(poly=='R') {
			R.clear();
			setr=false;}
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
