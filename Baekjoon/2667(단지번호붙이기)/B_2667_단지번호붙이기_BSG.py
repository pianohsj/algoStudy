import sys

dx = [-1, 0, 1, 0]
dy = [0, 1, 0, -1]
count=0
danji=[]
N = int(sys.stdin.readline())
matrix = [list(sys.stdin.readline()) for _ in range(N)]

def search(x, y):
    global count
    matrix[x][y] = '0'
    count += 1
    for i in range(4):
        nx = x + dx[i]
        ny = y + dy[i]
        if nx < 0 or nx >= N or ny < 0 or ny >= N:
            continue
        if matrix[nx][ny] == '1':
            search(nx, ny)


for i in range(N):
    for j in range(N):
        if matrix[i][j] == '1':
            count = 0
            search(i, j)
            danji.append(count)

print(len(danji))
danji.sort()
for i in danji:
    print(i)




