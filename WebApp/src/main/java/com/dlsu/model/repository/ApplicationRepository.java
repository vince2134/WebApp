package com.dlsu.model.repository;

import com.dlsu.model.BPApplication;
import com.dlsu.model.QueryPerEmployee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by avggo on 7/27/2017.
 */
public interface ApplicationRepository extends CrudRepository<BPApplication, Integer> {

    @Query("SELECT a " +
            "from BPApplication a" +
            " order by a.applicationDate desc ")
    ArrayList<BPApplication> findAllApplications();

    @Query("SELECT a " +
            "from BPApplication a " +
            "where a.status = 'pending' " +
            "order by a.applicationDate desc ")
    ArrayList<BPApplication> findPendingApplications();

    @Query("SELECT a " +
            "from BPApplication a " +
            "where a.step > 1 AND a.step <> 6" +
            "order by a.applicationDate desc ")
    ArrayList<BPApplication> findProcessingApplications();

    @Query("SELECT a " +
            "from BPApplication a " +
            "where a.step = 2 " +
            "order by a.applicationDate desc ")
    ArrayList<BPApplication> findInspectingApplications();

    @Query("SELECT a " +
            "from BPApplication a " +
            "where a.step = 3 " +
            "order by a.applicationDate desc ")
    ArrayList<BPApplication> findInspectedApplications();

    @Query("SELECT a " +
            "from BPApplication a " +
            "where a.inspectorId = :id and (a.step = 3 or a.step = 2)" +
            "order by a.applicationDate desc ")
    ArrayList<BPApplication> findByInspectorId(@Param("id") Integer id);

    @Query("SELECT a " +
            "from BPApplication a " +
            "where a.step = 4 " +
            "order by a.applicationDate desc ")
    ArrayList<BPApplication> findTreasuryApplications();

    @Query("SELECT a " +
            "from BPApplication a " +
            "where a.step = 5 " +
            "order by a.applicationDate desc ")
    ArrayList<BPApplication> getReadyForPaymentApplications();

    @Query("SELECT a " +
            "from BPApplication a " +
            "where a.step = 6 " +
            "order by a.applicationDate desc ")
    ArrayList<BPApplication> getApprovedApplications();

    @Query("SELECT a " +
            "from BPApplication a " +
            "where a.bpldId = :id and a.encodedDate between :fromDate and :toDate " +
            "order by a.applicationDate desc ")
    ArrayList<BPApplication> getEncodedApplicationsByEmployee(@Param("id") Integer id, @Param("fromDate") Date fromDate, @Param("toDate") Date toDate);

    @Query("SELECT a " +
            "from BPApplication a " +
            "where (a.assessBpld = :id or a.assessEng = :id) and (a.assessBpldDate between :fromDate and :toDate or a.assessEngDate between :fromDate and :toDate)" +
            "order by a.applicationDate desc ")
    ArrayList<BPApplication> getAssessedApplicationsByEmployee(@Param("id") Integer id, @Param("fromDate") Date fromDate, @Param("toDate") Date toDate);

    @Query("SELECT a " +
            "from BPApplication a " +
            "where a.inspectorId = :id and a.inspectionDateComplete between :fromDate and :toDate " +
            "order by a.applicationDate desc ")
    ArrayList<BPApplication> getInspectedApplicationsByInspector(@Param("id") Integer id, @Param("fromDate") Date fromDate, @Param("toDate") Date toDate);

    @Query("SELECT a " +
            "from BPApplication a " +
            "where a.step = 6 and a.approvalDate between :fromDate and :toDate " +
            "order by a.applicationDate desc ")
    ArrayList<BPApplication> getProcessedApplications(@Param("fromDate") Date fromDate, @Param("toDate") Date toDate);

    @Query("SELECT a " +
            "from BPApplication a " +
            "where a.applicationDate between :fromDate and :toDate " +
            "order by a.applicationDate desc ")
    ArrayList<BPApplication> getReceivedApplications(@Param("fromDate") Date fromDate, @Param("toDate") Date toDate);

    BPApplication findByReferenceNumber(Integer referenceNumber);
    BPApplication findById(Integer id);
}
