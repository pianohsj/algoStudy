import java.util.*;
public class Main {
	static int dx[] = {1,0,-1,0};
	static int dy[] = {0,-1,0,1};

	public static int switching(int d) {
		int next_d = d;
		if(d == 0) {
			next_d = 2;
		}
		else if(d==1) {
			next_d = 3;
		}
		else if(d==2) {
			next_d = 0;
		}
		else if(d==3) {
			next_d = 1;
		}
		return next_d;
	}
	public static void drawCruve(int map[][], int saveDir[], int sx, int sy, int d, int g, int n) {
		saveDir[0] = d;
		int tmp = 1;
		for(int i=1; i<=g; i++) {
			if(tmp == 1) {
				if(d==0) {
					saveDir[tmp] = 1;
				}
				else if(d==1) {
					saveDir[tmp] = 2;
				}
				else if(d==2) {
					saveDir[tmp] = 3;
				}
				else if(d==3) {
					saveDir[tmp] = 0;
				}
				tmp++;
			}// 초기 방향에서 90도 회전
			else {
				int r = (int)Math.pow(2, i-1);
				for(int k=0; k<r; k++) {
					if(k<r/2) {
						saveDir[tmp+k] = switching(saveDir[k]);
					}
					else {
						saveDir[tmp+k] = saveDir[k];
					}
				}
				tmp = tmp+r;
			}
			
			
		}
	}
	public static void makeDragon(int map[][], int saveDir[], int sx, int sy, int n) {
		map[sy][sx] = 1;
		for(int i=0; i<saveDir.length; i++) {
			if(saveDir[i] == -1) break;
			else {
				int nx = sx+dx[saveDir[i]];
				int ny = sy+dy[saveDir[i]];
				if(nx<0 || nx>100 || ny<0 || ny>100)continue;
				else {
					map[ny][nx] = 1;
					sx = nx;
					sy = ny;
				}
			}
		}
	}
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Scanner scn = new Scanner(System.in);
		int n = scn.nextInt();
		int map[][] = new int[101][101];
		int dragon[][] = new int[n][4];
		for(int i=0; i<n; i++) {
			dragon[i][0] = scn.nextInt();
			dragon[i][1] = scn.nextInt();
			dragon[i][2] = scn.nextInt();
			dragon[i][3] = scn.nextInt();
		}
		int saveDir[][] = new int[n][2048];
		for(int i=0; i<n; i++)
			Arrays.fill(saveDir[i], -1);
		for(int i=0; i<n; i++) {
			drawCruve(map, saveDir[i], dragon[i][0], dragon[i][1], dragon[i][2], dragon[i][3], n);
			makeDragon(map, saveDir[i], dragon[i][0], dragon[i][1], n);
		// d=>0(오른쪽) 1(위쪽) 2(왼쪽) 3(아랫쪽)
		}
		int ans = 0;
		for(int i=0; i<100; i++) {
			for(int j=0; j<100; j++) {
				if(map[i][j] == 1) {
					int cnt = 0;
					for(int y=0; y<2; y++) {
						for(int x=0; x<2; x++) {
							if(map[i+y][j+x] == 1) {
								cnt++;
							}
						}
					}
					if(cnt==4) {
						ans++;
					}
				}
			}
		}
		System.out.println(ans);
		
	}
}