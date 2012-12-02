package pigdeer.parking.base;

import pigdeer.parking.errors.NoSpaceForCarException;
import pigdeer.parking.interfaces.ParkLotChooser;

/**
 * Created with IntelliJ IDEA.
 * User: software
 * Date: 12-12-2
 * Time: 下午3:32
 * To change this template use File | Settings | File Templates.
 */
public class NormalChooser implements ParkLotChooser {
    @Override
    public ParkLot choose(Parket parket) throws NoSpaceForCarException{
        for(ParkLot p : parket.getParkingList()){
            if(p.getAvailableSpace()>0){
                return p;
            }
        }
        throw new NoSpaceForCarException();
    }
}
