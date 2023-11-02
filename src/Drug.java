public class Drug {
    // Atribures
    private int ID;
    private String name;
    // public String drugLabel;
    private String description;
    private float price;
    private int stock;

    // Methods for atributes(getters and setters)
    public int getID(){
        return ID;
    }
    public void setID(int id){
        this.ID = id;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public String setDescription(){
        return description;
    }
    public void getDescription(String description){
        this.description = description;
    }
    public float getPrice(){
        return price;
    }
    public void setPrice(float price){
        this.price = price;
    }
    public int getStock() {
        return stock;
    }
    public void setStock(int stock){
        this.stock = stock;
    }
}
