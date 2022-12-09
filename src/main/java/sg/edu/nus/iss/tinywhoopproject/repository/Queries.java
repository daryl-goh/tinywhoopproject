package sg.edu.nus.iss.tinywhoopproject.repository;

public class Queries {
        public static final String SQL_RETRIEVE_ALL_RACE_COURSES = """
                SELECT DISTINCT(rd.race_id), rd.closing_date, rd.number_of_laps, r.race_name
                FROM race_details rd, race r
                WHERE rd.race_id = r.race_id;
                """;
        public static final String SQL_RETRIEVE_RACE_PARTICAPTION_PILOTS = """
                SELECT rd.race_id, r.race_name, rd.pilot_id, p.pilot_name, p.drone_name
                FROM race_details rd
                LEFT JOIN race r
                ON rd.race_id = r.race_id
                LEFT JOIN pilot p
                on rd.pilot_id = p.pilot_id
                WHERE r.race_id = ?;
                """;
        public static final String SQL_RETRIEVE_ALL_PILOTS = """
                SELECT * FROM pilot;
                """;
        public static final String SQL_RETRIEVE_LAPTIMINGS_OF_PILOT_PARTICAPTION_BY_RACE= """
                SELECT p.pilot_id, p.pilot_name, l.race_id, l.time
                FROM laps l
                LEFT JOIN race r
                        on l.race_id  = r.race_id
                LEFT JOIN pilot p
                        on l.pilot_id = p.pilot_id
                WHERE l.pilot_id = ? and l.race_id = ?;
                """;
        public static final String SQL_RETRIEVE_RACE = """
                SELECT * from race WHERE race_id = ?;
                """;
        public static final String SQL_INSERT_RACE_COURSE = """
                INSERT INTO race_details (race_id, pilot_id, closing_date, number_of_laps) VALUES (?, ?, ?, ?);
                """;
        public static final String SQL_INSERT_RACE = """
                INSERT INTO race (race_name) VALUES (?);
                """;

        public static final String SQL_INSERT_PILOT = """
                INSERT INTO pilot (pilot_id, pilot_name, drone_name) VALUES (? ,?, ?);
                """;
        public static final String SQL_UPDATE_RACECOURSE = """
                UPDATE race_details rd
                JOIN race r
                        ON rd.race_id = r.race_id
                SET r.race_name = ?,  rd.closing_date = ?, rd.number_of_laps = ?
                WHERE rd.race_id = ?;
                """;
        public static final String SQL_DELETE_RACECOURSE = """
                DELETE FROM race
                WHERE race_id = ?;
                """;
        public static final String SQL_UPDATE_PILOT = """
                UPDATE pilot p
                SET p.pilot_name = ? , p.drone_name = ?
                WHERE p.pilot_id = ?;
                """;
        public static final String SQL_DELETE_PILOT = """
                DELETE FROM pilot
                WHERE pilot_id = ?;
                """;
        public static final String SQL_RETRIEVE_RACECOURSE_BY_RACEID = """
                SELECT * FROM race_details 
                WHERE race_id = ?;        
                """;
        public static final String SQL_INSERT_EXISTING_PILOT_INTO_EXISTING_RACE_COURSE = """
                INSERT INTO race_details (race_id, pilot_id, closing_date, number_of_laps)
                VALUES (?, ?, ?, ?);        
                """;

}
