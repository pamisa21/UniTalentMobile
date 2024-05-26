package com.example.unitalentv3;

public class Upload {
    private String UimageUrl;
    private String Utitle;
    private String Uname;

    public Upload() {
        //empty contructor
    }

    public Upload(String title, String name, String imageUrl) {

        if (title.trim().equals("")) {
            title = "Untitled";
        }

        if (name.trim().equals("")) {
            name = "Untitled";
        }
        Utitle = title;
        Uname = name;
        UimageUrl = imageUrl;
    }

    public String getUname() {
        return Uname;
    }

    public void setUname(String name) {
        Uname = name;
    }

    public String getUimageUrl(){
        return UimageUrl;
    }

    public void setUimageUrl(String imageUrl) {
        UimageUrl = imageUrl;
    }

    public String getUtitle(){
        return Utitle;
    }

    public void setUtitle(String title) {
        Utitle = title;
    }

}
