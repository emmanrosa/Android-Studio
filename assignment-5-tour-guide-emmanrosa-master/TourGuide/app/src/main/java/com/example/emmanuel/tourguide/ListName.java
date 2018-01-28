/**
 * Created by emmanuel on 9/30/17.
 */
package com.example.emmanuel.tourguide;

public class ListName {
    private Integer images;
    private String names;
    private String phones;
    private String addresss;

    public  ListName(Integer image, String name, String phone, String address){
    //public  ListName(String name, String address){
        images = image;
        names = name;
        phones = phone;
        addresss = address;
    }

    public Integer getImages() { return images;}
    public String  getNames() { return names;}
    public String  getPhones() { return phones;}
    public String  getAddresss() { return addresss;}
}
