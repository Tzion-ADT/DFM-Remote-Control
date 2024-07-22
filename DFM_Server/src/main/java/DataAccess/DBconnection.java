package DataAccess;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DBconnection implements AutoCloseable {
    private static DBconnection instance = null;

    public Connection connection;
    private String url = "jdbc:sqlite:D:\\subversion\\Android\\dfm_remote_control\\DFM_Server\\db\\gui.db";//will contain the DB path

    private DBconnection () throws SQLException {
        try{
            this.connection = DriverManager.getConnection(url);
        }catch (SQLException ex){
            throw new SQLException("Failed to create the database connection.", ex);
        }
    }

    public static DBconnection getInstance() throws SQLException {
        if(instance == null){
            instance = new DBconnection();
        }
        else if (instance.getConnction().isClosed()){
            instance = new DBconnection();
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
