//
// Created by wang songye on 2020/8/2.
//

#include "Parent.h"
#include <iostream>
using namespace std;
void Parent::test() {
  cout << "parent test" << endl;
}

void Parent1::test() {
  cout << "parent1 test" << endl;
}

void Child::test() {
  Parent::test();
  Parent1::test();
  cout << "child test" << endl;
}