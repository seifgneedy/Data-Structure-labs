package eg.edu.alexu.csd.datastructure.iceHockey.Classes;
import java.awt.*;
import java.util.*;

import eg.edu.alexu.csd.datastructure.iceHockey.Interfaces.IPlayersFinder;
public class FindPlayer implements IPlayersFinder {
	
	@Override
	public java.awt.Point[] findPlayers(String[] photo, int team, int threshold) {
		if (photo==null||photo.length==0||photo[0].length()==0)
			return null;
		int[][] address=new int[photo.length][photo[0].length()];
		ArrayList <java.awt.Point> resultlist= new ArrayList<>();
		address=setarray(photo,team);
		int[] arr=new int[5];
		for(int i=0;i<photo.length;i++) {
			for (int j=0;j<photo[0].length();j++) {
				arr[0]=0;arr[1]=j;arr[2]=j;arr[3]=i;arr[4]=i;
				if (address[i][j]!=0) {
					check(address,i,j,arr);
					int area=arr[0]*4;
					if(area<threshold)
						continue;
					else {
						int x = arr[1]+arr[2]+1;
						int y=arr[3]+arr[4]+1;
						resultlist.add(new Point(x,y));
					}
				}	
			}
		}
		int n=resultlist.size();
		if (n==0)
			return null;
		Point[] resultarr = new Point[n];
		resultlist.toArray(resultarr);
		Sortpoints(resultarr,n);
		return resultarr;
	}
	public void Sortpoints(Point arr[], int n) 
    { 
        int i, j;
        Point temp;
        for (i = 0; i < n - 1; i++) { 
            for (j = 0; j < n - i - 1; j++) { 
                if (arr[j].x > arr[j + 1].x) { 
                    // swap arr[j] and arr[j+1] 
                    temp = arr[j]; 
                    arr[j] = arr[j + 1]; 
                    arr[j + 1] = temp; 
                }
                else if(arr[j].x==arr[j+1].x&&arr[j].y>arr[j+1].y) {
                	temp = arr[j]; 
                    arr[j] = arr[j + 1]; 
                    arr[j + 1] = temp; 
                }
            } 
        } 
    } 
	public int[][] setarray(String[] photo,int team){
		int[][] address=new int[photo.length][photo[0].length()];
		int counter=1;
		char c;
		for(int i=0;i<photo.length;i++) {
			for (int j=0;j<photo[0].length();j++) {
				c=photo[i].charAt(j);
				if (c-'0'==team)
					address[i][j]=counter++;
			}
		}
		return address;
	}
	//arr[0]->number of adjacent players area
	//arr[1]->Xmax,arr[2]->Xmin,arr[3]->Ymax,arr[4]->Ymin
	public void check(int[][] address,int i,int j,int[] arr) {
		int n=address.length,m=address[0].length;
		address[i][j]=0;
		arr[0]++;
		
		//right
		if (j<m-1&&address[i][j+1]>0) {
			if(j+1>arr[1]) arr[1]=j+1;
			check(address,i,j+1,arr);
		}
		//left
		if (j>0&&address[i][j-1]>0) {
			if(j-1<arr[2]) arr[2]=j-1;
			check(address,i,j-1,arr);
		}
		//down
		if (i<n-1&&address[i+1][j]>0) {
			if(i+1>arr[3]) arr[3]=i+1;
			check(address,i+1,j,arr);
		}
		//up
		if (i>0&&address[i-1][j]>0) {
			if(i-1<arr[4]) arr[2]=i-1;
			check(address,i-1,j,arr);
		}
		
	}	
}
