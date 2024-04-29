fun whileLoopTest(x: Int) : Int {
    while (x > 0) {
        x--
    }
    do {
        val ++x
    } while (x < 10) // y is visible here!
    return x
}