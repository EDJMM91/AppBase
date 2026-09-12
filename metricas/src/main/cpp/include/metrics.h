#ifndef METRICAS_H
#define METRICAS_H

#include <string>
#include <vector>

namespace metrics {

struct MetricResult {
    double value;
    std::string label;
    long long timestamp;
};

std::vector<MetricResult> calculateMetrics(const std::vector<double>& data);
double calculateStandardDeviation(const std::vector<double>& data);
double calculateMean(const std::vector<double>& data);
double calculateMedian(std::vector<double> data);
double calculatePercentile(std::vector<double> data, double percentile);

} // namespace metrics

#endif // METRICAS_H
