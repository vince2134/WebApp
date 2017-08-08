package com.dlsu.controller.client;

import com.dlsu.model.BPApplication;
import com.dlsu.model.Fees;
import com.dlsu.model.User;
import com.dlsu.service.ApplicationService;
import com.dlsu.service.FeesService;
import com.dlsu.service.ReportService;
import com.dlsu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by avggo on 7/31/2017.
 */

@Controller
public class ClientController {

    @Autowired
    UserService userService;

    @Autowired
    ApplicationService applicationService;

    @Autowired
    ReportService reportService;

    @Autowired
    FeesService feesService;

    @RequestMapping(value = {"/enter-application-client"}, method = RequestMethod.GET)
    public ModelAndView enterApplication() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/client/enter-application");

        modelAndView.addObject("success", "success");

        return modelAndView;
    }

    @RequestMapping(value = {"/enter-application-client"}, method = RequestMethod.POST)
    public ModelAndView enterApplication(@RequestParam("referenceNumber") Integer referenceNumber) {
        ModelAndView modelAndView = new ModelAndView();

        if(applicationService.findByReferenceNumber(referenceNumber) != null) {
            BPApplication app = applicationService.findByReferenceNumber(referenceNumber);
            Fees fees = feesService.findByAppId(app.getId());

            modelAndView.addObject("success", "success");
            modelAndView.addObject("currentApplication", app);
            if(fees != null)
                modelAndView.addObject("fees", fees);
            if(fees != null)
                modelAndView.addObject("total", fees.getTotal());
            modelAndView.setViewName("/client/client-home");
        }
        else {
            modelAndView.addObject("succcess", "fail");
            modelAndView.setViewName("/client/enter-application");
        }

        return modelAndView;
    }
}
