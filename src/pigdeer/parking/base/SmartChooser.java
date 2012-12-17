package pigdeer.parking.base;

import pigdeer.parking.errors.NoSpaceInBoyException;
import pigdeer.parking.interfaces.ParkLotChooser;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: software
 * Date: 12-12-2
 * Time: 下午3:24
 * To change this template use File | Settings | File Templates.
 */
public class SmartChooser implements ParkLotChooser {
    @Override
    public ParkLot choose(List<ParkLot> parkLots) throws NoSpaceInBoyException {
        int space = 0;
        ParkLot parkLot = null;
        for(ParkLot p : parkLots){
            if(p.getAvailableSpace()>space){
                space = p.getAvailableSpace();
                parkLot = p;
            }
        }
        if(space>0) return parkLot;
        throw new NoSpaceInBoyException();
    }
}
