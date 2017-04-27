Plagiarism Checker Command Line Program

Language: Java (JRE version: 1.8)

How to run:

0.  (Unzip the compressed file)
1.  cd <path>/<to>/<PlagiarismChecker/src/>
2. javac *.java (to build. It is already been build, though.)
3. java Driver <synonym file> <file1> <file2> <[OPTIONAL] N>

Returns: The percentage plagiarised from file2 in file1, according to N-Tuple comparison algorithm.


Assumptions:

-   Percentage rounded off to nearest integer
-   Not case-sensitive (Upper case and lower case treated as equal)
-   (Punctuations and non-alphabetic characters not considered)
-   Exceptions added where needed
-   In case of no command line arguments, program prints out user instructions, and exits.

NOTE: You may see input files in two locations. Different IDEs (and command line) calculated different paths.
