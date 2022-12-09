package sg.edu.nus.iss.tinywhoopproject.services;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.iss.tinywhoopproject.model.Pilot;
import sg.edu.nus.iss.tinywhoopproject.repository.RaceRepository;

@Service
public class RaceCoursePilotsService {
    @Autowired
    private RaceRepository raceRepository;

    public boolean addPilotToRaceCourse(Integer raceId, Integer numberOfLaps, DateTime closingDate, String pilotId){
        return raceRepository.savePilotsToRaceCourse(raceId, numberOfLaps, closingDate, pilotId);
    }
}
