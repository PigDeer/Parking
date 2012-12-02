package pigdeer.parking.base;

import pigdeer.parking.errors.*;
import pigdeer.parking.interfaces.ParkingInterface;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: software
 * Date: 14-8-16
 * Time: 下午3:11
 * To change this template use File | Settings | File Templates.
 */
public class ParkLot implements ParkingInterface {
    private int totalSpace;
    private Map<Ticket, Car> parkedCarMap = new HashMap<Ticket, Car>();

    public ParkLot(int totalSpace){
        this.totalSpace = totalSpace;
    }

    public int getAvailableSpace(){
       return totalSpace - parkedCarMap.size();
    }

    public Ticket push(Car car) throws NoSpaceInLotException {
        if(getAvailableSpace()==0){
            throw new NoSpaceInLotException();
        }
        Ticket ticket = new Ticket();
        parkedCarMap.put(ticket,car);
        return ticket;
    }

    public Car pull(Ticket ticket) throws NoCarInLotException {
        if(!parkedCarMap.containsKey(ticket)){
            throw new NoCarInLotException();
        }
        return parkedCarMap.remove(ticket);
    }

    public double getSpaceRate() {
        return (double) getAvailableSpace() / totalSpace;
    }
}
