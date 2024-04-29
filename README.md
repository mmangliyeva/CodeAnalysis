# Code Complexity Analyzer

## Overview
This Kotlin application analyzes Java and Kotlin files in a specified directory and calculates the methods/functions for complexity(Cyclomatic Complexity). As suggested, for simplicity I limited analysis to counting the number of conditional statements (if, switch, for, while, etc.). It also performs a simple code style check by ensuring that method names adhere to a specified naming convention. Additionally, it reports the percentage of methods that do not follow the naming convention.

## Instructions
1. Clone or download the repository to your local machine.

2. Ensure you have Kotlin installed on your system. If not, you can download it from https://kotlinlang.org/docs/command-line.html.

3. Open a terminal or command prompt and navigate to the directory where you cloned/downloaded the repository.

4. Compile the Kotlin code using the following Gradle command:


```bash
# Example installation commands
git clone https://github.com/your/repository.git
cd your-project-directory
./gradlew build
```

5. Run the application by executing the following command:
```bash
java -jar build/libs/CodeAnalysis-1.0.jar <directory>
```

Replace `<directory>` with the path to the directory containing the Java files you want to analyze.


## Approach and Design Decisions
- The application is implemented in Kotlin
- It utilizes the JavaParser and Kotlin.psi library to parse Java and Kotlin files and traverse the abstract syntax tree (AST) to analyze method/function complexity.
- Cyclomatic Complexity is calculated by counting decision points such as if statements, loops, and switch statements.
- Reporting and error handling mechanisms implemented to report code check results and catch errors