package sg.edu.nus.iss.tinywhoopproject.repository;

import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
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
        
        return races;
    }

    public List<RaceCourse> getRaceCourseByRaceId(Integer raceId){
        List<RaceCourse> races = new ArrayList<>();
        List<Pilot> pilotsByRaceCourse = new ArrayList<>();

        SqlRowSet raceCourseResults = jdbcTemplate.queryForRowSet(SQL_RETRIEVE_RACECOURSE_BY_RACEID, raceId);
        
        while(raceCourseResults.next()){
            races.add(RaceCourse.create(raceCourseResults));       
        }
        
        return races;
    }

    public RaceCourse getRaceCoursePilots(Integer raceId){
        
        List<Pilot> pilotList = new ArrayList<>();

        SqlRowSet result = jdbcTemplate.queryForRowSet(SQL_RETRIEVE_RACE_PARTICAPTION_PILOTS, raceId);
        
        String raceName = "";

        while (result.next()) {
            pilotList.add(Pilot.create(result));
            raceName = result.getString("race_name");
        }
        return RaceCourse.create(pilotList, raceId, raceName);
    }

    public boolean saveRaceCourse(RaceCourse rc){
        String raceName = rc.getRace().getRaceName();
        KeyHolder keyholder = new GeneratedKeyHolder();

        // Insert into Race table get the primary key
        jdbcTemplate.update(conn -> {
            PreparedStatement ps = conn.prepareStatement(SQL_INSERT_RACE, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, raceName);
            return ps;
        }, keyholder);
        BigInteger primaryKeyVal = (BigInteger) keyholder.getKey();
        rc.setId(primaryKeyVal.intValue());
        
        // Insert into Race Course Table
        return jdbcTemplate.update(SQL_INSERT_RACE_COURSE, rc.getId(), null, new Timestamp(rc.getClosingDate().toDateTime().getMillis()), rc.getNumberOfLaps()) > 0;
    }

    public boolean updateRaceCourse(RaceCourse rc2) {
        return jdbcTemplate.update(SQL_UPDATE_RACECOURSE,
                rc2.getRace().getRaceName(),
                new Timestamp(rc2.getClosingDate().toDateTime().getMillis()),
                rc2.getNumberOfLaps(),
                rc2.getId()) > 0;
    }

    public boolean deleteRaceCourse(String raceId) {
        return jdbcTemplate.update(SQL_DELETE_RACECOURSE,
                raceId) > 0;
    }

    public boolean savePilotsToRaceCourse(Integer raceId, Integer numberOfLaps, DateTime closingDate, String pilotId){
        // Insert into Race Course Table
        return jdbcTemplate.update(SQL_INSERT_EXISTING_PILOT_INTO_EXISTING_RACE_COURSE, raceId, pilotId, new Timestamp(closingDate.toDateTime().getMillis()), numberOfLaps) > 0;
    }

}
