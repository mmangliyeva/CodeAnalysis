package org.example.visitors
import org.jetbrains.kotlin.psi.*

class KotlinExpressionVisitor : KtTreeVisitorVoid() {
    var complexity: Int = 0

    override fun visitIfExpression(expression: KtIfExpression) {
        super.visitIfExpression(expression)
        ++complexity
    }

    override fun visitWhileExpression(expression: KtWhileExpression) {
        super.visitWhileExpression(expression)
        ++complexity
    }

    override fun visitDoWhileExpression(expression: KtDoWhileExpression) {
        super.visitDoWhileExpression(expression)
        ++complexity
    }


    override fun visitForExpression(expression: KtForExpression) {
        super.visitForExpression(expression)
        ++complexity
    }

    override fun visitWhenExpression(expression: KtWhenExpression) {
        super.visitWhenExpression(expression)
        // Increment complexity by the number of entries in the when-expression
        complexity += expression.entries.size
    }
}