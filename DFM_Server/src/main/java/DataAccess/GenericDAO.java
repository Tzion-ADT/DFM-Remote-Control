package DataAccess;

import java.util.List;

public interface GenericDAO<T> {
    void insert(T obj);
    T getById(int id, Class<T> clazz);
    public T getPropertyBydbAndColumnAndTable(String DB , String columnName, String tableName , String propertyName);
    List<T> getAll(Class<T> clazz);
    void update(T obj);
    void delete(int id, Class<T> clazz);
}
