package baekjoon;

import java.util.Scanner;

public class B14501_Επ»η_HSJ {
	static int N;
	static Pair[] consult;
	static int maxP = 0;
	static class Pair{
		int t, p;
		Pair(int t, int p){
			this.t = t;
			this.p = p;
		}
	}		
		
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		consult = new Pair[N];
		for(int i = 0; i < N; i++) {
			consult[i] = new Pair(sc.nextInt(), sc.nextInt());
		}
		for(int i = 0; i < consult.length; i++) {
			if(i+consult[i].t > N) continue;
			else {
				dfs(i+consult[i].t, consult[i].p);
			}
		}
		System.out.println(maxP);
	}
	
	public static void dfs(int t, int s) {
		if(t >= N) {
			maxP = Math.max(maxP, s);
			return;
		}else {
			for(int i = t; i < N; i++) {
				if(i+consult[i].t <= N) s += consult[i].p;
				dfs(i+consult[i].t, s);
				if(i+consult[i].t <= N) s -= consult[i
				                                     ].p;
			}
		}
	}

}
