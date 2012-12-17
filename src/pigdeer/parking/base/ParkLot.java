package pigdeer.parking.base;

import pigdeer.parking.errors.*;
import pigdeer.parking.interfaces.ParkingInterface;
import pigdeer.parking.utils.PrintHelper;

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
    private String number;
    private int totalSpace;
    private Map<Ticket, Car> parkedCarMap = new HashMap<Ticket, Car>();

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Map<Ticket, Car> getParkedCarMap() {
        return parkedCarMap;
    }

    public void setParkedCarMap(Map<Ticket, Car> parkedCarMap) {
        this.parkedCarMap = parkedCarMap;
    }

    public int getTotalSpace() {
        return totalSpace;
    }

    public void setTotalSpace(int totalSpace) {
        this.totalSpace = totalSpace;
    }

    public ParkLot(int totalSpace, String number){
        this.totalSpace = totalSpace;
        this.number = number;
    }

    public int getAvailableSpace(){
       return totalSpace - parkedCarMap.size();
    }

    public double getSpaceRate() {
        return (double) getAvailableSpace() / totalSpace;
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

    @Override
    public String printInfo() {
        return printInfoWithTabs(0);
    }

    public String printInfoWithTabs(int tabs){
        return PrintHelper.getParkLotLabel(this.number,this.totalSpace,this.getAvailableSpace(),tabs);
    }

}
