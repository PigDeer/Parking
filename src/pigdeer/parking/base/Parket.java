package pigdeer.parking.base;

import pigdeer.parking.errors.NoCarInLotException;
import pigdeer.parking.errors.NoCarForTicketException;
import pigdeer.parking.errors.NoSpaceInLotException;
import pigdeer.parking.errors.NoSpaceForCarException;
import pigdeer.parking.interfaces.Parking;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: software
 * Date: 12-11-25
 * Time: 下午2:37
 * To change this template use File | Settings | File Templates.
 */
public class Parket implements Parking {
    private List<ParkLot> parkingList;
    private List<ParkBoy> parkBoys;

    public List<ParkBoy> getParkBoys() {
        return parkBoys;
    }

    public void setParkBoys(List<ParkBoy> parkBoys) {
        this.parkBoys = parkBoys;
    }

    public List<ParkLot> getParkingList() {
        return parkingList;
    }

    public void setParkingList(List<ParkLot> parkingList) {
        this.parkingList = parkingList;
    }

    public Parket(ArrayList<ParkLot> parkingList, ArrayList<ParkBoy> parkBoys){
        this.parkingList = parkingList;
        this.parkBoys = parkBoys;
    }

    public Ticket push(Car car)  throws NoSpaceForCarException {
        Ticket ticket;
        for(ParkLot p : parkingList){
            try{
                ticket = p.push(car);
                return ticket;
            }catch (NoSpaceInLotException e){
            }
        }
        throw new NoSpaceForCarException();
    }

    public Car pull(Ticket ticket) throws NoCarForTicketException {
        Car car;
        for(ParkLot p : parkingList){
            try{
                car = p.pull(ticket);
                return car;
            }catch (NoCarInLotException e){
            }
        }
        throw new NoCarForTicketException();
    }

    public ParkBoy findBoy(String normal) {
        return new ParkBoy("smart");
    }
}
