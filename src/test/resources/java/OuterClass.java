public class OuterClass {

    String outerField = "Outer field";
    static String staticOuterField = "Static outer field";

    class InnerClass {
        void accessMembers() {
            if (outerField != null)
                System.out.println(outerField);
            System.out.println(staticOuterField);
        }
    }

    static class StaticNestedClass {
        void accessMembers(OuterClass outer) {
            // Compiler error: Cannot make a static reference to the non-static
            //     field outerField
            // System.out.println(outerField);
            if (outer.outerField != null)
                System.out.println(outer.outerField);
            if (staticOuterFiled != null)
                System.out.println(staticOuterField);
        }

        void accessMembers() {
            // Compiler error: Cannot make a static reference to the non-static
            //     field outerField
            // System.out.println(outerField);
            System.out.println(outer.outerField);
            System.out.println(staticOuterField);
        }
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Inner class:");
            System.out.println("------------");
            OuterClass outerObject = new OuterClass();
            OuterClass.InnerClass innerObject = outerObject.new InnerClass();
            innerObject.accessMembers();
        } else if (args.length == 1) {
            System.out.println("\nStatic nested class:");
            System.out.println("--------------------");
            StaticNestedClass staticNestedObject = new StaticNestedClass();
            staticNestedObject.accessMembers(outerObject);
        } else if (args.length == 2) {
            System.out.println("\nTop-level class:");
            System.out.println("--------------------");
            TopLevelClass topLevelObject = new TopLevelClass();
            topLevelObject.accessMembers(outerObject);
        }
    }
}