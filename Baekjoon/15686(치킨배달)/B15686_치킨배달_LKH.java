import java.util.*;
public class codingTest {
	static LinkedList<Pair>home = new LinkedList<>();
	static LinkedList<Pair>chicken = new LinkedList<>();
	static class Pair{
		int x;
		int y;
		Pair(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	static int minDis = Integer.MAX_VALUE;
	public static void calDis(int selected[], int hSize) {
		int dis[] = new int[hSize];
		Arrays.fill(dis, Integer.MAX_VALUE);
		for(int i=0; i<selected.length; i++) {
			for(int j=0; j<hSize; j++) {
				Pair homes = home.get(j);
				int x1 = homes.x;
				int y1 = homes.y;
				Pair chick = chicken.get(selected[i]);
				int x2 = chick.x;
				int y2 = chick.y;
				int tmp = Math.abs(x1 - x2) + Math.abs(y1 - y2);
				if(tmp<dis[j]) dis[j] = tmp;
			}
		}
		
		int sum = 0;
		for(int i=0; i<dis.length; i++) {
			sum += dis[i];
		}
		if(sum<minDis) minDis = sum;
	}
	public static void findDis(int m, int cSize, int hSize, int output[] , int depth, int tmp) {
		if(depth == m) {
			// 거리 계산
			calDis(output, hSize);
			return;
		}
		for(int i=tmp; i<cSize; i++) {
			output[depth] = i;
			tmp++;
			findDis(m, cSize, hSize,output, depth+1, tmp);
			
		}
		
	}
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Scanner scn = new Scanner(System.in);
		int n = scn.nextInt();
		int m = scn.nextInt();
		int map[][] = new int[n][n];
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				map[i][j] = scn.nextInt();
				if(map[i][j] == 1) {
					home.add(new Pair(j,i));
				}
				else if(map[i][j] == 2) {
					chicken.add(new Pair(j,i));
				}
			}
		} // 도시 정보 저장
		int hSize = home.size();
		int cSize = chicken.size();
		int output[] = new int[m];
		findDis(m, cSize, hSize, output, 0, 0);
		System.out.println(minDis);
	}
	
}
