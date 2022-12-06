package sg.edu.nus.iss.tinywhoopproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import sg.edu.nus.iss.tinywhoopproject.model.Lap;
import sg.edu.nus.iss.tinywhoopproject.services.LapService;

@Controller
public class LapContoller {
    
    @Autowired
    private LapService lapService;
    // Race Course Pilots to Lap Timer Page
    @GetMapping(path={"/laptimer/{pilotId}/{raceId}"})
    public String lapTimer(Model model, @PathVariable String pilotId, @PathVariable Integer raceId) {
        List<Lap> laps = lapService.retrieveLapTimingsOfPilotsByRaceParticipation(pilotId,  raceId);
        model.addAttribute("lapTimings", laps);

        return "laptimer";
    }
    
}
