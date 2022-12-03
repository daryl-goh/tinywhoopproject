package sg.edu.nus.iss.tinywhoopproject.model;

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
}
