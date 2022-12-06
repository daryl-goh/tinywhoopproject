package sg.edu.nus.iss.tinywhoopproject.controller;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import sg.edu.nus.iss.tinywhoopproject.model.Race;
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
        model.addAttribute("listOfRaceCourses", raceCourses);
        return "racecourses";
    }

    

    // Race Courses to Race Course Pilots Page
    @GetMapping(path={"/racecoursepilots/{raceId}"})
    public String raceCoursePilots(Model model, @PathVariable Integer raceId) {
        RaceCourse raceCourse = raceCourseService.retrievePilotsByRace(raceId);
        model.addAttribute("raceCourseId", raceCourse.getId());
        model.addAttribute("raceCourse", raceCourse);
        System.out.println("!!" + raceCourse.getId());
        return "racecoursepilots";
    }

    @PostMapping (path={"/racecourse/add"})
    public String saveArticles(RedirectAttributes redirectAttributes, @RequestBody(required = false) MultiValueMap<String, String> form){
        if (form == null) {
            System.out.println("No race course to save!");
            return "redirect:/racecourses";
        }

        RaceCourse rc = new RaceCourse();
        String raceCourseName = form.getFirst("raceNameInput");
        String raceCourseNumberOfLaps = form.getFirst("numberOfLapsInput");
        String raceCourseClosingDate = form.getFirst("closingDateInput");
        
        DateTimeFormatter df =DateTimeFormat.forPattern("yyyy-MM-dd");
        long millis = df.parseMillis(raceCourseClosingDate);
        Date date  = new Date(millis);
        DateTime dateTime = new DateTime(date);
        
        rc.setRace(new Race(raceCourseName));
        rc.setNumberOfLaps(Integer.parseInt(raceCourseNumberOfLaps));
        rc.setClosingDate(dateTime);

        raceCourseService.addRaceCourse(rc);
        redirectAttributes.addFlashAttribute("message", "Race Course has been added successfully");
    
        return "redirect:/racecourses";
    }
}
