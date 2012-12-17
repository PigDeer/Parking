package pigdeer.parking.base;

import pigdeer.parking.interfaces.ParkLotChooser;
import pigdeer.parking.utils.PrintHelper;

import java.util.List;
import java.util.Map;

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
    public Ticket push(Car car) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Car pull(Ticket ticket) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String printInfo() {
        return printInfoWithTabs(0);
    }
    public String printInfoWithTabs(int tabs){
        String message =super.printInfoWithTabs(tabs);
        int space = super.getSpace();
        int empty = super.getEmpty();
        for(ParkBoy pb : this.getParkBoys()){
            message += PrintHelper.getParkBoyLabel(pb.getNumber(),tabs);
            message += pb.printInfoWithTabs(tabs+1);
            space += pb.getSpace();
            empty += pb.getSpace();
        }
        message += PrintHelper.getStatLabel(space, empty, tabs);
        return message;
    }
}
