package beans;

public class Menu {
    private int id;
    private String content;
    private String artOfMenu;
    private double price;

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getArtOfMenu() {
        return artOfMenu;
    }

    public void setArtOfMenu(String artOfMenu) {
        this.artOfMenu = artOfMenu;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
