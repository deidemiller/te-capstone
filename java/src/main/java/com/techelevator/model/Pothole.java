package com.techelevator.model;

import java.math.BigDecimal;
import java.util.Date;

public class Pothole {
    private int potholeId;
    private Date dateReported;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private String imageUrl;
    private String crossStreet1;
    private String crossStreet2;
    private String contactName;
    private String contactEmail;
    private String contactPhone;
    private boolean isPending;
    private String severity;
    private String repairStatus;
    private Date repairDate;
    private boolean isInspected;

    public boolean isInspected() {
        return isInspected;
    }

    public void setInspected(boolean inspected) {
        isInspected = inspected;
    }

    public String getRepairStatus() {
        return repairStatus;
    }

    public void setRepairStatus(String repairStatus) {
        this.repairStatus = repairStatus;
    }

    public Date getRepairDate() {
        return repairDate;
    }

    public void setRepairDate(Date repairDate) {
        this.repairDate = repairDate;
    }

    public boolean isPending() {
        return isPending;
    }

    public void setPending(boolean pending) {
        isPending = pending;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public String getCrossStreet1() {
        return crossStreet1;
    }

    public void setCrossStreet1(String crossStreet1) {
        this.crossStreet1 = crossStreet1;
    }

    public String getCrossStreet2() {
        return crossStreet2;
    }

    public void setCrossStreet2(String crossStreet2) {
        this.crossStreet2 = crossStreet2;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public int getPotholeId() {
        return potholeId;
    }

    public void setPotholeId(int potholeId) {
        this.potholeId = potholeId;
    }

    public Date getDateReported() {
        return dateReported;
    }

    public void setDateReported(Date dateReported) {
        this.dateReported = dateReported;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
