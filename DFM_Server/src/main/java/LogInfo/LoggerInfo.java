package LogInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
//		Definition Logger class for tracking info amd error flow
//
//
//	Date		Name								Description
//	----------------------------------------------------------------------------
// 2024         Tzion
//
//=============================================================================

//Logger class , using it to create app.log file
public class LoggerInfo {

    private static Logger loggerInfo ;
    //private ctor
    private LoggerInfo(){}

    public static Logger getLogger(){
        if(loggerInfo == null){
            loggerInfo = LoggerFactory.getLogger(LoggerInfo.class);
            loggerInfo.info("********************Logger Instance created********************");
        }

        return loggerInfo;
    }
}
