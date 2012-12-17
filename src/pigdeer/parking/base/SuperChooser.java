package pigdeer.parking.base;

import pigdeer.parking.errors.NoSpaceInBoyException;
import pigdeer.parking.interfaces.ParkLotChooser;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: software
 * Date: 12-12-2
 * Time: 下午4:02
 * To change this template use File | Settings | File Templates.
 */
public class SuperChooser implements ParkLotChooser {
    @Override
    public ParkLot choose(List<ParkLot> parkLots) throws NoSpaceInBoyException{
        double spaceRate = 0.0;
        ParkLot parkLot = null;
        for(ParkLot p : parkLots){
            if(p.getSpaceRate()>spaceRate){
                spaceRate = p.getSpaceRate();
                parkLot = p;
            }
        }
        if(spaceRate>0) return parkLot;
        throw new NoSpaceInBoyException();
    }
}
