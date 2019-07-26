import java.util.*;
public class Main{
	public static void dfs(char ch[], char result[], int depth, int target, int tmp, int n) {
		if(depth == target) {
			int mo = 0;
			int ja = 0;
			
			for(int i=0; i<target; i++) {
				if(result[i] == 'a' || result[i] == 'e' || result[i] == 'i' || result[i] == 'u' || result[i] == 'o') {
					mo++;
				}
				else ja++;
				
				
			}
			if(mo>=1 && ja>=2) {
				for(int i=0; i<target; i++) {
					System.out.print(result[i]);
				}
				System.out.println("");	
			}
			return;
		}
		for(int i=tmp; i<n; i++) {
			result[depth] = ch[i];
			tmp++;
			dfs(ch, result, depth+1, target, tmp, n);
		}
	}
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		int l = scn.nextInt();
		int c = scn.nextInt();
		String str = "";
		for(int i=0; i<c; i++) {
			String tmp = scn.next();
			if(!tmp.equals(" ")) {
				str += tmp;
			}
		}
		char ch[] = new char[c];
		char result[] = new char[l];
		ch = str.toCharArray();
		Arrays.sort(ch);
		dfs(ch, result, 0, l, 0, c);
		
	}	
}