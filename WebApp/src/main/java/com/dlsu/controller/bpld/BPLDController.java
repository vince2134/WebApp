package com.dlsu.controller.bpld;

import com.dlsu.model.BPApplication;
import com.dlsu.model.Fees;
import com.dlsu.model.Report;
import com.dlsu.model.User;
import com.dlsu.service.ApplicationService;
import com.dlsu.service.FeesService;
import com.dlsu.service.ReportService;
import com.dlsu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

/**
 * Created by avggo on 7/27/2017.
 */

@Controller
public class BPLDController {

    @Autowired
    UserService userService;

    @Autowired
    ApplicationService applicationService;

    @Autowired
    ReportService reportService;

    @Autowired
    FeesService feesService;

    @RequestMapping(value = {"/bpld-home"}, method = RequestMethod.GET)
    public ModelAndView adminHome(@RequestParam("curUserId") Integer curUserId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/bpld/bpld-home");

        User user = userService.findUserByUserId(curUserId);

        modelAndView.addObject("currentUser", user);
        modelAndView.addObject("applications", applicationService.getPendingApplications());

        return modelAndView;
    }

    @RequestMapping(value = {"/bpld-home-processing"}, method = RequestMethod.GET)
    public ModelAndView adminHomeProcessing(@RequestParam("curUserId") Integer curUserId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/bpld/bpld-home");

        User user = userService.findUserByUserId(curUserId);

        modelAndView.addObject("currentUser", user);
        modelAndView.addObject("applications", applicationService.getProcessingApplications());

        return modelAndView;
    }

    @RequestMapping(value = {"/encode"}, method = RequestMethod.GET)
    public ModelAndView encodeApplication(@RequestParam("curUserId") Integer curUserId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/bpld/encode");

        User user = userService.findUserByUserId(curUserId);

        modelAndView.addObject("currentUser", user);

        BPApplication app = new BPApplication();
        app.setBpldId(curUserId);

        modelAndView.addObject("newBPApplication", app);

        return modelAndView;
    }

    @RequestMapping(value = {"/encode"}, method = RequestMethod.POST)
    public ModelAndView encodeApplication(@RequestParam("curUserId") Integer curUserId, BPApplication newBPApplication, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/bpld/encode");

        User user = userService.findUserByUserId(curUserId);

        modelAndView.addObject("currentUser", user);

        newBPApplication.setEncodedDate(new Date());
        applicationService.createNewApplication(newBPApplication);

        BPApplication app = new BPApplication();
        app.setBpldId(curUserId);

        modelAndView.addObject("newBPApplication", app);
        modelAndView.addObject("success", "success");

        return modelAndView;
    }

    @RequestMapping(value = {"/enter-application"}, method = RequestMethod.GET)
    public ModelAndView enterApplication(@RequestParam("curUserId") Integer curUserId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/bpld/enter-application");

        User user = userService.findUserByUserId(curUserId);

        modelAndView.addObject("currentUser", user);
        modelAndView.addObject("success", "success");

        return modelAndView;
    }

    @RequestMapping(value = {"/enter-application"}, method = RequestMethod.POST)
    public ModelAndView enterApplication(@RequestParam("curUserId") Integer curUserId, @RequestParam("referenceNumber") Integer referenceNumber) {
        ModelAndView modelAndView = new ModelAndView();


        User user = userService.findUserByUserId(curUserId);

        if(applicationService.findByReferenceNumber(referenceNumber) != null) {
            modelAndView.addObject("success", "success");
            modelAndView.addObject("currentApplication", applicationService.findByReferenceNumber(referenceNumber));
            modelAndView.setViewName("/bpld/view-application");
        }
        else {
            modelAndView.addObject("succcess", "fail");
            modelAndView.setViewName("/bpld/enter-application");
        }

        modelAndView.addObject("currentUser", user);

        return modelAndView;
    }

    @RequestMapping(value = {"/view-application"}, method = RequestMethod.GET)
    public ModelAndView viewApplication(@RequestParam("curUserId") Integer curUserId, @RequestParam("appId") Integer appId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/bpld/view-application");

        User user = userService.findUserByUserId(curUserId);
        Fees fees = feesService.findByAppId(appId);

        modelAndView.addObject("currentUser", user);
        modelAndView.addObject("fees", fees);
        if(fees != null)
            modelAndView.addObject("total", fees.getTotal());
        modelAndView.addObject("currentUser", user);
        modelAndView.addObject("currentApplication", applicationService.findByIdNumber(appId));

        return modelAndView;
    }

    @RequestMapping(value = {"/report-employee-bpld"}, method = RequestMethod.GET)
    public ModelAndView reportEmployee(@RequestParam("curUserId") Integer curUserId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/bpld/report-employee");

        User user = userService.findUserByUserId(curUserId);

        System.out.print(user.getUserId());

        Report report = new Report();
        report.setAdminId(curUserId);

        modelAndView.addObject("currentUser", user);
        modelAndView.addObject("report", report);

        return modelAndView;
    }

    @RequestMapping(value = {"/report-employee-bpld"}, method = RequestMethod.POST)
    public ModelAndView saveReportEmployee(@RequestParam("curUserId") Integer curUserId, Report report, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/bpld/report-employee");

        User user = userService.findUserByUserId(curUserId);

        modelAndView.addObject("report", new Report());
        modelAndView.addObject("currentUser", user);
        reportService.createNewReport(report);

        modelAndView.addObject("success", "success");

        return modelAndView;
    }

    @RequestMapping(value = {"/choose-to-compute-fee-bpld"}, method = RequestMethod.GET)
    public ModelAndView chooseToComputeFee(@RequestParam("curUserId") Integer curUserId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/bpld/choose-to-compute-fee");

        User user = userService.findUserByUserId(curUserId);

        modelAndView.addObject("currentUser", user);
        modelAndView.addObject("applications", applicationService.getInspectedApplications());

        return modelAndView;
    }

    @RequestMapping(value = {"/compute-fees-bpld"}, method = RequestMethod.GET)
    public ModelAndView computeFees(@RequestParam("curUserId") Integer curUserId, @RequestParam("appId") Integer appId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/bpld/compute-fee");

        User user = userService.findUserByUserId(curUserId);

        if(feesService.findByAppId(appId) != null){
            if(feesService.findByAppId(appId).verified()) {
                modelAndView.addObject("verified", "verified");
            }

            modelAndView.addObject("fees", feesService.findByAppId(appId));
        }
        else{
            modelAndView.addObject("fees", new Fees());
        }

        modelAndView.addObject("currentUser", user);
        modelAndView.addObject("app", applicationService.findByIdNumber(appId));

        return modelAndView;
    }

    @RequestMapping(value = {"/compute-fees-bpld"}, method = RequestMethod.POST)
    public ModelAndView computeFees(@RequestParam("curUserId") Integer curUserId, @RequestParam("appId") Integer appId, Fees fees) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/bpld/compute-fee");

        User user = userService.findUserByUserId(curUserId);
        BPApplication app = applicationService.findByIdNumber(appId);
        app.setAssessBpldDate(new Date());
        app.setAssessBpld(curUserId);
        applicationService.createNewApplication(app);

        if(fees.getOthers1Date().isEmpty())
            fees.setOthers1Date(null);
        if(fees.getOthers2Date().isEmpty())
            fees.setOthers2Date(null);
        if(fees.getOthers3Date().isEmpty())
            fees.setOthers3Date(null);
        if(fees.getOthers4Date().isEmpty())
            fees.setOthers4Date(null);

        feesService.saveFees(fees);

        if(fees.verified())
            modelAndView.addObject("verified", "verified");

        modelAndView.addObject("success", "success");
        modelAndView.addObject("currentUser", user);
        modelAndView.addObject("fees", fees);
        modelAndView.addObject("app", app);


        return modelAndView;
    }

    @RequestMapping(value = {"/send-to-treasury-bpld"}, method = RequestMethod.POST)
    public ModelAndView chooseToComputeFee(@RequestParam("curUserId") Integer curUserId, @RequestParam("appId") Integer appId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/bpld/choose-to-compute-fee");

        User user = userService.findUserByUserId(curUserId);

        BPApplication app = applicationService.findByIdNumber(appId);
        app.setStep(5);

        applicationService.createNewApplication(app);

        modelAndView.addObject("treasury", "treasury");
        modelAndView.addObject("currentUser", user);
        modelAndView.addObject("applications", applicationService.getInspectedApplications());

        return modelAndView;
    }

    @RequestMapping(value = {"/record-list-bpld"}, method = RequestMethod.GET)
    public ModelAndView recordList(@RequestParam("curUserId") Integer curUserId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/bpld/records-list");

        User user = userService.findUserByUserId(curUserId);

        modelAndView.addObject("currentUser", user);
        modelAndView.addObject("applications", applicationService.getApprovedApplications());

        return modelAndView;
    }

    @RequestMapping(value = {"/verification-of-document-application"}, method = RequestMethod.GET)
    public ModelAndView verification(@RequestParam("curUserId") Integer curUserId, @RequestParam("appId") Integer appId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/bpld/verification-of-document-application");

        User user = userService.findUserByUserId(curUserId);

        if(feesService.findByAppId(appId) != null){
            modelAndView.addObject("fees", feesService.findByAppId(appId));
        }
        else
            modelAndView.addObject("fees", new Fees());

        modelAndView.addObject("app", applicationService.findByIdNumber(appId));
        modelAndView.addObject("currentUser", user);

        return modelAndView;
    }

    @RequestMapping(value = {"/verification-of-document-application"}, method = RequestMethod.POST)
    public ModelAndView verificationDone(@RequestParam("curUserId") Integer curUserId, @RequestParam("appId") Integer appId, Fees fees) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/bpld/verification-of-document-application");

        User user = userService.findUserByUserId(curUserId);

        if(fees.getOthers1Date().isEmpty())
            fees.setOthers1Date(null);
        if(fees.getOthers2Date().isEmpty())
            fees.setOthers2Date(null);
        if(fees.getOthers3Date().isEmpty())
            fees.setOthers3Date(null);
        if(fees.getOthers4Date().isEmpty())
            fees.setOthers4Date(null);
        if(fees.getBarangayClearanceVerifier().isEmpty())
            fees.setBarangayClearanceVerifier(null);
        if(fees.getLocationClearanceVerifier().isEmpty())
            fees.setLocationClearanceVerifier(null);
        if(fees.getHealthClearanceVerifier().isEmpty())
            fees.setHealthClearanceVerifier(null);
        if(fees.getOccupancyPermitVerifier().isEmpty())
            fees.setOccupancyPermitVerifier(null);
        if(fees.getFireSafetyClearanceVerifier().isEmpty())
            fees.setFireSafetyClearanceVerifier(null);

        if(fees.getBarangayClearanceDate() != null)
            fees.setBarangayClearanceVerifier(user.getFirstName() + ' ' + user.getLastName());
        if(fees.getHealthClearanceDate() != null)
            fees.setHealthClearanceVerifier(user.getFirstName() + ' ' + user.getLastName());
        if(fees.getLocationClearanceDate() != null)
            fees.setLocationClearanceVerifier(user.getFirstName() + ' ' + user.getLastName());
        if(fees.getFireSafetyClearanceDate() != null)
            fees.setFireSafetyClearanceVerifier(user.getFirstName() + ' ' + user.getLastName());
        if(fees.getOccupancyPermitDate() != null)
            fees.setOccupancyPermitVerifier(user.getFirstName() + ' ' + user.getLastName());
        if(fees.getOthers1Date() != null)
            fees.setOthers1Verifier(user.getFirstName() + ' ' + user.getLastName());
        if(fees.getOthers2Date() != null)
            fees.setOthers2Verifier(user.getFirstName() + ' ' + user.getLastName());
        if(fees.getOthers3Date() != null)
            fees.setOthers3Verifier(user.getFirstName() + ' ' + user.getLastName());
        if(fees.getOthers4Date() != null)
            fees.setOthers4Verifier(user.getFirstName() + ' ' + user.getLastName());

        feesService.saveFees(fees);

        if(fees.verified())
            modelAndView.addObject("readyToVerify", "readyToVerify");

        modelAndView.addObject("fees", feesService.findByAppId(appId));
        modelAndView.addObject("app", applicationService.findByIdNumber(appId));
        modelAndView.addObject("success", "success");
        modelAndView.addObject("currentUser", user);

        return modelAndView;
    }

    @RequestMapping(value = {"/verify-document"}, method = RequestMethod.POST)
    public ModelAndView verify(@RequestParam("curUserId") Integer curUserId, @RequestParam("appId") Integer appId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/bpld/verification-of-document-application");

        User user = userService.findUserByUserId(curUserId);
        BPApplication app = applicationService.findByIdNumber(appId);

        if(feesService.findByAppId(appId) != null){
            modelAndView.addObject("fees", feesService.findByAppId(appId));
        }
        else
            modelAndView.addObject("fees", new Fees());

        modelAndView.addObject("app", app);
        modelAndView.addObject("currentUser", user);

        app.setStep(2);
        applicationService.createNewApplication(app);

        modelAndView.addObject("verified", "verified");

        return modelAndView;
    }
}
