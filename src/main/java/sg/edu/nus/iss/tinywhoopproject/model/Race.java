package sg.edu.nus.iss.tinywhoopproject.model;

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
}
