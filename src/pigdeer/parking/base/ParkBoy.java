package pigdeer.parking.base;

import pigdeer.parking.errors.NoCarForTicketException;
import pigdeer.parking.errors.NoCarInLotException;
import pigdeer.parking.errors.NoSpaceForCarException;
import pigdeer.parking.errors.NoSpaceInLotException;
import pigdeer.parking.interfaces.ParkingInterface;

/**
 * Created with IntelliJ IDEA.
 * User: software
 * Date: 12-11-25
 * Time: 下午4:38
 * To change this template use File | Settings | File Templates.
 */
public class ParkBoy implements ParkingInterface {
    public static final String C_SMART = "smart";
    public static final String C_NORMAL = "normal";

    private String character;
    private Parket parket;

    public ParkBoy(String character) {
        this.setCharacter(character);
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public Parket getParket() {
        return parket;
    }

    public void setParket(Parket parket) {
        this.parket = parket;
    }

    public Ticket push(Car car)  throws NoSpaceForCarException {
        if(this.getCharacter()==C_SMART){
            return parket.findBestLot().push(car);
        }else{
            for(ParkLot p : parket.getParkingList()){
                try{
                    return p.push(car);
                }catch (NoSpaceInLotException e){
                }
            }
            throw new NoSpaceForCarException();
        }
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
