package com.dlsu.service;

import com.dlsu.model.BPApplication;
import com.dlsu.model.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by avggo on 7/27/2017.
 */

@Service("applicationService")
public class ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    public ArrayList<BPApplication> getAllApplications(){
        return applicationRepository.findAllApplications();
    }

    public ArrayList<BPApplication> getPendingApplications(){
        return applicationRepository.findPendingApplications();
    }

    public ArrayList<BPApplication> getApplicationsByInspectorId(Integer id){
        return applicationRepository.findByInspectorId(id);
    }

    public ArrayList<BPApplication> getProcessingApplications(){
        return applicationRepository.findProcessingApplications();
    }

    public ArrayList<BPApplication> getInspectingApplications(){
        return applicationRepository.findInspectingApplications();
    }

    public ArrayList<BPApplication> getInspectedApplications(){
        return applicationRepository.findInspectedApplications();
    }

    public ArrayList<BPApplication> getReadyForPaymentApplications(){
        return applicationRepository.getReadyForPaymentApplications();
    }

    public ArrayList<BPApplication> getApprovedApplications(){
        return applicationRepository.getApprovedApplications();
    }

    public ArrayList<BPApplication> getTreasuryApplications(){
        return applicationRepository.findTreasuryApplications();
    }
    public void createNewApplication(BPApplication app){
        applicationRepository.save(app);
    }

    public BPApplication findByReferenceNumber(Integer refNum){
        return applicationRepository.findByReferenceNumber(refNum);
    }

    public BPApplication findByIdNumber(Integer IdNum){
        return applicationRepository.findById(IdNum);
    }

    public ArrayList<BPApplication> getEncodedApplicationsByEmployee(Integer id, Date fromDate, Date toDate){
        return applicationRepository.getEncodedApplicationsByEmployee(id, fromDate, toDate);
    }

    public ArrayList<BPApplication> getAssessedApplicationsByEmployee(Integer id, Date fromDate, Date toDate){
        return applicationRepository.getAssessedApplicationsByEmployee(id, fromDate, toDate);
    }

    public ArrayList<BPApplication> getInspectedApplicationsByInspector(Integer id, Date fromDate, Date toDate){
        return applicationRepository.getInspectedApplicationsByInspector(id, fromDate, toDate);
    }

    public ArrayList<BPApplication> getProcessedApplications(Date fromDate, Date toDate){
        return applicationRepository.getProcessedApplications(fromDate, toDate);
    }

    public ArrayList<BPApplication> getReceivedApplications(Date fromDate, Date toDate){
        return applicationRepository.getReceivedApplications(fromDate, toDate);
    }
}
