import java.util.ArrayList;
import java.util.List;

public class Sale {
    public Pharmacy pharmacy;
    public List<Drug> shoppingCart;

    public Sale(Pharmacy pharmacy){
        this.pharmacy = pharmacy;
        this.shoppingCart = new ArrayList<>();
    }

}
