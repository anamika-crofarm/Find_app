package com.example.find;

public class businessdata {
    public businessdata(){

    }
    String name;
    String email;
    String password;
    String phno;
    String businessname;
    String address;
    String age;
    String category;
    String details;


    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }




    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }



    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }



    public String getPhno() {
        return phno;
    }
    public void setPhno(String phno) {
        this.phno = phno;
    }




    public String getBusinessname() {
        return businessname;
    }
    public void setBusinessname(String businessname) {
        this.businessname = businessname;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public String getAge() {
        return age;
    }
    public void setAge(String age) {
        this.age = age;
    }

    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }



    public  businessdata(String name,String password,String email,String phno, String age)
    {
        this.name=name;
        this.password=password;
        this.email = email;
        this.phno= phno;
        this.age = age;
    }
}
