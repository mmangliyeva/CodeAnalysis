package org.example


fun main(args: Array<String>) {
    if (args.isEmpty()) {
        println("Error: no path specified")
        return
    }
    val path = args[0]
    val analysis = CodeAnalysis.fromFile(path)
    println("The most complex methods are:")
    for ((functionName, complexity) in analysis.mostComplexMethods) {
        println("'$functionName' with $complexity statements")
    }
    println("The ratio of misnamed function is ${analysis.misnamedFunctionRatio}")
    for (functionName in analysis.misnamedFunctions) {
        println("The function name '$functionName' does not match camelCase")
    }
}
