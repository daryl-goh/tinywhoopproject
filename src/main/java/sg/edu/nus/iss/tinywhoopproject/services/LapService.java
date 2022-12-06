package sg.edu.nus.iss.tinywhoopproject.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.iss.tinywhoopproject.model.Lap;
import sg.edu.nus.iss.tinywhoopproject.repository.LapRepository;

@Service
public class LapService {
    @Autowired
    private LapRepository lapRepository;
    
    public List<Lap> retrieveLapTimingsOfPilotsByRaceParticipation(String pilotId, Integer raceId){
        return lapRepository.getLapTimingsOfPilotsByRaceParticipation(pilotId, raceId);
    }
}
