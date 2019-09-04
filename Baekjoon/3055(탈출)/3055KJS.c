#include <stdio.h>
#include <queue>
using namespace std;
int dy[4]={0,0,1,-1};
int dx[4]={1,-1,0,0};
char map[60][60];
char visit[60][60];
int cnt;
int n,m;
int flag=0;
int bfs(int y,int x)
{
  queue<pair<int,int>>q;
  visit[y][x]=1;
  q.push(make_pair(y,x));
  int gy=q.front().first;
  int gx=q.front().second;
  //printf("%d %d\n",gy,gx);
  q.pop();
  for(int k=0;k<4;k++)
  {
    int ny=gy+dy[k];
    int nx=gx+dx[k];
    if(ny<n && nx<m && ny>=0 && nx >=0 && map[ny][nx]!='*' && map[ny][nx]!='X'&&visit[ny][nx]==0)
    {
      visit[ny][nx]=1;
      map[ny][nx]='S';
      //printf("%d %d\n",ny,nx);
      q.push(make_pair(ny,nx));
    }
  }
    cnt++;
  
  
}
int main() 
{
  flag =0;
  scanf("%d %d",&n,&m);
  for(int i=0;i<n;i++)
  {
    scanf("%s",&map[i]);
  }
  
  //     for(int i=0;i<n;i++)
  //     {
  //       for(int j=0;j<m;j++)
  //       {
  //         printf("%c",map[i][j]);
  //       }
  //       printf("\n");
  //     }
  
  for(int i=0;i<n;i++)
  {
    for(int j=0;j<m;j++)
    {
      if(map[i][j]=='*')
      {
        for(int k=0;k<4;k++)
        {
          int ny=i+dy[k];
          int nx=j+dx[k];
          if(ny<n && nx<m && ny>=0 && nx >=0 && map[ny][nx]!='D' && map[ny][nx]!='X'&&visit[ny][nx]==0)
          {
            printf("%d %d\n",ny,nx);
            visit[ny][nx]=1;
            map[ny][nx]='*';
          }
        }
      }
      
      for(int i=0;i<n;i++)
      {
        for(int j=0;j<m;j++)
        {
          if(map[i][j]!='X'&&map[i][j]!='*'&& visit[i][j]==0 && map[i][j]=='S')
          {
            visit[i][j]=1;
            bfs(i,j);
          }
        }
      }
      for(int i=0;i<n;i++)
      {
        for(int j=0;j<m;j++)
        {
          printf("%c",map[i][j]);
        }
        printf("\n");
      }
      printf("\n");
      
      
    }
  }
  if(flag ==1)
  {
    printf("%d",cnt);
  }
  else
  {
    printf("KAKTUS");
  }
  return 0;
}