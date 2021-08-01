package com.crdesigns.appedgenet;

public class InterestsUsers {
    private int id;
    private String iduser;
    private String interest;

    public InterestsUsers(){}

    public InterestsUsers(String iduser, String interest) {
        super();
        this.iduser = iduser;
        this.interest = interest;
    }
    public String getIduser(){
        return this.iduser;
    }
    public void setIduser(String iduser){this.iduser = iduser;}
    public String getInterest(){
        return this.interest;
    }
    public void setInterest(String interest){this.interest = interest;}
    public Integer getId(){
        return this.id;
    }
    public void setId(Integer id){
        this.id = id;
    }
    @Override
    public String toString() {
        return "InterestsUsers [id=" + id + ", interest=" + interest + ", iduser=" + iduser +"]";
    }
}
