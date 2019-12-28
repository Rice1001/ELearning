package com.example.elearning;

public class person {
    private String name;
    private String password;
    private String mail;

    public person(String name, String password, String mail)
    {
        this.name = name;
        this.password = password;
        this.mail = mail;
    }

    public person(){};


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
