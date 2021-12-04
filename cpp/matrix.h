#include <vector>

#pragma once

class Matrix {
public:
    Matrix(int n,
           std::vector<double> xCoords,
           std::vector<double> yCoords);

    void setDistance(int a, int b, int distance);

    int getDistance(int a, int b);

private:
    int length;
    std::vector<int> distances;
    int test;

    int index(int a, int b);
};