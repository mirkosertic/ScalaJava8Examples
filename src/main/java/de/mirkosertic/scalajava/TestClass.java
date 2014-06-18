package de.mirkosertic.scalajava;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestClass {

    public void iterateTest1() {
        List<String> theData = Arrays.asList(new String[] {"A", "B", "C"});
        Consumer<String> theCons = System.out::println;
        theData.stream().map(T -> "First "+T).forEach(theCons);
    }

    public void filterTestWithPredicate() {
        List<String> theData = Arrays.asList(new String[] {"A", "B", "C"});
        Consumer<String> theCons = System.out::println;
        theData.stream().filter(T -> T.startsWith("A")).forEach(theCons);
    }

    public void composeTest() {
        Function<String, String> theFunction = (T) -> "First "+ T;
        theFunction = theFunction.andThen(T -> "FirstFirst " + T);
        System.out.println(theFunction.apply("Test"));
    }

    public void funTest1() {
        TestInterface1 theTestFun = T -> "First "+T;
        System.out.println(theTestFun.compute("Value"));
    }

    public void funTest2() {

        // Java8 Lambda
        TestInterface2 theTestFun = (T,V) -> "First "+T+V;


        // Old Style
        TestInterface2 theTestFun22 = new TestInterface2() {
            @Override
            public String compute(String aValue, int aOtherValue) {
                return "First "+aValue+aOtherValue;
            }
        };

        System.out.println(theTestFun.compute("First",17));
    }

    public TestInterface2 funTest3() {
        return (T,V) -> "Second "+T+V;
    }

    public void funTest4() {
        TestInterface2 theTestFun = (T, V) -> {
            return "Third "+T+V;
        };
        System.out.println(theTestFun.compute("First",17));
    }

    public void consumerTest() {
        List<String> theData = Arrays.asList(new String[] {"A", "B", "C"});
        theData.stream().forEach(T -> {System.out.println(T);});
        theData.stream().forEach(T -> System.out.println(T));
    }

    public void supplierTest() {
        // Infinite Stream
        Stream.generate(() -> Math.random()).limit(4).forEach(System.out::println);
        Stream.generate(Math::random).limit(4).forEach(System.out::println);
    }

    public void groupTest() {
        List<String> theData = Arrays.asList(new String[] {"Mirko", "Klaus", "Beat", "Marianne", "Bertram"});

        theData.stream().collect(Collectors.groupingBy(T -> T.substring(0, 1))).forEach((aKey, aValues) -> {
            System.out.println(aKey+" = "+aValues.size());
        });
    }

    public static void main(String[] args) {
        TestClass theClass = new TestClass();
        theClass.iterateTest1();
        theClass.filterTestWithPredicate();
        theClass.composeTest();
        theClass.funTest1();
        theClass.funTest2();
        theClass.funTest3().compute("Value1", 17);
        theClass.funTest4();
        theClass.consumerTest();
        theClass.supplierTest();
        theClass.groupTest();
    }
}
