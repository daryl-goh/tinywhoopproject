package sg.edu.nus.iss.tinywhoopproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import sg.edu.nus.iss.tinywhoopproject.model.RaceCourse;
import sg.edu.nus.iss.tinywhoopproject.services.RaceCourseService;

@Controller
public class RaceCourseController {
    @Autowired
    private RaceCourseService raceCourseService;
    
    // Landing Page to Race Courses Page
    @GetMapping(path={"/racecourses"})
    public String raceCourses(Model model) {
        List<RaceCourse> raceCourses = raceCourseService.retrieveAllRaceCourse();
        System.out.println(raceCourses + "!!!!!");
        model.addAttribute("listOfRaceCourses", raceCourses);
        return "racecourses";
    }

    

    // Race Courses to Race Course Pilots Page
    @GetMapping(path={"/racecoursepilots"})
    public String raceCoursePilots(Model model, @PathVariable Integer raceId) {
        List<RaceCourse> race = raceCourseService.retrievePilotsByRace(raceId);
        System.out.println(race + "????");;
        model.addAttribute("pilotsByRace", race);

        return "racecoursepilots";
    }


}
