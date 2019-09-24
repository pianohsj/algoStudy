import java.util.*;
public class Main {
	static int dx[] = {-1,1,0,0};
	static int dy[] = {0,0,-1,1};
	static int red_sx = 0;
	static int red_sy = 0;
	static int blue_sx = 0;
	static int blue_sy = 0;
	static int hole_x = 0;
	static int hole_y = 0;
	static boolean check = false;
	static int tmp_rx = 0;
	static int tmp_ry = 0;
	static int tmp_bx = 0;
	static int tmp_by = 0;
	public static class Pair{
		int x, y;
		Pair(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	static boolean isFin = false;
	public static void move(String map[][], Queue<Pair> q, int way, int start, int n, int m) {
		boolean isFail = false;
		LinkedList<String> ball = new LinkedList<>();
		while(!q.isEmpty()) {
			Pair p = q.remove();
			int x = p.x;
			int y = p.y;
			int nx = x;
			int ny = y;
			while(true) {
				nx = (nx+dx[way]);
				ny = (ny+dy[way]);
				if(!map[ny][nx].equals(".")) {
					if(map[ny][nx].equals("O") && map[y][x].equals("R") && !isFail) {
						map[y][x] = ".";
						ball.add("R");
					}
					else if(map[ny][nx].equals("O") && map[y][x].equals("B")) {
						map[y][x] = ".";
						ball.add("B");
					}
					if(map[y][x].equals("R")) {
						tmp_rx = nx-dx[way];
						tmp_ry = ny-dy[way];
						map[y][x] = ".";
						map[tmp_ry][tmp_rx] = "R";
					}
					else if(map[y][x].equals("B")) {
						tmp_bx = nx-dx[way];
						tmp_by = ny-dy[way];
						map[y][x] = ".";
						map[tmp_by][tmp_bx] = "B";
					}
					break;
				} // 빈 공간외를 만날 경우
			}
		}
		if(!ball.isEmpty()) {
			if(ball.size()==1) {
				if(ball.get(0).equals("R")) {
					check = true;
				}
				else {
					isFin = true;
				}
			}
			else if(ball.size()>1) {
				isFin = true;
			}
			
		}
	}
	public static void select(String map[][], int way, int red_nx, int red_ny, int blue_nx, int blue_ny, int n, int m) {
		Queue<Pair> q = new LinkedList<>();
		if(way == 0) {
			if(red_nx<blue_nx) {
				q.add(new Pair(red_nx, red_ny));
				q.add(new Pair(blue_nx, blue_ny));
			}
			else {
				q.add(new Pair(blue_nx, blue_ny));
				q.add(new Pair(red_nx, red_ny));
			}
		} // 좌
		else if(way == 1) {
			if(red_nx>blue_nx) {
				q.add(new Pair(red_nx, red_ny));
				q.add(new Pair(blue_nx, blue_ny));
			}
			else {
				q.add(new Pair(blue_nx, blue_ny));
				q.add(new Pair(red_nx, red_ny));
			}			
		} // 우
		else if(way == 2) {
			if(red_ny<blue_ny) {
				q.add(new Pair(red_nx, red_ny));
				q.add(new Pair(blue_nx, blue_ny));
			}
			else {
				q.add(new Pair(blue_nx, blue_ny));
				q.add(new Pair(red_nx, red_ny));
			}
		} // 상
		else if(way == 3) {
			if(red_ny>blue_ny) {
				q.add(new Pair(red_nx, red_ny));
				q.add(new Pair(blue_nx, blue_ny));
			}
			else {
				q.add(new Pair(blue_nx, blue_ny));
				q.add(new Pair(red_nx, red_ny));
			}
		} // 하
		move(map, q, way, 0, n, m);
	}
	public static void pickWay(String map[][], int output[], int depth, int target, int n, int m) {
		if(depth == target) {
			String copy[][] = new String[n][m];
			for(int i=0; i<n; i++) {
				for(int j=0; j<m; j++) {
					copy[i][j] = map[i][j];
				}
			}// 배열 복제
			int red_nx = red_sx;
			int red_ny = red_sy;
			int blue_nx = blue_sx;
			int blue_ny = blue_sy;
			boolean tmps = false;
			int tmp[] = {0,3,0,3};
			for(int i=0; i<target; i++) {
				//select(copy, output[i], n, m);
				if(isFin) break;
				select(copy, output[i], red_nx, red_ny, blue_nx, blue_ny, n, m);
				red_nx = tmp_rx;
				red_ny = tmp_ry;
				blue_nx = tmp_bx;
				blue_ny = tmp_by;
				tmps = check;
			}
			isFin = false;
			return;
		}
		for(int i=0; i<4; i++) {
			output[depth] = i;
			pickWay(map, output, depth+1, target, n, m);
		} // 방향 뽑기
	}
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		int n = scn.nextInt();
		int m = scn.nextInt();
		String map[][] = new String[n][m];
		for(int i=0; i<n; i++) {
			String str = scn.next();
			map[i] = str.split("");
		}
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				if(map[i][j].equals("R")) {
					red_sx = j;
					red_sy = i;
				}
				else if(map[i][j].equals("B")) {
					blue_sx = j;
					blue_sy = i;
				}
				else if(map[i][j].equals("O")) {
					hole_x = j;
					hole_y = i;
				}
			}
		}
		int result = 0;
		for(int i=1; i<=10 ; i++) {
			int output[] = new int[i];
			pickWay(map, output, 0, i, n, m);
			if(check) {
				result = i;
				break;
			}
		}
		if(result>0) System.out.println(result);
		else System.out.println("-1");
	}	
}