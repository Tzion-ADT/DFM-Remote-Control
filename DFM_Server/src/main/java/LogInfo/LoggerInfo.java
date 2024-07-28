package LogInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//class that is Singelton
public class LoggerInfo {

    private static Logger loggerInfo ;
    //private ctor
    private LoggerInfo(){}

    public static Logger getLogger(){
        if(loggerInfo == null){
            loggerInfo = LoggerFactory.getLogger(LoggerInfo.class);
        }

        return loggerInfo;
    }
}
