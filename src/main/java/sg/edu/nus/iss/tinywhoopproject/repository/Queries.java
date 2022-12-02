package sg.edu.nus.iss.tinywhoopproject.repository;

public class Queries {
    public static String SQL_SELECT_RACE_COURSE = """
            SELECT * FROM race_course;
            """;
    public static String SQL_SELECT_RACE_PARTICAPTION_WITH_PILOTS = """
            SELECT r.race_name, p.pilot_id, p.pilot_name
            FROM race_course r, pilot p 
            WHERE r.pilot_id = p.pilot_id;
            """;
    public static String SQL = """
            
            """;
}
