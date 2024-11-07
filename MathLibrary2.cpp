// MathLibrary.cpp
#include <iostream>

extern "C" {
    __declspec(dllexport) int add(int a, int b) {
        return a + b;
    }
}


g++ -shared -o MathLibrary.dll MathLibrary.cpp -I/usr/lib/jvm/java-17-openjdk-amd64/include -I/usr/lib/jvm/java-17-openjdk-amd64/include/linux
g++ -shared -o MathLibrary.dll MathLibrary.cpp -I"C:\Program Files\Java\jdk-17\include" -I"C:\Program Files\Java\jdk-17\include\win32"
