package market.hz.function;

public class main_adapter_data {

    private String image;
    private String text;
    private String id;
    private String rank;

    public main_adapter_data(String image, String text, String id, String rank) {
        this.image = image;
        this.text = text;
        this.id = id;
        this.rank = rank;
    }


    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
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
}
