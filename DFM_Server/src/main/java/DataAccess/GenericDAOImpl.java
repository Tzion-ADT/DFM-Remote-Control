package DataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class GenericDAOImpl<T> implements GenericDAO<T> {

    private static String VERSION_ROOT_FOLDER_NAME = "ADT";
    private static String  ROOT_DRIVER_FOLDER= "D:";
    private static String  JAVA_SQLITE_DRIVER= "jdbc:sqlite:";

    private static String DB = "db";

    public GenericDAOImpl() {
        getVersion();
    }

    private void getVersion() {

    }

    private Connection getConnection() throws SQLException {
        //The location of the database will be the same for each customer : D:\ADT\...."the version"
        //Connection conn = DBconnection.getInstance(JAVA_SQLITE_DRIVER + ROOT_DRIVER_FOLDER +"\\"+ VERSION_ROOT_FOLDER_NAME+"\\"+______ WILL BE THE CURRENT VERSION FROM REGISTRY+DB+"\\").getConnction();

        //**************************For tests only**************************//
        Connection conn = DBconnection.getInstance(JAVA_SQLITE_DRIVER+"D:\\\\subversion\\\\Android\\\\dfm_remote_control\\\\DFM_Server\\\\db\\\\gui.db").getConnction();
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
}
