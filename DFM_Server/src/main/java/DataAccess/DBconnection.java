package DataAccess;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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
//		Definition for Singleton connection to DB for every client there is only on instance
//
//
//	Date		Name								Description
//	----------------------------------------------------------------------------
// 2024         Tzion
//
//=============================================================================

public class DBconnection implements AutoCloseable {
    private static Connection connectionInstance = null;
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
