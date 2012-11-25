package pigdeer.parking.base;

import pigdeer.parking.errors.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: software
 * Date: 14-8-16
 * Time: 下午3:11
 * To change this template use File | Settings | File Templates.
 */
public class Parking {
    private int totalSpace;
    private Map<Ticket, Car> parkedCarMap = new HashMap<Ticket, Car>();

    public Parking(int totalSpace){
        this.totalSpace = totalSpace;
    }

    public int getAvailableSpace(){
       return totalSpace - parkedCarMap.size();
    }

    public Ticket push(Car car) throws NoSpaceException{
        if(getAvailableSpace()==0){
            throw new NoSpaceException();
        }
        Ticket ticket = new Ticket();
        parkedCarMap.put(ticket,car);
        return ticket;
    }

    public Car pull(Ticket ticket) throws NoCarException{
        if(!parkedCarMap.containsKey(ticket)){
            throw new NoCarException();
        }
        return parkedCarMap.remove(ticket);
    }

}
