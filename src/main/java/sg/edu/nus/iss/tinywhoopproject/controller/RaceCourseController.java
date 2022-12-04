package sg.edu.nus.iss.tinywhoopproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import sg.edu.nus.iss.tinywhoopproject.model.RaceCourse;
import sg.edu.nus.iss.tinywhoopproject.services.RaceCourseService;

@Controller
public class RaceCourseController {
    @Autowired
    private RaceCourseService raceCourseService;
    
    // Landing Page to Race Courses Page
    @GetMapping(path={"/racecourses"})
    public String raceCourses(Model model) {
        // List<RaceCourse> raceCourses = raceCourseService.retrieveAllRaceCourse();
        // model.addAttribute("listOfRaceCourses", raceCourses);
        return "racecourses";
    }

    // Race Courses to Race One Pilots Page
    @GetMapping(path={"/raceonepilots"})
    public String raceOnePilots(Model model) {
        return "raceonepilots";
    }
}
