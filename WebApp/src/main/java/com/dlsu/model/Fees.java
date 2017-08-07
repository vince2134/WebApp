package com.dlsu.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by avggo on 7/30/2017.
 */

@Entity
@Table(name = "application_fees")
public class Fees {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Integer appId;

    private Double localBusinessTax = 0.0;

    private Double taxOnDelivery = 0.0;

    private Double taxOnStorage = 0.0;

    private Double taxOnSignboard = 0.0;

    private Double mayorPermit = 0.0;

    private Double garbageCharges = 0.0;

    private Double deliveryPermit = 0.0;

    private Double sanitaryInspection = 0.0;

    private Double buildingInspection = 0.0;

    private Double electricalInspection = 0.0;

    private Double mechanicalInspection = 0.0;

    private Double plumbingInspection = 0.0;

    private Double signboardRenewal = 0.0;

    private Double signboardPermit = 0.0;

    private Double storageFlam = 0.0;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date barangayClearanceDate;

    private String barangayClearanceVerifier;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date locationClearanceDate;

    private String locationClearanceVerifier;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date healthClearanceDate;

    private String healthClearanceVerifier;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date occupancyPermitDate;

    private String occupancyPermitVerifier;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fireSafetyClearanceDate;

    private String fireSafetyClearanceVerifier;

    @Column(name = "others1_description")
    private String others1Description;

    @Column(name = "others1_office")
    private String others1Office;

    @Column(name = "others1_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String others1Date;

    @Column(name = "others1_verifier")
    private String others1Verifier;

    @Column(name = "others2_description")
    private String others2Description;

    @Column(name = "others2_office")
    private String others2Office;

    @Column(name = "others2_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String others2Date;

    @Column(name = "others2_verifier")
    private String others2Verifier;

    @Column(name = "others3_description")
    private String others3Description;

    @Column(name = "others3_office")
    private String others3Office;

    @Column(name = "others3_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String others3Date;

    @Column(name = "others3_verifier")
    private String others3Verifier;

    @Column(name = "others4_description")
    private String others4Description;

    @Column(name = "others4_office")
    private String others4Office;

    @Column(name = "others4_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String others4Date;

    @Column(name = "others4_verifier")
    private String others4Verifier;

    private String penalties;

    private String remarks;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAppId() {
        return appId;
    }

    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    public Double getLocalBusinessTax() {
        return localBusinessTax;
    }

    public void setLocalBusinessTax(Double localBusinessTax) {
        this.localBusinessTax = localBusinessTax;
    }

    public Double getTaxOnDelivery() {
        return taxOnDelivery;
    }

    public void setTaxOnDelivery(Double taxOnDelivery) {
        this.taxOnDelivery = taxOnDelivery;
    }

    public Double getTaxOnStorage() {
        return taxOnStorage;
    }

    public void setTaxOnStorage(Double taxOnStorage) {
        this.taxOnStorage = taxOnStorage;
    }

    public Double getTaxOnSignboard() {
        return taxOnSignboard;
    }

    public void setTaxOnSignboard(Double taxOnSignboard) {
        this.taxOnSignboard = taxOnSignboard;
    }

    public Double getMayorPermit() {
        return mayorPermit;
    }

    public void setMayorPermit(Double mayorPermit) {
        this.mayorPermit = mayorPermit;
    }

    public Double getGarbageCharges() {
        return garbageCharges;
    }

    public void setGarbageCharges(Double garbageCharges) {
        this.garbageCharges = garbageCharges;
    }

    public Double getDeliveryPermit() {
        return deliveryPermit;
    }

    public void setDeliveryPermit(Double deliveryPermit) {
        this.deliveryPermit = deliveryPermit;
    }

    public Double getSanitaryInspection() {
        return sanitaryInspection;
    }

    public void setSanitaryInspection(Double sanitaryInspection) {
        this.sanitaryInspection = sanitaryInspection;
    }

    public Double getBuildingInspection() {
        return buildingInspection;
    }

    public void setBuildingInspection(Double buildingInspection) {
        this.buildingInspection = buildingInspection;
    }

    public Double getElectricalInspection() {
        return electricalInspection;
    }

    public void setElectricalInspection(Double electricalInspection) {
        this.electricalInspection = electricalInspection;
    }

    public Double getMechanicalInspection() {
        return mechanicalInspection;
    }

    public void setMechanicalInspection(Double mechanicalInspection) {
        this.mechanicalInspection = mechanicalInspection;
    }

    public Double getPlumbingInspection() {
        return plumbingInspection;
    }

    public void setPlumbingInspection(Double plumbingInspection) {
        this.plumbingInspection = plumbingInspection;
    }

    public Double getSignboardRenewal() {
        return signboardRenewal;
    }

    public void setSignboardRenewal(Double signboardRenewal) {
        this.signboardRenewal = signboardRenewal;
    }

    public Double getSignboardPermit() {
        return signboardPermit;
    }

    public void setSignboardPermit(Double signboardPermit) {
        this.signboardPermit = signboardPermit;
    }

    public Double getStorageFlam() {
        return storageFlam;
    }

    public void setStorageFlam(Double storageFlam) {
        this.storageFlam = storageFlam;
    }

    public Date getBarangayClearanceDate() {
        return barangayClearanceDate;
    }

    public void setBarangayClearanceDate(Date barangayClearanceDate) {
        this.barangayClearanceDate = barangayClearanceDate;
    }

    public String getBarangayClearanceVerifier() {
        return barangayClearanceVerifier;
    }

    public void setBarangayClearanceVerifier(String barangayClearanceVerifier) {
        this.barangayClearanceVerifier = barangayClearanceVerifier;
    }

    public Date getLocationClearanceDate() {
        return locationClearanceDate;
    }

    public void setLocationClearanceDate(Date locationClearanceDate) {
        this.locationClearanceDate = locationClearanceDate;
    }

    public String getLocationClearanceVerifier() {
        return locationClearanceVerifier;
    }

    public void setLocationClearanceVerifier(String locationClearanceVerifier) {
        this.locationClearanceVerifier = locationClearanceVerifier;
    }

    public Date getHealthClearanceDate() {
        return healthClearanceDate;
    }

    public void setHealthClearanceDate(Date healthClearanceDate) {
        this.healthClearanceDate = healthClearanceDate;
    }

    public String getHealthClearanceVerifier() {
        return healthClearanceVerifier;
    }

    public void setHealthClearanceVerifier(String healthClearanceVerifier) {
        this.healthClearanceVerifier = healthClearanceVerifier;
    }

    public Date getOccupancyPermitDate() {
        return occupancyPermitDate;
    }

    public void setOccupancyPermitDate(Date occupancyPermitDate) {
        this.occupancyPermitDate = occupancyPermitDate;
    }

    public String getOccupancyPermitVerifier() {
        return occupancyPermitVerifier;
    }

    public void setOccupancyPermitVerifier(String occupancyPermitVerifier) {
        this.occupancyPermitVerifier = occupancyPermitVerifier;
    }

    public Date getFireSafetyClearanceDate() {
        return fireSafetyClearanceDate;
    }

    public void setFireSafetyClearanceDate(Date fireSafetyClearanceDate) {
        this.fireSafetyClearanceDate = fireSafetyClearanceDate;
    }

    public String getFireSafetyClearanceVerifier() {
        return fireSafetyClearanceVerifier;
    }

    public void setFireSafetyClearanceVerifier(String fireSafetyClearanceVerifier) {
        this.fireSafetyClearanceVerifier = fireSafetyClearanceVerifier;
    }

    public String getOthers1Description() {
        return others1Description;
    }

    public void setOthers1Description(String others1Description) {
        this.others1Description = others1Description;
    }

    public String getOthers1Office() {
        return others1Office;
    }

    public void setOthers1Office(String others1Office) {
        this.others1Office = others1Office;
    }

    public String getOthers1Date() {
        return others1Date;
    }

    public void setOthers1Date(String others1Date) {
        this.others1Date = others1Date;
    }

    public String getOthers1Verifier() {
        return others1Verifier;
    }

    public void setOthers1Verifier(String others1Verifier) {
        this.others1Verifier = others1Verifier;
    }

    public String getOthers2Description() {
        return others2Description;
    }

    public void setOthers2Description(String others2Description) {
        this.others2Description = others2Description;
    }

    public String getOthers2Office() {
        return others2Office;
    }

    public void setOthers2Office(String others2Office) {
        this.others2Office = others2Office;
    }

    public String getOthers2Date() {
        return others2Date;
    }

    public void setOthers2Date(String others2Date) {
        this.others2Date = others2Date;
    }

    public String getOthers2Verifier() {
        return others2Verifier;
    }

    public void setOthers2Verifier(String others2Verifier) {
        this.others2Verifier = others2Verifier;
    }

    public String getOthers3Description() {
        return others3Description;
    }

    public void setOthers3Description(String others3Description) {
        this.others3Description = others3Description;
    }

    public String getOthers3Office() {
        return others3Office;
    }

    public void setOthers3Office(String others3Office) {
        this.others3Office = others3Office;
    }

    public String getOthers3Date() {
        return others3Date;
    }

    public void setOthers3Date(String others3Date) {
        this.others3Date = others3Date;
    }

    public String getOthers3Verifier() {
        return others3Verifier;
    }

    public void setOthers3Verifier(String others3Verifier) {
        this.others3Verifier = others3Verifier;
    }

    public String getOthers4Description() {
        return others4Description;
    }

    public void setOthers4Description(String others4Description) {
        this.others4Description = others4Description;
    }

    public String getOthers4Office() {
        return others4Office;
    }

    public void setOthers4Office(String others4Office) {
        this.others4Office = others4Office;
    }

    public String getOthers4Date() {
        return others4Date;
    }

    public void setOthers4Date(String others4Date) {
        this.others4Date = others4Date;
    }

    public String getOthers4Verifier() {
        return others4Verifier;
    }

    public void setOthers4Verifier(String others4Verifier) {
        this.others4Verifier = others4Verifier;
    }

    public String getPenalties() {
        return penalties;
    }

    public void setPenalties(String penalties) {
        this.penalties = penalties;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Boolean verified(){
        return getBarangayClearanceDate() != null && getLocationClearanceDate() != null && getHealthClearanceDate() != null
                && getOccupancyPermitDate() != null && getFireSafetyClearanceDate()!= null && getBarangayClearanceVerifier() != null && getLocationClearanceVerifier() != null
                && getHealthClearanceVerifier() != null && getOccupancyPermitVerifier() != null && getFireSafetyClearanceVerifier() != null;
    }

    public Double getTotal(){
        Double total = 0.0;

        if(localBusinessTax != null)
            total += localBusinessTax;
        if(taxOnDelivery != null)
            total += taxOnDelivery;
        if(taxOnStorage != null)
            total += taxOnStorage;
        if(taxOnSignboard != null)
            total += taxOnSignboard;
        if(mayorPermit != null)
            total += mayorPermit;
        if(garbageCharges != null)
            total += garbageCharges;
        if(deliveryPermit != null)
            total += deliveryPermit;
        if(sanitaryInspection != null)
            total += sanitaryInspection;
        if(buildingInspection != null)
            total += buildingInspection;
        if(electricalInspection != null)
            total += electricalInspection;
        if(mechanicalInspection != null)
            total += mechanicalInspection;
        if(plumbingInspection != null)
            total += plumbingInspection;
        if(signboardRenewal != null)
            total += signboardRenewal;
        if(signboardPermit != null)
            total += signboardPermit;
        if(storageFlam != null)
            total += storageFlam;

        return total;
    }
}
