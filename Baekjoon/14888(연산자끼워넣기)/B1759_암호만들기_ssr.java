package 백준;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B1759_암호만들기_ssr
{
	static char ch[];
	static char[] case_answer;
	
	public static void main(String[] args) 
	{
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		try {
			String s=br.readLine();
			StringTokenizer st=new StringTokenizer(s, " ");

			String s2=br.readLine();
			StringTokenizer st2=new StringTokenizer(s2, " ");

			int L=Integer.parseInt(st.nextToken());//L개의 알파뱃 소문자
			int C=Integer.parseInt(st.nextToken());//c개의 문자
			
			ch=new char[C];
			for(int i=0;i<C;i++){
				ch[i]=st2.nextToken().toCharArray()[0];
			}
			
			encrypt(L,C, ch);
	
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
	
	public static void encrypt(int L,int C, char[] ch){
		Arrays.sort(ch);
		
		case_answer=new char[C];
		dfs(0,0, L, C);
	}
	
	public static void dfs(int index, int depth,int L, int C) {

		if(depth==L) {
			if(isCheck(case_answer)) {
				for(char c:case_answer) {
					System.out.print(c);
				}
				System.out.println();
			}
			return;
			
		}else {
			for(int i=index;i<C;i++) {
				case_answer[depth]=ch[i];
				dfs(i+1,depth+1, L,  C);
			}
		}		
	}
	
	public static boolean isCheck(char str[]) {
		int vowal=0;
		int cons=0;
		for(char s:str) {
			if(s=='a'||s=='e'||s=='i'||s=='o'||s=='u')
				vowal++;
			else
				cons++;
		}
		return vowal>=1&&cons>=2;
	}
	
}
