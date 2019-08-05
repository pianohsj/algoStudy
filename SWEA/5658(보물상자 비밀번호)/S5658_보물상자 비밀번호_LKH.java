import java.util.*;
public class Solution {
	public static void rotate(LinkedList<String> list) {
		String tmp = list.removeLast();
		list.addFirst(tmp);
	}
	public static boolean isHave(LinkedList<Integer> list, int num) {
		boolean check = false;
		for(int i=0; i<list.size(); i++) {
			if(list.get(i) == num) {
				check = true;
			}
		}
		return check;
	}
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Scanner scn = new Scanner(System.in);
		int tc = scn.nextInt();
		for(int t=1; t<=tc; t++) {
			int n = scn.nextInt();
			int k = scn.nextInt();
			String str = scn.next();
			String arr[] = new String[n];
			arr = str.split("");
			LinkedList<String> list = new LinkedList<>();
			LinkedList<Integer> result = new LinkedList<>();
			for(int i=0; i<n; i++) {
				list.add(arr[i]);
			}
			int comeback = 0;
			while(comeback<4) {
				int cnt = 0;
				comeback = 0;
				String st = "";
				for(int i=0; i<n; i++) {
					if(cnt==(n/4)-1) {
						cnt = 0;
						st += list.get(i);
						int tmp = Integer.parseInt(st, 16);
						if(!isHave(result,tmp) && (result.size()>0)) {
							result.add(tmp);
						}
						else if(result.size() == 0){
							result.add(tmp);
						}
						else {
							comeback++;
						}
						st = "";
					}
					else {
						st+= list.get(i);
						cnt++;
					}
				}
				rotate(list);
			}
			Collections.sort(result);
			int cnt = 0;
			System.out.println("#"+t+" "+result.get(result.size()-k));			
		}
	}
}