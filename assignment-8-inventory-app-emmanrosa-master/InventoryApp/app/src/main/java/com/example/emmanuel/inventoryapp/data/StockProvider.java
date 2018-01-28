package com.example.emmanuel.inventoryapp.data;

/**
 * Created by emmanuel on 11/6/17.
 */

public class StockProvider {

    private final String name;
    private final String price;
    private final int quantity;
    private final String image;

    public StockProvider(String mname, String mprice, int mquantity, String mimage)
    {
        name = mname;
        price = mprice;
        quantity = mquantity;
        image = mimage;
    }

    public String getname() {
        return name;
    }

    public String getprice() {
        return price;
    }

    public int getquantity() {
        return quantity;
    }

    public String getimage() {return image;}

}
