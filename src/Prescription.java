import java.util.Date;
import java.util.List;

public class Prescription {
    // Atributes
    private int ID;
    private Date date;
    private Client patient;
    private List<Drug> prescriptMedications;

    // Methods for atributes(getters and setters)
    public int getID() {
        return ID;
    }
    public void setID(int ID) {
        this.ID = ID;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public Client getPatient() {
        return patient;
    }
    public void setPatient(Client patient) {
        this.patient = patient;
    }
    public List<Drug> getPrescriptMedications() {
        return prescriptMedications;
    }
    public void setPrescriptMedications(List<Drug> prescriptMedications) {
        this.prescriptMedications = prescriptMedications;
    }
}
