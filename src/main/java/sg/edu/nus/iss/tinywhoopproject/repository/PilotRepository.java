package sg.edu.nus.iss.tinywhoopproject.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.tinywhoopproject.model.Pilot;

import static sg.edu.nus.iss.tinywhoopproject.repository.Queries.*;

@Repository
public class PilotRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    public List<Pilot> getAllPilots(){
        List<Pilot> pilots = new ArrayList<>();

        SqlRowSet result = jdbcTemplate.queryForRowSet(SQL_RETRIEVE_ALL_PILOTS);
        
        while (result.next()){
            pilots.add(Pilot.create(result));
        }
        return pilots;
    }
}
