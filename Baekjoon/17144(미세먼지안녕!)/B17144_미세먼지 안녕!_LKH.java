import java.util.*;
public class Main {
	static int dx[] = {-1,1,0,0};
	static int dy[] = {0,0,-1,1};
	public static int[][] spreadDust(int map[][], int r, int c) {
		int next_map[][] = new int[c][r];
		for(int i=0; i<c; i++) {
			for(int j=0; j<r; j++) {
				int cnt = 0;
				int div = map[i][j]/5;
				int x = j;
				int y = i;
				for(int k=0; k<4; k++) {
					int nx = x+dx[k];
					int ny = y+dy[k];
					if(nx<0 || ny<0 || nx>r-1 || ny>c-1) continue;
					else if(map[ny][nx] == -1) {
						next_map[ny][nx] = -1;
					}
					else {
						next_map[ny][nx] += div;
						cnt++;
					}
				}
				next_map[i][j] += map[i][j]-(cnt*div);
			}
		}
		
		//next_map = copy(map, clean, r, c);
		
		return next_map;
	}
	
	public static int[][] turnAir(int map[][], int clean[][], int r, int c){
		int next_map[][] = new int[c][r];
		int start_x = clean[0][0];
		int start_y = clean[0][1];
		
		int start_x2 = clean[1][0];
		int start_y2 = clean[1][1];
		next_map = copy(map, clean, r, c);
		
		
			Queue<Integer> q = new LinkedList<>();
			for(int i=start_x+1; i<r-1; i++) {
				q.add(map[start_y][i]);
			}
			for(int i =start_x+2; i<r; i++) {
				next_map[start_y][i] = q.remove();
			}
			
			next_map[start_y-1][r-1] = map[start_y][r-1];
			for(int i=start_y; i>0; i--) {
				q.add(map[i][r-1]);
			}
			for(int i=start_y-1; i>=0; i--) {
				next_map[i][r-1] = q.remove();
			}
			next_map[0][r-2] = map[0][r-1];
			
			for(int i=r-1; i>0; i--) {
				q.add(map[0][i]);
			}
			for(int i =r-2; i>=0; i--) {
				next_map[0][i] = q.remove();
			}
			next_map[1][0] = map[0][0];
			
			for(int i=0; i<start_y; i++) {
				q.add(map[i][0]);
			}
			for(int i=1; i<start_y; i++) {
				next_map[i][0] = q.remove();
			}
		
			while(!q.isEmpty()) q.remove();
		
			
			for(int i=start_x2+1; i<r-1; i++) {
				q.add(map[start_y2][i]);
			}
			for(int i =start_x2+2; i<r; i++) {
				next_map[start_y2][i] = q.remove();
			}
			
			next_map[start_y2+1][r-1] = map[start_y2][r-1];
			for(int i=start_y2; i<c-1; i++) {
				q.add(map[i][r-1]);
			}
			for(int i=start_y2+1; i<c; i++) {
				next_map[i][r-1] = q.remove();
			}
			next_map[c-1][r-2] = map[c-1][r-1];
			
			for(int i=r-1; i>0; i--) {
				q.add(map[c-1][i]);
			}
			for(int i =r-2; i>=0; i--) {
				next_map[c-1][i] = q.remove();
			}
			next_map[c-2][0] = map[c-1][0];
			
			for(int i=c-1; i>start_y2; i--) {
				q.add(map[i][0]);
			}
			for(int i=c-2; i>start_y2; i--) {
				next_map[i][0] = q.remove();
			}
	
		//next_map = copy(map, clean, r, c);
		
		return next_map;
	}
	
	public static int[][] copy(int map[][], int clean[][], int r, int c) {
		int next_map[][] = new int[c][r];
		int x1 = clean[0][0];
		int x2 = clean[1][0];
		int y1 = clean[0][1];
		int y2 = clean[1][1];
		for(int i=1; i<c-1; i++) {
			for(int j=1; j<r-1; j++) {
				if((i==y1) || (i==y2) || i==0 || i==c-1) continue;
				else {
					next_map[i][j] = map[i][j];
				}
			}
		}
		return next_map;
	}
	
	static int sum = 0;
	public static void simulation(int map[][], int clean[][], int t, int r, int c) {
		int dir = 0;
		for(int k=0; k<t; k++) {
			map = spreadDust(map, r, c);
			
			
			map = turnAir(map,clean, r, c);
		}
		for(int i=0; i<c; i++) {
			for(int j=0; j<r; j++){
				if(map[i][j]>0)
					sum += map[i][j];
			}
		}
		
		
	}
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		int c = scn.nextInt();
		int r = scn.nextInt();
		int t = scn.nextInt();
		int map[][] = new int[c][r];
		int clean[][] = new int[2][2];
		int tmp = 0;
		for(int i=0; i<c; i++) {
			for(int j=0; j<r; j++) {
				map[i][j] = scn.nextInt();
				if(map[i][j] == -1) {
					clean[tmp][0] = j;
					clean[tmp][1] = i;
					tmp++;
				}
			}
		}
		// map의 -1은 공기청정기
		
		simulation(map, clean, t, r, c);
		System.out.println(sum);		
	}
}