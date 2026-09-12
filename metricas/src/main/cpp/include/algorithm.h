#ifndef ALGORITHM_H
#define ALGORITHM_H

#include <vector>
#include <string>

namespace algorithm {

std::vector<double> optimizeGradientDescent(
    const std::vector<double>& initial,
    double learningRate,
    int iterations);

std::vector<double> kMeansCluster(
    const std::vector<std::vector<double>>& data,
    int k);

double computeEntropy(const std::vector<double>& probabilities);

std::vector<int> sortIndices(const std::vector<double>& data);

} // namespace algorithm

#endif // ALGORITHM_H
