#include <iostream>

typedef struct Student {
  int i;
  short j;
};
typedef union Data {
  int i;
  int j;
  float f;
  char str[20];
};

int main() {

  int arr[] = {100, 200, 300};
  int* p[3];//指针数组，数组中包含的数据是指针
  for (int i = 0; i < 3; i++) {
    p[i] = &arr[i];
  }
  printf("----操作后----\n");
  for (int i = 0; i < 3; i++) {
    printf("%d:%d\n", i, *p[i]);
  }
  struct Student student;
  student.i = 10;
  student.j = 5;
  printf("student: %i  %i\n", student.i, student.j);
  printf("student size: %d\n", sizeof(student));

  union Data data;
  data.i = 10;
  printf("data: %i\n", data.i);
  printf("data: %i\n", data.j);
  data.j = 11;
  printf("data: %i\n", data.i);
  printf("data: %f\n", data.j);
  return 0;
}
