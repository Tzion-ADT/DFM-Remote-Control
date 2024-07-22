package DataAccess;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DBconnection implements AutoCloseable {
    private static DBconnection instance = null;

    //**********************All DB Options ****************************//
    //GUI DB
    private static String GUI_DB = "gui.db";

    //PP DB
    private static String PP_DB = "gui.db";

    //PERS DB
    private static String PERS_DB = "gui.db";
    //*****************************************************************//

    public Connection connection;

    //temp --> for tests ONLY
    //private String url = "jdbc:sqlite:D:\\subversion\\Android\\dfm_remote_control\\DFM_Server\\db\\gui.db";//will contain the DB path

    private DBconnection (String DBurl , String DB) throws SQLException {
        try{
            this.connection = DriverManager.getConnection(DBurl);
        }catch (SQLException ex){
            throw new SQLException("Failed to create the database connection.", ex);
        }
    }

    public static DBconnection getInstance(String DBurl) throws SQLException {
        if(instance == null){
            instance = new DBconnection(DBurl , DB);
        }
        else if (instance.getConnction().isClosed()){
            instance = new DBconnection(DBurl , DB);
        }

        return instance;
    }

    public Connection getConnction() {
        return connection;
    }

    @Override
    public void close() throws Exception {
        if(connection != null && !connection.isClosed())
            instance.getConnction().close();
    }
}
