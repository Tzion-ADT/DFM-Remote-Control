package DataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GenericDAOImpl<T> implements GenericDAO<T> {

    //*********************Private usefully Strings*********************
    private static String VERSION_ROOT_FOLDER_NAME = "ADT";
    private static String DEBUG_SUBVERSION ="subversion";
    private static String  ROOT_DRIVER_FOLDER= "D:";
    private static String  JAVA_SQLITE_DRIVER= "jdbc:sqlite:";
    private static String HKEY_LOCAL_MACHINE = "HKEY_LOCAL_MACHINE";
    private static String SOFTWARE = "SOFTWARE";
    private static String BIT_32_APP = "WOW6432Node";

    private static String DB = "db";
    private String currentVersion = null;
    //******************************************************************

    public GenericDAOImpl() {
        currentVersion = getVersion();
    }

    // Get the GUI version --> for the root folder in future
    private String getVersion() {
        //Need to change depend on the app, if it is:
        //      1. 64 bit --> SOFTWARE\ADT
        //      1. 32 bit --> SOFTWARE\WOW6432Node\ADT
        String location = null;
        if(is64BitOS()){
            location = HKEY_LOCAL_MACHINE+"\\"+SOFTWARE+"\\"+BIT_32_APP+"\\"+VERSION_ROOT_FOLDER_NAME;
        }else
            location = HKEY_LOCAL_MACHINE+"\\"+SOFTWARE+"\\"+VERSION_ROOT_FOLDER_NAME;


        String key = "RootPath";

        return readRegistry(location, key);
    }
    public static String readRegistry(String location, String key) {
        try {
            // Create the command to read the registry
            String command = "reg query \"" + location + "\" /v " + key;

            // Run the command
            Process process = Runtime.getRuntime().exec(command);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            String result = null;
            int siftToTheRight = 1;

            // Read the output of the command
            while ((line = reader.readLine()) != null) {
                if (line.contains(key)) {
                    // Split the line and get the last part, which should be the value
                    String [] allPath   = line.split("\\s+");
                    String fullRrootPath = allPath[allPath.length -1 ];

                    result = fullRrootPath.substring(fullRrootPath.indexOf("\\") + siftToTheRight);
                }
            }
            reader.close();
            return result;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    private Connection getConnection() throws SQLException {
        //The location of the database will be the same for each customer : D:\ADT\...."the version"
        Connection conn = DBconnection.getInstance(JAVA_SQLITE_DRIVER + ROOT_DRIVER_FOLDER +"\\"+this.currentVersion+"\\"+DB +"\\" , "gui.db"); //WILL BE THE CURRENT VERSION FROM REGISTRY+DB+"\\").getConnction();

        //**************************For tests only**************************//
        //Connection conn = DBconnection.getInstance(JAVA_SQLITE_DRIVER+"D:\\\\subversion\\\\Android\\\\dfm_remote_control\\\\DFM_Server\\\\db\\" , "\\gui.db").getConnction();
        return conn;
    }

    //check if the OS is 64 bit or 32 bit
    public static boolean is64BitOS() {
        String osArch = System.getProperty("os.arch");
        return osArch.contains("64");
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
                    System.out.println(nameObj);
                }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return obj;
    }


    @Override
    public void insert(T obj) {

    }

    @Override
    public T getById(int id, Class<T> clazz) {
        return null;
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
}
