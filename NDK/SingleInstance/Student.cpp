//
// Created by wang songye on 2020/8/2.
//

#include "Student.h"

Student* Student::instance = 0;
Student::Student() {
  this->i = 11;
}
Student* Student::getInstance() {
  if (!instance) {
    instance = new Student();
  }
  return instance;
}

int Student::getI() {
  return this->i;
}