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
public class InspectorController {

    @Autowired
    UserService userService;

    @Autowired
    ApplicationService applicationService;

    @Autowired
    ReportService reportService;

    @Autowired
    FeesService feesService;


    @RequestMapping(value = {"/inspector-home"}, method = RequestMethod.GET)
    public ModelAndView adminHome(@RequestParam("curUserId") Integer curUserId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/engineering/inspector/inspector-home");

        User user = userService.findUserByUserId(curUserId);

        modelAndView.addObject("currentUser", user);
        modelAndView.addObject("applications", applicationService.getApplicationsByInspectorId(user.getUserId()));

        return modelAndView;
    }

    @RequestMapping(value = {"/enter-application-inspector"}, method = RequestMethod.GET)
    public ModelAndView enterApplication(@RequestParam("curUserId") Integer curUserId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/engineering/inspector/enter-application");

        User user = userService.findUserByUserId(curUserId);

        modelAndView.addObject("currentUser", user);
        modelAndView.addObject("success", "success");

        return modelAndView;
    }

    @RequestMapping(value = {"/enter-application-inspector"}, method = RequestMethod.POST)
    public ModelAndView enterApplication(@RequestParam("curUserId") Integer curUserId, @RequestParam("referenceNumber") Integer referenceNumber) {
        ModelAndView modelAndView = new ModelAndView();


        User user = userService.findUserByUserId(curUserId);

        if(applicationService.findByReferenceNumber(referenceNumber) != null) {
            modelAndView.addObject("success", "success");
            modelAndView.addObject("currentApplication", applicationService.findByReferenceNumber(referenceNumber));
            modelAndView.setViewName("/engineering/inspector/view-application");
        }
        else {
            modelAndView.addObject("succcess", "fail");
            modelAndView.setViewName("/engineering/inspector/enter-application");
        }

        modelAndView.addObject("currentUser", user);

        return modelAndView;
    }

    @RequestMapping(value = {"/inspector-home-processing"}, method = RequestMethod.GET)
    public ModelAndView adminHomeProcessing(@RequestParam("curUserId") Integer curUserId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/engineering/inspector/inspector-home");

        User user = userService.findUserByUserId(curUserId);

        modelAndView.addObject("currentUser", user);
        modelAndView.addObject("applications", applicationService.getInspectingApplications());

        return modelAndView;
    }

    @RequestMapping(value = {"/schedule-list-inspector"}, method = RequestMethod.GET)
    public ModelAndView newSchedule(@RequestParam("curUserId") Integer curUserId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/engineering/inspector/schedule-list");

        User user = userService.findUserByUserId(curUserId);

        modelAndView.addObject("currentUser", user);
        modelAndView.addObject("schedules", applicationService.getInspectingApplications());

        return modelAndView;
    }

    @RequestMapping(value = {"/report-employee-inspector"}, method = RequestMethod.GET)
    public ModelAndView reportEmployee(@RequestParam("curUserId") Integer curUserId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/engineering/inspector/report-employee");

        User user = userService.findUserByUserId(curUserId);

        System.out.print(user.getUserId());

        Report report = new Report();
        report.setAdminId(curUserId);

        modelAndView.addObject("currentUser", user);
        modelAndView.addObject("report", report);

        return modelAndView;
    }

    @RequestMapping(value = {"/report-employee-inspector"}, method = RequestMethod.POST)
    public ModelAndView saveReportEmployee(@RequestParam("curUserId") Integer curUserId, Report report, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/engineering/inspector/report-employee");

        User user = userService.findUserByUserId(curUserId);

        modelAndView.addObject("report", new Report());
        modelAndView.addObject("currentUser", user);
        reportService.createNewReport(report);

        modelAndView.addObject("success", "success");

        return modelAndView;
    }

    @RequestMapping(value = {"/schedule-inspection-inspector"}, method = RequestMethod.GET)
    public ModelAndView newSchedule(@RequestParam("curUserId") Integer curUserId, @RequestParam("appId") Integer appId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/engineering/inspector/create-new-schedule");

        User user = userService.findUserByUserId(curUserId);
        BPApplication app = applicationService.findByIdNumber(appId);

        modelAndView.addObject("currentUser", user);
        modelAndView.addObject("newAppSched", app);
        modelAndView.addObject("inspectors", userService.getInspectors());

        return modelAndView;
    }

    @RequestMapping(value = {"/schedule-inspection-inspector"}, method = RequestMethod.POST)
    public ModelAndView newSchedule(@RequestParam("curUserId") Integer curUserId, BPApplication scheduledApplication, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/engineering/inspector/create-new-schedule");

        User user = userService.findUserByUserId(curUserId);

        modelAndView.addObject("currentUser", user);
        modelAndView.addObject("inspectors", userService.getInspectors());


        scheduledApplication.setStep(2);
        scheduledApplication.setStatus("inspecting");
        scheduledApplication.setInspectorName(userService.findUserByUserId(scheduledApplication.getInspectorId()).getFirstName() + userService.findUserByUserId(scheduledApplication.getInspectorId()).getLastName());

        applicationService.createNewApplication(scheduledApplication);

        modelAndView.addObject("newAppSched", scheduledApplication);
        modelAndView.addObject("success", "success");

        return modelAndView;
    }

    @RequestMapping(value = {"/view-application-inspector"}, method = RequestMethod.GET)
    public ModelAndView viewApplication(@RequestParam("curUserId") Integer curUserId, @RequestParam("appId") Integer appId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/engineering/inspector/view-application");

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

    @RequestMapping(value = {"/inspection-criteria"}, method = RequestMethod.GET)
    public ModelAndView inspectionCriteria(@RequestParam("curUserId") Integer curUserId, @RequestParam("appId") Integer appId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/engineering/inspector/inspection-criteria");

        User user = userService.findUserByUserId(curUserId);

        modelAndView.addObject("currentUser", user);
        modelAndView.addObject("app", applicationService.findByIdNumber(appId));

        return modelAndView;
    }

    @RequestMapping(value = {"/inspection-criteria"}, method = RequestMethod.POST)
    public ModelAndView inspectionCriteria(@RequestParam("curUserId") Integer curUserId, @RequestParam("appId") Integer appId, BPApplication app) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/engineering/inspector/inspection-criteria");

        User user = userService.findUserByUserId(curUserId);

        modelAndView.addObject("currentUser", user);
        modelAndView.addObject("success", "success");
        modelAndView.addObject("app", applicationService.findByIdNumber(appId));

        if(app.inspected()) {
            app.setStep(3);
            app.setInspectionDateComplete(new Date());
            //app.setStatus("inspected");
        }
        else
            app.setStep(2);

        applicationService.createNewApplication(app);

        return modelAndView;
    }

    @RequestMapping(value = {"/record-list-inspector"}, method = RequestMethod.GET)
    public ModelAndView recordList(@RequestParam("curUserId") Integer curUserId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/engineering/inspector/records-list");

        User user = userService.findUserByUserId(curUserId);

        modelAndView.addObject("currentUser", user);
        modelAndView.addObject("applications", applicationService.getApprovedApplications());

        return modelAndView;
    }
}
