package DataAccess;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DBconnection implements AutoCloseable {
    private static Connection connectionInstance = null;

    //**********************All DB Options ****************************//
    //GUI DB
    private static String GUI_DB = "gui.db";

    //PP DB
    private static String PP_DB = "pp.db";

    //PERS DB
    private static String PERS_DB = "pers.db";
    //*****************************************************************//

    //temp --> for tests ONLY
    //private String url = "jdbc:sqlite:D:\\subversion\\Android\\dfm_remote_control\\DFM_Server\\db\\gui.db";//will contain the DB path

    private DBconnection (String DBurl , String DB) throws SQLException {
        try{
            this.connectionInstance = DriverManager.getConnection(DBurl + DB);
        }catch (SQLException ex){
            throw new SQLException("Failed to create the database connection to: ." + DBurl + DB, ex);
        }
    }

    public static Connection getInstance(String DBurl , String DB) throws SQLException {
        if(connectionInstance == null){
            new DBconnection(DBurl , DB);
        }
        else if (connectionInstance.isClosed()){
            new DBconnection(DBurl , DB);
        }

        return connectionInstance;
    }

    @Override
    public void close() throws Exception {
        if(connectionInstance != null && !connectionInstance.isClosed())
            connectionInstance.close();
    }
}
