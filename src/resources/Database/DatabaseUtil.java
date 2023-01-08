package resources.Database;

public class DatabaseUtil {
    static String PASS;
    static String DB_URL;
    static String USER;

    public DatabaseUtil() {
        DB_URL = "jdbc:mysql://localhost:3306/chat_program";
        USER = "root";
        PASS = "";
    }

    public static String getPASS() {
        return PASS;
    }

    public static String getUSER() {
        return USER;
    }

    public static String getDbUrl() {
        return DB_URL;
    }
}
