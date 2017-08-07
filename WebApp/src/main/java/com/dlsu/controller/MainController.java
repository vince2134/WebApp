package com.dlsu.controller;

import com.dlsu.model.User;
import com.dlsu.service.ApplicationService;
import com.dlsu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 * Created by avggo on 7/24/2017.
 */

@Controller
public class MainController {

    @Autowired
    UserService userService;

    @Autowired
    ApplicationService applicationService;

    @RequestMapping(value = {"/login"}, method = RequestMethod.POST)
    public ModelAndView redirectAfterLogin(User user){
        ModelAndView modelAndView = new ModelAndView();

        if(userService.validateUser(user.getUsername(), user.getPassword())){
            user = userService.findUserByUsername(user.getUsername());

            modelAndView.addObject("currentUser", user);

            if(user.getDepartment().equalsIgnoreCase("admin")){
                modelAndView.addObject("users", userService.getAllUsers());
                modelAndView.setViewName("/admin/admin-home");
            }
            else if(user.getDepartment().equalsIgnoreCase("bpld")){
                modelAndView.addObject("applications", applicationService.getStep1Applications());
                modelAndView.setViewName("/bpld/bpld-home");
            }
            else if(user.getDepartment().equalsIgnoreCase("engineering - employee")){
                modelAndView.addObject("applications", applicationService.getStep2Applications());
                modelAndView.setViewName("/engineering/employee/engineering-home");
            }
            else if(user.getDepartment().equalsIgnoreCase("engineering - inspector")){
                modelAndView.addObject("applications", applicationService.getApplicationsByInspectorId(user.getUserId()));
                modelAndView.setViewName("/engineering/inspector/inspector-home");
            }
            else if(user.getDepartment().equalsIgnoreCase("treasury")){
                modelAndView.addObject("applications", applicationService.getTreasuryApplications());
                modelAndView.setViewName("/treasury/treasury-home");
            }
            else if(user.getDepartment().equalsIgnoreCase("chief")){
                modelAndView.addObject("applications", applicationService.getReadyForPaymentApplications());
                modelAndView.setViewName("/chief/chief-home");
            }
            else{
                modelAndView.addObject("hasError", "error");
                modelAndView.setViewName("login");
            }
        }
        else{
            modelAndView.addObject("hasError", "error");
            modelAndView.setViewName("login");
        }

        return modelAndView;
    }

    @RequestMapping(value = {"/"})
    public ModelAndView main(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");

        modelAndView.addObject("user", new User());

        return modelAndView;
    }
}
