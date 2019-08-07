import sys

N=B=C=0


def solve(A):
    count = 0

    for i in range(N):

        if A[i] > 0:
            A[i] -= B
            count += 1

        if A[i] > 0:
            count += int(A[i] / C)

            if A[i] % C != 0:
                count += 1

    return count


if __name__ == '__main__':
    N = int(sys.stdin.readline())
    A = list(map(int, sys.stdin.readline().split()))
    B, C = map(int, sys.stdin.readline().split())

    print(solve(A))