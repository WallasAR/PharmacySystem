package com.table.view;

public class RecordTable {
    private int idR;
    private String userR;
    private String medicineR;
    private int amountR;
    private float priceR;
    private String dateR;

    public RecordTable(int idR, String userR, String medicineR, int amountR, float priceR, String dateR) {
        this.idR = idR;
        this.userR = userR;
        this.medicineR = medicineR;
        this.amountR = amountR;
        this.priceR = priceR;
        this.dateR = dateR;
    }

    public int getIdR() {
        return idR;
    }

    public void setIdR(int idR) {
        this.idR = idR;
    }

    public String getUserR() {
        return userR;
    }

    public void setUserR(String userR) {
        this.userR = userR;
    }

    public String getMedicineR() {
        return medicineR;
    }

    public void setMedicineR(String medicineR) {
        this.medicineR = medicineR;
    }

    public int getAmountR() {
        return amountR;
    }

    public void setAmountR(int amountR) {
        this.amountR = amountR;
    }

    public float getPriceR() {
        return priceR;
    }

    public void setPriceR(float priceR) {
        this.priceR = priceR;
    }

    public String getDateR() {
        return dateR;
    }

    public void setDateR(String dateR) {
        this.dateR = dateR;
    }
}
