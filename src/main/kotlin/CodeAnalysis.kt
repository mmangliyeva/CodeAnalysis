package org.example


import org.example.analyzers.CodeAnalyzer
import org.example.analyzers.JavaCodeAnalyzer
import org.example.analyzers.KotlinCodeAnalyzer
import java.io.File
import java.io.FileNotFoundException

class CodeAnalysis(val methodComplexities: Map<String, Int>) {
    val misnamedFunctions: List<String>
        get() {
            val res = mutableListOf<String>()
            methodComplexities.forEach { (methodName, _) ->
                val methodNameWithoutClass = methodName.substringAfterLast('.')
                val methodNameWithoutParams = methodNameWithoutClass.substringBefore('(')
                if (!methodNameWithoutParams.matches("[a-z][A-Za-z0-9]*".toRegex())) {
                    res.add(methodNameWithoutParams)
                }
            }
            return res
        }

    val misnamedFunctionRatio: Double
        get() {
            return if (methodComplexities.isEmpty()) 0.0
            else misnamedFunctions.size.toDouble() / methodComplexities.size.toDouble()
        }

    val mostComplexMethods: Map<String, Int>
        get() {
            val sortedEntries = methodComplexities.entries.sortedByDescending { it.value }.take(3)
            val sortedMap = LinkedHashMap<String, Int>()
            for ((key, value) in sortedEntries) {
                sortedMap[key] = value
            }
            return sortedMap
        }

    companion object {
        fun fromFile(path: String): CodeAnalysis {
            val file = File(path)
            if (!file.exists()) {
                throw FileNotFoundException("File not found at path $path")
            }
            return chooseCodeAnalyzer(path).analyze(file)
        }

        private fun chooseCodeAnalyzer(path: String): CodeAnalyzer {
            return when {
                path.endsWith(".java") -> {
                    JavaCodeAnalyzer()
                }

                path.endsWith(".kt") -> {
                    KotlinCodeAnalyzer()
                }

                else -> {
                    throw UnsupportedOperationException("Unsupported file extension: $path.")
                }
            }
        }
    }
}