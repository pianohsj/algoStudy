import java.util.*;
public class Main {
	static int min = Integer.MAX_VALUE;
	static int dx[] = {1,0,1};
	static int dy[] = {0,1,1};
	static boolean isGoing = true;
	static int count = 0;
	public static void refresh(int map[][], int size, int sx, int sy) {
		for(int i=0; i<size; i++) {
			for(int j=0; j<size; j++) {
				map[sy+i][sx+j] = 0;
			}
		}
	}
	public static void print(int map[][], int size, int sx, int sy) {
		for(int i=0; i<size; i++) {
			for(int j=0; j<size; j++) {
				map[sy+i][sx+j] = 1;
			}
		}
	}
	public static int findSize(int map[][], int visited[][], int sx, int sy) {
		int size = 0;
		int tmp = 0;
		boolean canGo = true;
		int nx = sx;
		int ny = sy;
		int cnt = 4;
		int tmp_cnt = 0;
		while(true) {
			if(tmp>2)break;
			if(!canGo || tmp_cnt == 4) {
				tmp++;
				canGo = true;
				if(cnt>tmp_cnt) cnt = tmp_cnt;
				tmp_cnt = 0;
				nx = sx;
				ny = sy;
				continue;
			}
			nx = nx+dx[tmp];
			ny = ny+dy[tmp];
			if(nx<0 || ny<0 || nx>9 || ny>9) canGo = false;
			else if(map[ny][nx] == 0 || visited[ny][nx] == 1) canGo = false;
			else tmp_cnt++;
			
		}
		if(cnt>size) size = cnt;
		return size+1;
	}
	public static boolean isFail(int visited[][]) {
		boolean result = true;
		int cnt = 0;
		for(int i=0; i<10; i++) {
			for(int j=0; j<10; j++) {
				if(visited[i][j] == 1) cnt++;
			}
		}
		if(cnt != count) result = false;
		return result;
	}
	
	public static void dfs(int map[][], int visited[][], int output[], int size, int sx, int sy) {
		for(int i=0; i<10; i++) {
			if(!isGoing) break;
			for(int j=0; j<10; j++) {
				if(map[i][j] == 1 && visited[i][j] == 0) {
					int s = findSize(map, visited, j, i);
					while(s>0) {
						if(output[s-1]==5) return;
						output[s-1]++;
						print(visited, s, j, i);
						dfs(map, visited, output, size, sx, sy);
						refresh(visited, s, j, i);
						output[s-1]--;
						s--;
						if(s<=0) return;
					}
				}
			}
		}
		
		int sum = 0;
		if(!isFail(visited)) return;
		for(int i=0; i<5; i++) {
			sum+=output[i];
		}
		if(min>sum) {
			min = sum;
		}
	}
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		int map[][] = new int[10][10];
		for(int i=0; i<10; i++) {
			for(int j=0; j<10; j++) {
				map[i][j] = scn.nextInt();
				if(map[i][j] == 1) count++;
			}
		}
		int visited[][] = new int[10][10];
		int output[] = new int[5];
		dfs(map, visited, output, 0, 0, 0);
		if(min>25) System.out.println("-1");
		else System.out.println(min);
	}
	
}