package com.dlsu.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;

/**
 * Created by avggo on 7/27/2017.
 */

@Entity
@Table(name = "applications")
public class BPApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Integer bpldId;

    private String status;

    private String applicantName;

    private String email;

    private Integer taxYear;

    private String applicationType;

    private Integer referenceNumber;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date applicationDate;

    private Integer registrationNumber;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date registrationDate;

    private String businessName;

    private Integer unitNumber;

    private String street;

    private String barangay;

    private String subdivision;

    private String city;

    private Integer step;

    private Integer inspectorId;

    private String inspectorName;

    private Integer assessBpld;

    private Integer assessEng;

    private Boolean c1, c2, c3, c4, c5, c6, c7, c8, c9, c10;

    @Column(name = "inspection_time")
    private String inspectionTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date inspectionDate;

    @Column(name = "assess_bpld_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date assessBpldDate;

    @Column(name = "assess_eng_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date assessEngDate;

    @Column(name = "encoded_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date encodedDate;

    @Column(name = "inspection_date_complete")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date inspectionDateComplete;

    @Column(name = "approval_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date approvalDate;

    public BPApplication(){
        this.status = "pending";
        this.step = 1;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBpldId() {
        return bpldId;
    }

    public void setBpldId(Integer bpldId) {
        this.bpldId = bpldId;
    }

    public Integer getTaxYear() {
        return taxYear;
    }

    public void setTaxYear(Integer taxYear) {
        this.taxYear = taxYear;
    }

    public String getApplicationType() {
        return applicationType;
    }

    public void setApplicationType(String applicationType) {
        this.applicationType = applicationType;
    }

    public Integer getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(Integer referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    public Date getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(Date applicationDate) {
        this.applicationDate = applicationDate;
    }

    public Integer getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(Integer registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public Integer getUnitNumber() {
        return unitNumber;
    }

    public void setUnitNumber(Integer unitNumber) {
        this.unitNumber = unitNumber;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getBarangay() {
        return barangay;
    }

    public void setBarangay(String barangay) {
        this.barangay = barangay;
    }

    public String getSubdivision() {
        return subdivision;
    }

    public void setSubdivision(String subdivision) {
        this.subdivision = subdivision;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getApplicantName() {
        return applicantName;
    }

    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getStep() {
        return step;
    }

    public void setStep(Integer step) {
        this.step = step;
    }

    public Integer getInspectorId() {
        return inspectorId;
    }

    public void setInspectorId(Integer inspectorId) {
        this.inspectorId = inspectorId;
    }

    public String getInspectionTime() {
        return inspectionTime;
    }

    public void setInspectionTime(String inspectionTime) {
        this.inspectionTime = inspectionTime;
    }

    public Date getInspectionDate() {
        return inspectionDate;
    }

    public void setInspectionDate(Date inspectionDate) {
        this.inspectionDate = inspectionDate;
    }

    public String getInspectorName() {
        return inspectorName;
    }

    public void setInspectorName(String inspectorName) {
        this.inspectorName = inspectorName;
    }

    public Boolean getC1() {
        return c1;
    }

    public void setC1(Boolean c1) {
        this.c1 = c1;
    }

    public Boolean getC2() {
        return c2;
    }

    public void setC2(Boolean c2) {
        this.c2 = c2;
    }

    public Boolean getC3() {
        return c3;
    }

    public void setC3(Boolean c3) {
        this.c3 = c3;
    }

    public Boolean getC4() {
        return c4;
    }

    public void setC4(Boolean c4) {
        this.c4 = c4;
    }

    public Boolean getC5() {
        return c5;
    }

    public void setC5(Boolean c5) {
        this.c5 = c5;
    }

    public Boolean getC6() {
        return c6;
    }

    public void setC6(Boolean c6) {
        this.c6 = c6;
    }

    public Boolean getC7() {
        return c7;
    }

    public void setC7(Boolean c7) {
        this.c7 = c7;
    }

    public Boolean getC8() {
        return c8;
    }

    public void setC8(Boolean c8) {
        this.c8 = c8;
    }

    public Boolean getC9() {
        return c9;
    }

    public void setC9(Boolean c9) {
        this.c9 = c9;
    }

    public Boolean getC10() {
        return c10;
    }

    public void setC10(Boolean c10) {
        this.c10 = c10;
    }

    public Boolean inspected(){
        return c1 && c2 && c3 && c4 && c5 && c6 && c7 && c8 && c9 && c10;
    }

    public Integer getAssessBpld() {
        return assessBpld;
    }

    public void setAssessBpld(Integer assessBpld) {
        this.assessBpld = assessBpld;
    }

    public Integer getAssessEng() {
        return assessEng;
    }

    public void setAssessEng(Integer assessEng) {
        this.assessEng = assessEng;
    }

    public Date getAssessBpldDate() {
        return assessBpldDate;
    }

    public void setAssessBpldDate(Date assessBpldDate) {
        this.assessBpldDate = assessBpldDate;
    }

    public Date getAssessEngDate() {
        return assessEngDate;
    }

    public void setAssessEngDate(Date assessEngDate) {
        this.assessEngDate = assessEngDate;
    }

    public Date getEncodedDate() {
        return encodedDate;
    }

    public void setEncodedDate(Date encodedDate) {
        this.encodedDate = encodedDate;
    }

    public Date getInspectionDateComplete() {
        return inspectionDateComplete;
    }

    public void setInspectionDateComplete(Date inspectionDateComplete) {
        this.inspectionDateComplete = inspectionDateComplete;
    }

    public Date getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(Date approvalDate) {
        this.approvalDate = approvalDate;
    }
}
