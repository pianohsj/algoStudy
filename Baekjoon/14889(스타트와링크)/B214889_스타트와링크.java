package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B214889_스타트와링크 {
	static int N;
	static int s[][];
	static boolean visited[];
	static int min=999999999;

	public static void main(String[] args) throws IOException{
		//N명(짝수)
		//N/2명으로 이뤄진 스타트 팀, 링크 팀
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		N=Integer.parseInt(br.readLine().split(" ")[0]);
		
		s=new int[N][N];
		visited=new boolean[N];
		
		for(int i=0;i<N;i++) {
			StringTokenizer st=new StringTokenizer(br.readLine(), " ");
			//visited[i]=false;
			for(int j=0;j<N;j++) {
				s[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		dfs(0,0);	
		System.out.print(min);
	}
	
	//0,1 ,2 3팀 중 두팀의 합의 차가 가장 작은 경우
	public static void dfs(int index, int depth) {
		int s_sum=0;
		int l_sum=0;
		int diff=0;
		if(depth==N/2) {
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					if(visited[i]&&visited[j])s_sum+=s[i][j];
					
					if(!visited[i]&&!visited[j])l_sum+=s[i][j];
				}
			}	
			diff=Math.abs(s_sum-l_sum);
			min=Math.min(diff,min);
			
			//System.out.println(s_sum+" "+l_sum);
			return;
		}
				
		for(int i=index;i<N;i++) {
			if(!visited[i]) {
				visited[i]=true;
				dfs(index+1,depth+1);//,s_sum,l_sum);
				visited[i]=false;
			}
		}
				
	}
}
