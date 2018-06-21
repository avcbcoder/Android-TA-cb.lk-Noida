package com.av.lec3_lists_adapters;

import java.util.Random;

/**
 * Created by Ankit on 21-06-2018.
 */

public class Student {
    String name;
    int age, marks;
    static Random r = new Random();

    public Student(String name, int age, int marks) {
        this.name = name;
        this.age = age;
        this.marks = marks;
    }

    Student() {
        name = "";
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getMarks() {
        return marks;
    }

    public static Student newStudent() {
        String s = "";
        for (int i = 0; i < 10; i++) {
            s += (char) (r.nextInt() + 'a');
        }
        Student ns = new Student(s, r.nextInt(30), r.nextInt(100));
        return ns;
    }
}
