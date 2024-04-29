package org.example.analyzers

import com.github.javaparser.StaticJavaParser
import com.github.javaparser.ast.CompilationUnit
import com.github.javaparser.ast.body.MethodDeclaration
import com.github.javaparser.ast.stmt.*
import org.example.CodeAnalysis
import org.example.visitors.JavaMethodVisitor
import java.io.File

class JavaCodeAnalyzer : CodeAnalyzer {
    override fun analyze(file: File): CodeAnalysis {
        val methodComplexities = mutableMapOf<String, Int>()
        val cu: CompilationUnit = StaticJavaParser.parse(file)
        JavaMethodVisitor().visit(cu, methodComplexities)
        return CodeAnalysis(methodComplexities)
    }
}
