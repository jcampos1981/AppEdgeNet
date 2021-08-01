package com.crdesigns.appedgenet;

public class Users {
    private int id;
    private String email;
    private String password;
    private String points;

    public Users(){}

    public Users(String email, String password, String points) {
        super();
        this.email = email;
        this.password = password;
        this.points = points;
    }
    public String getEmail(){
        return this.email;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public String getPassword(){
        return this.password;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public String getPoints(){
        return this.points;
    }
    public void setPoints(String points){
        this.points = points;
    }
    public Integer getId(){
        return this.id;
    }
    public void setId(Integer id){
        this.id = id;
    }
    @Override
    public String toString() {
        return "Users [id=" + id + ", email=" + email + ", password=" + password + ", points=" + points + "]";
    }
}
