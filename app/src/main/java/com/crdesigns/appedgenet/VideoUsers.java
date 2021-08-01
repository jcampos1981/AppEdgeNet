package com.crdesigns.appedgenet;

public class VideoUsers {
    private int id;
    private String url;
    private String url_edge;
    private String iduser;
    private String watched;

    public VideoUsers(){}

    public VideoUsers(String url, String url_edge, String iduser, String watched) {
        super();
        this.url = url;
        this.url_edge = url_edge;
        this.iduser = iduser;
        this.watched = watched;
    }
    public String getUrl(){
        return this.url;
    }
    public void setUrl(String url){
        this.url = url;
    }
    public String getUrl_edge(){
        return this.url_edge;
    }
    public void setUrl_edge(String url){
        this.url_edge = url_edge;
    }
    public String getIduser(){
        return this.iduser;
    }
    public void setIduser(String iduser){
        this.iduser = iduser;
    }
    public String getWatched(){
        return this.watched;
    }
    public void setWatched(String watched){
        this.watched = watched;
    }

    public Integer getId(){
        return this.id;
    }
    public void setId(Integer id){
        this.id = id;
    }
    @Override
    public String toString() {
        return "Interests [id=" + id + ", url=" + url + "]";
    }
}
