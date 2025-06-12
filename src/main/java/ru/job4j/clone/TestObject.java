package ru.job4j.clone;

public class TestObject implements Cloneable {
    int num;

    @Override
    protected TestObject clone() throws CloneNotSupportedException {
        return (TestObject) super.clone();
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        TestObject testObj1 = new TestObject();
        testObj1.num = 5;

        TestObject testObj2 = testObj1.clone();
        testObj2.num = 10;

        System.out.println(testObj1.num);
        System.out.println(testObj2.num);

    }
}
