import java.util.*;
public class Main {
	static int dx[] = {1,-1,0,0};
	static int dy[] = {0,0,-1,1};
	public static int[] move(int dice[], int dir) {
		int next_dice[] = new int[6];
		if(dir == 0) {
			next_dice[0] = dice[3];
			next_dice[1] = dice[2];
			next_dice[2] = dice[0];
			next_dice[3] = dice[1];
			next_dice[4] = dice[4];
			next_dice[5] = dice[5];
		}//동
		else if(dir == 1) {
			next_dice[0] = dice[2];
			next_dice[1] = dice[3];
			next_dice[2] = dice[1];
			next_dice[3] = dice[0];
			next_dice[4] = dice[4];
			next_dice[5] = dice[5];			
		}//서
		else if(dir == 2) {
			next_dice[0] = dice[5];
			next_dice[1] = dice[4];
			next_dice[2] = dice[2];
			next_dice[3] = dice[3];
			next_dice[4] = dice[0];
			next_dice[5] = dice[1];
		}//북
		else {
			next_dice[0] = dice[4];
			next_dice[1] = dice[5];
			next_dice[2] = dice[2];
			next_dice[3] = dice[3];
			next_dice[4] = dice[1];
			next_dice[5] = dice[0];
		}//남
		return next_dice;
	}
	public static void simulation(int map[][], int dice[], int sx, int sy, int c_Arr[], int n, int m) {
		int nx = sx;
		int ny = sy;
		for(int i=0; i<c_Arr.length; i++) {
			int dir = c_Arr[i]-1;
			nx = sx+dx[dir];
			ny = sy+dy[dir];
			if(nx<0 || ny<0 || nx>m-1 || ny>n-1) continue;
			else {
				dice = move(dice, dir);
				if(map[ny][nx]==0) map[ny][nx] = dice[1];
				else {
					dice[1] = map[ny][nx];
					map[ny][nx] = 0;
				}
				sx = nx;
				sy = ny;
				System.out.println(dice[0]);
			}
		}
	}
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		int n = scn.nextInt();
		int m = scn.nextInt();
		int map[][] = new int[n][m];
		
		int sx = scn.nextInt();
		int sy = scn.nextInt();
		//시작 위치
		int c_N = scn.nextInt();
		int c_Arr[] = new int[c_N];
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				map[i][j] = scn.nextInt();
			}
		} // 지도 정보
		for(int i=0; i<c_N; i++) {
			c_Arr[i] = scn.nextInt();
		} // 방향 정보
		
		int dice[] = new int[6];
		// 0 :위, 1 : 아래, 2 : 동, 3 : 서, 4 : 남, 5 : 북
		// 입력부
		
		simulation(map, dice, sy, sx, c_Arr, n, m);
		
	}	
}
