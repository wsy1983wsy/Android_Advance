//
// Created by wang songye on 2020/8/2.
//

#include "Test.h"

Test Test::operator+(Test& t) {
  Test temp;
  temp.i = this->i + t.i;
  return temp;
}