import java.util.*;
public class Solution {
	static int result = 0;
	public static void print(int map[][], int sx, int sy, int order, int n) {
		for(int i=0; i<n; i++) {
			if(sx+i<=n-1 && map[sy][sx+i] == 0) map[sy][sx+i] = order;
			if(sx-i>=0 && map[sy][sx-i] == 0) map[sy][sx-i] = order;
			if(sy+i<=n-1 && map[sy+i][sx] == 0) map[sy+i][sx] = order;
			if(sy-i>=0 && map[sy-i][sx] == 0) map[sy-i][sx] = order;
			if((sx+i<=n-1 && sy+i<=n-1) && map[sy+i][sx+i] == 0) map[sy+i][sx+i] = order;
			if((sx-i>=0 && sy+i<=n-1) && map[sy+i][sx-i] == 0) map[sy+i][sx-i] = order;
		}
	}
	public static void remove(int map[][], int sx, int sy, int order, int n) {
		for(int i=0; i<n; i++) {
			if(sx+i<=n-1 && map[sy][sx+i] == order) map[sy][sx+i] = 0;
			if(sx-i>=0 && map[sy][sx-i] == order) map[sy][sx-i] = 0;
			if(sy+i<=n-1 && map[sy+i][sx] == order) map[sy+i][sx] = 0;
			if(sy-i>=0 && map[sy-i][sx] == order) map[sy-i][sx] = 0;
			if((sx+i<=n-1 && sy+i<=n-1) && map[sy+i][sx+i] == order) map[sy+i][sx+i] = 0;
			if((sx-i>=0 && sy+i<=n-1) && map[sy+i][sx-i] == order) map[sy+i][sx-i] = 0;
		}
	}
	public static void dfs(int map[][], int depth, int order, int n) {
		if(depth == n) {
			result++;
			return;
		}
		for(int i=0; i<n; i++) {
			if(map[depth][i] == 0) {
				print(map, i, depth, order, n);
				dfs(map, depth+1, order+1, n);
				remove(map, i, depth, order, n);
			}
		}
		
	}
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		int tc = scn.nextInt();
		for(int t=1; t<=tc; t++) {
			int n = scn.nextInt();
			int map[][] = new int[n][n];
			dfs(map, 0, 1, n);
			System.out.println("#"+t+" "+result);
			result = 0;
		}
	}	
}