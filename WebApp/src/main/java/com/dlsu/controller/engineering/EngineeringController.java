package com.dlsu.controller.engineering;

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
 * Created by avggo on 7/29/2017.
 */

@Controller
public class EngineeringController {

    @Autowired
    UserService userService;

    @Autowired
    ApplicationService applicationService;

    @Autowired
    ReportService reportService;

    @Autowired
    FeesService feesService;

    @RequestMapping(value = {"/engineering-home"}, method = RequestMethod.GET)
    public ModelAndView adminHome(@RequestParam("curUserId") Integer curUserId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/engineering/employee/engineering-home");

        User user = userService.findUserByUserId(curUserId);

        modelAndView.addObject("currentUser", user);
        modelAndView.addObject("applications", applicationService.getPendingApplications());

        return modelAndView;
    }

    @RequestMapping(value = {"/engineering-home-processing"}, method = RequestMethod.GET)
    public ModelAndView adminHomeProcessing(@RequestParam("curUserId") Integer curUserId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/engineering/employee/engineering-home");

        User user = userService.findUserByUserId(curUserId);

        modelAndView.addObject("currentUser", user);
        modelAndView.addObject("applications", applicationService.getProcessingApplications());

        return modelAndView;
    }

    @RequestMapping(value = {"/enter-application-engineering"}, method = RequestMethod.GET)
    public ModelAndView enterApplication(@RequestParam("curUserId") Integer curUserId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/engineering/employee/enter-application");

        User user = userService.findUserByUserId(curUserId);

        modelAndView.addObject("currentUser", user);
        modelAndView.addObject("success", "success");

        return modelAndView;
    }

    @RequestMapping(value = {"/enter-application-engineering"}, method = RequestMethod.POST)
    public ModelAndView enterApplication(@RequestParam("curUserId") Integer curUserId, @RequestParam("referenceNumber") Integer referenceNumber) {
        ModelAndView modelAndView = new ModelAndView();


        User user = userService.findUserByUserId(curUserId);

        if(applicationService.findByReferenceNumber(referenceNumber) != null) {
            modelAndView.addObject("success", "success");
            modelAndView.addObject("currentApplication", applicationService.findByReferenceNumber(referenceNumber));
            modelAndView.setViewName("/engineering/employee/view-application");
        }
        else {
            modelAndView.addObject("succcess", "fail");
            modelAndView.setViewName("/engineering/employee/enter-application");
        }

        modelAndView.addObject("currentUser", user);

        return modelAndView;
    }

    @RequestMapping(value = {"/view-application-engineering"}, method = RequestMethod.GET)
    public ModelAndView viewApplication(@RequestParam("curUserId") Integer curUserId, @RequestParam("appId") Integer appId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/engineering/employee/view-application");

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

    @RequestMapping(value = {"/schedule-inspection"}, method = RequestMethod.GET)
    public ModelAndView newSchedule(@RequestParam("curUserId") Integer curUserId, @RequestParam("appId") Integer appId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/engineering/employee/create-new-schedule");

        User user = userService.findUserByUserId(curUserId);
        BPApplication app = applicationService.findByIdNumber(appId);

        modelAndView.addObject("currentUser", user);
        modelAndView.addObject("newAppSched", app);
        modelAndView.addObject("inspectors", userService.getInspectors());

        return modelAndView;
    }

    @RequestMapping(value = {"/schedule-inspection"}, method = RequestMethod.POST)
    public ModelAndView newSchedule(@RequestParam("curUserId") Integer curUserId, BPApplication scheduledApplication, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/engineering/employee/create-new-schedule");

        User user = userService.findUserByUserId(curUserId);

        modelAndView.addObject("currentUser", user);
        modelAndView.addObject("inspectors", userService.getInspectors());


        scheduledApplication.setStep(3);
        scheduledApplication.setStatus("inspecting");
        scheduledApplication.setInspectorName(userService.findUserByUserId(scheduledApplication.getInspectorId()).getFirstName() + userService.findUserByUserId(scheduledApplication.getInspectorId()).getLastName());

        applicationService.createNewApplication(scheduledApplication);

        modelAndView.addObject("newAppSched", scheduledApplication);
        modelAndView.addObject("success", "success");

        return modelAndView;
    }

    @RequestMapping(value = {"/change-schedule"}, method = RequestMethod.GET)
    public ModelAndView changeSchedule(@RequestParam("curUserId") Integer curUserId, @RequestParam("appId") Integer appId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/engineering/employee/change-schedule");

        User user = userService.findUserByUserId(curUserId);
        BPApplication app = applicationService.findByIdNumber(appId);

        modelAndView.addObject("currentUser", user);
        modelAndView.addObject("newAppSched", app);
        modelAndView.addObject("inspectors", userService.getInspectors());

        return modelAndView;
    }

    @RequestMapping(value = {"/change-schedule"}, method = RequestMethod.POST)
    public ModelAndView changeSchedule(@RequestParam("curUserId") Integer curUserId, BPApplication scheduledApplication, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/engineering/employee/change-schedule");

        User user = userService.findUserByUserId(curUserId);

        modelAndView.addObject("currentUser", user);
        modelAndView.addObject("inspectors", userService.getInspectors());


        scheduledApplication.setStep(3);
        scheduledApplication.setStatus("inspecting");
        scheduledApplication.setInspectorName(userService.findUserByUserId(scheduledApplication.getInspectorId()).getFirstName() + userService.findUserByUserId(scheduledApplication.getInspectorId()).getLastName());

        applicationService.createNewApplication(scheduledApplication);

        modelAndView.addObject("newAppSched", scheduledApplication);
        modelAndView.addObject("success", "success");

        return modelAndView;
    }

    @RequestMapping(value = {"/schedule-list"}, method = RequestMethod.GET)
    public ModelAndView newSchedule(@RequestParam("curUserId") Integer curUserId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/engineering/employee/schedule-list");

        User user = userService.findUserByUserId(curUserId);

        modelAndView.addObject("currentUser", user);
        modelAndView.addObject("schedules", applicationService.getInspectingApplications());

        return modelAndView;
    }

    @RequestMapping(value = {"/report-employee-engineering"}, method = RequestMethod.GET)
    public ModelAndView reportEmployee(@RequestParam("curUserId") Integer curUserId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/engineering/employee/report-employee");

        User user = userService.findUserByUserId(curUserId);

        System.out.print(user.getUserId());

        Report report = new Report();
        report.setAdminId(curUserId);

        modelAndView.addObject("currentUser", user);
        modelAndView.addObject("report", report);

        return modelAndView;
    }

    @RequestMapping(value = {"/report-employee-engineering"}, method = RequestMethod.POST)
    public ModelAndView saveReportEmployee(@RequestParam("curUserId") Integer curUserId, Report report, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/engineering/employee/report-employee");

        User user = userService.findUserByUserId(curUserId);

        modelAndView.addObject("report", new Report());
        modelAndView.addObject("currentUser", user);
        reportService.createNewReport(report);

        modelAndView.addObject("success", "success");

        return modelAndView;
    }

    @RequestMapping(value = {"/choose-to-compute-fee"}, method = RequestMethod.GET)
    public ModelAndView chooseToComputeFee(@RequestParam("curUserId") Integer curUserId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/engineering/employee/choose-to-compute-fee");

        User user = userService.findUserByUserId(curUserId);

        modelAndView.addObject("currentUser", user);
        modelAndView.addObject("applications", applicationService.getInspectedApplications());

        return modelAndView;
    }

    @RequestMapping(value = {"/compute-fees"}, method = RequestMethod.GET)
    public ModelAndView computeFees(@RequestParam("curUserId") Integer curUserId, @RequestParam("appId") Integer appId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/engineering/employee/compute-fee");

        User user = userService.findUserByUserId(curUserId);

        if(feesService.findByAppId(appId) != null){
            if(feesService.findByAppId(appId).verified())
                modelAndView.addObject("verified", "verified");

            modelAndView.addObject("fees", feesService.findByAppId(appId));
        }
        else{
            modelAndView.addObject("fees", new Fees());
        }

        modelAndView.addObject("currentUser", user);
        modelAndView.addObject("app", applicationService.findByIdNumber(appId));

        return modelAndView;
    }

    @RequestMapping(value = {"/compute-fees"}, method = RequestMethod.POST)
    public ModelAndView computeFees(@RequestParam("curUserId") Integer curUserId, @RequestParam("appId") Integer appId, Fees fees) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/engineering/employee/compute-fee");

        User user = userService.findUserByUserId(curUserId);
        BPApplication app = applicationService.findByIdNumber(appId);
        app.setAssessEngDate(new Date());
        app.setAssessEng(curUserId);
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

    @RequestMapping(value = {"/send-to-treasury"}, method = RequestMethod.POST)
    public ModelAndView chooseToComputeFee(@RequestParam("curUserId") Integer curUserId, @RequestParam("appId") Integer appId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/engineering/employee/choose-to-compute-fee");

        User user = userService.findUserByUserId(curUserId);

        BPApplication app = applicationService.findByIdNumber(appId);
        app.setStep(5);

        applicationService.createNewApplication(app);

        modelAndView.addObject("treasury", "treasury");
        modelAndView.addObject("currentUser", user);
        modelAndView.addObject("applications", applicationService.getInspectedApplications());

        return modelAndView;
    }

    @RequestMapping(value = {"/record-list-engineering"}, method = RequestMethod.GET)
    public ModelAndView recordList(@RequestParam("curUserId") Integer curUserId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/engineering/employee/records-list");

        User user = userService.findUserByUserId(curUserId);

        modelAndView.addObject("currentUser", user);
        modelAndView.addObject("applications", applicationService.getApprovedApplications());

        return modelAndView;
    }


}
