package sg.edu.nus.iss.tinywhoopproject.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.tinywhoopproject.model.Lap;

import static sg.edu.nus.iss.tinywhoopproject.repository.Queries.*;
@Repository
public class LapRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    public List<Lap> getLapTimingsOfPilotsByRaceParticipation(String pilotId, int raceId){
        List<Lap> laps = new ArrayList<>();

        SqlRowSet lapTimingResult = jdbcTemplate.queryForRowSet(SQL_RETRIEVE_LAPTIMINGS_OF_PILOT_PARTICAPTION_BY_RACE, pilotId, raceId);

        while (lapTimingResult.next()){
            laps.add(Lap.create(lapTimingResult));
        }
        return laps;
    }
}
