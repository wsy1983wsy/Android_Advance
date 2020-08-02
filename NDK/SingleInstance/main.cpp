#include <iostream>
#include "Student.h"
#include "Test.h"
#include "Parent.h"
//函数模板  对应于java中的泛型方法
template<typename T>
T max(T i, T j) {
  return i > j ? i : j;
}
template<typename T>
T add(T i, T j) {
  return i + j;
}

//类模板  对应于java中的泛型类
template<class T, class E>
class Q {
 public:
  E test(T t, E e) {
    return t + e;
  }
};
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

  std::cout << add<float>(10, 15.5) << std::endl;

  Q<int, float> q;
  std::cout << q.test(10, 1.2) << std::endl;
  return 0;
}
