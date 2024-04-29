package org.example.analyzers
import org.example.CodeAnalysis
import org.example.visitors.KotlinFunctionVisitor
import org.jetbrains.kotlin.cli.jvm.compiler.EnvironmentConfigFiles
import org.jetbrains.kotlin.cli.jvm.compiler.KotlinCoreEnvironment
import org.jetbrains.kotlin.com.intellij.openapi.util.Disposer
import org.jetbrains.kotlin.com.intellij.psi.PsiManager
import org.jetbrains.kotlin.com.intellij.testFramework.LightVirtualFile
import org.jetbrains.kotlin.config.CompilerConfiguration
import org.jetbrains.kotlin.idea.KotlinFileType
import java.io.File

class KotlinCodeAnalyzer : CodeAnalyzer {
    override fun analyze(file: File): CodeAnalysis {
        val code = file.readText();
        val visitor = KotlinFunctionVisitor()
        val disposable = Disposer.newDisposable()
        try {
            val environment = KotlinCoreEnvironment.createForProduction(
                disposable,
                CompilerConfiguration(),
                EnvironmentConfigFiles.JVM_CONFIG_FILES
            )
            val temp = LightVirtualFile("temp.kt", KotlinFileType.INSTANCE, code)
            PsiManager.getInstance(environment.project).findFile(temp)?.accept(visitor)
        } finally {
            disposable.dispose()
        }
        return CodeAnalysis(visitor.methodComplexities);
    }
}