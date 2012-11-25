package pigdeer.parking.base;

import pigdeer.parking.errors.NoCarException;
import pigdeer.parking.errors.NoCarForTicketException;
import pigdeer.parking.errors.NoSpaceException;
import pigdeer.parking.errors.NoSpaceForCarException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: software
 * Date: 12-11-25
 * Time: 下午2:37
 * To change this template use File | Settings | File Templates.
 */
public class ParkingBoy {
    private List<Parking> parkingList;

    public ParkingBoy(ArrayList<Parking> parkingList){
        this.parkingList = parkingList;
    }

    public Ticket push(Car car)  throws NoSpaceForCarException {
        Ticket ticket;
        for(Parking p : parkingList){
            try{
                ticket = p.push(car);
                return ticket;
            }catch (NoSpaceException e){
            }
        }
        throw new NoSpaceForCarException();
    }

    public Car pull(Ticket ticket) throws NoCarForTicketException {
        Car car;
        for(Parking p : parkingList){
            try{
                car = p.pull(ticket);
                return car;
            }catch (NoCarException e){
            }
        }
        throw new NoCarForTicketException();
    }
}
