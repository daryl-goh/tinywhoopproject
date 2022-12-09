package sg.edu.nus.iss.tinywhoopproject.controller;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import sg.edu.nus.iss.tinywhoopproject.model.Pilot;
import sg.edu.nus.iss.tinywhoopproject.model.RaceCourse;
import sg.edu.nus.iss.tinywhoopproject.services.PilotService;
import sg.edu.nus.iss.tinywhoopproject.services.RaceCoursePilotsService;
import sg.edu.nus.iss.tinywhoopproject.services.RaceCourseService;


@Controller
public class RaceCoursePilotsController {

    @Autowired
    private RaceCourseService raceCourseService;

    @Autowired
    private RaceCoursePilotsService raceCoursePilotsService;


    @PostMapping (path={"/racecoursepilots/add/{raceId}"})
    public String saveArticles(Model model, RedirectAttributes redirectAttributes, @RequestBody(required = false) MultiValueMap<String, String> form, @PathVariable Integer raceId){

        if (form == null) {
            System.out.println("No race course to save!");
            return "redirect:/racecoursepilots/{raceId}";
        }

        List<RaceCourse> rc = raceCourseService.retrieveRaceCourseById(raceId);
        int numberOfLaps = rc.get(0).getNumberOfLaps();
        DateTime closingDate = rc.get(0).getClosingDate();

        // Get IDs of selected pilots from dropdown
        List<String> pilots = form.get("selected-pilots");
    
        for (String pid : pilots){
            boolean result = raceCoursePilotsService.addPilotToRaceCourse(raceId, numberOfLaps, closingDate, pid);
            if (result){
                System.out.println("Successfully Added Pilot " + pid + " into Race Course " + raceId );
            } else{
                System.out.println("Failed to add Pilot " + pid + " into Race Course " + raceId );
            }
        }

        redirectAttributes.addFlashAttribute("message", "Pilots have been added into Race Course successfully");
    
        return "redirect:/racecoursepilots/{raceId}";
    }
}
