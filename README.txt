Author: Dhruv Luthra
-------------------------------------------------------------------------------
What: A HashMap implementation in Java written for the KPCB Engineering
fellows challenge question.
-------------------------------------------------------------------------------
Files included:

HashMap.java: The implementation of the HashMap

TestMap.java: A test class designed to test the implementation of HashMap using
randomly generated String keys and Object values

execute.sh: A shell script designed to simplify compiling and executing
-------------------------------------------------------------------------------
This HashMap implementation requires no additional packages other than a
standard Java compiler.
-------------------------------------------------------------------------------
HOW TO EXECUTE
A shell script is include to simplify executing and testing the programs. Once
in the appropriate directory, containing both HashMap.java and TestMap.java,
both classes can be compiled using the following command line function:

'sh execute.sh'

The script will compile the HashMap and TestMap classes and then execute the
main method of the TestMap program.
-------------------------------------------------------------------------------
NOTES ON IMPLEMENTATION

The HashMap was implemented using an array of linked lists.

More information on the various parts of the HashMap and TestMap classes can be
found in the various comments associated with each method / critical lines of
code. 
