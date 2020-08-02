cmake_minimum_required(VERSION 3.17)
project(SingleInstance)

set(CMAKE_CXX_STANDARD 14)

add_executable(SingleInstance main.cpp Student.cpp Student.h Test.cpp Test.h)