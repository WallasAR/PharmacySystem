import java.util.ArrayList;
import java.util.List;

public class Sale {
    Pharmacy pharmacy;
    List<Drug> shoppingCart;

    public Sale(Pharmacy pharmacy){
        this.pharmacy = pharmacy;
        this.shoppingCart = new ArrayList<>();
    }

}
