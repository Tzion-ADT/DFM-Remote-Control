package DataAccess;

import java.util.List;

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
//		Declaration for CRUD operations
//
//
//	Date		Name								Description
//	----------------------------------------------------------------------------
// 2024         Tzion
//
//=============================================================================

public interface GenericDAO<T> {
    void insert(T obj);
    T getById(int id, Class<T> clazz);
    public T getPropertyBydbAndColumnAndTable(String DB , String columnName, String tableName , String propertyName);
    List<T> getAll(Class<T> clazz);
    void update(T obj);
    void delete(int id, Class<T> clazz);
}
