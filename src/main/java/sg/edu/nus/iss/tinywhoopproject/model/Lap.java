package sg.edu.nus.iss.tinywhoopproject.model;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class Lap {
    private String pilotId;
    private String pilotName;
    private Integer raceId;
    private double time;
    
    public String getPilotId() {
        return pilotId;
    }
    public void setPilotId(String pilotId) {
        this.pilotId = pilotId;
    }
    public String getPilotName() {
        return pilotName;
    }
    public void setPilotName(String pilotName) {
        this.pilotName = pilotName;
    }
    public Integer getRaceId() {
        return raceId;
    }
    public void setRaceId(Integer raceId) {
        this.raceId = raceId;
    }
    public double getTime() {
        return time;
    }
    public void setTime(double time) {
        this.time = time;
    }

    public static Lap create (SqlRowSet rs) {
        Lap lap = new Lap();
        lap.setPilotId(rs.getString("pilot_id"));
        lap.setPilotName(rs.getString("pilot_name"));
        lap.setRaceId(rs.getInt("race_id"));
        lap.setTime(rs.getDouble("time"));
        return lap;
    }

    public static Lap create (String pilotId, Integer raceId) {
        Lap lap = new Lap();
        lap.setPilotId(pilotId);
        lap.setRaceId(raceId);
        
        return lap;
    }
}
