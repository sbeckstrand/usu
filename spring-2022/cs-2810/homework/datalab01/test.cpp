#include <iostream>
#include <bitset>

int greatestBitPos(int x) {
  int y = x;
  y = (y >> 1) | y;
  y = (y >> 2) | y;
  y = (y >> 4) | y;
  y = (y >> 8) | y;
  y = (y >> 16) | y;
  y = y & ((1 << 31 ) ^ (~y >> 1));
  return y;
}

int main() {
   int testNumber = 5;
    printf("testNumber: %s\n", std::bitset<32>(testNumber).to_string().c_str());
}



