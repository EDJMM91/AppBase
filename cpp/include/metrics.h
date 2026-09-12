#ifndef CPP_METRICS_H
#define CPP_METRICS_H

#include <vector>
#include <string>

namespace moto {
namespace cpp {

struct MetricResult {
    double value;
    std::string label;
    long long timestamp;
};

std::vector<MetricResult> calculateMetrics(const std::vector<double>& data);
double calculateMean(const std::vector<double>& data);
double calculateStandardDeviation(const std::vector<double>& data);
double calculateMedian(std::vector<double> data);

} // namespace cpp
} // namespace moto

#endif
