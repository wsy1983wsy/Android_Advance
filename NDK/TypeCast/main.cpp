#include <iostream>
#include "vector"
#include "set"
#include "map"

using namespace std;
int main() {
  const char* a = "123";
  char* b = const_cast<char*>(a);
  // 容器 包含在std 标准模板裤中
  // 序列式，关联式
  // 序列式，元素顺序排列与元素本身无关，只与添加顺序有关系
  // vector,list dequeue, queue,stack,priority queue

  // 声明包含有一个元素的vector，该元素的值为1
  vector<int> vector1(1);
  vector<int> vector2(6, 1);//6个元素，每个都是1
  vector<int> vector3(vector2);//由vector2创建vector3
  vector<int> vector4;//该vector没有任何元素，
  vector4.push_back(10);
  cout << "通过下标获取元素：" << vector4[0] << endl;
  vector1.front();

  //关联式 set map hashmap
  // set 集合 元素不可重复
  set<int> set1 = {1, 2, 3, 4};
  set1.insert(1);
  cout << "set size: " << set1.size() << endl;

  set<int>::iterator iterator = set1.begin();
  set1.end();//指向最后一个元素的下一个元素
  for (; iterator != set1.end(); iterator++) {
    cout << *iterator << endl;
  }
  //map
  map<int, string> map1;
  map<int, string> map2 = {{1, "A"}, {2, "B"}};
  map1.insert({1, "a"});

  return 0;
}
