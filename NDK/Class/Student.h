//
// Created by wang songye on 2020/8/2.
//

#ifndef CLASS__STUDENT_H_
#define CLASS__STUDENT_H_

class Student {
  //友元函数 可以访问Student的私有变量
  friend void test(Student* student);
  friend class Teacher;
  int i;
 public:
  Student();
  Student(int i, int j);
  void setJ(int j);
  int getJ();
  ~Student();//析构函数,系统会自动调用
 private:
  int j;
 protected:
  int k;
 public:
  int l;
};
//友元类，这样Teacher就可以访问Student的私有属性
class Teacher {
 public:
  void call(Student* student) {
    student->j = 11;
  }
};

#endif //CLASS__STUDENT_H_
