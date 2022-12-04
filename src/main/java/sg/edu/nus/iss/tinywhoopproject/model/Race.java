package sg.edu.nus.iss.tinywhoopproject.model;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class Race {
    private Integer id;
    private String raceName;

    public Race(){

    }

    public Race(String raceName){
        this.raceName = raceName;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getRaceName() {
        return raceName;
    }
    public void setRaceName(String raceName) {
        this.raceName = raceName;
    }
    
    public static Race create (SqlRowSet rs) {
        Race rc = new Race();
        rc.setId(rs.getInt("race_id"));
        rc.setRaceName(rs.getString("race_name"));
        return rc;
    }
}
