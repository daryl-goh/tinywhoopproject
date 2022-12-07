package sg.edu.nus.iss.tinywhoopproject.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.iss.tinywhoopproject.model.Pilot;
import sg.edu.nus.iss.tinywhoopproject.repository.PilotRepository;

@Service
public class PilotService {
    @Autowired
    private PilotRepository pilotRepository;

    public List<Pilot> retrieveAllPilots(){
        return pilotRepository.getAllPilots();
    }

    public boolean addPilot(Pilot p){
        return pilotRepository.savePilot(p);
    }

    public boolean editPilot(Pilot p){
        return pilotRepository.updatePilot(p);
    }

    public boolean removePilot(String pilotId){
        return pilotRepository.deletePilot(pilotId);
    }
}
