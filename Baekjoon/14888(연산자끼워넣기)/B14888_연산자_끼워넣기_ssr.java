package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//dfs
public class B14888_연산자_끼워넣기_ssr {
	static int N;
	static int A[];
	static int op[]; 
	static int min, max;
	static boolean is_first_op=true;
	
	public static void main(String[] args) {
		
			BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
			
			try {
				String s[];
				s = br.readLine().split(" ");
			
				N=Integer.parseInt(s[0]);
				A=new int[N];
				op=new int[4]; //+ - x /
				
				StringTokenizer st1=new StringTokenizer(br.readLine(), " ");
				for(int i=0;i<N;i++)
					A[i]=Integer.parseInt(st1.nextToken());
				
				StringTokenizer st2=new StringTokenizer(br.readLine(), " ");
				for(int i=0;i<4;i++)
					op[i]=Integer.parseInt(st2.nextToken());
				
				min=A[0];
				max=A[0];
				
				dfs(1, A[0]);
				
				//최댓값
				System.out.println(max);
				//최소값
				System.out.println(min);
			
			} catch (IOException e) {
				e.printStackTrace();
			}
		
	}
	
	public static void dfs(int index, int sum) {
		if(index==N) {	
			if(is_first_op) {
				min=sum;
				max=sum;
				is_first_op=false;
			}else {
				if (min>=sum)min=sum;
				if(max<=sum)max=sum;
			}
			
			return;
		}
		
		for(int i=0;i<4;i++) {
			if(op[i]!=0) {
				op[i]--;
				if(i==0) {//+
					dfs(index+1, sum+A[index]);
				}else if(i==1) {//-
					dfs(index+1, sum-A[index]);
				}else if(i==2) {//*
					dfs(index+1, sum*A[index]);
				}else {// /
					dfs(index+1, sum/A[index]);
				}
				op[i]++;
			}
		}
	}
}
