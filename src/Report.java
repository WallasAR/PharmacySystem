import java.util.Date;

public class Report {
    // Atributes
    private int ID;
    private Date data;
    private String ReportType;
    private String ReportData;

    // Methods for atributes(getters and setters)
    public int getID(){
        return ID;
    }
    public void setID(int id){
        this.ID = id;
    }
    public Date getData(){
        return data;
    }
    public void setData(Date data){
        this.data = data;
    }

    public String getReportType() {
        return ReportType;
    }

    public void setReportType(String reportType) {
        ReportType = reportType;
    }

    public String getReportData() {
        return ReportData;
    }

    public void setReportData(String reportData) {
        ReportData = reportData;
    }
}
