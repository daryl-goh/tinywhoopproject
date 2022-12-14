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

    public RaceCourse retrievePilotsByRace(Integer raceId){
        return raceRepository.getRaceCoursePilots(raceId);
    }
    public boolean addRaceCourse(RaceCourse rc){
        return raceRepository.saveRaceCourse(rc);
    }
    
    public boolean editRaceCourse(RaceCourse rc2){
        return raceRepository.updateRaceCourse(rc2);
    }

    public boolean removeRaceCourse(String raceId){
        return raceRepository.deleteRaceCourse(raceId);
    }

    public List<RaceCourse> retrieveRaceCourseById(Integer raceId){
        return raceRepository.getRaceCourseByRaceId(raceId);
    }
}
