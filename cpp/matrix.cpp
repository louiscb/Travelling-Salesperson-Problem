#include "matrix.h"
#include <math.h>

Matrix::Matrix(int n, std::vector<double> xCoords, std::vector<double> yCoords) {
    distances = std::vector<int>(n * n);

    double dx, dy, distance;
    length = n;

    for (int i = 0; i < n; i++) {
        for (int j = i + 1; j < n; j++) {
            dx = xCoords[i] - xCoords[j];
            dy = yCoords[i] - yCoords[j];
            distance = sqrt(dx * dx + dy * dy);

            distances[index(i, j)] = (int) round(distance);
        }
    }
}

int Matrix::getDistance(int a, int b) {
    return distances[index(a, b)];
}

void Matrix::setDistance(int a, int b, int distance) {
    distances[index(a, b)] = distance;
}

int Matrix::index(int a, int b) {
    if (a < b) {
        return a * length + b;
    }

    return b * length + a;
}