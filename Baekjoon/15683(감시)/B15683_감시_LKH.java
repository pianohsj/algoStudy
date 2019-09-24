import java.util.*;
public class Main {
	static int dx[] = {-1,1,0,0};
	static int dy[] = {0,0,-1,1};
	public static class Pair{
		int x, y;
		Pair(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	static int min = Integer.MAX_VALUE;
	public static void print(int map[][],int dx[], int dy[], int sx, int sy, int n, int m) {
			int index = 0;
			int nx = sx;
			int ny = sy;
			while(true) {
				if(index == 4) break;
				if(dx[index] == 0 && dy[index] == 0) {
					index++;
					nx = sx;
					ny = sy;
					continue;
				}
				nx = nx+dx[index];
				ny = ny+dy[index];
				if(nx<0 || ny<0 || nx>m-1 || ny>n-1 || (map[ny][nx] == 6)) {
					index++;
					nx = sx;
					ny = sy;
					continue;
				}
				else {
					map[ny][nx] = 9;
				}
			}
		
	}
	
	public static int [][] way(int cctv, int rotate) {
		int result[][] = new int[2][4];
		if(cctv == 1) {
			if(rotate == 0) {
				int tmpx[] = {1,0,0,0};
				int tmpy[] = {0,0,0,0};
				result[0] = tmpx;
				result[1] = tmpy;
			}
			else if(rotate == 1) {
				int tmpx[] = {-1,0,0,0};
				int tmpy[] = {0,0,0,0};
				result[0] = tmpx;
				result[1] = tmpy;				
			}
			else if(rotate == 2) {
				int tmpx[] = {0,0,0,0};
				int tmpy[] = {1,0,0,0};
				result[0] = tmpx;
				result[1] = tmpy;
			}
			else if(rotate == 3) {
				int tmpx[] = {0,0,0,0};
				int tmpy[] = {-1,0,0,0};
				result[0] = tmpx;
				result[1] = tmpy;
			}
		}
		else if(cctv == 2) {
			if(rotate == 0) {
				int tmpx[] = {1,-1,0,0};
				int tmpy[] = {0,0,0,0};
				result[0] = tmpx;
				result[1] = tmpy;
			}
			else if(rotate == 1) {
				int tmpx[] = {0,0,0,0};
				int tmpy[] = {-1,1,0,0};
				result[0] = tmpx;
				result[1] = tmpy;
			}
			else if(rotate == 2) {
				int tmpx[] = {1,-1,0,0};
				int tmpy[] = {0,0,0,0};
				result[0] = tmpx;
				result[1] = tmpy;
			}
			else if(rotate == 3) {
				int tmpx[] = {0,0,0,0};
				int tmpy[] = {-1,1,0,0};
				result[0] = tmpx;
				result[1] = tmpy;
			}
		}
		else if(cctv == 3) {
			if(rotate == 0) {
				int tmpx[] = {1,0,0,0};
				int tmpy[] = {0,-1,0,0};
				result[0] = tmpx;
				result[1] = tmpy;
			}
			else if(rotate == 1) {
				int tmpx[] = {-1,0,0,0};
				int tmpy[] = {0,-1,0,0};
				result[0] = tmpx;
				result[1] = tmpy;
			}
			else if(rotate == 2) {
				int tmpx[] = {-1,0,0,0};
				int tmpy[] = {0,1,0,0};
				result[0] = tmpx;
				result[1] = tmpy;
			}
			else if(rotate == 3) {
				int tmpx[] = {1,0,0,0};
				int tmpy[] = {0,1,0,0};
				result[0] = tmpx;
				result[1] = tmpy;
			}
		}
		else if(cctv == 4) {
			if(rotate == 0) {
				int tmpx[] = {1,0,-1,0};
				int tmpy[] = {0,-1,0,0};
				result[0] = tmpx;
				result[1] = tmpy;
			}
			else if(rotate == 1) {
				int tmpx[] = {1,0,-1,0};
				int tmpy[] = {0,1,0,0};
				result[0] = tmpx;
				result[1] = tmpy;
			}
			else if(rotate == 2) {
				int tmpx[] = {-1,0,0,0};
				int tmpy[] = {0,-1,1,0};
				result[0] = tmpx;
				result[1] = tmpy;
			}
			else if(rotate == 3) {
				int tmpx[] = {1,0,0,0};
				int tmpy[] = {0,1,-1,0};
				result[0] = tmpx;
				result[1] = tmpy;
			}
		}
		else if(cctv == 5) {
			int tmpx[] = {1,0,-1,0};
			int tmpy[] = {0,-1,0,1};
			result[0] = tmpx;
			result[1] = tmpy;
		}
		return result;
	}
	public static void pick(int map[][], LinkedList<Pair> list, int output[], int depth, int n, int m) {
		if(depth == list.size()) {
			int copy[][] = new int[n][m];
			for(int i=0; i<n; i++) {
				for(int j=0; j<m; j++) {
					copy[i][j] = map[i][j];
				}
			}
			for(int i=0; i<list.size(); i++) {
				Pair p = list.get(i);
				int x = p.x;
				int y = p.y;
				int tmp[][] = way(map[y][x], output[i]);
				print(copy, tmp[0], tmp[1], x, y, n, m);
			}
			int cnt = 0;
			for(int i=0; i<n; i++) {
				for(int j=0; j<m; j++) {
					if(copy[i][j] == 0) {
						cnt++;
					}
				}
			}
			if(min>cnt) min = cnt;
			return;
		}
		for(int i=0; i<4; i++) {
			output[depth] = i;
			pick(map, list, output, depth+1, n, m);
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scn = new Scanner(System.in);
		int n = scn.nextInt();
		int m = scn.nextInt();
		int map[][] = new int[n][m];
		int cnt = 0;
		LinkedList<Pair> list = new LinkedList<>();
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				map[i][j] = scn.nextInt();
				if(map[i][j]>0 && map[i][j]<6) {
					list.add(new Pair(j,i));
					cnt++;
				}
			}
		}
		// 입력부
		int output[] = new int[cnt];
		pick(map, list, output, 0, n, m);
		System.out.println(min);
	}
}