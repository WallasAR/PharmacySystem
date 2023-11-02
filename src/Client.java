import java.util.List;

public class Client {
    // Atributes
    private int ID;
    private String name;
    private String address;
    private List<Sale> purchHistoric;
    private List<Prescription> prescriptions;

    // Methods for atributes(getters and setters)
    public int getID() {
        return ID;
    }
    public void setID(int ID) {
        this.ID = ID;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public List<Sale> getPurchHistoric() {
        return purchHistoric;
    }
    public void setPurchHistoric(List<Sale> purchHistoric) {
        this.purchHistoric = purchHistoric;
    }
    public List<Prescription> getPrescriptions() {
        return prescriptions;
    }
    public void setPrescriptions(List<Prescription> prescriptions) {
        this.prescriptions = prescriptions;
    }
}
