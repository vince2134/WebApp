package com.dlsu.service;

import com.dlsu.model.Report;
import com.dlsu.model.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Created by avggo on 7/26/2017.
 */

@Service("reportService")
public class ReportService {

    @Autowired
    private ReportRepository reportRepository;

    public void createNewReport(Report report) {
        reportRepository.save(report);
    }

    public ArrayList<Report> getAllReports(){
        return reportRepository.findAllReports();
    }

    public Report findById(Integer id){
        return reportRepository.findById(id);
    }
}
