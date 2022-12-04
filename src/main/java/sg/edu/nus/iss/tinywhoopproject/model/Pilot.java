package sg.edu.nus.iss.tinywhoopproject.model;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class Pilot {
    private Integer id;
    private String pilotName;
    private String droneName;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getPilotName() {
        return pilotName;
    }
    public void setPilotName(String pilotName) {
        this.pilotName = pilotName;
    }
    public String getDroneName() {
        return droneName;
    }
    public void setDroneName(String droneName) {
        this.droneName = droneName;
    }

    public static Pilot create (SqlRowSet rs){
        Pilot p = new Pilot();
        p.setId(rs.getInt("pilot_id"));
        p.setPilotName(rs.getString("pilot_name"));
        p.setDroneName(rs.getString("drone_name"));
        return p;
    }
}
