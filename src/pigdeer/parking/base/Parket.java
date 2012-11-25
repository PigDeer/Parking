package pigdeer.parking.base;

import pigdeer.parking.errors.*;
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
        //维护关系
        for(ParkBoy pb : this.parkBoys){
            pb.setParket(this);
        }
    }

    public List<ParkLot> getParkingList() {
        return parkingList;
    }

    public void setParkingList(List<ParkLot> parkingList) {
        this.parkingList = parkingList;
    }

    public Parket(ArrayList<ParkLot> parkingList, ArrayList<ParkBoy> parkBoys){
        this.setParkingList(parkingList);
        this.setParkBoys(parkBoys);
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

    public ParkBoy findBoy(String character) throws NoParkBoyException {
        for(ParkBoy p: this.parkBoys){
            if(p.getCharacter() == character){
                return p;
            }
        }
        throw new NoParkBoyException();
    }

    public ParkLot findBestLot() throws NoSpaceForCarException {
        int space = 0;
        ParkLot parkLot = null;
        for(ParkLot p : this.getParkingList()){
            if(p.getAvailableSpace()>space){
                space = p.getAvailableSpace();
                parkLot = p;
            }
        }
        if(space>0) return parkLot;
        throw new NoSpaceForCarException();
    }
}
