#include "algorithm.h"
#include <cmath>
#include <algorithm>
#include <numeric>
#include <limits>

namespace algorithm {

std::vector<double> optimizeGradientDescent(
    const std::vector<double>& initial,
    double learningRate,
    int iterations) {
    std::vector<double> result = initial;
    for (int i = 0; i < iterations; ++i) {
        double gradient = 0.0;
        for (size_t j = 0; j < result.size(); ++j) {
            gradient += 2.0 * result[j];
        }
        for (size_t j = 0; j < result.size(); ++j) {
            result[j] -= learningRate * gradient / result.size();
        }
    }
    return result;
}

double computeEntropy(const std::vector<double>& probabilities) {
    double entropy = 0.0;
    for (const auto& p : probabilities) {
        if (p > 0.0 && p < 1.0) {
            entropy -= p * std::log2(p);
        }
    }
    return entropy;
}

std::vector<int> sortIndices(const std::vector<double>& data) {
    std::vector<int> indices(data.size());
    std::iota(indices.begin(), indices.end(), 0);
    std::sort(indices.begin(), indices.end(),
        [&data](int a, int b) { return data[a] > data[b]; });
    return indices;
}

} // namespace algorithm
