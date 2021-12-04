#include <iostream>
#include <vector>
#include <climits>
#include <cstdlib>
#include "matrix.h"
#include "twoOpt.h"
#include "savingsMethod.h"

Matrix getMatrixFromInput(int length);

std::vector<int> getInitialTour(int length, Matrix matrix);

int tourLength(int n, std::vector<int> tour, Matrix &matrix) {
    int length = 0;

    for (int i = 1; i < n; i++) {
        length += matrix.getDistance(tour[i - 1], tour[i]);
    }

    length += matrix.getDistance(tour[0], tour[n - 1]);
    return length;
}

int main(int argc, char const *argv[]) {
    int length;
    std::cin >> length;

    Matrix matrix = getMatrixFromInput(length);

    std::vector<int> result = getInitialTour(length, matrix);

    int bestResult = tourLength(length, result, matrix);

    std::vector<int> resultUpdate = twoOpt(result, matrix);

    for (int i: result) {
        std::cout << i << std::endl;
    }


    printf("Tour length: %d\n", bestResult);
}

std::vector<int> getInitialTour(int length, Matrix matrix) {
    std::vector<int> result;
    srand(time(nullptr));

    int len = INT_MAX;

    int numOfInitialToursToTry = 10;

    for (int i = 0; i < numOfInitialToursToTry; ++i) {
        auto tour = savingsMethod(length, matrix, rand() % length);
        int newLen = tourLength(length, tour, matrix);
        if (newLen < len) {
            len = newLen;
            result = tour;
        }
    }
    return result;
}

Matrix getMatrixFromInput(int length) {
    std::vector<double> xcoord;
    std::vector<double> ycoord;

    double x, y;
    for (int i = 0; i < length; i++) {
        std::cin >> x >> y;
        xcoord.push_back(x);
        ycoord.push_back(y);
    }

    Matrix matrix = Matrix(length, xcoord, ycoord);
    return matrix;
}
