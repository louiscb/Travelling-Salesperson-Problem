#include <vector>
#include "matrix.h"
#include "edge.h"

int shouldSwap(Edge x, Edge y, Matrix &matrix) {
    int oldLength = matrix.getDistance(x.a, x.b) + matrix.getDistance(y.a, y.b);
    int newLength = matrix.getDistance(x.a, y.a) + matrix.getDistance(x.b, y.b);

    return oldLength - newLength;
}

void swap(std::vector<int> &tour, int i, int j, int n) {
    int firstI = i;
    i = (i + 1) % n;
    int swaps = j - i;
    if (swaps < 0) swaps += n;

    if (swaps > n / 2) {
        swap(tour, j, firstI, n);
        return;
    }

    for (int swap = 0; swap < swaps / 2; swap++) {
        int temp = tour[i];
        tour[i] = tour[j];
        tour[j] = temp;

        i = (i + 1) % n;
        j = (n + j - 1) % n;
    }
}

std::vector<int> twoOpt(std::vector<int> tour, Matrix &matrix) {
    int n = tour.size();
    int iterations = 10;

    while (iterations > 0) {
        iterations--;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) continue;

                Edge x = {tour[i], tour[(i + 1) % n]};
                Edge y = {tour[j], tour[(j + 1) % n]};

                if (shouldSwap(x, y, matrix) > 0) {
                    swap(tour, i, j, n);
                }
            }
        }
    }

    return tour;
}
