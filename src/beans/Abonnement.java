package beans;

public class Abonnement {
    private int id;
    private String artOfAbo;
    private int countOfMenus;
    private String artOfMenu;
    private double totalPrice;
    private boolean hasPaid;
    private int customerId;
    private String startDate;
    private String duration;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getArtOfAbo() {
        return artOfAbo;
    }

    public void setArtOfAbo(String artOfAbo) {
        this.artOfAbo = artOfAbo;
    }

    public int getCountOfMenus() {
        return countOfMenus;
    }

    public void setCountOfMenus(int countOfMenus) {
        this.countOfMenus = countOfMenus;
    }

    public String getArtOfMenu(){
        return artOfMenu;
    }

    public void setArtOfMenu(String artOfMenu) {
        this.artOfMenu = artOfMenu;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public boolean isHasPaid() {
        return hasPaid;
    }

    public void setHasPaid(boolean hasPaid) {
        this.hasPaid = hasPaid;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
