package com.moto.project.metricas

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MetricasManager @Inject constructor() {

    private val _metricsState = MutableStateFlow<MetricsState>(MetricsState.Idle)
    val metricsState: Flow<MetricsState> = _metricsState

    external fun nativeCalculateMean(data: DoubleArray): Double
    external fun nativeCalculateStdDev(data: DoubleArray): Double
    external fun nativeOptimize(data: DoubleArray, learningRate: Double, iterations: Int): DoubleArray

    init {
        System.loadLibrary("metricas-native")
    }

    fun processMetrics(data: List<Double>): MetricResult {
        val array = data.toDoubleArray()
        val mean = nativeCalculateMean(array)
        val stdDev = nativeCalculateStdDev(array)
        _metricsState.value = MetricsState.Ready(mean, stdDev)
        return MetricResult(mean = mean, stdDev = stdDev)
    }
}

data class MetricResult(
    val mean: Double = 0.0,
    val stdDev: Double = 0.0,
    val label: String = "metricas"
)

enum class MetricsState {
    Idle,
    Computing,
    Ready(Double = 0.0, Double = 0.0),
    Error(String? = null)
}
