package sg.edu.nus.iss.tinywhoopproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RaceCourseController {
    
    // Landing Page to Race Courses Page
    @GetMapping(path={"/racecourses"})
    public String raceCourses(Model model) {
        return "racecourses";
    }

    // Race Courses to Race One Pilots Page
    @GetMapping(path={"/raceonepilots"})
    public String raceOnePilots(Model model) {
        return "raceonepilots";
    }
}
