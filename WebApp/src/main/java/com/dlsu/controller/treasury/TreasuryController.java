package com.dlsu.controller.treasury;

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

/**
 * Created by avggo on 7/30/2017.
 */

@Controller
public class TreasuryController {

    @Autowired
    UserService userService;

    @Autowired
    ApplicationService applicationService;

    @Autowired
    FeesService feesService;

    @Autowired
    ReportService reportService;

    @RequestMapping(value = {"/treasury-home"}, method = RequestMethod.GET)
    public ModelAndView adminHome(@RequestParam("curUserId") Integer curUserId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/treasury/treasury-home");

        User user = userService.findUserByUserId(curUserId);

        modelAndView.addObject("currentUser", user);
        modelAndView.addObject("applications", applicationService.getTreasuryApplications());

        return modelAndView;
    }

    @RequestMapping(value = {"/approve-fee"}, method = RequestMethod.GET)
    public ModelAndView approveFee(@RequestParam("curUserId") Integer curUserId, @RequestParam("appId") Integer appId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/treasury/approve-fee");

        User user = userService.findUserByUserId(curUserId);

        Fees fees = feesService.findByAppId(appId);
        BPApplication app = applicationService.findByIdNumber(appId);

        modelAndView.addObject("currentUser", user);
        modelAndView.addObject("app", app);
        modelAndView.addObject("fees", fees);
        modelAndView.addObject("total", fees.getTotal());
        modelAndView.addObject("bpldAssess", userService.findUserByUserId(app.getAssessBpld()));
        modelAndView.addObject("engAssess", userService.findUserByUserId(app.getAssessEng()));


        return modelAndView;
    }

    @RequestMapping(value = {"/approve-fee"}, method = RequestMethod.POST)
    public ModelAndView approveFeeSubmit(@RequestParam("curUserId") Integer curUserId, @RequestParam("appId") Integer appId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/treasury/approve-fee");

        User user = userService.findUserByUserId(curUserId);

        Fees fees = feesService.findByAppId(appId);
        BPApplication app = applicationService.findByIdNumber(appId);

        app.setStep(5);

        applicationService.createNewApplication(app);

        modelAndView.addObject("success", "success");
        modelAndView.addObject("currentUser", user);
        modelAndView.addObject("app", app);
        modelAndView.addObject("fees", fees);
        modelAndView.addObject("total", fees.getTotal());
        modelAndView.addObject("bpldAssess", userService.findUserByUserId(app.getAssessBpld()));
        modelAndView.addObject("engAssess", userService.findUserByUserId(app.getAssessEng()));


        return modelAndView;
    }

    @RequestMapping(value = {"/enter-application-treasury"}, method = RequestMethod.GET)
    public ModelAndView enterApplication(@RequestParam("curUserId") Integer curUserId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/treasury/enter-application");

        User user = userService.findUserByUserId(curUserId);

        modelAndView.addObject("currentUser", user);
        modelAndView.addObject("success", "success");

        return modelAndView;
    }

    @RequestMapping(value = {"/enter-application-treasury"}, method = RequestMethod.POST)
    public ModelAndView enterApplication(@RequestParam("curUserId") Integer curUserId, @RequestParam("referenceNumber") Integer referenceNumber) {
        ModelAndView modelAndView = new ModelAndView();


        User user = userService.findUserByUserId(curUserId);

        if(applicationService.findByReferenceNumber(referenceNumber) != null) {
            modelAndView.addObject("success", "success");
            modelAndView.addObject("currentApplication", applicationService.findByReferenceNumber(referenceNumber));
            modelAndView.setViewName("/treasury/view-application");
        }
        else {
            modelAndView.addObject("succcess", "fail");
            modelAndView.setViewName("/treasury/enter-application");
        }

        modelAndView.addObject("currentUser", user);

        return modelAndView;
    }

    @RequestMapping(value = {"/treasury-home-processing"}, method = RequestMethod.GET)
    public ModelAndView adminHomeProcessing(@RequestParam("curUserId") Integer curUserId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/treasury/treasury-home");

        User user = userService.findUserByUserId(curUserId);

        modelAndView.addObject("currentUser", user);
        modelAndView.addObject("applications", applicationService.getProcessingApplications());

        return modelAndView;
    }

    @RequestMapping(value = {"/report-employee-treasury"}, method = RequestMethod.GET)
    public ModelAndView reportEmployee(@RequestParam("curUserId") Integer curUserId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/treasury/report-employee");

        User user = userService.findUserByUserId(curUserId);

        System.out.print(user.getUserId());

        Report report = new Report();
        report.setAdminId(curUserId);

        modelAndView.addObject("currentUser", user);
        modelAndView.addObject("report", report);

        return modelAndView;
    }

    @RequestMapping(value = {"/report-employee-treasury"}, method = RequestMethod.POST)
    public ModelAndView saveReportEmployee(@RequestParam("curUserId") Integer curUserId, Report report, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/treasury/report-employee");

        User user = userService.findUserByUserId(curUserId);

        modelAndView.addObject("report", new Report());
        modelAndView.addObject("currentUser", user);
        reportService.createNewReport(report);

        modelAndView.addObject("success", "success");

        return modelAndView;
    }

    @RequestMapping(value = {"/view-application-treasury"}, method = RequestMethod.GET)
    public ModelAndView viewApplication(@RequestParam("curUserId") Integer curUserId, @RequestParam("appId") Integer appId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/treasury/view-application");

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

    @RequestMapping(value = {"/record-list-treasury"}, method = RequestMethod.GET)
    public ModelAndView recordList(@RequestParam("curUserId") Integer curUserId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/treasury/records-list");

        User user = userService.findUserByUserId(curUserId);

        modelAndView.addObject("currentUser", user);
        modelAndView.addObject("applications", applicationService.getApprovedApplications());

        return modelAndView;
    }
}
