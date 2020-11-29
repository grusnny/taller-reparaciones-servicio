package com.tallerdevehiculos.reparaciones.entity;
import java.io.Serializable;
import java.util.List;
import java.util.Set;


public class Repair implements Serializable{

    private String rId;

    private String repairDate;

    private String repairBy;

    private String licensePlateVehicle;

    private String state;

    private List<String> listStates;

    private List<String> spareParts;

    private int cost;

    private List<Integer> partsCost;

    private int otherCosts;

    private String inCharge;

    public String getrId() {
        return rId;
    }

    public void setrId(String rId) {
        this.rId = rId;
    }

    public String getRepairDate() {
        return repairDate;
    }

    public void setRepairDate(String repairDate) {
        this.repairDate = repairDate;
    }

    public String getRepairBy() {
        return repairBy;
    }

    public void setRepairBy(String repairBy) {
        this.repairBy = repairBy;
    }

    public String getLicensePlateVehicle() {
        return licensePlateVehicle;
    }

    public void setLicensePlateVehicle(String licensePlateVehicle) {
        this.licensePlateVehicle = licensePlateVehicle;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<String> getListStates() {
        return listStates;
    }

    public void setListStates(List<String> listStates) {
        this.listStates = listStates;
    }

    public List<String> getSpareParts() {
        return spareParts;
    }

    public void setSpareParts(List<String> spareParts) {
        this.spareParts = spareParts;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public List<Integer> getPartsCost() {
        return partsCost;
    }

    public void setPartsCost(List<Integer> partsCost) {
        this.partsCost = partsCost;
    }

    public int getOtherCosts() {
        return otherCosts;
    }

    public void setOtherCosts(int otherCosts) {
        this.otherCosts = otherCosts;
    }

    public String getInCharge() {
        return inCharge;
    }

    public void setInCharge(String inCharge) {
        this.inCharge = inCharge;
    }
}


