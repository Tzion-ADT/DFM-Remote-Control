package DataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.lang.reflect.Method;

public class GenericDAOImpl<T> implements GenericDAO<T> {

    // Windows registry root keys
    public static final int HKEY_CURRENT_USER = 0x80000001;
    public static final int HKEY_LOCAL_MACHINE = 0x80000002;
    private static String VERSION_ROOT_FOLDER_NAME = "ADT";
    private static String  ROOT_DRIVER_FOLDER= "D:";
    private static String  JAVA_SQLITE_DRIVER= "jdbc:sqlite:";

    private static String DB = "db";

    public GenericDAOImpl() {
        getVersion();
    }

    private void getVersion() {
        String key = "HKEY_LOCAL_MACHINE\\SOFTWARE\\WOW6432Node\\ADT";
        String valueName = "IsDebug";

        String value = readString(HKEY_LOCAL_MACHINE, key, valueName);
        int x = 20;
    }

    private Connection getConnection() throws SQLException {
        //The location of the database will be the same for each customer : D:\ADT\...."the version"
        //Connection conn = DBconnection.getInstance(JAVA_SQLITE_DRIVER + ROOT_DRIVER_FOLDER +"\\"+ VERSION_ROOT_FOLDER_NAME+"\\"+______ WILL BE THE CURRENT VERSION FROM REGISTRY+DB+"\\").getConnction();

        //**************************For tests only**************************//
        Connection conn = DBconnection.getInstance(JAVA_SQLITE_DRIVER+"D:\\\\subversion\\\\Android\\\\dfm_remote_control\\\\DFM_Server\\\\db\\" , "\\gui.db").getConnction();
        return conn;
    }

    @Override
    public void insert(T obj) {

    }

    @Override
    public T getById(int id, Class<T> clazz) {
        return null;
    }


    public T getPropertyBydbAndColumnAndTable(String DB , String columnName, String tableName , String propertyName) {
        String sql = "SELECT * FROM " + tableName ;
        T obj = null;

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
                //pstmt.setString(1, "swname");
                ResultSet rs = pstmt.executeQuery();

                while (rs.next()) {
                   // if()
                    System.out.println(rs.next());
                    String nameObj = rs.getString("swname");

                }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return obj;
    }

    @Override
    public List<T> getAll(Class<T> clazz) {
        return null;
    }

    @Override
    public void update(T obj) {

    }

    @Override
    public void delete(int id, Class<T> clazz) {

    }

    // Utility method to convert a string to a null-terminated byte array
    private static byte[] toCstr(String str) {
        byte[] result = new byte[str.length() + 1];
        for (int i = 0; i < str.length(); i++) {
            result[i] = (byte) str.charAt(i);
        }
        result[str.length()] = 0; // null-terminate
        return result;
    }

    // Method to read a string value from the registry
    public static String readString(int hkey, String key, String valueName) {
        try {
            // Get the Class object for WindowsPreferences
            Class<?> clazz = Class.forName("java.util.prefs.WindowsPreferences");

            // Get the Method objects for the required methods
            Method openKey = clazz.getDeclaredMethod("WindowsRegOpenKey", int.class, byte[].class, int.class);
            openKey.setAccessible(true);
            Method closeKey = clazz.getDeclaredMethod("WindowsRegCloseKey", int.class);
            closeKey.setAccessible(true);
            Method queryValue = clazz.getDeclaredMethod("WindowsRegQueryValueEx", int.class, byte[].class);
            queryValue.setAccessible(true);

            // Open the registry key
            int[] handles = (int[]) openKey.invoke(null, hkey, toCstr(key), 0x20019);
            if (handles[1] != 0) {
                throw new IllegalAccessException("Failed to open key: " + key);
            }

            // Query the value
            byte[] valb = (byte[]) queryValue.invoke(null, handles[0], toCstr(valueName));
            String value = (valb != null ? new String(valb).trim() : null);

            // Close the registry key
            closeKey.invoke(null, handles[0]);

            return value;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
