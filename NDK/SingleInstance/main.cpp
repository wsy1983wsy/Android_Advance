#include <iostream>
#include "Student.h"
#include "Test.h"
#include "Parent.h"

int main() {
  Student* student = Student::getInstance();
  std::cout << student->getI() << std::endl;

  Test test1;
  test1.i = 10;
  Test test2;
  test2.i = 11;
  Test test3 = test1 + test2;
  std::cout << "operator " << test3.i << std::endl;

  Child child;
  child.test();

  Child1 child2;
  //child2.test();  //私有方法

  //多态 ，静态多态
  Parent* child3 = new Child();
  child3->test();

  Parent1* child4 = new Child();
  child4->test();

  return 0;
}
