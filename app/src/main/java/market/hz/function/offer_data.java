package market.hz.function;

public class offer_data {

    private String image;
    private String text;
    private String price;
    private String old_price;
    private String id;


    public offer_data(String image, String text, String price, String old_price, String id) {
        this.image = image;
        this.text = text;
        this.price = price;
        this.old_price = old_price;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getOld_price() {
        return old_price;
    }

    public void setOld_price(String old_price) {
        this.old_price = old_price;
    }
}
