import sys
import itertools


def OperatorList(N, add, sub, mul, div):# 연산자의 개수 받아서 연산자 문자생성
    operator = []

    for i in range(N - 1):
        if add > 0:
            operator.append("+")
            add -= 1
        elif sub > 0:
            operator.append("-")
            sub -= 1
        elif mul > 0:
            operator.append("*")
            mul -= 1
        elif div > 0:
            operator.append("/")
            div -= 1

    return operator


def solve(N, A, operator): #연산자 직접 계산 -대신 우선순위가 없어야 하므로 각 연산자에 대한 계산을 함
    result = A[0]

    for i in range(N - 1):
        if operator[i] == "+":
            result = result + A[i + 1]
        elif operator[i] == "-":
            result = result - A[i + 1]
        elif operator[i] == "*":
            result = result * A[i + 1]
        elif operator[i] == "/":
            result = int(result / A[i + 1])#정수만 반환해야 한다

    return result


if __name__ == '__main__':
    N = int(sys.stdin.readline())
    A = [i for i in list(map(int, sys.stdin.readline().split()))]
    add, sub, mul, div = map(int, sys.stdin.readline().split())

    operatorList = OperatorList(N, add, sub, mul, div)

    operators = itertools.permutations(operatorList) #순서가 정해져 있으므로 조합이 아닌 순열.

    max = -sys.maxsize
    min = sys.maxsize

    for operator in operators:
        result = solve(N, A, operator)

        if result < min:
            minVal = result

        if result > max:
            maxVal = result

    print(max)
    print(min)
