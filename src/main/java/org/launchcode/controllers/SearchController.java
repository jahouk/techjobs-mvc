package org.launchcode.controllers;

import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;

import static org.launchcode.models.JobData.findAll;
import static org.launchcode.models.JobData.findByColumnAndValue;
import static org.launchcode.models.JobData.findByValue;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", ListController.columnChoices);
        return "search";
    }

    // TODO #1 - Create handler to process search request and display results
    @RequestMapping(value="results", method= RequestMethod.GET)
    public String results(Model model, HttpServletRequest request){
        String searchType = "";
        String searchTerm = "";
        if(!(request.getParameter("searchType").isEmpty())){
            searchType = request.getParameter("searchType");
            if(!(request.getParameter("searchTerm").isEmpty())){
                searchTerm = request.getParameter("searchTerm");
            }
        }else{
            return "redirect:/search";
        }

        ArrayList<HashMap<String, String>> jobs = new ArrayList<>();

        if(searchType.equals("all")){
            jobs = findByValue(searchTerm);
        }else{
            jobs = findByColumnAndValue(searchType, searchTerm);
        }
        model.addAttribute("columns", ListController.columnChoices);
        model.addAttribute("jobs", jobs);

        return "search";
    }

}
