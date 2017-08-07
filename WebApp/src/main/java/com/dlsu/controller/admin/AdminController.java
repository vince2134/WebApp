package com.dlsu.controller.admin;

import com.dlsu.model.Report;
import com.dlsu.model.User;
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
 * Created by avggo on 7/25/2017.
 */

@Controller
public class AdminController {

    @Autowired
    UserService userService;

    @Autowired
    ReportService reportService;

    @RequestMapping(value = {"/admin-home"}, method = RequestMethod.GET)
    public ModelAndView adminHome(@RequestParam("curUserId") Integer curUserId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/admin/admin-home");

        User user = userService.findUserByUserId(curUserId);

        modelAndView.addObject("currentUser", user);
        modelAndView.addObject("users", userService.getAllUsers());

        return modelAndView;
    }

    @RequestMapping(value = {"/create-employee"}, method = RequestMethod.GET)
    public ModelAndView createEmployee(@RequestParam("curUserId") Integer curUserId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/admin/create-employee");

        User curUser = userService.findUserByUserId(curUserId);

        modelAndView.addObject("currentUser", curUser);
        modelAndView.addObject("user", new User());

        return modelAndView;
    }

    @RequestMapping(value = {"/create-employee"}, method = RequestMethod.POST)
    public ModelAndView createNewEmployee(@RequestParam("curUserId") Integer curUserId, User user) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/admin/create-employee");

        try {
            userService.createNewUser(user);
            modelAndView.addObject("success", "success");
        } catch (Exception e){
            modelAndView.addObject("fail", "fail");
        }

        User curUser = userService.findUserByUserId(curUserId);

        modelAndView.addObject("user", new User());
        modelAndView.addObject("currentUser", curUser);

        return modelAndView;
    }

    @RequestMapping(value = {"/edit-employee"}, method = RequestMethod.GET)
    public ModelAndView editEmployee(@RequestParam("userIdEdit") Integer userIdEdit, @RequestParam("curUserId") Integer curUserId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/admin/edit-employee");

        modelAndView.addObject("user", userService.findUserByUserId(userIdEdit));
        modelAndView.addObject("currentUser", userService.findUserByUserId(curUserId));

        return modelAndView;
    }

    @RequestMapping(value = {"/edit-employee"}, method = RequestMethod.POST)
    public ModelAndView saveEdittedEmployee(@RequestParam("curUserId") Integer curUserId, User user) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/admin/edit-employee");

        User curUser = userService.findUserByUserId(curUserId);

        modelAndView.addObject("currentUser", curUser);
        modelAndView.addObject("success", "success");
        userService.createNewUser(user);

        return modelAndView;
    }

    @RequestMapping(value = {"/delete-employee"}, method = RequestMethod.POST)
    public ModelAndView deleteEmployee(@RequestParam("userIdDelete") Integer userIdDelete, @RequestParam("curUserId") Integer curUserId) {
        ModelAndView modelAndView = new ModelAndView();

        userService.deleteUser(userService.findUserByUserId(userIdDelete));

        if(userIdDelete != curUserId) {
            modelAndView.setViewName("/admin/admin-home");

            User curUser = userService.findUserByUserId(curUserId);

            modelAndView.addObject("currentUser", curUser);

            modelAndView.addObject("success", "success");
            modelAndView.addObject("users", userService.getAllUsers());
        }
        else {
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("/login");
        }

        return modelAndView;
    }

    @RequestMapping(value = {"/report-employee"}, method = RequestMethod.GET)
    public ModelAndView reportEmployee(@RequestParam("curUserId") Integer curUserId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/admin/report-employee");

        User user = userService.findUserByUserId(curUserId);

        System.out.print(user.getUserId());

        Report report = new Report();
        report.setAdminId(curUserId);

        modelAndView.addObject("currentUser", user);
        modelAndView.addObject("report", report);

        return modelAndView;
    }

    @RequestMapping(value = {"/report-employee"}, method = RequestMethod.POST)
    public ModelAndView saveReportEmployee(@RequestParam("curUserId") Integer curUserId, Report report, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/admin/report-employee");

        User user = userService.findUserByUserId(curUserId);

        modelAndView.addObject("report", new Report());
        modelAndView.addObject("currentUser", user);
        reportService.createNewReport(report);

        modelAndView.addObject("success", "success");

        return modelAndView;
    }
}
