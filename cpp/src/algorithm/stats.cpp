#include "metrics.h"
#include <cmath>
#include <algorithm>
#include <numeric>

namespace moto {
namespace cpp {

double calculateMean(const std::vector<double>& data) {
    if (data.empty()) return 0.0;
    double sum = std::accumulate(data.begin(), data.end(), 0.0);
    return sum / data.size();
}

double calculateStandardDeviation(const std::vector<double>& data) {
    if (data.size() < 2) return 0.0;
    double mean = calculateMean(data);
    double sq_sum = 0.0;
    for (const auto& v : data) {
        sq_sum += (v - mean) * (v - mean);
    }
    return std::sqrt(sq_sum / (data.size() - 1));
}

double calculateMedian(std::vector<double> data) {
    if (data.empty()) return 0.0;
    std::sort(data.begin(), data.end());
    size_t n = data.size();
    if (n % 2 == 0) return (data[n/2 - 1] + data[n/2]) / 2.0;
    return data[n/2];
}

std::vector<MetricResult> calculateMetrics(const std::vector<double>& data) {
    std::vector<MetricResult> results;
    MetricResult meanResult{};
    meanResult.label = "mean";
    meanResult.value = calculateMean(data);
    meanResult.timestamp = std::time(nullptr);
    results.push_back(meanResult);
    return results;
}

} // namespace cpp
} // namespace moto
