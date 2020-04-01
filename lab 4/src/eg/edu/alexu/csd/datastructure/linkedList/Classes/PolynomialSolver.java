package eg.edu.alexu.csd.datastructure.linkedList.Classes;
import java.awt.*;
import eg.edu.alexu.csd.datastructure.linkedList.Interfaces.IPolynomialSolver;
public class PolynomialSolver implements IPolynomialSolver {
	Slinkedlist A = new Slinkedlist();
	Slinkedlist B = new Slinkedlist();
	Slinkedlist C = new Slinkedlist();
	Slinkedlist R = new Slinkedlist();
	public boolean seta=false,setb=false,setc=false,setr=false;
	//set means it has values
	public Slinkedlist choosePolynomial(char poly) {
		switch(poly) {
		case 'A':
			return A;
		case 'B':
			return B;
		case 'C':
			return C;
		default:
			return R;
		}
		
	}
	public Slinkedlist arrayToLinkedList(int [][] input) {
		Slinkedlist result = new Slinkedlist();
		for (int i=0;i<input.length;i++) {
			if ( !(Math.abs(input[i][0]-0)<(float)Math.pow(10,-7)) )
				result.add(new Point(input[i][0],input[i][1]));
		}
		return result;
	}
	public int[][] LinkedListToArray(Slinkedlist input){
		if (input.size() == 0) return null;
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
	public void sortarray(int[][] terms) {
		boolean swapped=false;
		int temp;
		for(int i=0;i<terms.length-1;i++) {
			for(int j=0;j<terms.length-i-1;j++) {
				if(terms[j][1]<terms[j+1][1]) {
					temp=terms[j][1];
					terms[j][1]=terms[j+1][1];
					terms[j+1][1]=temp;
					temp=terms[j][0];
					terms[j][0]=terms[j+1][0];
					terms[j+1][0]=temp;
					swapped=true;
				}
			}
			if(!swapped)
				break;
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
		float result=0.0f;
		Slinkedlist show = choosePolynomial(poly);
		for (int i=0;i<show.size();i++) {
			Point sub = (Point)show.get(i);
			result+=sub.x* (float)Math.pow(value, sub.y);
		}
		return result;
	}
	@Override
	public int[][] add(char poly1, char poly2) {
		Slinkedlist list1 = choosePolynomial(poly1);
		Slinkedlist list2 = choosePolynomial(poly2);
		Slinkedlist sumList = new Slinkedlist();
		int i=0,j=0;
		while(i<list1.size() && j<list2.size()) {
			Point p1 = (Point) list1.get(i);
			Point p2 = (Point) list2.get(j);
			if(p1.y == p2.y) {
				sumList.add(new Point(p1.x+p2.x,p1.y));
				i++; j++;
			} else if (p1.y > p2.y) {
				sumList.add(new Point(p1.x,p1.y));
				i++;
			} else {
				sumList.add(new Point(p2.x,p2.y));
				j++;
			}
		}
		while(i<list1.size()) {
			Point p1 = (Point) list1.get(i);
			sumList.add(new Point(p1.x,p1.y));
			i++;
		}
		while(j<list2.size()) {
			Point p2 = (Point) list2.get(j);
			sumList.add(new Point(p2.x,p2.y));
			j++;
		}
		for(int k=0; k<sumList.size(); k++) {
			if( ((Point)sumList.get(k)).x == 0 ) {
				sumList.remove(k);
				k--;
			}
		}
		R = sumList;
		return LinkedListToArray(sumList);
	}
	/*
	 * First: Choosing the second list and multiply it by -1
	 * Second: Setting R to the negative list 
	 * 				so that it can be reached from outside the function
	 * Finally: Using add function to do the sum of list1 and negative list 2
	 * */
	@Override
	public int[][] subtract(char poly1, char poly2) {
		Slinkedlist list2 = choosePolynomial(poly2);
		Slinkedlist negativeList2 = new Slinkedlist();
		for (int i=0; i<list2.size(); i++) {
			Point p = (Point)list2.get(i);
			negativeList2.add(new Point(p.x*-1, p.y));
		}
		R = negativeList2;
		return add(poly1,'R');
	}
	@Override
	public int[][] multiply(char poly1, char poly2) {
		Slinkedlist list1 = choosePolynomial(poly1);
		Slinkedlist list2 = choosePolynomial(poly2);
		Slinkedlist tempA = A;
		R = new Slinkedlist();
		for(int i=0; i<list1.size(); i++) {
			Slinkedlist product = new Slinkedlist();
			Point p1 = (Point) list1.get(i);
			for (int j=0; j<list2.size(); j++) {
				Point p2 = (Point) list2.get(j);
				product.add(new Point(p1.x*p2.x, p1.y+p2.y));
			}
			A = product;
			add('A','R');
		}
		A = tempA;
		return LinkedListToArray(R);
	}
}
