package sg.edu.nus.iss.tinywhoopproject.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.tinywhoopproject.model.Pilot;
import sg.edu.nus.iss.tinywhoopproject.model.RaceCourse;

import static sg.edu.nus.iss.tinywhoopproject.repository.Queries.*;

@Repository
public class RaceRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    public List<RaceCourse> getAllRaceCourse(){
        List<RaceCourse> races = new ArrayList<>();
        List<Pilot> pilotsByRaceCourse = new ArrayList<>();

        SqlRowSet raceCourseResults = jdbcTemplate.queryForRowSet(SQL_RETRIEVE_ALL_RACE_COURSES);
        
        while(raceCourseResults.next()){
            int raceCourseId = raceCourseResults.getInt("race_id");
            SqlRowSet racePilotParticipationResults = jdbcTemplate.queryForRowSet(SQL_RETRIEVE_RACE_PARTICAPTION_PILOTS, raceCourseId);
           
            while (racePilotParticipationResults.next()){
                pilotsByRaceCourse.add(Pilot.create(racePilotParticipationResults));
            }
          
            races.add(RaceCourse.create(raceCourseResults, pilotsByRaceCourse));       
        }
        System.out.println(races);
        
        return races;
    }

    


 

}
