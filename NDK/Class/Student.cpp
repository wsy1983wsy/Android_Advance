//
// Created by wang songye on 2020/8/2.
//

#include <iostream>
#include "Student.h"
using namespace std;
void test(Student* student) {
  student->j = 10;
}

Student::Student() {
  std::cout << "I am a student" << endl;
}

Student::Student(int i, int j) : i(i), j(j) {
  this->i = i;
  this->j = j;
}
void Student::setJ(int j) {
  this->j = j;
}
int Student::getJ() {
  return this->j;
}

Student::~Student() {
  std::cout << "析构函数" << endl;
}