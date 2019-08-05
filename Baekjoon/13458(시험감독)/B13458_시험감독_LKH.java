import java.util.*;
public class Main {
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Scanner scn = new Scanner(System.in);
		int n = scn.nextInt();
		int test[] = new int[n];
		for(int i=0; i<n; i++) {
			test[i] = scn.nextInt();
		}
		int main = scn.nextInt();
		int sub = scn.nextInt();
		long observe = n;
		for(int i=0; i<n; i++) {
			if(test[i]-main <= 0) continue;
			else {
				if((test[i]-main)%sub!=0) {
					observe += (((test[i]-main)/sub)+1);
				}
				else {
					observe += ((test[i]-main)/sub);
				}
			}
		}
		System.out.println(observe);
	}	
}