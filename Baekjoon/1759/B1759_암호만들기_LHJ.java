import java.util.*;
public class Main {
	public static int L,M;
	public static String MO = "aeiou";
	public static void Permutation(String p,String rest,int ja, int mo) {
		for(int i=0; i<rest.length(); i++) {
			int newJa=0,newMo=0;
			if(MO.indexOf(rest.charAt(i))==-1) {
				newJa=ja+1;
			}else {
				newMo=mo+1;
			}
			String newP=p+rest.charAt(i);
			String nRest=rest.substring(i+1);
			
			if(newP.length()<L) {
				Permutation(newP,nRest,newJa,newMo);
			}else {
				System.out.println(newP+" "+newMo+" "+newJa);
//				if(mo>=1 && ja>=2) {
//					System.out.println(newP);
//				}
			}
		}
	}
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		L=s.nextInt();
		M=s.nextInt();
		String[] S = new String[M];
		for(int i=0; i<M; i++) {
			S[i]=s.next();
		}
		Arrays.sort(S);
		String ss="";
		for(int i=0; i<M; i++) {
			ss+=S[i];
		}
		String p="";
		Permutation(p,ss,0,0);
		
	}
}
