package pigdeer.parking.base;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: Zhutao
 * Date: 12-12-17
 * Time: 下午12:02
 * To change this template use File | Settings | File Templates.
 */
public class ParkBoyTest {
    private ParkBoy parkBoyNormal;
    private ParkBoy parkBoySmart;
    private ParkBoy parkBoySuper;

    @Before
    public void initParkBoy(){
        ArrayList<ParkLot> parkBoyNormalLots = new ArrayList<ParkLot>();
        parkBoyNormalLots.add(new ParkLot(3,"PL.011"));
        parkBoyNormalLots.add(new ParkLot(2,"PL.012"));
        parkBoyNormal = new ParkBoy(new NormalChooser(), parkBoyNormalLots, "PB.001");

        ArrayList<ParkLot> parkBoySmartLots = new ArrayList<ParkLot>();
        parkBoySmartLots.add(new ParkLot(3,"PL.021"));
        parkBoySmartLots.add(new ParkLot(2,"PL.022"));
        parkBoySmart = new ParkBoy(new SmartChooser(), parkBoySmartLots, "PB.002");

        ArrayList<ParkLot> parkBoySuperLots = new ArrayList<ParkLot>();
        parkBoySuperLots.add(new ParkLot(3,"PL.031"));
        parkBoySuperLots.add(new ParkLot(2,"PL.032"));
        parkBoySuper = new ParkBoy(new SuperChooser(), parkBoySuperLots, "PB.003");
    }

    @Test
    public void should_park_random_if_face_the_normal_boy(){
        parkBoyNormal.push(new Car());
        parkBoyNormal.push(new Car());
        assertEquals(1,parkBoyNormal.getParkLots().get(0).getAvailableSpace());
        assertEquals(2,parkBoyNormal.getParkLots().get(1).getAvailableSpace());
        parkBoyNormal.push(new Car());
        assertEquals(0,parkBoyNormal.getParkLots().get(0).getAvailableSpace());
        assertEquals(2,parkBoyNormal.getParkLots().get(1).getAvailableSpace());
    }

    @Test
    public void should_park_more_space_if_face_the_smart_boy(){
        parkBoySmart.push(new Car());
        parkBoySmart.push(new Car());
        assertEquals(1,parkBoySmart.getParkLots().get(0).getAvailableSpace());
        assertEquals(2,parkBoySmart.getParkLots().get(1).getAvailableSpace());
        parkBoySmart.push(new Car());
        assertEquals(1,parkBoySmart.getParkLots().get(0).getAvailableSpace());
        assertEquals(1,parkBoySmart.getParkLots().get(1).getAvailableSpace());
    }

    @Test
    public void should_park_highest_free_rate_if_face_the_super_boy(){
        parkBoySuper.push(new Car());
        parkBoySuper.push(new Car());
        assertEquals(2,parkBoySuper.getParkLots().get(0).getAvailableSpace());
        assertEquals(1,parkBoySuper.getParkLots().get(1).getAvailableSpace());
        parkBoySuper.push(new Car());
        assertEquals(1,parkBoySuper.getParkLots().get(0).getAvailableSpace());
        assertEquals(1,parkBoySuper.getParkLots().get(1).getAvailableSpace());
    }

    @Test
    public void should_print_info_if_super_boy_has_three_car(){
        parkBoySuper.push(new Car());
        parkBoySuper.push(new Car());
        parkBoySuper.push(new Car());
        String message =
                "停车场编号：PL.031\n" +
                "\t车位数：3\n" +
                "\t空位数：1\n" +
                "停车场编号：PL.032\n" +
                "\t车位数：2\n" +
                "\t空位数：1\n" +
                "Total车位数：5\n" +
                "Total空位数：2\n";
        assertEquals(message,parkBoySuper.printInfo());
    }

}
