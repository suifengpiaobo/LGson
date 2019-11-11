package com.leju.lgson;

import java.util.List;

/**
 * Description
 *
 * @author: zxl
 * create on 2019-11-11 14:30.
 */
public class Student {
    public String name;
    public int age;

    public List<String> classes;

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", classes=" + classes +
                '}';
    }
}
