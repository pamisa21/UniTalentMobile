package com.example.unitalentv3;

public class UploadPoem {
    private String UPoem;
    private String Utitle;
    private String Uname;

    public UploadPoem() {
        //empty contructor
    }

    public UploadPoem(String title, String name, String poem) {

        if (title.trim().equals("")) {
            title = "Untitled";
        }

        if (name.trim().equals("")) {
            name = "Untitled";
        }

        if (poem.equals("")){

        }
        Utitle = title;
        Uname = name;
        UPoem = poem;
    }

    public String getUname() {
        return Uname;
    }

    public void setUname(String name) {
        Uname = name;
    }

    public String getUPoem(){
        return UPoem;
    }

    public void setUPoem(String poem) {
        UPoem = poem;
    }

    public String getUtitle(){
        return Utitle;
    }

    public void setUtitle(String title) {
        Utitle = title;
    }

}
