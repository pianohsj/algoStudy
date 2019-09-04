import java.util.*;
public class Main {
	static int max = Integer.MIN_VALUE;
	public static int cal(String arr[], int n) {
		int result = 0;
		String tmp[] = new String[n];
		for(int i=0; i<n; i++) {
			tmp[i] = arr[i];
		}
		for(int i=1; i<n-1; i+=2) {
			if(tmp[i-1] == null || tmp[i]== null || tmp[i+1]== null) break;
			else {
				if(tmp[i].equals("+")) {
					result = Integer.parseInt(tmp[i-1]) + Integer.parseInt(tmp[i+1]);
					tmp[i+1] = String.valueOf(result);
				}
				else if(tmp[i].equals("-")) {
					result = Integer.parseInt(tmp[i-1]) - Integer.parseInt(tmp[i+1]);
					tmp[i+1] = String.valueOf(result);
				}
				else if(tmp[i].equals("*")) {
					result = Integer.parseInt(tmp[i-1]) * Integer.parseInt(tmp[i+1]);
					tmp[i+1] = String.valueOf(result);
				}
			}
		}
		return result;
	}
	public static void dfs(String arr[], boolean visited[], int output[], int depth, int target, int cnt, int n) {
		if(depth == target) {
			int sum = 0;
			boolean check[] = new boolean[n];
			String result[] = new String[n];
			int tmp = 0;
			for(int i=0; i<n; i++) {
				boolean isGoing = false;
				for(int j=0; j<output.length; j++) {
					if(i == output[j]) {
						isGoing = true;
						tmp--;
						if(arr[output[j]].equals("+")){
							check[output[j]-1] = true;
							check[output[j]] = true;
							check[output[j]+1] = true;
							result[tmp] = String.valueOf(Integer.parseInt(arr[output[j]-1])+ Integer.parseInt(arr[output[j]+1]));
							tmp++;
						}
						else if(arr[output[j]].equals("-")){
							check[output[j]-1] = true;
							check[output[j]] = true;
							check[output[j]+1] = true;
							result[tmp] = String.valueOf(Integer.parseInt(arr[output[j]-1])-Integer.parseInt(arr[output[j]+1]));
							tmp++;
						}
						else if(arr[output[j]].equals("*")){
							check[output[j]-1] = true;
							check[output[j]] = true;
							check[output[j]+1] = true;
							result[tmp] = String.valueOf(Integer.parseInt(arr[output[j]-1])*Integer.parseInt(arr[output[j]+1]));
							tmp++;
						}
						
					} // 괄호가 있을 경우
				}
				if(!isGoing && !check[i]) {
					result[tmp] = arr[i];
					tmp++;
				}
			}
			sum  = cal(result, n);
			if(max<sum) max = sum;
			return;
		}
		for(int i=1; i<n-1; i++) {
			if(!visited[i-1] && !visited[i] && !visited[i+1]) {
				if(arr[i].equals("+") || arr[i].equals("-") || arr[i].equals("*")) {
					output[depth] = i;
					visited[i-1] = true;
					visited[i] = true;
					visited[i+1] = true;
					dfs(arr, visited, output, depth+1, target, cnt, n);
					visited[i-1] = false;
					visited[i] = false;
					visited[i+1] = false;
				}
			}
			
		}
	}
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Scanner scn = new Scanner(System.in);
		int n = scn.nextInt();
		String arr[] = new String[n];
		String input = scn.next();
		arr = input.split("");
		int cnt = 0;
		for(int i=0; i<n; i++) {
			String st = arr[i];
			if((!st.equals("+")) && (!st.equals("-")) && (!st.equals("*"))) {
				cnt++;
			}
		}
		if(cnt == 1) {
			max = Integer.parseInt(arr[0]);
		}
		else if(cnt<=2) {
			max = cal(arr, n);
		}
		else {
			max = cal(arr, n);
			int index = cnt/2;
			for(int i=1; i<=index; i++) {
				boolean visited[] = new boolean[n];
				int output[] = new int[i];
				dfs(arr, visited, output, 0, i, cnt, n);
			}
		}		
		System.out.println(max);
	}
}