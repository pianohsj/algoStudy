import sys
import collections

Topni = []
turns = []


def turnLeft(i, d):
    if i < 0:
        return

    if Topni[i][2] != Topni[i + 1][6]:
        turnLeft(i - 1, -d)
        Topni[i].rotate(d)


def turnRight(i, d):
    if i > 3:
        return

    if Topni[i][6] != Topni[i - 1][2]:
        turnRight(i + 1, -d)
        Topni[i].rotate(d)


def solve():
    for turn in turns:
        [idx, direction] = turn

        turnLeft(idx - 1, -direction)
        turnRight(idx + 1, -direction)

        Topni[idx].rotate(direction)


if __name__ == '__main__':
    for i in range(4):
        Topni.append(collections.deque(list(sys.stdin.readline())[:8]))

    K = int(sys.stdin.readline())

    for i in range(K):
        v1, v2 = map(int, sys.stdin.readline().split())
        turns.append([v1 - 1, v2])

    solve()
    sum = 0
    
