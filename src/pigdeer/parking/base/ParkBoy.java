package pigdeer.parking.base;

import pigdeer.parking.errors.*;
import pigdeer.parking.interfaces.ParkLotChooser;
import pigdeer.parking.interfaces.ParkingInterface;

/**
 * Created with IntelliJ IDEA.
 * User: software
 * Date: 12-11-25
 * Time: 下午4:38
 * To change this template use File | Settings | File Templates.
 */
public class ParkBoy implements ParkingInterface {
    private ParkLotChooser parkLotChooser;
    private Parket parket;

    public ParkLotChooser getParkLotChooser() {
        return parkLotChooser;
    }

    public void setParkLotChooser(ParkLotChooser parkLotChooser) {
        this.parkLotChooser = parkLotChooser;
    }

    public Parket getParket() {
        return parket;
    }

    public void setParket(Parket parket) {
        this.parket = parket;
    }

    public ParkBoy(ParkLotChooser parkLotChooser, Parket parket) {
        this.setParkLotChooser(parkLotChooser);
        this.setParket(parket);
    }

    public Ticket push(Car car)  throws NoSpaceForCarException {
        ParkLot p = parkLotChooser.choose(parket);
        return p.push(car);
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
