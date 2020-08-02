//
// Created by wang songye on 2020/8/2.
//

#ifndef SINGLEINSTANCE__PARENT_H_
#define SINGLEINSTANCE__PARENT_H_
class Parent {
 public:
  void test();
};

class Parent1 {
 public:
  void test();
};

class Child : public Parent, public Parent1 {
 public :
  void test();
};
//私有继承，则所有Parent的所有方法都变成了私有
class Child1 : private Parent, Parent1 {

};

#endif //SINGLEINSTANCE__PARENT_H_
