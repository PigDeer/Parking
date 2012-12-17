package pigdeer.parking.base;

import pigdeer.parking.errors.NoCarForTicketException;
import pigdeer.parking.errors.NoCarInBoyException;
import pigdeer.parking.errors.NoSpaceForCarException;
import pigdeer.parking.errors.NoSpaceInBoyException;
import pigdeer.parking.interfaces.ParkLotChooser;
import pigdeer.parking.utils.PrintHelper;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Zhutao
 * Date: 12-12-17
 * Time: 下午12:23
 * To change this template use File | Settings | File Templates.
 */
public class ParkManager extends ParkBoy{

    private List<ParkBoy> parkBoys;

    public ParkManager(ParkLotChooser parkLotChooser, List<ParkLot> parkLots, String number, List<ParkBoy> parkBoys) {
        super(parkLotChooser, parkLots, number);
        this.setParkBoys(parkBoys);
    }

    public List<ParkBoy> getParkBoys() {
        return parkBoys;
    }

    public void setParkBoys(List<ParkBoy> parkBoys) {
        this.parkBoys = parkBoys;
    }

    @Override
    public Ticket push(Car car) throws NoSpaceForCarException{
        Ticket ticket = null;
        for(ParkBoy pb : this.getParkBoys()){
            try{
                ticket = pb.push(car);
                return ticket;
            }catch (NoSpaceInBoyException e){ }
        }
        try{
            ticket = super.push(car);
            return ticket;
        }catch (NoSpaceInBoyException e){ }
        throw new NoSpaceForCarException();
    }

    @Override
    public Car pull(Ticket ticket) throws NoCarForTicketException{
        Car car = null;
        for(ParkBoy pb : this.getParkBoys()){
            try{
                car = pb.pull(ticket);
                return car;
            }catch (NoCarInBoyException e){ }
        }
        try{
            car = super.pull(ticket);
            return car;
        }catch (NoCarInBoyException e){ }
        throw new NoCarForTicketException();
    }

    @Override
    public String printInfo() {
        return printInfoWithTabs(0);
    }

    public String printInfoWithTabs(int tabs){
        String message =super.printInfoWithTabsNoTotal(tabs);
        int space = super.getSpace();
        int empty = super.getEmpty();
        for(ParkBoy pb : this.getParkBoys()){
            message += PrintHelper.getParkBoyLabel(pb.getNumber(),tabs);
            message += pb.printInfoWithTabs(tabs+1);
            space += pb.getSpace();
            empty += pb.getEmpty();
        }
        message += PrintHelper.getStatLabel(space, empty, tabs);
        return message;
    }
}
