package sg.edu.nus.iss.tinywhoopproject.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.tinywhoopproject.model.RaceCourse;

import static sg.edu.nus.iss.tinywhoopproject.repository.Queries.*;

@Repository
public class RaceRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    public List<RaceCourse> getAllRaceCourse(){
        List<RaceCourse> races = new ArrayList<>();

        SqlRowSet result = jdbcTemplate.queryForRowSet(SQL_RETRIEVE_ALL_RACE_COURSES);

        int raceId = 1;
        while(result.next()){
            races.add(RaceCourse.create(result, raceId));
        }
        return races;
    }
 

}
