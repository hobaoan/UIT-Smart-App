package com.example.uitsmart.Model;

public class User {
    public static int ID;
    public static String name;
    public static String passWord;
    public static String classU;

    public User(int ID, String name, String passWord, String classU) {
        this.ID = ID;
        this.name = name;
        this.passWord = passWord;
        this.classU = classU;
    }

    public static int getID() {
        return ID;
    }

    public static void setID(int ID) {
        User.ID = ID;
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        User.name = name;
    }

    public static String getPassWord() {
        return passWord;
    }

    public static void setPassWord(String passWord) {
        User.passWord = passWord;
    }

    public static String getClassU() {
        return classU;
    }

    public static void setClassU(String classU) {
        User.classU = classU;
    }
}
