class Example {

    // Method with low complexity
    public void simpleMethod() {
        System.out.println("while this method contains words such as if else while and for, this method should be simple.");
    }

    // Method with medium complexity
    public void MediumComplexityMethod(int x, int y) {
        for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <= 10; j++) {
                System.out.print(i * j + "\t");
            }
            System.out.println();
        }

        // Nested if statements to determine a student's grade
        int score = 85;
        if (score >= 90) {
            if(score>95) {
                System.out.println("Grade: A");
            }
            else{
                System.out.println("Grade: AB");
            }
        } else {
            if (score >= 80) {
                System.out.println("Grade: B");
            } else {
                if (score >= 70) {
                    System.out.println("Grade: C");
                } else {
                    if (score >= 60) {
                        System.out.println("Grade: D");
                    } else {
                        System.out.println("Grade: F");
                    }
                }
            }
        }
    }

    // Method with high complexity
    public void highComplexityMethod(int[] numbers) {
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] % 2 == 0) {
                System.out.println(numbers[i] + " is even.");
            } else {
                System.out.println(numbers[i] + " is odd.");
            }
        }
    }

    public void methodWithKeyWordsInString() {
        int start=0;
        int end=100;
        for (int i = start; i <= end; i++) {
            if (i % 3 == 0 && i % 5 == 0) {
                System.out.println("FizzBuzz");
            } else if (i % 3 == 0) {
                System.out.println("Fizz");
            } else if (i % 5 == 0) {
                System.out.println("Buzz");
            } else {
                System.out.println(i);
            }
        }
    }
}