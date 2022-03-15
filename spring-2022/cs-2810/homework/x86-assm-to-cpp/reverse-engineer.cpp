#include<iostream>
using namespace std;

void whatDoIDo() {
    int s1 = 1;
    int s2 = 2;
    int s3 = 3;
    int s4 = 4;
    int s5 = 5;

    int result = s2 + s1 + s3 + s4 + s5;

    cout << "Result: " << result << std::endl;

}

int main() {
    whatDoIDo(); // call the function
    return 0;
}