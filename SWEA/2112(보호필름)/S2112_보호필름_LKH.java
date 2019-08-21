import java.util.*;
public class Solution {
	static boolean flag = false;
	static int ans = 0;
	public static boolean check(int layer[][], int d, int w, int k) {
		boolean result = false;
		int cnt = 0;
		int okay = 0;
		int now = -1;
		boolean isFail = false;
		for(int i=0; i<w; i++) {
			now = -1;
			cnt = 0;
			if(isFail) break;
			for(int j=0; j<d; j++) {
				if(now==-1) {
					now = layer[j][i];
					cnt++;
				}
				else {
					if(now==layer[j][i])cnt++;
					else {
						now = layer[j][i];
						cnt = 1;
					}
				}
				if(cnt>=k) {
					okay++;
					isFail = false;
					break;
				}
				else isFail = true;
			}
		}
		if(okay==w) result = true;
		return result;
	}// 성능 평가
	public static void pick(int layer[][], int output[], int inner[], int depth, int target, int d, int w, int k) {
		if(depth == target) {
			int copy[][] = new int[d][w];
			boolean b = false;
			int tm = 0;
			int sample[] = {2,4};
			int sample2[] = {1,1};
			for(int i=0; i<d;i++) {
				for(int j=0; j<w; j++) {
					if(output[tm]!=i) {
						copy[i][j] = layer[i][j];
						b = false;
					}
					else {
						copy[i][j] = inner[tm];
						b = true;
					}
				}
				if(b && tm<output.length-1) {
					tm++;
				}
			}
			if(check(copy, d, w, k)) {
				ans = depth;
				flag = true;
			}
		
			return;
		}
		
		for(int i=0; i<2; i++) {
			if(!flag) {
				inner[depth] = i;
				pick(layer, output, inner, depth+1, target, d, w, k);
			}
		}
	}
	public static void select(int layer[][], int output[], int depth, int target, int tmp, int d, int w, int k) {
		if(depth == target) {
			int inner[] = new int[depth];
			pick(layer, output, inner, 0, target, d, w, k);
			return;
		}
		for(int i=tmp; i<d; i++) {
			if(!flag) {
				output[depth] = i;
				tmp++;
				select(layer, output, depth+1, target, tmp, d, w, k);
			}
		}
	}
	
	
	public static void main(String[] args){
	// TODO Auto-generated method stub
		Scanner scn = new Scanner(System.in);
		int tc = scn.nextInt();
		for(int i=1; i<=tc; i++) {
			int d = scn.nextInt();
			int w = scn.nextInt();
			int k = scn.nextInt();
			int layer[][] = new int[d][w];
			for(int a=0; a<d; a++) {
				for(int b=0; b<w; b++) {
					layer[a][b] = scn.nextInt();
				}
			}
			// 입력부
			if(check(layer, d, w, k)) {
				System.out.println("#"+i+" "+ans);
			}//순정 보호 필름의 성능 검사를 했을 때 통과하는 경우
            else if(k == 1) {
				System.out.println("#"+i+" "+"0");
			}
			else {
				for(int r=1; r<=d; r++) {
					int output[] = new int[r];
					select(layer, output, 0, r, 0, d, w, k);
					if(flag) break;
				}
				System.out.println("#"+i+" "+ans);
			}
			ans = 0;
			flag = false;
		}
		
	}
}