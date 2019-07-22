cmake_minimum_required(VERSION 3.6.0)

add_library(system-properties
    SHARED

        src/main/cpp/system-properties.cpp)

find_library(log-lib
    log)

target_link_libraries(system-properties
    ${log-lib})