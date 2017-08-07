package com.dlsu.model.repository;

import com.dlsu.model.Report;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

/**
 * Created by avggo on 7/26/2017.
 */
public interface ReportRepository extends CrudRepository<Report, Integer> {
    @Query("SELECT r " +
            "from Report r")
    ArrayList<Report> findAllReports();

    Report findById(Integer id);
}
