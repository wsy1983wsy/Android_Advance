#include <iostream>
#include "Student.h"
#include "Test.h"

int main() {
  Student* student = Student::getInstance();
  std::cout << student->getI() << std::endl;

  Test test1;
  test1.i = 10;
  Test test2;
  test2.i = 11;
  Test test3 = test1 + test2;
  std::cout << "operator " << test3.i << std::endl;
  return 0;
}
