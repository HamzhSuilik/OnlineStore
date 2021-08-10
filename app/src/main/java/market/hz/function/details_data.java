package market.hz.function;

import org.w3c.dom.Text;

import java.io.Serializable;

public class details_data implements Serializable {

    private String title;
    private String description;
    private String size_text;
    private String size_1;
    private String size_2;
    private String size_3;
    private String size_4;
    private String size_5;
    private String price;
    private String old_price;
    private int colors;


    public details_data(String title, String description, String size_text, String size_1, String size_2, String size_3, String size_4, String size_5, String price, String old_price, int colors) {
        this.title = title;
        this.description = description;
        this.size_text = size_text;
        this.size_1 = size_1;
        this.size_2 = size_2;
        this.size_3 = size_3;
        this.size_4 = size_4;
        this.size_5 = size_5;
        this.price = price;
        this.old_price = old_price;
        this.colors = colors;
    }


    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getSize_text() {
        return size_text;
    }

    public String getSize_1() {
        return size_1;
    }

    public String getSize_2() {
        return size_2;
    }

    public String getSize_3() {
        return size_3;
    }

    public String getSize_4() {
        return size_4;
    }

    public String getSize_5() {
        return size_5;
    }

    public String getPrice() {
        return price;
    }

    public String getOld_price() {
        return old_price;
    }

    public int getColors() {
        return colors;
    }
}
