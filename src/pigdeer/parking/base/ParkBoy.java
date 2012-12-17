package pigdeer.parking.base;

import pigdeer.parking.errors.*;
import pigdeer.parking.interfaces.ParkLotChooser;
import pigdeer.parking.interfaces.ParkingInterface;
import pigdeer.parking.utils.PrintHelper;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: software
 * Date: 12-11-25
 * Time: 下午4:38
 * To change this template use File | Settings | File Templates.
 */
public class ParkBoy implements ParkingInterface {
    private String number;
    private ParkLotChooser parkLotChooser;
    private List<ParkLot> parkLots;

    public String getNumber() {
        return number;
    }
    public void setNumber(String number) {
        this.number = number;
    }
    public ParkLotChooser getParkLotChooser() {
        return parkLotChooser;
    }
    public void setParkLotChooser(ParkLotChooser parkLotChooser) {
        this.parkLotChooser = parkLotChooser;
    }
    public List<ParkLot> getParkLots() {
        return parkLots;
    }
    public void setParkLots(List<ParkLot> parkLots) {
        this.parkLots = parkLots;
    }

    public ParkBoy(ParkLotChooser parkLotChooser, List<ParkLot> parkLots, String number) {
        this.setParkLotChooser(parkLotChooser);
        this.setParkLots(parkLots);
        this.setNumber(number);
    }

    public Ticket push(Car car)  throws NoSpaceInBoyException {
        ParkLot p = parkLotChooser.choose(this.getParkLots());
        return p.push(car);
    }

    public Car pull(Ticket ticket) throws NoCarInBoyException {
        Car car;
        for(ParkLot p : this.getParkLots()){
            try{
                car = p.pull(ticket);
                return car;
            }catch (NoCarInLotException e){
            }
        }
        throw new NoCarInBoyException();
    }

    @Override
    public String printInfo() {
        return printInfoWithTabs(0);
    }

    public String printInfoWithTabs(int tabs){
        String message = "";
        int space = 0;
        int empty = 0;
        for(ParkLot pl : this.getParkLots()){
            message += pl.printInfoWithTabs(tabs);
            space += pl.getTotalSpace();
            empty += pl.getAvailableSpace();
        }
        message += PrintHelper.getStatLabel(space,empty,tabs);
        return message;
    }

    public int getSpace(){
        int space = 0;
        for(ParkLot pl : this.getParkLots()){
            space += pl.getTotalSpace();
        }
        return space;
    }

    public int getEmpty(){
        int empty = 0;
        for(ParkLot pl : this.getParkLots()){
            empty += pl.getAvailableSpace();
        }
        return empty;
    }
}
