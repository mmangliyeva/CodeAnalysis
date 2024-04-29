package org.example.analyzers
import org.example.CodeAnalysis
import java.io.File

interface CodeAnalyzer {
    fun analyze(file: File): CodeAnalysis
}