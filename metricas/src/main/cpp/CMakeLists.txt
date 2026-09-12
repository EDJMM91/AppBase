cmake_minimum_required(VERSION 3.22.1)
project("metricas")

add_library(
    metricas-native
    SHARED
    src/metrics/native-lib.cpp
    src/algorithm/stats.cpp
    src/algorithm/optimizer.cpp
)

find_library(log-lib log)

target_link_libraries(
    metricas-native
    ${log-lib}
)

include_directories(${CMAKE_SOURCE_DIR}/include)
