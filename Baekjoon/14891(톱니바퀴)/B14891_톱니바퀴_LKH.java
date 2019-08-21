import java.util.*;
public class Main {
	
	static LinkedList<Character> first = new LinkedList<>();
	static LinkedList<Character> second = new LinkedList<>();
	static LinkedList<Character> third = new LinkedList<>();
	static LinkedList<Character> fourth = new LinkedList<>();
	
	public static void moveRight(LinkedList<Character> list) {
		char first = list.remove(0);
		list.addLast(first);
	} // 반시계방향으로 회전
	public static void moveLeft(LinkedList<Character> list) {
		char last = list.remove(list.size()-1);
		list.addFirst(last);
	} // 시계방향으로 회전
	
	public static void insert(LinkedList<Character> list, char arr[]) {
		for(int i=0; i<8; i++) {
			list.add(arr[i]);
		}
	}
	public static void select(int i, char arr[]) {
		switch(i) {
		case 1:
			insert(first, arr);
			break;
		case 2:
			insert(second, arr);
			break;
		case 3:
			insert(third, arr);
			break;
		case 4:
			insert(fourth, arr);
			break;
			
		}
	}
	
	public static int rightR(int index, int where) {
		int rotate = 0;
		if(index == 0) {
			if(first.get(2) != second.get(6)) {
				if(where == 1) {
					rotate = -1;
				} // 1번이 시계 방향으로 회전할 경우
				else if(where == -1) {
					rotate = 1;
				}
				else rotate = 0;
			}
		}
		else if(index == 1) {
			if(second.get(2) != third.get(6)) {
				if(where == 1) {
					rotate = -1;
				} // 1번이 시계 방향으로 회전할 경우
				else if(where == -1) {
					rotate = 1;
				}
				else rotate = 0;
			}
		}
		else if(index == 2) {
			if(third.get(2) != fourth.get(6)) {
				if(where == 1) {
					rotate = -1;
				} // 1번이 시계 방향으로 회전할 경우
				else if(where == -1) {
					rotate = 1;
				}
				else rotate = 0;
			}
		}
		return rotate;
	}
	public static int leftR(int index, int where) {
		int rotate = 0;
		if(index == 1) {
			if(first.get(2) != second.get(6)) {
				if(where == 1) {
					rotate = -1;
				} // 1번이 시계 방향으로 회전할 경우
				else if(where == -1) {
					rotate = 1;
				}
				else rotate = 0;
			} // 1번 톱니바퀴로 갈수 있는 경우
		}
		else if(index == 2) {
			if(second.get(2) != third.get(6)) {
				if(where == 1) {
					rotate = -1;
				} // 1번이 시계 방향으로 회전할 경우
				else if(where == -1) {
					rotate = 1;
				}
				else rotate = 0;
			}
		} // 2번 톱니바퀴로 갈 수 있는 경우
		else if(index == 3) {
			if(third.get(2) != fourth.get(6)) {
				if(where == 1) {
					rotate = -1;
				} // 1번이 시계 방향으로 회전할 경우
				else if(where == -1) {
					rotate = 1;
				}
				else rotate = 0;
			}
		} // 3번 톱니바퀴로 갈 수 있는 경우
		return rotate;
	}
	
	public static void rotation(int index, boolean visited[], int rotated[], int rotate) {
		if(index < 0 || index > 3) {
			return;
		}
		
		
		if(index>=0 && index<=3) {
			visited[index] = true;
			rotated[index] = rotate;	
		}
		if(index>0) {
			if(!visited[index-1]) {
				int left = leftR(index, rotate); 
				if(left!=0) {
					rotation(index-1, visited, rotated, left);
				}
			}
		}
		if(index <3) {
			if(!visited[index+1]) {
				int right = rightR(index, rotate); 
				if(right!=0) {
					rotation(index+1, visited, rotated, right);
				}
			}
		}
		
				
	}
	public static void rotate(int index, int where) {
		
		if(index == 1) {
			if(where == 1) {
				moveLeft(first);
			}
			else if(where == -1) {
				moveRight(first);
			}
		}
		else if(index == 2) {
			if(where == 1) {
				moveLeft(second);
			}
			else if(where == -1) {
				moveRight(second);
			}
		}
		else if(index == 3) {
			if(where == 1) {
				moveLeft(third);
			}
			else if(where == -1) {
				moveRight(third);
			}
		}
		else if(index == 4) {
			if(where == 1) {
				moveLeft(fourth);
			}
			else if(where == -1) {
				moveRight(fourth);
			}
		}
	}
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Scanner scn = new Scanner(System.in);
		char arr[] = new char[8];
		for(int i=0; i<4; i++) {
			String str = scn.nextLine();
			arr = str.toCharArray();
			select(i+1, arr);
		}
		boolean visited[] = new boolean[4];
		int rotated[] = new int[4];
		int testcase = scn.nextInt();
		for(int i=0; i<testcase; i++) {
			int index = scn.nextInt();
			int where = scn.nextInt();
			rotation(index-1, visited, rotated, where);
			for(int j=0; j<4; j++) {
				rotate(j+1, rotated[j]);
			}
			Arrays.fill(visited, false);
			Arrays.fill(rotated, 0);
		}
		int ans = 0;
		if(first.get(0) == '1') ans += 1;
		if(second.get(0) == '1') ans += 2;
		if(third.get(0) == '1') ans += 4;
		if(fourth.get(0) == '1') ans += 8;
		
		System.out.println(ans);
		
		
	}
}