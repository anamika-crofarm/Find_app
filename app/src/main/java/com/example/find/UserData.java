package com.example.find;

public class UserData {
    public UserData(){

    }

     String name, password, email, phno;

//    public UserData(String name, String password, String email, String phno, String radio) {
//    }

    public String getName() {
        return name;
    }

    public void setName() {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword() {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail() {
        this.email = email;
    }

    public String getPhno() {
        return phno;
    }

    public void setPhno(String phno) {
        this.phno = phno;
    }



    public  UserData(String name,String password,String email, String phno){
        this.name=name;
        this.password=password;
        this.email = email;
        this.phno= phno;
    }
}
