#include <iostream>
#include "func.h"

int func(void* a) {
  printf("Hello\n");
  return -1;
}

int func(void* pathName, int a) {
  printf("Hello1\n");
  return -1;
}

//指针函数，返回值为指针的函数
int* int_add_func(void* param) {
  printf("指针函数\n");
  int b = 10;
  int* p = &b;
  return p;
}

// 函数指针： 只想函数的指针变量，本质上是一个指针变量
// int (*f)(int x); //声明一个函数指针,f前面必须有*，且用()包括住，前面是返回值类型
// func;//将func函数的首地址赋给指针f

void (* funcp)();
void point_func() {
  printf("函数指针\n");
}

int main() {
  std::cout << "Hello, World!" << std::endl;
  int a = 1;
  func(&a);
  func(&a, a);

  int* pointer = int_add_func(&a);
  printf("pointer result:%d\n", *pointer);

  int (* f)(void*) = func;//将func的首地址赋值给f，f是一个函数指针
  f(&a);
  funcp = point_func;
  funcp();
  return 0;
}
