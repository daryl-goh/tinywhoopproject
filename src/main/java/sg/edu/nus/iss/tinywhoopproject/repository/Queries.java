package sg.edu.nus.iss.tinywhoopproject.repository;

public class Queries {
    public static final String SQL_RETRIEVE_ALL_RACE_COURSES = """
            SELECT DISTINCT(r.race_name)
            FROM race_details rd, race r
            WHERE rd.race_id = r.race_id;
            """;
    public static final String SQL_RETRIEVE_RACE_PARTICAPTION_PILOTS = """
            SELECT r.race_name, p.pilot_name
            FROM race_details rd
            LEFT JOIN race r
            ON rd.race_id = r.race_id
            LEFT JOIN pilot p
            on rd.pilot_id = p.pilot_id
            WHERE r.race_id = ?;
            """;
    public static final String SQL_RETRIEVE_LAPTIMINGS_OF_PILOT_PARTICAPTION_BY_RACE= """
            SELECT p.pilot_name, l.lap_id, l.time
            FROM laps l
            LEFT JOIN race r
                on l.race_id  = r.race_id
            LEFT JOIN pilot p
                on l.pilot_id = p.pilot_id
            WHERE l.pilot_id = ? and l.race_id = ?;
            """;
            
}
