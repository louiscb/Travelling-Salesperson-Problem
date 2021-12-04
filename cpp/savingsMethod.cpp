#include <vector>
#include "matrix.h"
#include "closestEdges.h"
#include <iostream>

std::vector<int> savingsMethod(int n, Matrix &matrix, int start) {
    Matrix updatedMatrix = matrix;

    for (int i = 0; i < n; i++) {
        if (i == start) {
            continue;
        }
        for (int j = i + 1; j < n; j++) {
            if (j == start) {
                continue;
            }

            updatedMatrix.setDistance(i, j, matrix.getDistance(i, j) - matrix.getDistance(i, start) -
                                        matrix.getDistance(j, start));
        }
    }

    return closestEdges(n, updatedMatrix);
}
