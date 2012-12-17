package pigdeer.parking.interfaces;

import pigdeer.parking.base.ParkLot;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: software
 * Date: 12-12-2
 * Time: 下午3:20
 * To change this template use File | Settings | File Templates.
 */
public interface ParkLotChooser {
    public ParkLot choose(List<ParkLot> parkLots);
}
