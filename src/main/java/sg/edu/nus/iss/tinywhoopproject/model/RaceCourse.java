package sg.edu.nus.iss.tinywhoopproject.model;

import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class RaceCourse {
    private Integer id;
    private Race race;
    private List<Pilot> pilot;
    private DateTime closingDate;
    private Integer numberOfLaps;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Race getRace() {
        return race;
    }
    public void setRace(Race race) {
        this.race = race;
    }
    public List<Pilot> getPilot() {
        return pilot;
    }
    public void setPilot(List<Pilot> pilot) {
        this.pilot = pilot;
    }
    public DateTime getClosingDate() {
        return closingDate;
    }
    public void setClosingDate(DateTime closingDate) {
        this.closingDate = closingDate;
    }
    public Integer getNumberOfLaps() {
        return numberOfLaps;
    }
    public void setNumberOfLaps(Integer numberOfLaps) {
        this.numberOfLaps = numberOfLaps;
    }

    public static RaceCourse create (SqlRowSet raceCourseResult, List<Pilot> pilots) {
        RaceCourse rc = new RaceCourse();
        rc.setId(raceCourseResult.getInt("race_id"));
        rc.setRace(new Race(raceCourseResult.getString("race_name")));
        rc.setPilot(pilots);
        String dateTime = raceCourseResult.getString("closing_date");
        // Format for input
        DateTimeFormatter dtf = DateTimeFormat.forPattern("yyyy-MM-dd");
        // Parsing the date
        DateTime jodatime = dtf.parseDateTime(dateTime);
        rc.setClosingDate(jodatime);
        rc.setNumberOfLaps(raceCourseResult.getInt("number_of_laps"));
     

      


        return rc;
    }

}
