CC      = g++

FLAGS   = -std=c++11 -g -Wall -pedantic

main.out: main.cpp matrix.o twoOpt.o closestEdges.o savingsMethod.o
	$(CC) $(FLAGS) -o main.out main.cpp matrix.o twoOpt.o closestEdges.o savingsMethod.o

matrix.o: matrix.h matrix.cpp
	$(CC) $(FLAGS) -c matrix.cpp

twoOpt.o: matrix.o twoOpt.h twoOpt.cpp
	$(CC) $(FLAGS) -c twoOpt.cpp

closestEdges.o: matrix.o closestEdges.h closestEdges.cpp
	$(CC) $(FLAGS) -c closestEdges.cpp

savingsMethod.o: matrix.o closestEdges.o savingsMethod.h savingsMethod.cpp
	$(CC) $(FLAGS) -c savingsMethod.cpp

clean:
	rm -f *.o *.out
