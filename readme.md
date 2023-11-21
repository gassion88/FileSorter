# ExternalSort

This is a Java program that performs external sorting on a file.

## Description

The ExternalSort class contains a method externalSort which takes an input file, an output file, and a memory limit as parameters. The method reads the input file, divides it into chunks that can fit into memory, sorts each chunk in memory, and then merges the sorted chunks into the output file.

## Usage

To use this program, follow these steps:

1. Clone the repository or download the ExternalSort.java file.
2. Compile the Java program using a Java compiler.
3. Run the compiled program with the following command: java ExternalSort.

Make sure to provide the input file name, output file name, and memory limit as command line arguments.

Example usage: java ExternalSort input.txt output.txt 1000000

## Dependencies

This program does not have any external dependencies.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for more information.