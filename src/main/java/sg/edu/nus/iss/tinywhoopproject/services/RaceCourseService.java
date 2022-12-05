package sg.edu.nus.iss.tinywhoopproject.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.iss.tinywhoopproject.model.RaceCourse;
import sg.edu.nus.iss.tinywhoopproject.repository.RaceRepository;

@Service
public class RaceCourseService {

    @Autowired
    private RaceRepository raceRepository;
    
    public List<RaceCourse> retrieveAllRaceCourse(){
        return raceRepository.getAllRaceCourse();
    }

    public List<RaceCourse> retrievePilotsByRace(int raceId){
        return raceRepository.getPilotsByRace(raceId);
    }
}
