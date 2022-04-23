package beans;

public class abonnement {
    private String artOfAbo;
    private int countOfMenues;
    private double totalPrice;
    private boolean hasPaid;
    private String startDate;
    private String endDate;

    public String getArtOfAbo() {
        return artOfAbo;
    }

    public void setArtOfAbo(String artOfAbo) {
        this.artOfAbo = artOfAbo;
    }

    public int getCountOfMenues() {
        return countOfMenues;
    }

    public void setCountOfMenues(int countOfMenues) {
        this.countOfMenues = countOfMenues;
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
