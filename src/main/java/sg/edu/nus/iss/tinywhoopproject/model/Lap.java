package sg.edu.nus.iss.tinywhoopproject.model;

import org.joda.time.DateTime;

public class Lap {
    private Integer id;
    private Integer pilotId;
    private Integer raceId;
    private double time;
    
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getPilotId() {
        return pilotId;
    }
    public void setPilotId(Integer pilotId) {
        this.pilotId = pilotId;
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
}
