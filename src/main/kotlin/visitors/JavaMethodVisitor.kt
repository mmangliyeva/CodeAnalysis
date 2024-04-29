package org.example.visitors

import com.github.javaparser.ast.Node
import com.github.javaparser.ast.body.MethodDeclaration
import com.github.javaparser.ast.stmt.*
import com.github.javaparser.ast.visitor.VoidVisitorAdapter
import org.jetbrains.kotlin.utils.join
import kotlin.jvm.optionals.getOrNull

class JavaMethodVisitor : VoidVisitorAdapter<MutableMap<String, Int>>() {
    override fun visit(methodDeclaration: MethodDeclaration, arg: MutableMap<String, Int>?) {
        val complexity = calculateCyclomaticComplexityJava(methodDeclaration)
        arg?.put(methodPathWithSignature(methodDeclaration), complexity)
    }

    private fun calculateCyclomaticComplexityJava(methodDeclaration: MethodDeclaration): Int {
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

    private fun methodPathWithSignature(method: MethodDeclaration): String {
        val path = methodPath(method)
        path.add(methodSignature(method))
        return join(path, ".")
    }

    private fun methodSignature(method: MethodDeclaration): String {
        val methodName = method.nameAsString
        val parameters = method.parameters.joinToString(", ") { parameter ->
            parameter.typeAsString
        }
        return "$methodName($parameters)"
    }

    private fun methodPath(method: MethodDeclaration): MutableList<String> {
        val path = mutableListOf<String>()
        var parent: Node? = method.parentNode.getOrNull()
        while (parent != null) {
            if (parent is com.github.javaparser.ast.body.TypeDeclaration<*>) {
                path.add(parent.nameAsString)
            }
            parent = parent.parentNode.getOrNull()
        }
        path.reverse()
        return path
    }
}