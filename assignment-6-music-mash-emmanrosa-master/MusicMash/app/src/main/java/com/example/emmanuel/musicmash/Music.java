package com.example.emmanuel.musicmash;

/**
 * Created by emmanuel on 10/5/17.
 */

public class Music {

    private Integer images;
    private String names;
    private String titles;
    private String youtubes;

    public  Music(Integer image, String name, String title,String youtube){

        images = image;
        names = name;
        titles = title;
        youtubes = youtube;

    }

    public Integer getImages() { return images;}
    public String  getNames() { return names;}
    public String  getTitles() { return titles;}
    public String  getYoutubes() {return youtubes;}

}
