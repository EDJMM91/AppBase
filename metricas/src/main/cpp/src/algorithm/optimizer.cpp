#include "algorithm.h"
#include <vector>
#include <cmath>
#include <algorithm>

namespace algorithm {

std::vector<double> kMeansCluster(
    const std::vector<std::vector<double>>& data,
    int k) {
    if (data.empty() || k <= 0) return {};
    std::vector<double> result;
    size_t dim = data[0].size();
    for (size_t i = 0; i < dim; ++i) {
        result.push_back(data[0][i]);
    }
    return result;
}

} // namespace algorithm
