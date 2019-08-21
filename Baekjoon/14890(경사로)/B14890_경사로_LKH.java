import java.util.*;
public class Main {
	public static boolean findWay(int map[], int l, int n) {
		boolean result = true;
		int start = map[0];
		boolean check[] = new boolean[n];
		for(int i=1; i<n; i++) {
			int cnt = 0;
			if(result) {
				if(start == map[i]) {
					// 계속 진행
				} // 같은 높이 일 경우
				else if(start == map[i]+1) {
					check[i] = true;
					for(int j=i+1; j<i+l; j++) {
						if(j>n-1) break;
						if(map[i] == map[j]) {
							if(!check[j]) {
								cnt++;
								check[j] = true;
							}
								
						}
					}
					if(cnt+1 < l) {
						result = false;
					}
					else i+=(l-1);
					// 오른쪽 검사
				} // 높은 높이 일 경우
				else if(start+1 == map[i]) {
					for(int j=i-1; j>i-l-1; j--) {
						if(j<0) break;
						if(start == map[j]) {
							if(!check[j]) {
								cnt++;
								check[j] = true;
							}
						}
					}
					if(cnt < l) {
						result = false;
					}
					// 왼쪽 검사
				} // 낮은 높이 일 경우
				else result = false; // 높이 차가 2이상일 경우
			}
			else break;
			start = map[i];
		}
		return result;
	}
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Scanner scn = new Scanner(System.in);
		int n = scn.nextInt();
		int l = scn.nextInt();
		int map[][] = new int[n][n];
		int map2[][] = new int[n][n]; 
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				map[i][j] = scn.nextInt();
				map2[j][i] = map[i][j];
			}
		}
		int check = 0;
		for(int i=0; i<n; i++) {
			if(findWay(map[i], l, n)) {
				check++;
			}
			if(findWay(map2[i], l, n)) {
				check++;
			}
		}
		
		System.out.println(check);
	}
}