package com.example.elearning;

public class Person {
    private String name;
    private String password;
    private String mail;

    public Person(String name, String password, String mail)
    {
        this.name = name;
        this.password = password;
        this.mail = mail;
    }
    public Person(String name, String pass){
        this.name = name;
        this.password = pass;
    }

    public Person(){};


    public String getMail() {
        return mail;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public void setMail(String mail) {
        this.mail = mail;
    }

}
