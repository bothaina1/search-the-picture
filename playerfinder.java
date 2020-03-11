package eg.edu.alexu.csd.datastructure.iceHockey.cs20;

import java.awt.Point;

public class playerfinder implements IPlayersFinder  {

   
    static int no_of_players=0;
    static int x_max, y_max, x_min, y_min;
	static int count=1;
	static int count1=0;
	static int count2=0;
    static boolean[][] test =new boolean[50][50];
    static int[] x=new int[50];
    static int[] y=new int[50];
    
	
	
    
	public void  find (String[] photo ,int i ,int j,int team ,int threshold,int len,int no_of_players)
	{
	
		playerfinder search =new playerfinder();
	
		if( j!=len-1) {
			
			if(Character.digit(photo[i].charAt(j), 10)==team&&Character.digit(photo[i].charAt(j+1), 10)==team&&(test[i][j]==false||test[i][j+1]==false)) {
				test[i][j]=true;test[i][j+1]=true;
				count++;
			    count1++;
			    if(x_min>j) 
				{x_min=j;}
				if(x_max<j+1) 
				{x_max=j+1;}
				search.find(photo, i, j+1, team, threshold,len,no_of_players);
				}
			
			}		
			if(j!=0) {
            if(Character.digit(photo[i].charAt(j), 10)==team&&Character.digit(photo[i].charAt(j-1), 10)==team&&(test[i][j]==false||test[i][j-1]==false)) {
				count++;
				count1++;
				test[i][j]=true;test[i][j-1]=true;
				if(x_min>j-1) 
				{x_min=j-1;}
				if(x_max<j) 
				{x_max=j;}
				search.find(photo, i, j-1, team, threshold,len,no_of_players);
			}
		}
				
		try {
		if(photo[i]!=null&&photo[i+1]!=null) {
			if(Character.digit(photo[i].charAt(j), 10)==team&&Character.digit(photo[i+1].charAt(j), 10)==team&&(test[i][j]==false||test[i+1][j]==false)) {
				count++;count2++;
				test[i][j]=true;test[i+1][j]=true;	
				if(y_min>i) 
				{y_min=i;}
				if(y_max<i+1) 
				{y_max=i+1;}
			search.find(photo, i+1, j, team, threshold,len,no_of_players);
				}
		}
			
		if(photo[i]!=null&&photo[i-1]!=null)
		{
            if(Character.digit(photo[i].charAt(j), 10)==team && Character.digit(photo[i-1].charAt(j), 10)==team&&(test[i][j]==false||test[i-1][j]==false)) {
				count++;count2++;
				test[i][j]=true;test[i-1][j]=true;
				if(y_min>i-1) 
				{	y_min=i-1;}
				if(y_max<i) 
				{	y_max=i;}
				search.find(photo, i-1, j, team, threshold,len,no_of_players);
				}
			}
		
		}
		catch(ArrayIndexOutOfBoundsException e){}
		
		if(count==1) 
			{x_max=x_min=j;y_max=y_min=i;}
		if(count1==0)
			{x_max=x_min=j;}
		if(count2==0)
			{y_max=y_min=i;}
		
		 try {
		
		if(count>=threshold/4) {
			x[no_of_players]=x_min+x_max+1;
			y[no_of_players]=y_min+y_max+1;	
		}
		
		 }
		catch(NullPointerException e) {}
	
	}
		public  Point[] findPlayers(String[] photo, int team, int threshold){
		int len = photo[0].length();
		playerfinder search = new playerfinder();
		int i=0;
		int j=0;
		 
		while(photo[i]!=null) {
			for(j=0;j<len;j++) {
				if(test[i][j]==false&&Character.digit(photo[i].charAt(j), 10)==team)
				{
					search.find(photo, i, j, team, threshold,len,no_of_players);
					  x_max=0; y_max=0;
					  x_min=50; y_min=50;
					  count=1;
					  no_of_players++;
				}	
			}
			i++;
		}
	    Point points[]=new Point[no_of_players] ;
		for(int a=0;a<no_of_players;a++) {
			points[a]=new Point();
			points[a].setLocation(x[a], y[a]);
			
		}
		
			 Point temp;
	        for (int l = 0; l < no_of_players; l++) {
	            for (int k = 0; k <no_of_players-1-l; k++) {
	                if(points[k].getX()>points[k+1].getX())
	                {
	                    temp=points[k];
	                    points[k]=points[k+1];
	                    points[k+1]=temp;
	                }
	                else if (points[k].getX()==points[k+1].getX() && (points[k].y>points[k+1].getY()))
	                {
	                    temp=points[k];
	                    points[k]=points[k+1];
	                    points[k+1]=temp;
	                }
	            }

	        } 
		return points ;
	}
	
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	