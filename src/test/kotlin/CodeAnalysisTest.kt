import org.example.CodeAnalysis
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import kotlin.math.abs
import kotlin.test.assertTrue

class CodeAnalysisTest {

    @Test
    fun ifStatementTest() {
        val filePath = "src/test/resources/kotlin/IfStatementTest.kt"
        val analysis = CodeAnalysis.fromFile(filePath)
        assertEquals(1, analysis.methodComplexities["ifStatementTest(Int, Int)"])
        assertTrue(analysis.misnamedFunctions.isEmpty())
        assertEquals(linkedMapOf("ifStatementTest(Int, Int)" to 1), analysis.mostComplexMethods)

    }

    @Test
    fun ifElseStatementTest() {
        val filePath = "src/test/resources/kotlin/IfElseStatementTest.kt"
        val analysis = CodeAnalysis.fromFile(filePath)
        assertEquals(1, analysis.methodComplexities["ifElseStatementTest(Int, Int)"])
        assertTrue(analysis.misnamedFunctions.isEmpty())
    }

    @Test
    fun ifElseIfElseStatementTest() {
        val filePath = "src/test/resources/kotlin/IfElseIfElseStatementTest.kt"
        val analysis = CodeAnalysis.fromFile(filePath)
        assertEquals(2, analysis.methodComplexities["ifElseIfElseStatementTest(Int)"])
        assertTrue(analysis.misnamedFunctions.isEmpty())
    }

    @Test
    fun nestedIfElseStatementTest() {
        val filePath = "src/test/resources/kotlin/NestedIfElseStatementTest.kt"
        val analysis = CodeAnalysis.fromFile(filePath)
        assertEquals(3, analysis.methodComplexities["nestedIfElseStatementTest(Int)"])
        assertTrue(analysis.misnamedFunctions.isEmpty())
    }

    @Test
    fun nestedFunctionTest() {
        val filePath = "src/test/resources/kotlin/NestedFunctionTest.kt"
        val analysis = CodeAnalysis.fromFile(filePath)
        assertEquals(3, analysis.methodComplexities["outerFunction(Double)"])
        assertTrue(analysis.misnamedFunctions.isEmpty())
    }

    @Test
    fun nestedFunctionWithNameClashTest() {
        val filePath = "src/test/resources/kotlin/NestedFunctionWithNameClashTest.kt"
        val analysis = CodeAnalysis.fromFile(filePath)
        val mostComplexMethods = analysis.mostComplexMethods
        val expected = linkedMapOf(
            "outerFunction(Double)" to 3,
            "innerFunction(Double)" to 2,
            "outerFunction.innerFunction(Double)" to 1
        )
        assertEquals(expected, mostComplexMethods)
        assertTrue(analysis.misnamedFunctions.isEmpty())
    }

    @Test
    fun methodTest() {
        val filePath = "src/test/resources/kotlin/MethodTest.kt"
        val analysis = CodeAnalysis.fromFile(filePath)
        val functionComplexities = analysis.methodComplexities
        assertEquals(1, functionComplexities["MaxCalculator.max(Int, Int)"])
        assertEquals(1, functionComplexities["MinCalculator.min(Int, Int)"])
        assertTrue(analysis.misnamedFunctions.isEmpty())
    }

    @Test
    fun whenExpressionTest() {
        val filePath = "src/test/resources/kotlin/WhenExpressionTest.kt"
        val analysis = CodeAnalysis.fromFile(filePath)
        assertEquals(3, analysis.methodComplexities["whenExpressionTest(Int, Int)"])
        assertTrue(analysis.misnamedFunctions.isEmpty())
    }

    @Test
    fun forLoopTest() {
        val filePath = "src/test/resources/kotlin/ForLoopTest.kt"
        val analysis = CodeAnalysis.fromFile(filePath)
        assertEquals(2, analysis.methodComplexities["forLoopTest()"])
        assertTrue(analysis.misnamedFunctions.isEmpty())
    }

    @Test
    fun ifElseExpressionTest() {
        val filePath = "src/test/resources/kotlin/IfElseExpressionTest.kt"
        val analysis = CodeAnalysis.fromFile(filePath)
        assertEquals(1, analysis.methodComplexities["ifElseExpressionTest(Int, Int)"])
        assertTrue(analysis.misnamedFunctions.isEmpty())
    }

    @Test
    fun whileLoopTest() {
        val filePath = "src/test/resources/kotlin/WhileLoopTest.kt"
        val analysis = CodeAnalysis.fromFile(filePath)
        assertEquals(2, analysis.methodComplexities["whileLoopTest(Int)"])
        assertTrue(analysis.misnamedFunctions.isEmpty())
    }

    @Test
    fun misnamedFunctionTest() {
        val filePath = "src/test/resources/kotlin/MisnamedFunctionTest.kt"
        val analysis = CodeAnalysis.fromFile(filePath)
        val misnamedFunctions = analysis.misnamedFunctions
        val expectedList = listOf(
            "PascalCaseTest",
            "ALL_CAPS_TEST",
            "UPPERCASETEST",
            "snake_case_test",
            "camel_Snake_Case_Test",
            "Title_Case_Test",
            "_reserved_test"
        )
        val mostComplexMethods = analysis.mostComplexMethods
        assertEquals(expectedList, misnamedFunctions)
        val expected = linkedMapOf(
            "aLtErNaTiNgCaPsTeSt(Int)" to 3,
            "snake_case_test(Int, Int)" to 3,
            "ALL_CAPS_TEST(Int)" to 2
        )
        assertEquals(expected, mostComplexMethods)
        val expectedRatior = 0.7
        val actualRatio = analysis.misnamedFunctionRatio
        assertTrue(abs(expectedRatior-actualRatio) < 0.01)
    }

    @Test
    fun javaTest() {
        val filePath = "src/test/resources/java/Example.java"
        val codeComplexity = CodeAnalysis.fromFile(filePath)
        val functionComplexities = codeComplexity.methodComplexities
        assertEquals(0, functionComplexities["Example.simpleMethod()"])
        assertEquals(7, functionComplexities["Example.MediumComplexityMethod(int, int)"])
        assertEquals(2, functionComplexities["Example.highComplexityMethod(int[])"])
        assertEquals(4, functionComplexities["Example.methodWithKeyWordsInString()"])
    }

    @Test
    fun javaNestedClassTest() {
        val filePath = "src/test/resources/java/OuterClass.java"
        val codeComplexity = CodeAnalysis.fromFile(filePath)
        val functionComplexities = codeComplexity.methodComplexities
        assertEquals(1, functionComplexities["OuterClass.InnerClass.accessMembers()"])
        assertEquals(2, functionComplexities["OuterClass.StaticNestedClass.accessMembers(OuterClass)"])
        assertEquals(3, functionComplexities["OuterClass.main(String[])"])
    }

    @Test
    fun anonymousClassTest() {
        val filePath = "src/test/resources/java/AnonymousClassExample.java"
        val codeComplexity = CodeAnalysis.fromFile(filePath)
        val functionComplexities = codeComplexity.methodComplexities
        assertEquals(1, functionComplexities["AnonymousClassExample.start(Stage)"])
        assertEquals(0, functionComplexities["AnonymousClassExample.main(String[])"])
        assertEquals(2, functionComplexities.size)
    }

    @Test
    fun varargsTest() {
        val filePath = "src/test/resources/java/VarargsExample.java"
        val codeComplexity = CodeAnalysis.fromFile(filePath)
        val functionComplexities = codeComplexity.methodComplexities
        assertEquals(0, functionComplexities["VarargsExample.firstOfFirst(List<String>)"])
    }
}