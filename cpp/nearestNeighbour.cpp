//
// Created by Louis on 2021-12-02.
//

#include <vector>
#include "nearestNeighbour.h"
#include "matrix.h"

std::vector<int> nearestNeighbour(int n, int start, Matrix &matrix) {
    std::vector<int, std::allocator<int>> tour = std::vector<int>(n);
    std::vector<bool, std::allocator<bool>> used = std::vector<bool>(n);

    tour[0] = start;
    used[start] = true;

    for (int i = 1; i < n; i++) {
        int best = -1;
        for (int j = 0; j < n; j++) {
            if (!used[j] &&
                (best == -1 || matrix.getDistance(tour[i - 1], j) < matrix.getDistance(tour[i - 1], best))) {
                best = j;
            }
        }
        tour[i] = best;
        used[best] = true;
    }
    return tour;
}