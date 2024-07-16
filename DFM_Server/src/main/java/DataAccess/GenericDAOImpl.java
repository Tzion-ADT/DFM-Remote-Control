package DataAccess;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class GenericDAOImpl<T> implements GenericDAO<T> {

    private DBconnection getConnection() throws SQLException {
        return DBconnection.getInstance();
    }

    @Override
    public void insert(T obj) {

    }

    @Override
    public T getById(int id, Class<T> clazz) {
        String sql = "SELECT * FROM " + clazz.getSimpleName().toLowerCase() + " WHERE id = ?";
        T obj = null;

        try (DBconnection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                obj = clazz.getDeclaredConstructor().newInstance();
                for (Field field : clazz.getDeclaredFields()) {
                    field.setAccessible(true);
                    field.set(obj, rs.getObject(field.getName()));
                }
            }
        } catch (SQLException | ReflectiveOperationException e) {
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
