#include "metrics.h"
#include <vector>
#include <cmath>
#include <algorithm>
#include <numeric>

namespace moto {
namespace cpp {

std::vector<double> gradientDescent(
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

} // namespace cpp
} // namespace moto
