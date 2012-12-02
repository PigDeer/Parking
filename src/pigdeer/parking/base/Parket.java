package pigdeer.parking.base;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: software
 * Date: 12-11-25
 * Time: 下午2:37
 * To change this template use File | Settings | File Templates.
 */
public class Parket {
    private List<ParkLot> parkingList;

    public List<ParkLot> getParkingList() {
        return parkingList;
    }

    public void setParkingList(List<ParkLot> parkingList) {
        this.parkingList = parkingList;
    }

    public Parket(ArrayList<ParkLot> parkingList){
        this.setParkingList(parkingList);
    }
}
