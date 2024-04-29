// passes
fun camelCaseTest(a: Int, b: Int): Int {
    if (a > b) {
        return a
    }
    return b;
}

// does not pass
fun PascalCaseTest(a: Int, b: Int): Int {
    if (a > b) {
        return a
    } else {
        return b;
    }
}

// does not pass
fun ALL_CAPS_TEST(a: Int): String {
    if (a % 3 == 0) {
        return "Fizz"
    } else if (a % 5 == 0) {
        return "Buzz";
    } else {
        return a.toString()
    }
}

// passes
fun aLtErNaTiNgCaPsTeSt(a: Int): String {
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

// passes
fun flatcasetest() {
    for (i in 1..3) {
        println(i)
    }
    for (i in 6 downTo 0 step 2) {
        println(i)
    }
}

// does not pass
fun UPPERCASETEST(a: Int, b: Int): Int {
    return a if (a > b) else b
}

// passes
fun snake_case_test(a: Int, b: Int): Int {
    when (x) {
        1 -> print("x == 1")
        2 -> print("x == 2")
        else -> {
            print("x is neither 1 nor 2")
        }
    }
}

// passes
fun camel_Snake_Case_Test(x: Int) : Int {
    while (x > 0) {
        x--
    }
    do {
        val ++x
    } while (x < 10)
    return x
}

// does not pass
fun Title_Case_Test(a: Int, b: Int): Int {
    if (a > b) {
        return a
    }
    return b;
}

// does not pass
fun _reserved_test(a: Int, b: Int): Int {
    if (a > b) {
        return a
    }
    return b;
}