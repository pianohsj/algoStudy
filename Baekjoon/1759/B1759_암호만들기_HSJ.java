package baekjoon;

import java.util.Arrays;
import java.util.Scanner;

public class B1759_암호만들기_HSJ {
	static int L, C;
	static char[] cArr;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		L = sc.nextInt();
		C = sc.nextInt();
		cArr = new char[C];
		
		for(int i = 0; i < cArr.length; i++) {
			cArr[i] = sc.next().charAt(0);
		}
		
		Arrays.sort(cArr);
		dfs(0,"");
		
	}
	
	public static void dfs(int idx, String s) {
		if(s.length() == L) {
			if(check(s)) {
				System.out.println(s);
			}
			return;
		}else if(idx >= C) {
			return;
		}else {
			dfs(idx+1,s+cArr[idx]);
			dfs(idx+1,s);
		}
	}
	
	public static boolean check(String s) {
		int ja = 0, mo = 0;
		for(char c : s.toCharArray()) {
			if(c=='a' || c=='e' || c=='i' || c=='o' || c=='u') {
				mo++;
			}else {
				ja++;
			}
		}
		
		return (mo>=1&&ja>=2)?true:false;
	}

}
