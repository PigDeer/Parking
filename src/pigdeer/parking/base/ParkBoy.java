package pigdeer.parking.base;

import pigdeer.parking.errors.NoCarForTicketException;
import pigdeer.parking.errors.NoCarInLotException;
import pigdeer.parking.errors.NoSpaceForCarException;
import pigdeer.parking.errors.NoSpaceInLotException;
import pigdeer.parking.interfaces.Parking;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: software
 * Date: 12-11-25
 * Time: 下午4:38
 * To change this template use File | Settings | File Templates.
 */
public class ParkBoy implements Parking {
    private String character;
    private Parket parket;

    public ParkBoy(String character) {
        this.character = character;
    }

    public Ticket push(Car car)  throws NoSpaceForCarException {
        Ticket ticket;
        for(ParkLot p : parket.getParkingList()){
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
        for(ParkLot p : parket.getParkingList()){
            try{
                car = p.pull(ticket);
                return car;
            }catch (NoCarInLotException e){
            }
        }
        throw new NoCarForTicketException();
    }
}
