import java.util.*;
public class Main {
	static int min = Integer.MAX_VALUE;
	static int dx[] = {1,0,-1,0};
	static int dy[] = {0,1,0,-1};
	public static void rotation(int map[][], int rotate[], int n, int m) {
		int r = rotate[0];
		int c = rotate[1];
		int s = rotate[2];
		int sx = c-s-1;
		int sy = r-s-1;
		int ex = c+s-1;
		int ey = r+s-1;
		int visited[][] = new int[n][m];
		int tmp = 0;
		int nx = sx;
		int ny = sy;
		LinkedList<Integer>list = new LinkedList<>();
		list.add(map[sy][sx]);
		while(true) {
			if(tmp>3) {
				sx+=1;
				sy+=1;
				ex-=1;
				ey-=1;
				if(visited[sy][sx] == 1) break;
				else {
					tmp = 0;
					nx = sx;
					ny = sy;
					if(list.size()>0) list.remove();
					list.add(map[sy][sx]);
				}
			}
			else {
				nx = nx+dx[tmp];
				ny = ny+dy[tmp];
				if(nx<sx || ny<sy || ny>n-1 || nx>m-1 || nx>ex || ny>ey || visited[ny][nx] == 1) {
					nx -=dx[tmp];
					ny -=dy[tmp];
					tmp++;
					continue;
				}
				else {
					visited[ny][nx] = 1;
					list.add(map[ny][nx]);
					map[ny][nx] = list.remove();
				}
			}
		}
		
	}
	public static void dfs(int map[][], int rotate[][], int output[], boolean visited[], int k, int depth, int n, int m) {
		if(depth == k) {
			int copy[][] = new int[n][m];
			for(int i=0; i<n; i++) {
				for(int j=0; j<m; j++) {
					copy[i][j] = map[i][j];
				}
			}
			for(int i=0; i<k; i++) {
				rotation(copy, rotate[output[i]], n, m);
				// 배열 회전
			}
			// 최소값 탐색
			for(int i=0; i<n; i++) {
				int sum = 0;
				for(int j=0; j<m; j++) {
					sum+=copy[i][j];
				}
				if(min>sum) min = sum;
			}
			return;
		}
		for(int i=0; i<k; i++) {
			if(!visited[i]) {
				visited[i] = true;
				output[depth] = i;
				dfs(map, rotate, output, visited, k, depth+1, n, m);
				visited[i] = false;
			}
		}
	}
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		int n = scn.nextInt();
		int m = scn.nextInt();
		int k = scn.nextInt();
		int map[][] = new int[n][m];
		int rotate[][] = new int[k][3];
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				map[i][j] = scn.nextInt();
			}
		}
		for(int i=0; i<k; i++) {
			for(int j=0; j<3; j++) {
				rotate[i][j] = scn.nextInt();
			}
		}
		// 입력부
		int output[] = new int[k];
		boolean visited[] = new boolean[k];
		dfs(map, rotate, output, visited, k, 0, n, m);
		System.out.println(min);
	}	
}