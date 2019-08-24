import java.util.*;
public class Solution {
	static int dx[] = {1,1,-1,-1};
	static int dy[] = {1,-1,1,-1};
	static int cnt = 0;
	public static void makeRectangle(int map[][], int num[], int turn, int sx, int sy, int ex, int ey, int n) {
		if(turn == 4) {
			if(sx == ex && sy == ey) {
				int sum = 0;
				for(int i=1; i<101; i++) {
					if(num[i]>0) {
						sum++;
					}
				}
				if(sum>cnt) cnt = sum;
				return;
			} // 한 바퀴 돌고 
		} // 한바퀴를 다 돌았을 경우
		for(int i=0; i<4; i++) {
			int nx = sx+dx[i];
			int ny = sy+dy[i];
			if(nx<0 || ny<0 || nx>n-1 || ny>n-1) continue;
			else if(turn == 0 && (i==1 || i==2 || i==3)) continue;
			else if(turn == 1 && (i==1 || i==3)) continue;
			else if(turn == 2 && (i==0 || i==1)) continue;
			else if(turn == 3 && (i==0 || i==2)) continue;
			else if(turn == 4 && (i==0 || i==2 || i==3)) continue;
			else if((ny!=ey || nx!=ex) && num[map[ny][nx]]>0) continue;
			else {
				if((turn == 1 && i==0) || (turn == 2 && i==2) || (turn == 3 && i==3) || (turn==4 && i==1)) {
					num[map[ny][nx]]++;
					makeRectangle(map, num, turn, nx, ny, ex, ey, n);
					num[map[ny][nx]]--;
				}
				else {
					num[map[ny][nx]]++;
					makeRectangle(map, num, turn+1, nx, ny, ex, ey, n);
					num[map[ny][nx]]--;
				}
				
			}
		}	
	}
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Scanner scn = new Scanner(System.in);
		int tc = scn.nextInt();
		for(int i=1; i<=tc; i++) {
			int n = scn.nextInt();
			int map[][] = new int[n][n];
			for(int a=0; a<n; a++) {
				for(int b=0; b<n; b++) {
					map[a][b] = scn.nextInt();
				}
			}
			int num[] = new int[101];
			int visited[][] = new int[n][n];
			// 입력부
			for(int y=0; y<n; y++) {
				for(int x=1; x<n-1; x++) {
					num[map[y][x]]++;
					makeRectangle(map, num, 0, x, y, x, y, n);
					num[map[y][x]]--;
				}
			}
			if(cnt == 0)
				System.out.println("#"+i+" "+"-1");
			else
				System.out.println("#"+i+" "+cnt);
			cnt = 0;
			Arrays.fill(num, 0);
		}
	}
}