/*
//14500 : 테트로미노 
import java.util.*;
public class Main{
	public static void main(String args[]) {
		int n,m;
		
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		
		int[][] arr = new int[n][m];
	
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++){
				arr[i][j]=sc.nextInt();
			}
		}
		int max = 0;
		
		for (int i=0; i<n; i++) {
            for (int j=0; j<m; j++) {
            	
            	//1x4
            	if (j+3 < m) {
            		int temp = arr[i][j] + arr[i][j+1] + arr[i][j+2] + arr[i][j+3];
                    if (max < temp) max = temp;
            	}
            	 
            	//4x1
            	if (i+3 < n) {
            		int temp = arr[i][j] + arr[i+1][j] + arr[i+2][j] + arr[i+3][j];
                    if (max < temp) max = temp;
            	}
            	
            	//2x2
            	if (i+1 < n && j+1 < m) {
            		int temp = arr[i][j] + arr[i+1][j] + arr[i][j+1] + arr[i+1][j+1];
                    if (max < temp) max = temp;
            	}
            	
            	//2x3
            	if (i+1 < n && j+2 < m) {
            	   //4
            	   int temp = arr[i][j] + arr[i][j+1] 
            			    + arr[i+1][j+1] + arr[i+1][j+2];
                   if (max < temp) max = temp;
                    
                   //6
                   temp = arr[i][j+1] + arr[i][j+2] 
                    	+ arr[i+1][j] + arr[i+1][j+1] ;
                   if (max < temp) max = temp;
                    
                   //8
                   temp = arr[i][j] + arr[i][j+1] + arr[i][j+2] 
                   	    + arr[i+1][j+2];
                   if (max < temp) max = temp;
                    
                  //10
                    temp = arr[i][j]
                    	 + arr[i+1][j] + arr[i+1][j+1] + arr[i+1][j+2];
                    if (max < temp) max = temp;
                    
                  //14
                    temp = arr[i][j+2] 
                    	 + arr[i+1][j] + arr[i+1][j+1] + arr[i+1][j+2];
                    if (max < temp) max = temp;
                    
                  //16
                    temp = arr[i][j] + arr[i][j+1] + arr[i][j+2] 
                    	 + arr[i+1][j];
                    if (max < temp) max = temp;
                    
                  //18
                    temp = arr[i][j] + arr[i][j+1] + arr[i][j+2] 
                    	 + arr[i+1][j+1];
                    if (max < temp) max = temp;
                    
                  //19
                    temp = arr[i][j+1]
                    	 + arr[i+1][j] + arr[i+1][j+1] + arr[i+1][j+2];
                    if (max < temp) max = temp;
                    
            	}
            	
            	//3x2
            	if (i+2 < n && j+1 < m) {
            		//5
             	    int temp = arr[i][j+1]
             			     + arr[i+1][j] + arr[i+1][j+1]
             			     + arr[i+2][j];
                    if (max < temp) max = temp;
                    
                  //7
             	    temp = arr[i][j]
             			 + arr[i+1][j] + arr[i+1][j+1]
             			 + arr[i+2][j+1];
                    if (max < temp) max = temp;
                    
                  //9
             	    temp = arr[i][j+1]
             			 + arr[i+1][j+1]
             			 + arr[i+2][j] + arr[i+2][j+1];
                    if (max < temp) max = temp;
                    
                  //11
             	    temp = arr[i][j] + arr[i][j+1]
             			 + arr[i+1][j] 
             			 + arr[i+2][j];
                    if (max < temp) max = temp;
                    
                  //12
             	    temp = arr[i][j]
             			     + arr[i+1][j] + arr[i+1][j+1]
             			     + arr[i+2][j];
                    if (max < temp) max = temp;
                    
                  //13
             	    temp =arr[i][j+1]
             			 + arr[i+1][j] + arr[i+1][j+1]
             			 + arr[i+2][j+1];
                    if (max < temp) max = temp;
                    
                  //15
             	    temp = arr[i][j] + arr[i][j+1]
             			 + arr[i+1][j+1]
             			 + arr[i+2][j+1];
                    if (max < temp) max = temp;
                    
                  //17
             	    temp = arr[i][j]
             			 + arr[i+1][j]
             			 + arr[i+2][j] + arr[i+2][j+1];
                    if (max < temp) max = temp;
                     
            	}
            
            }
		}
		
        System.out.println(max);	
		
	}
}
*/



//3055 : 탈출 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main{
	
	static int[] dx = {0,1,0,-1};
    static int[] dy = {-1,0,1,0};
    static int r;
    static int c;
    
    static boolean [][] check;
    static char[][] map;
    
    static Queue<pair> water = new LinkedList<pair>(); 
    static Queue<pair> dochi = new LinkedList<pair>(); 
  
    
    public static class pair{
    	int x,y;
    	public pair(int x, int y){
    		this.x = x;
    		this.y = y;
    	}
    }
    
    public static int bfs(char[][] map) {
    	int cnt = 0;
    	while(!dochi.isEmpty()) {
    		cnt++;
    		
    		//water
    		for(int i=0;i<water.size();i++) {
    			pair w_xy = water.poll();

		    		for(int j=0;j<4;j++){
		                int xx = w_xy.x+dx[j];
		                int yy = w_xy.y+dy[j];
		                if(xx<0||yy<0||xx>=c||yy>=r){
		                    continue;
		                }
		                
		                if(map[xx][yy]=='.') {
		                	map[xx][yy]='*';
		                    water.add(new pair(xx,yy));
		                }
		            }

    		}
    		
    		//dochi
    		for(int i=0;i<dochi.size();i++) {
    			pair d_xy = dochi.poll();

		    		for(int j=0;j<4;j++){
		                int xx = d_xy.x+dx[j];
		                int yy = d_xy.y+dy[j];
		                if(xx<0||yy<0||xx>=c||yy>=r){
		                    continue;
		                }
	                
		                if(!check[xx][yy]&& map[xx][yy]!='*' &&map[xx][yy]!='X') {
		                	dochi.add(new pair(xx,yy));
	
	                        check[xx][yy] = true;
	                        
	                        if(map[xx][yy]=='D') {
	                        	return cnt;
	                        }
	                    }
		    		}
    		}
    		
		}
		return -1;
    		
	}
    
    
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        
        map = new char [r][c];
        check = new boolean[r][c];
        
       
        for (int i = 0; i < r; i++) {
            String s = br.readLine().trim();
            for (int j = 0; j < c; j++) {
                
                if(map[i][j] == '*') { 
                    water.add(new pair(i,j));
                }
                if(map[i][j] == 'S') { 
                	dochi.add(new pair(i,j));
                	check[i][j] =true;
                }

                map[i][j] = s.charAt(j);
            }
        }
        
        	
        int result = bfs(map);
        
		if(result < 0) System.out.print("KAKTUS");
        if(result > 0) System.out.print(result);
	}
}

