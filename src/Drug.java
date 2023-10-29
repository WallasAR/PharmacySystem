public class Drug {
    String name;
    String drugLabel;
    String description;
    String leaflet;
    int validity; // date type!
    float price;
    int stock;

    public Drug(String name,String drugLabel, String description, String leaflet, int validity, float price, int stock) {
        this.name = name;
        this.drugLabel = drugLabel;
        this.description = description;
        this.leaflet = leaflet;
        this.validity = validity;
        this.price = price;
        this.stock = stock;
    }

}
