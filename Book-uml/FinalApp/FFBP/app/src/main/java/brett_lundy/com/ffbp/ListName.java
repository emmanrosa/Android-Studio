/**
 * Created by emmanuel on 9/30/17.
 */
package brett_lundy.com.ffbp;

public class ListName {
    private String images;
    private String title;
    private String cond;
    private String price;
    private String isbn;
    private String muserId;
    private String muserEmail;
    private String key;



    public  ListName() {}

    public  ListName(String mkey, String mtitle, String misbn, String mprice, String _cond, String muri, String _muserid, String _muserEmail) {
        key = mkey;

        title = mtitle;
        isbn = misbn;
        price = mprice;
        cond = _cond;
        images = muri;
        muserId = _muserid;
        muserEmail = _muserEmail;

    }

    public void setImages(String images) {
        this.images = images;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCond(String cond) {
        this.cond = cond;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setMUserId(String muserid) {
        this.muserId = muserid;
    }

    public void setMUserEmail(String museremail) {
        this.muserEmail = museremail;
    }

    public String getMUserEmail() { return muserEmail;}

    public String getKey() { return key;}

    public String getMUserId() { return muserId;}

    public String getImages() { return images;}

    public String  getTitle() { return title;}

    public String  getCond() { return cond;}

    public String  getPrice() { return price;}

    public String getIsbn() { return isbn;}
}
