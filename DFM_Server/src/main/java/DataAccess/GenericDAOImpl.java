package DataAccess;

import LogInfo.LoggerInfo;
import org.slf4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

////////////////////////////////////////////////////////////////////////////////
//
//  COPYRIGHT (c) 2024 ADT, INC.
//
//  This software is the property of ADT Industries, Inc.
//  Any reproduction or distribution to a third party is
//  strictly forbidden unless written permission is given by an
//  authorized agent of ADT.
//
//  DESCRIPTION
//		Implementation for CRUD operations
//
//
//	Date		Name								Description
//	----------------------------------------------------------------------------
// 2024         Tzion
//
//=============================================================================

public class GenericDAOImpl<T> implements GenericDAO<T> {

    //*********************Private usefully Strings*********************
    //SQLIte driver:
    private static String  JAVA_SQLITE_DRIVER= "jdbc:sqlite:";

    //Folders:
    private static String DB = "db";
    private static String VERSION_ROOT_FOLDER_NAME = "ADT";
    private static String DEBUG_SUBVERSION ="subversion";
    private static String  ROOT_DRIVER_FOLDER= "D:";

    //Registry path:
    private static String HKEY_LOCAL_MACHINE = "HKEY_LOCAL_MACHINE";
    private static String SOFTWARE = "SOFTWARE";
    private static String APP_32_BIT = "WOW6432Node";
    private static String key = "RootPath";

    private String currentVersion = null;
    //******************************************************************

    //************************DB Options ******************************//
    //GUI DB
    private static final String GUI_DB = "gui.db";

    //PP DB
    private static final String PP_DB = "pp.db";

    //PERS DB
    private static final String PERS_DB = "pers.db";

    private static final String LOG_DB = "log.db";
    //*****************************************************************//
    //************************LoggerDebug******************************//
    private Logger loggerInfo;
    //*****************************************************************//


    public GenericDAOImpl() {
        //Single instance
        loggerInfo = LoggerInfo.getLogger();

        currentVersion = getVersion();
    }

    // Get GUI version
    private String getVersion() {
        String result= "";
        String location  = "";

        try {
            //check if GUI is 64 bit
            location = HKEY_LOCAL_MACHINE + "\\" + SOFTWARE + "\\" + VERSION_ROOT_FOLDER_NAME;
            String is64 = readRegistry(location, key);
            //there is chance that it is not 64 bit
            //doing this only to get some path to the version
            //will check in the next lines in 100% if it is 32 or 64 bit application
            if(is64 == null){
                //set it to 32 bit path
                location = HKEY_LOCAL_MACHINE + "\\" + SOFTWARE + "\\" + APP_32_BIT + "\\" + VERSION_ROOT_FOLDER_NAME;
            }

            //Need to change depend on the app version and the OS:
            //      1. 64 bit --> SOFTWARE\ADT
            //      1. 32 bit --> SOFTWARE\WOW6432Node\ADT
            //if (isApp_64Bit(ROOT_DRIVER_FOLDER +"\\"+ readRegistry(location, key)+"\\src\\NewGUI\\bin\\Release\\NewGUI.exe")) {
            //    loggerInfo.info("");
            //    location = HKEY_LOCAL_MACHINE + "\\" + SOFTWARE + "\\" + APP_32_BIT + "\\" + VERSION_ROOT_FOLDER_NAME;
            //} else {
            //    location = HKEY_LOCAL_MACHINE + "\\" + SOFTWARE + "\\" + VERSION_ROOT_FOLDER_NAME;
            //}

            loggerInfo.info("Get data from the registry successfully ");
            loggerInfo.info("getVersion()");

            result = readRegistry(location, key);
        }
        catch (Exception ex){
            loggerInfo.error("error in GenericDAOImpl class --> getVersion() --> readRegistry");
        }
        return result;
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
    private Connection getConnection() {
        //The location of the database will be the same for each customer : D:\ADT\...."the version"
        Connection conn = null;
        //chooseDb(conn);

        //**************************For tests only**************************//
        //Connection conn = DBconnection.getInstance(JAVA_SQLITE_DRIVER+"D:\\\\subversion\\\\Android\\\\dfm_remote_control\\\\DFM_Server\\\\db\\" , "\\gui.db").getConnction();
        return conn;
    }

    private void chooseDb(Connection conn ,String DB) {
        String chosenDB = "";

        try {
            switch (DB){
                case GUI_DB:  chosenDB = new String (GUI_DB);  break;
                case PERS_DB: chosenDB = new String (PERS_DB); break;
                case PP_DB:   chosenDB = new String (PP_DB);   break;
                case LOG_DB:  chosenDB = new String (LOG_DB);  break;

                default: break;
            }

            if(!chosenDB.isEmpty())
                conn = DBconnection.getInstance(JAVA_SQLITE_DRIVER + ROOT_DRIVER_FOLDER + "\\" + this.currentVersion + "\\" + DB + "\\", chosenDB); //WILL BE THE CURRENT VERSION FROM REGISTRY+DB+"\\").getConnction();

        }catch (Exception ex){
            //To Do --> Need to handle exception 25.07.24 - Tzion
        }
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
