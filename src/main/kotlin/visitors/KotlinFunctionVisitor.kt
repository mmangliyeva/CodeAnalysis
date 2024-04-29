package org.example.visitors

import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.psi.KtNamedDeclaration
import org.jetbrains.kotlin.psi.KtNamedFunction
import org.jetbrains.kotlin.psi.KtTreeVisitorVoid
import org.jetbrains.kotlin.utils.join
import org.example.visitors.KotlinExpressionVisitor

class KotlinFunctionVisitor : KtTreeVisitorVoid() {
    val methodComplexities: MutableMap<String, Int> = mutableMapOf()

    override fun visitNamedFunction(function: KtNamedFunction) {
        super.visitNamedFunction(function)
        val visitor = KotlinExpressionVisitor()
        function.bodyExpression?.accept(visitor)
        methodComplexities[methodPathWithSignature(function)] = visitor.complexity
    }

    private fun methodPathWithSignature(function: KtNamedFunction): String {
        val path = methodPath(function)
        path.add(methodSignature(function))
        return join(path, ".")
    }

    private fun methodSignature(function: KtNamedFunction): String {
        val methodName = function.name
        val parameters = function.valueParameters.joinToString(", ") { "${it.typeReference?.text}" }
        return "$methodName($parameters)"
    }

    private fun methodPath(function: KtNamedFunction): MutableList<String> {
        val pathBuilder = mutableListOf<String>()
        var parent = function.parent
        while (parent != null && parent !is KtFile) {
            if (parent is KtNamedDeclaration) {
                pathBuilder.add("${parent.fqName}")
            }
            parent = parent.parent
        }
        pathBuilder.reverse()
        return pathBuilder
    }

}