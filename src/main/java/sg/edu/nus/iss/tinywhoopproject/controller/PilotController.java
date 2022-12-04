package sg.edu.nus.iss.tinywhoopproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PilotController {

    // Landing Page to Pilot List Page
    @GetMapping(path={"/pilotlist"}) 
    public String pilotlist(Model model) {
        return "pilotlist";
    }
    
    // Race One Pilots to Lap Timer Page
    @GetMapping(path={"/laptimer"})
    public String lapTimer(Model model) {
        return "laptimer";
    }
    
}
