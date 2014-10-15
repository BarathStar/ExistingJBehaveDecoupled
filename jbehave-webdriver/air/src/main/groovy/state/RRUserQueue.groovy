package state

import com.swacorp.dotcom.webscenarios.air.Data
import com.swacorp.dotcom.webscenarios.air.RRUser
import com.swacorp.dotcom.webscenarios.air.SiebelQA334LoyaltyData
import java.util.concurrent.LinkedBlockingQueue

public class RRUserQueue {

    private static LinkedBlockingQueue userQueue = new LinkedBlockingQueue()

    RRUserQueue(Data data){
        data.getRrUserMap().findAll{
             if(it.getKey().contains("goodUser")) {
                 putUser(it.getValue())
             }
        }
    }

    static RRUser pollUser() {
        return userQueue.poll()
    }
    
    static def putUser(RRUser user) {
        try {
            userQueue.put(user)
            return true
        } catch (InterruptedException e) {
            println("Interrupted Exception Found!!")
            return false
        }
    }
}
