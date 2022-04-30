package beans;

public class Abonnement {
    private int id;
    private String artOfAbo;
    private int countOfMenus;
    private int menuId;
    private double totalPrice;
    private boolean hasPaid;
    private String startDate;
    private String endDate;

    public int getId() {
        return id;
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

    public int getMenuId(){
        return menuId;
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

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
