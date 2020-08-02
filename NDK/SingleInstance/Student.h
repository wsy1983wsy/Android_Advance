//
// Created by wang songye on 2020/8/2.
//

#ifndef SINGLEINSTANCE__STUDENT_H_
#define SINGLEINSTANCE__STUDENT_H_

class Student {
 private:
  static Student* instance;
 private:
  int i;
  Student();
 public:
  static Student* getInstance();
 public:
  int getI();
};

#endif //SINGLEINSTANCE__STUDENT_H_
