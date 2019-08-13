import java.util.*;
public class Solution {
    static int dx[] = {-1,0,1,0};
    static int dy[] = {0,-1,0,1};
    static int maxScore = 0;
    public static class Pair{
        int x, y, dir;
        Pair(int x, int y, int dir){
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
    }
    public static int changeDir(int dir, int block) {
        int next_dir = dir;
        if(block == 1) {
            if(dir == 0) {
                next_dir = 1;
            }
            else if(dir == 1) {
                next_dir = 3;
            }
            else if(dir == 2) {
                next_dir = 0;
            }
            else if(dir == 3) {
                next_dir = 2;
            }
        }
        else if(block == 2) {
            if(dir == 0) {
                next_dir = 3;
            }
            else if(dir == 1) {
                next_dir = 2;
            }
            else if(dir == 2) {
                next_dir = 0;
            }
            else if(dir == 3) {
                next_dir = 1;
            }           
        }
        else if(block == 3) {
            if(dir == 0) {
                next_dir = 2;
            }
            else if(dir == 1) {
                next_dir = 0;
            }
            else if(dir == 2) {
                next_dir = 3;
            }
            else if(dir == 3) {
                next_dir = 1;
            }
        }
        else if(block == 4) {
            if(dir == 0) {
                next_dir = 2;
            }
            else if(dir == 1) {
                next_dir = 3;
            }
            else if(dir == 2) {
                next_dir = 1;
            }
            else if(dir == 3) {
                next_dir = 0;
            }
        }
        else if(block == 5) {
            if(dir == 0) {
                next_dir = 2;
            }
            else if(dir == 1) {
                next_dir = 3;
            }
            else if(dir == 2) {
                next_dir = 0;
            }
            else if(dir == 3) {
                next_dir = 1;
            }
        }
         
        return next_dir;
    }
    public static void simulation(int map[][], int wormHole[][], int sx, int sy, int d, int n) {
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(sx, sy, d));
        int score = 0;
        while(!q.isEmpty()) {
            Pair p = q.remove();
            int x = p.x;
            int y = p.y;
            int dir = p.dir;
            int nx = x+dx[dir];
            int ny = y+dy[dir];
            if(nx<0 || ny<0 || nx>n-1 || ny>n-1) {
                int tmp_dir = changeDir(dir, 5);
                q.add(new Pair(nx, ny, tmp_dir));
                score++;
            }
            else if(map[ny][nx] == -1 || (nx == sx && ny == sy)) break;
            else {
                if(map[ny][nx]>0 && map[ny][nx]<6) {
                    int tmp_dir = changeDir(dir, map[ny][nx]);
                    q.add(new Pair(nx, ny, tmp_dir));
                    score++;
                }
                else if(map[ny][nx]>5 && map[ny][nx]<11) {
                    q.add(new Pair(wormHole[map[ny][nx]*2][0], wormHole[map[ny][nx]*2][1], dir));
                }
                else if(map[ny][nx]>10){
                    q.add(new Pair(wormHole[map[ny][nx]/2][0], wormHole[map[ny][nx]/2][1], dir));
                }
                else {
                    q.add(new Pair(nx, ny, dir));
                }
            }
        }
        if(maxScore<score) maxScore = score;
    }
    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        Scanner scn = new Scanner(System.in);
        int tc = scn.nextInt();
        for(int t=1; t<=tc; t++) {
            int n = scn.nextInt();
            maxScore = 0;
            int map[][] = new int[n][n];
            LinkedList<Pair> list = new LinkedList<>();
            int wormHole[][] = new int[21][3];
            for(int i=0; i<n; i++) {
                for(int j=0; j<n; j++) {
                    int tmp = scn.nextInt();
                    if(tmp>5) {
                        if(wormHole[tmp][2]==1) {
                            map[i][j] = tmp*2;
                            wormHole[tmp*2][0] = j;
                            wormHole[tmp*2][1] = i;
                        }
                        else {
                            map[i][j] = tmp;
                            wormHole[tmp][0] = j;
                            wormHole[tmp][1] = i;
                            wormHole[tmp][2] = 1;
                        }
                    }
                    else {
                        map[i][j] = tmp;
                    }
                }
            }
            for(int i=0; i<n; i++) {
                for(int j=0; j<n; j++){
                    if(map[i][j] == 0){
                        for(int d=0; d<4; d++) {
                            simulation(map, wormHole, j, i, d, n);
                        }
                    }
                }
            }
            System.out.println("#"+t+" "+maxScore);
        }
    }
}