#include <vector>
#include <list>
#include <algorithm>
#include "closestEdges.h"
#include "edge.h"

const int FREE_NODE = -1;
const int CONNECTING_NODE = -2;

bool addEdge(Edge &edge, std::vector<int> &stateNodes) {
    int a = stateNodes[edge.a];
    int b = stateNodes[edge.b];

    if (a == CONNECTING_NODE || b == CONNECTING_NODE) {
        return false;
    } else if (a == FREE_NODE && b == FREE_NODE) {
        stateNodes[edge.a] = edge.b;
        stateNodes[edge.b] = edge.a;
        return true;
    } else if (a == FREE_NODE || b == FREE_NODE) {
        int freeNode = a == FREE_NODE ? edge.a : edge.b;
        int destinationNode = a == FREE_NODE ? edge.b : edge.a;

        stateNodes[freeNode] = stateNodes[destinationNode];
        stateNodes[stateNodes[destinationNode]] = freeNode;
        stateNodes[destinationNode] = CONNECTING_NODE;
        return true;
    } else if (a == edge.b || b == edge.a) {
        return false;
    }

    // add the edge
    stateNodes[edge.a] = CONNECTING_NODE;
    stateNodes[a] = b;
    stateNodes[edge.b] = CONNECTING_NODE;
    stateNodes[b] = a;

    return true;
}

std::vector<int> closestEdges(int n, Matrix &matrix) {
    std::vector<int, std::allocator<int>> vertexState = std::vector<int>(n, FREE_NODE);
    std::vector<Edge> edgesVector;
    std::vector<Edge> tour;

    for (int i = 0; i < n; i++) {
        for (int j = i + 1; j < n; j++) {
            edgesVector.push_back(Edge{
                    i,
                    j,
                    matrix.getDistance(i, j),
            });
        }
    }

    std::sort(edgesVector.begin(), edgesVector.end(), [](const Edge &a, const Edge &b) {
            return a.length < b.length;
        }
    );

    std::list<Edge, std::allocator<Edge>> edges = std::list<Edge>(edgesVector.begin(), edgesVector.end());

    for (int i = 0; i < n; i++) {
        for (auto it = edges.begin(); it != edges.end();) {
            if (addEdge(*it, vertexState)) {
                tour.push_back(*it);
                edges.erase(it);
                break;
            }
            if (vertexState[it->a] == CONNECTING_NODE || vertexState[it->b] == CONNECTING_NODE) {
                it = edges.erase(it);
            } else {
                it++;
            }
        }
    }

    int begin = -1;
    int end = -1;
    for (int i = 0; i < n; i++) {
        if (vertexState[i] >= 0) {
            if (begin == -1) begin = i; else end = i;
        }
    }

    tour.push_back({begin, end, -1});

    std::vector<bool, std::allocator<bool>> used = std::vector<bool>(n);
    std::vector<int, std::allocator<int>> finalTour = std::vector<int>(n);
    used[0] = true;
    finalTour[0] = 0;

    int current = 0;
    for (int i = 1; i < n; i++) {
        for (auto edge: tour) {
            if (edge.a == current && !used[edge.b]) {
                current = edge.b;
            } else if (edge.b == current && !used[edge.a]) {
                current = edge.a;
            } else {
                continue;
            }
            used[current] = true;
            finalTour[i] = current;
            break;
        }
    }

    return finalTour;
}