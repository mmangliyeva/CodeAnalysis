fun ifStatementTest(a: Int, b: Int): Int {
    // if in a comment is not counted
    if (a > b) {
        return a
    }
    return b;
}

fun ifElseStatementTest(a: Int, b: Int): Int {
    if (a > b) {
        return a
    } else {
        return b;
    }
}