import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Sale {
    // Atributes
    private Date date;
    private List<Drug> productsSold;
    private Client client;
    private double total;

    // Methods for atributes(getters and setters)
    public Date getDate(){
        return date;
    }
    public void setDate(Date date){
        this.date = date;
    }
    public List<Drug> getProductsSold() {
        return productsSold;
    }
    public void setProductsSold(List<Drug> productsSold) {
        this.productsSold = productsSold;
    }
    public Client getClient() {
        return client;
    }
    public void setClient(Client client) {
        this.client = client;
    }
    public double getTotal() {
        return total;
    }
    public void setTotal(double total) {
        this.total = total;
    }
}
