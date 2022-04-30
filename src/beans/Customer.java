package beans;

public class Customer extends Person {
    private int id;
    private int abonnementId;
    private String telephone;
    private String address;
    private String mailAddress;

    public int getId(){
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAbonnementId(){
        return abonnementId;
    }

    public void setAbonnementId(int abonnementId){
        this.abonnementId = abonnementId;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }
}
