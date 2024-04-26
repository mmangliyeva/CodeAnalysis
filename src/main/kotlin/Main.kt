package org.example
import com.github.javaparser.StaticJavaParser
import com.github.javaparser.ast.CompilationUnit
import com.github.javaparser.ast.body.MethodDeclaration
import com.github.javaparser.ast.visitor.VoidVisitorAdapter
import com.github.javaparser.ast.stmt.*
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths

fun main(args: Array<String>) {

    val directory = args[0]
    try {
        analyzeDirectory(directory)
    } catch (e: Exception) {
        println("Error: ${e.message}")
    }
}

fun analyzeDirectory(directory: String) {
    var totalMethods = 0
    var nonCompliantMethods = 0
    var methodComplexities = mutableMapOf<String, Int>()
    try {
        val kotlinFiles = mutableListOf<File>()
        Files.walk(Paths.get(directory))
            .filter { Files.isRegularFile(it) }
            .forEach { path ->
                if (path.toString().endsWith(".java")) {
                    analyzeJavaFile(path.toFile(), methodComplexities)
                } else if (path.toString().endsWith(".kt")) {
                    //analyzeKotlinFile(path.toFile(), methodComplexities)
                }
            }

    } catch (e: Exception) {
        println("Error reading directory: ${e.message}")
        return
    }

    methodComplexities.forEach { (methodName, complexity) ->
        totalMethods++
        if (!methodName.matches("[a-z][A-Za-z0-9]*".toRegex())) {
            nonCompliantMethods++
        }
    }

    val nonCompliantPercentage = (nonCompliantMethods.toDouble() / totalMethods) * 100
    println("Percentage of methods not adhering to naming convention: $nonCompliantPercentage%")

    methodComplexities.entries.sortedByDescending { it.value }
        .take(3)
        .forEach { entry -> println("Method: ${entry.key}, Complexity: ${entry.value}") }
}

fun analyzeJavaFile(file: File, methodComplexities: MutableMap<String, Int>) {
    val cu: CompilationUnit
    try {
        cu = StaticJavaParser.parse(file)
    } catch (e: Exception) {
        println("Error parsing file ${file.name}: ${e.message}")
        return
    }

    MethodComplexityVisitor().visit(cu, methodComplexities)
}



fun calculateCyclomaticComplexityJava(methodDeclaration: MethodDeclaration): Int {
    var complexity = 0 // Base complexity for each method
    methodDeclaration.body.ifPresent { body ->
        body.findAll(IfStmt::class.java).forEach { complexity++ }
        body.findAll(WhileStmt::class.java).forEach { complexity++ }
        body.findAll(ForStmt::class.java).forEach { complexity++ }
        body.findAll(ForEachStmt::class.java).forEach { complexity++ }
        body.findAll(SwitchStmt::class.java).forEach { complexity += it.entries.size }

    }
    return complexity
}




class MethodComplexityVisitor : VoidVisitorAdapter<MutableMap<String, Int>>() {
    override fun visit(methodDeclaration: MethodDeclaration, arg: MutableMap<String, Int>?) {
        val complexity = calculateCyclomaticComplexityJava(methodDeclaration)
        arg?.put(methodDeclaration.nameAsString, complexity)
    }
}
