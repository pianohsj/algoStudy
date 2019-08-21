import java.util.*;
public class Solution {
	public static class Pair{
		int x, y, l;
		Pair(int x, int y, int l){
			this.x = x;
			this.y = y;
			this.l = l;
		}
	}
	static int dx[] = {-1,1,0,0};
	static int dy[] = {0,0,-1,1};
	public static int[] way(int pipe){
		int[] result = new int[4];
		if(pipe == 1) {
			result[0] = 0;
			result[1] = 1;
			result[2] = 2;
			result[3] = 3;
		}
		else if(pipe == 2) {
			result[0] = 2;
			result[1] = 3;
			result[2] = 4;
			result[3] = 4;
		}
		else if(pipe == 3) {
			result[0] = 0;
			result[1] = 1;
			result[2] = 4;
			result[3] = 4;
		}
		else if(pipe == 4) {
			result[0] = 1;
			result[1] = 2;
			result[2] = 4;
			result[3] = 4;
		}
		else if(pipe == 5) {
			result[0] = 1;
			result[1] = 3;
			result[2] = 4;
			result[3] = 4;
		}
		else if(pipe == 6) {
			result[0] = 0;
			result[1] = 3;
			result[2] = 4;
			result[3] = 4;
		}
		else if(pipe == 7) {
			result[0] = 0;
			result[1] = 2;
			result[2] = 4;
			result[3] = 4;
		}
		else if(pipe == 0) {
			result[0] = 4;
			result[1] = 4;
			result[2] = 4;
			result[3] = 4;
		}
		return result;
	}
	public static boolean isGoing(int dir, int nextP) {
		boolean check = true;
		if(dir == 0) {
			if(nextP == 2 || nextP == 6 || nextP == 7) {
				check = false;
			}
		} // 좌
		else if(dir == 1) {
			if(nextP == 2 || nextP == 4 || nextP == 5) {
				check = false;
			}
		} // 우
		else if(dir == 2) {
			if(nextP == 3 || nextP == 4 || nextP == 7) {
				check = false;
			}
		} // 상
		else if(dir == 3) {
			if(nextP == 3 || nextP == 5 || nextP == 6) {
				check = false;
			}
		} // 하
		return check;
	}
	public static int simulation(int map[][], int sx, int sy, int t, int n, int m) {
		int cnt = 0;
		Queue<Pair> q = new LinkedList<>();
		q.add(new Pair(sx, sy, 1));
		int ways[] = new int[4];
		int visited[][] = new int[n][m];
		visited[sy][sx] = 1;
		while(!q.isEmpty()) {
			Pair p = q.remove();
			int x = p.x;
			int y = p.y;
			int now = p.l;
			ways = way(map[y][x]);
			for(int i=0; i<4; i++) {
				int nx = x;
				int ny = y;
				if(ways[i]!=4) {
					nx +=dx[ways[i]];
					ny +=dy[ways[i]];
					if(nx<0 || ny<0 || nx>m-1 || ny>n-1 || visited[ny][nx] == 1 || now>t-1 || map[ny][nx] == 0) continue;
					else if(!isGoing(ways[i], map[ny][nx])) continue;
					else {
						q.add(new Pair(nx, ny, now+1));
						visited[ny][nx] = 1;
					}
				}
			}
			
		}
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				if(visited[i][j] == 1) {
					cnt++;
				}
			}
		}
		return cnt;
	}
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Scanner scn = new Scanner(System.in);
		int tc = scn.nextInt();
		for(int t=1; t<=tc; t++) {
			int n = scn.nextInt();
			int m = scn.nextInt();
			int map[][] = new int[n][m];
			int y = scn.nextInt();
			int x = scn.nextInt();
			int l = scn.nextInt();
			for(int i=0; i<n; i++) {
				for(int j=0; j<m; j++) {
					map[i][j] = scn.nextInt();
				}
			}
			// 입력부
			int ans = 0;
			ans = simulation(map, x, y, l, n, m);
			System.out.println("#"+t+" "+ans);			
		}
	}
}