import java.util.*;
public class Main {
	static int min = Integer.MAX_VALUE;
	public static int status(int arr[][], int team[], int n) {
		int result = 0;
		for(int i=0; i<n/2; i++) {
			for(int j=0; j<n/2; j++) {
				if(i!=j) {
					result += arr[team[i]][team[j]];
				}
			}
		}
		
		return result;
	}
	public static void dfs(int arr[][], int teamA[], int depth, int tmp, int n) {
		if(depth == n/2) {
			int teamB[] = new int[n/2];
			int index = 0;
			for(int i=0; i<n; i++) {
				boolean isHave = false;
				for(int j=0; j<n/2; j++) {
					if(i==teamA[j]) {
						isHave = true;
					}
				}
				if(!isHave) {
					teamB[index] = i;
					index++;
				}
			}
			int scoreA = status(arr, teamA, n);
			int scoreB = status(arr, teamB, n);
			int cal = Math.abs(scoreA-scoreB);
			if(min>cal) min = cal;
			return;
		}
		for(int i=tmp; i<n; i++) {
			tmp++;
			teamA[depth] = i;
			dfs(arr, teamA, depth+1, tmp, n);
		}
		
	}
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		int n = scn.nextInt();
		int arr[][] = new int[n][n];
		int teamA[] = new int[n/2];
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				arr[i][j] = scn.nextInt();
			}
		}
		
		dfs(arr, teamA, 0, 0, n);
		System.out.println(min);
		
	}	
}