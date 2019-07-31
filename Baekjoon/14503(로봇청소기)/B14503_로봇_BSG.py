import sys

N = M = 0
arr = []

dx = [-1, 0, 1, 0]
dy = [0, 1, 0, -1]


def countClean():
    count = 0
    for i in range(N):
        for j in range(M):
            if arr[i][j] > 1:
                count += 1
    return count


def leftTurn(d):
    if d == 0:
        return 3
    else:
        return d - 1


def clean(x, y, d, turnCount):
    while True:
      
        if turnCount == 4:
            backX = x - dx[d]
            backY = y - dy[d]

            if arr[backX][backY] == 1:
                print(countClean())
                return
            else:
                x, y, d, turnCount = backX, backY, d, 0

        ## 1. 현재 위치를 청소한다.
        if arr[x][y] == 0:
            arr[x][y] = 2

        ## 2. 왼쪽 방향부터 탐색
        ld = leftTurn(d)
        nx = x + dx[ld]
        ny = y + dy[ld]

        if arr[nx][ny] == 0:
            x, y, d, turnCount = nx, ny, ld, 0
        else:
            x, y, d, turnCount = x, y, ld, turnCount + 1


if __name__ == '__main__':
    N, M = map(int, sys.stdin.readline().split())
    r, c, d = map(int, sys.stdin.readline().split())

    for i in range(N):
        arr.append(list(map(int, sys.stdin.readline().split())))

    clean(r, c, d, 0)
