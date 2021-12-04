# Travelling Salesperson Problem

This project aims to solve the travelling salesperson problem.

## Example input & output

Input will be given to the main method like this example:

```
10
95.0129 61.5432
23.1139 79.1937
60.6843 92.1813
48.5982 73.8207
89.1299 17.6266
76.2097 40.5706
45.6468 93.5470
1.8504 91.6904
82.1407 41.0270
44.4703 89.3650
```

Which will produce an output something like this:

```
0
8
5
4
3
9
6
2
1
7
```

You can see further sample input data in the `samples/` folder or run the
`create-input.py` script with your specified value to create new random inputs.

# Running the programs

There are two versions of the program, one in Java and one in C++. They are
similar with some small implementation differences. 

To run the C++ program:

1) Enter the `cpp` directory and run `make`.
2) Once you have your object files, choose a sample input file and pipe it into
the main object file: `cat samples/thousand | make.out`

To run the Java program:

1) Enter the 'java' directory and compile the Java program using your IDE of choice or manually e.g: `javac -d src/com/tsp/out src/com/tsp/*.java
`
2) Once you have your compiled Java class files, pipe a sample file into it as
you execute the main class e.g `cat samples/thousand | java -classpath
src/com/tsp/out/ com.tsp.Main`
3) You can optionally add `-v` as an argument parameter when calling the Java
main class, this will enable verbose mode in the application.
