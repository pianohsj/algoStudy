import java.util.*;
public class Main {
	public static class Pair{
		int x,y,days;
		Pair(int x, int y, int days){
			this.x = x;
			this.y = y;
			this.days = days;
		}
	}
	static int ans = 0;
	static int dx[] = {-1,1,0,0};
	static int dy[] = {0,0,-1,1};
	public static boolean spread(int map[][], int visited[][], int n, int m) {
		boolean result = false;
        Queue<Pair> q = new LinkedList<>();
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				if(map[i][j] == 1) {
					q.add(new Pair(j, i, 1));
				}
			}
		}
		int day = 0;
		while(!q.isEmpty()) {
			Pair p = q.remove();
			int x = p.x;
			int y = p.y;
			day = p.days;
			for(int i=0; i<4; i++) {
				int nx = x+dx[i];
				int ny = y+dy[i];
				if(nx<0 || ny<0 || nx>m-1 || ny>n-1 || visited[ny][nx]>0 || map[ny][nx] !=0) continue;
				else {
					visited[ny][nx] = day+1;
					q.add(new Pair(nx, ny, day+1));
				}
			}
		}
		ans = day-1;
        for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				if(visited[i][j] == 0 && map[i][j] == 0) {
					result = true;
				}
			}
		}
        return result;
	}
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		int m = scn.nextInt();
		int n = scn.nextInt();
		int map[][] = new int[n][m];
		int visited[][] = new int[n][m];
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				map[i][j] = scn.nextInt();
			}
		}
		// ÀÔ·ÂºÎ
		
		boolean isFail = spread(map, visited, n, m);
		
		if(isFail) System.out.println("-1");
		else System.out.println(ans);	
    }	
}