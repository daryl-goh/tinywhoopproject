package sg.edu.nus.iss.tinywhoopproject.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    
    // Race Course Pilots to Lap Timer Page
    @GetMapping(path={"/laptimer"})
    public String lapTimer(Model model) {
        return "laptimer";
    }

    @PostMapping (path={"/pilot/add"})
    public String savePilot(RedirectAttributes redirectAttributes, @RequestBody(required = false) MultiValueMap<String, String> form){
        if (form == null) {
            System.out.println("No pilot to save!");
            return "redirect:/pilotlist";
        }

        Pilot pilot = new Pilot();
        String pilotName = form.getFirst("addPilotNameInput");
        String quadName = form.getFirst("addQuadNameInput");
        
        String pilotId=UUID.randomUUID().toString().substring(0,8); //Generates random pilot ID

        pilot.setId(pilotId);
        pilot.setPilotName(pilotName);
        pilot.setDroneName(quadName);

        pilotService.addPilot(pilot);
        redirectAttributes.addFlashAttribute("message", "Pilot has been added successfully");
    
        return "redirect:/pilotlist";
    }

    @PostMapping (path = {"/pilot/update"})
    public String updatePilot(RedirectAttributes redirectAttributes, @RequestBody(required = false) MultiValueMap<String, String> form){
        if (form == null){
            System.out.println("No pilot to update!");
            return "redirect:/pilotlist";
        }

        Pilot pilotToUpdate = new Pilot();
        String pilotId = form.getFirst("editPilotIdInput");
        String pilotName = form.getFirst("editPilotNameInput");
        String quadName = form.getFirst("editQuadNameInput");

        pilotToUpdate.setId(pilotId);
        pilotToUpdate.setPilotName(pilotName);
        pilotToUpdate.setDroneName(quadName);

        boolean isEditSuccess = pilotService.editPilot(pilotToUpdate);
        if (isEditSuccess){
            redirectAttributes.addFlashAttribute("message", "Pilot has been updated successfully");
        }
        
        return "redirect:/pilotlist";
    }

    @PostMapping (path = {"/pilot/delete"})
    public String deletePilot(RedirectAttributes redirectAttributes, @RequestBody(required = false) MultiValueMap<String, String> form){
        String pilotToBeDeleted = form.getFirst("pilot-to-be-deleted");
        boolean isDeleteSuccess = pilotService.removePilot(pilotToBeDeleted);
        if (isDeleteSuccess){
            redirectAttributes.addFlashAttribute("message", "Pilot has been deleted successfully");
        }
        return "redirect:/pilotlist";
    }
    
}
