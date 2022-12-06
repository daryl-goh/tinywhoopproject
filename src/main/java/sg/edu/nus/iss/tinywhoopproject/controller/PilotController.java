package sg.edu.nus.iss.tinywhoopproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import sg.edu.nus.iss.tinywhoopproject.model.Pilot;
import sg.edu.nus.iss.tinywhoopproject.services.PilotService;


@Controller
public class PilotController {
    @Autowired
    private PilotService pilotService;

    // Landing Page to Pilot List Page
    @GetMapping(path={"/pilotlist"}) 
    public String pilotlist(Model model) {
        List<Pilot> pilots = pilotService.retrieveAllPilots();
        model.addAttribute("listOfPilots", pilots);
        return "pilotlist";
    }
    
    
}
