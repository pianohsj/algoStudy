import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main{
	
	static int dx[] = new int [] {-1,1,0,0};
	static int dy [] = new int [] {0,0,1,-1};
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
    	int size = 0;
    	while(!dochi.isEmpty()) {
    		cnt++;
    		size = water.size();
    		//water
    		for(int i=0;i<size;i++) {
    			pair w_xy = water.poll();

		    		for(int j=0;j<4;j++){
		                int xx = w_xy.x+dx[j];
		                int yy = w_xy.y+dy[j];
		                
		                if(xx<0||yy<0||xx>=r||yy>=c){
		                    continue;
		                }
		                
		                if(map[xx][yy]=='.') {
		                	map[xx][yy]='*';
		                    water.add(new pair(xx,yy));
		                }
		            }

    		}
    		
    		size = dochi.size();
    		
    		//dochi
    		for(int i=0;i<size;i++) {
    			pair d_xy = dochi.poll();

		    		for(int j=0;j<4;j++){
		                int xx = d_xy.x+dx[j];
		                int yy = d_xy.y+dy[j];
		                if(xx<0||yy<0||xx>=r||yy>=c){
		                    continue;
		                }
		                
		                if(!check[xx][yy]&& map[xx][yy]!='*' && map[xx][yy]!='X') {
		                	
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
        
        map = new char[r+1][c+1];
        check = new boolean[r+1][c+1];
        
       
        for (int i = 0; i < r; i++) {
        	char input[] = br.readLine().toCharArray();
            for (int j = 0; j < c; j++) {
                
            	map[i][j] = input[j];
            	
                if(map[i][j] == '*') { 
                    water.add(new pair(i,j));
                }
                if(map[i][j] == 'S') { 
                	dochi.add(new pair(i,j));
                	check[i][j] =true;
                }
                
            }
        }
        
        	
        int result = bfs(map);
        
		if(result == -1) System.out.print("KAKTUS");
		else System.out.print(result);
	}
}
