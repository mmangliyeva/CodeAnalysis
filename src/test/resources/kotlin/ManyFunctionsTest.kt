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

fun ifElseIfElseStatementTest(a: Int): String {
    if (a % 3 == 0) {
        return "Fizz"
    } else if (a % 5 == 0) {
        return "Buzz";
    } else {
        return a.toString()
    }
}

fun nestedIfElseStatementTest(a: Int): String {
    if (a % 3 == 0) {
        if (a % 5) {
            return "FizzBuzz"
        } else {
            return "Fizz"
        }
    } else {
        if (a % 5 == 0) {
            return "Buzz";
        } else {
            return a.toString()
        }
    }
}