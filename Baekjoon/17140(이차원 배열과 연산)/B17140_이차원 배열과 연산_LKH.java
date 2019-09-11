import java.util.*;
public class Main {
	static int time = 0;
	public static int rCal(int map[][], int w, int h) {
		int next_w = 0;
		for(int i=0; i<h; i++) {
			int num[] = new int[101];
			for(int j=0; j<w; j++) {
				num[map[i][j]]++;
				map[i][j] = 0;
			}
			int tmp = 0;
			for(int j=1; j<101; j++) {
				for(int k=1; k<101; k++) {
					if(num[k] == j) {
						map[i][tmp] = k;
						map[i][tmp+1] = num[k];
						tmp+=2;
					}
				}
			}
			if(next_w<(tmp)) next_w = tmp;
		}
		return next_w;
	} // 열 연산(가로가 세로와 같거나 작을때)
	public static int cCal(int map[][], int w, int h) {
		int next_h = 0;
		for(int i=0; i<w; i++) {
			int num[] = new int[101];
			for(int j=0; j<h; j++) {
				num[map[j][i]]++;
				map[j][i] = 0;
			}
			int tmp = 0;
			for(int j=1; j<101; j++) {
				for(int k=1; k<101; k++) {
					if(num[k] == j) {
						map[tmp][i] = k;
						map[tmp+1][i] = num[k];
						tmp+=2;
					}
				}
			}
			if(next_h<tmp) next_h = tmp;
		}
		return next_h;
	} // 행 연산(가로가 세로보다 클 때)
	public static void simulation(int map[][], int r, int c, int k) {
		int w = 3;
		int h = 3;
		int now = 0;
		while(true) {
			if(map[r-1][c-1] == k) {
				time = now;
				break;
			}
			if(now>100) {
				time = -1;
				break;
			}
			if(w<=h) {
				w= rCal(map, w, h);
			} // r연산 실행
			else {
				h= cCal(map, w, h);
			} // c연산 실행
			now++;
		}
	}
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		
		int r = scn.nextInt();
		int c = scn.nextInt();
		int k = scn.nextInt();
		int map[][] = new int[500][500];
		for(int i=0; i<3; i++) {
			for(int j=0; j<3; j++) {
				map[i][j] = scn.nextInt();
				}
		}
			// 입력부
		simulation(map, r, c, k);
		System.out.println(time);
	}	
}