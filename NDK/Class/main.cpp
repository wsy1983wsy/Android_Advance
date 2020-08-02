#include <iostream>
#include "Student.h"

int main() {
  Student student;
  Student* student1 = new Student(10, 11);
  student1->setJ(12);
  std::cout << student1->getJ() << std::endl;
  test(&student);
  std::cout << "student:j:" << student.getJ() << std::endl;
  delete student1;
  return 0;
}
