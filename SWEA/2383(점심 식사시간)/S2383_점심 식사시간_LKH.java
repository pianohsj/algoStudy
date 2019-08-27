import java.util.*;
public class Solution {
	public static class Pair{
		int x, y;
		Pair(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	static int min = Integer.MAX_VALUE;
	public static int simulation(int team[], int arrived[], int stairLen) {
		boolean isFinish[] = new boolean[10];
		int time = 0;
		int t[] = new int[10];
		int status = 0;
		LinkedList<Integer> wait = new LinkedList<>();
		while(true) {
			int finCnt = 0;
			for(int i=0; i<10; i++) {
				if(isFinish[i]) {
					finCnt++;
				}
			}
			if(finCnt == team.length) break;			
			for(int i=0; i<team.length; i++) {
				if(time == arrived[team[i]] && status<3) {
					status++;
					t[i]++;
				}// 해당 시간에 사람들이 계단에 도착할 경우
				else if(time == arrived[team[i]] && status>=3) {
					wait.add(i);
				} // 도착은 했지만 계단 인원이 최대일 경우 => 대기한다.
				else if(time>arrived[team[i]] && status<3 && !isFinish[i] && t[i]==0) {
					status++;
					t[wait.removeFirst()]++;
				}
				else if(t[i]>0 && !isFinish[i]) t[i]++;
				
			}
			for(int i=0; i<10; i++) {
				if(t[i] == stairLen) {
					isFinish[i] = true;
					status--;
				}
			}
			time++;
		}
		return time;
	}
	public static void pick(int map[][], int people, int arrived[][], Pair s[], int output[], int depth, int target, int c) {
		if(depth == target) {
			int teamA = 0;
			int teamB = 0;
			int tmp = 0;
			if(people-target>0) {
				int other[] = new int[people-target];
				int idx = 0;
				for(int i=0; i<people; i++) {
					boolean isHave = false;
					for(int j=0; j<depth; j++) {
						if(i==output[j]) isHave = true;
					}
					if(!isHave) {
						other[idx] = i;
						idx++;
					}
				}
				teamA = simulation(output, arrived[0], map[s[0].y][s[0].x]);
				teamB = simulation(other, arrived[1], map[s[1].y][s[1].x]);
				if(teamA>teamB) tmp = teamA;
				else tmp = teamB;
			}
			else {
				teamA = simulation(output, arrived[0], map[s[0].y][s[0].x]);
				teamB = simulation(output, arrived[1], map[s[1].y][s[1].x]);
				if(teamA<teamB) tmp = teamA;
				else tmp = teamB;
			}

			if(min>tmp) min = tmp;
			return;
		}
		for(int i=c; i<people; i++) {
			c++;
			output[depth] = i;
			pick(map, people, arrived, s, output, depth+1, target, c);
		}
	}
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Scanner scn = new Scanner(System.in);
		int tc = scn.nextInt();
		for(int t=1; t<=tc; t++) {
			int n = scn.nextInt();
			int map[][] = new int[n][n];
			int human = 0;
			boolean isCheck = false;
			Pair[] stair = new Pair[2];
			LinkedList<Pair> people = new LinkedList<>();
			for(int i=0; i<n; i++) {
				for(int j=0; j<n; j++) {
					map[i][j] = scn.nextInt();
					if(map[i][j] == 1) {
						people.add(new Pair(j, i));
					}
					if(map[i][j]>1) {
						if(!isCheck) {
							stair[0] = new Pair(j,i);
							isCheck = true;
						}
						else {
							stair[1] = new Pair(j,i);
						}
					}
				}
			}
			int length[][] = new int[2][people.size()];
			for(int i=0; i<people.size(); i++) {
				length[0][i] = Math.abs(people.get(i).x - stair[0].x)+ Math.abs(people.get(i).y - stair[0].y);
				length[1][i] = Math.abs(people.get(i).x - stair[1].x)+ Math.abs(people.get(i).y - stair[1].y);
			} // 사람들로부터 계단1과 계단2 각각의 거리를 구하여 저장
			for(int i=1; i<=people.size(); i++) {
				int output[] = new int[i];
				pick(map, people.size(), length, stair, output, 0, i, 0);
			}
			System.out.println("#"+t+" "+(min+1));
			min = Integer.MAX_VALUE;
		}
	}

}
