package pigdeer.parking.base;

import org.junit.Before;
import org.junit.Test;
import pigdeer.parking.errors.NoCarForTicketException;
import pigdeer.parking.errors.NoSpaceForCarException;

import java.util.ArrayList;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertSame;

/**
 * Created with IntelliJ IDEA.
 * User: software
 * Date: 14-8-16
 * Time: 下午3:05
 * To change this template use File | Settings | File Templates.
 */
public class ParkTest {
    private Parket parket;
    private ParkBoy parkBoyNormal;
    private ParkBoy parkBoySmart;
    private ParkBoy parkBoySuper;

    @Before
    public void initPark(){
        ArrayList<ParkLot> pl = new ArrayList<ParkLot>();
        pl.add(new ParkLot(3));
        pl.add(new ParkLot(2));
        parket = new Parket(pl);
        parkBoyNormal = new ParkBoy(new NormalChooser(),parket);
        parkBoySmart = new ParkBoy(new SmartChooser(),parket);
        parkBoySuper = new ParkBoy(new SuperChooser(),parket);
    }

    @Test
    public void should_push_a_car_if_has_space() {
        Ticket ticket = parkBoyNormal.push(new Car());
        assertNotNull(ticket);
    }

    @Test
    public void should_pull_a_car_if_had_park_it(){
        Car car = new Car();
        Ticket ticket = parkBoyNormal.push(car);
        assertSame(car, parkBoyNormal.pull(ticket));
    }

    @Test (expected = NoSpaceForCarException.class)
    public void should_not_push_a_car_if_has_no_space(){
        parkBoyNormal.push(new Car());
        parkBoyNormal.push(new Car());
        parkBoyNormal.push(new Car());
        parkBoyNormal.push(new Car());
        parkBoyNormal.push(new Car());
        parkBoyNormal.push(new Car());
    }

    @Test (expected = NoCarForTicketException.class)
    public void should_not_pull_a_car_if_has_no_car(){
        Ticket ticket = new Ticket();
        parkBoyNormal.pull(ticket);
    }

    @Test
    public void should_park_random_if_face_the_normal_boy(){
        parkBoyNormal.push(new Car());
        parkBoyNormal.push(new Car());
        assertEquals(1,parket.getParkingList().get(0).getAvailableSpace());
        assertEquals(2,parket.getParkingList().get(1).getAvailableSpace());
        parkBoyNormal.push(new Car());
        assertEquals(0,parket.getParkingList().get(0).getAvailableSpace());
        assertEquals(2,parket.getParkingList().get(1).getAvailableSpace());
    }

    @Test
    public void should_park_more_space_if_face_the_smart_boy(){
        parkBoySmart.push(new Car());
        parkBoySmart.push(new Car());
        assertEquals(1,parket.getParkingList().get(0).getAvailableSpace());
        assertEquals(2,parket.getParkingList().get(1).getAvailableSpace());
        parkBoySmart.push(new Car());
        assertEquals(1,parket.getParkingList().get(0).getAvailableSpace());
        assertEquals(1,parket.getParkingList().get(1).getAvailableSpace());
    }

    @Test
    public void should_park_highest_free_rate_if_face_the_super_boy(){
        parkBoySuper.push(new Car());
        parkBoySuper.push(new Car());
        assertEquals(2,parket.getParkingList().get(0).getAvailableSpace());
        assertEquals(1,parket.getParkingList().get(1).getAvailableSpace());
        parkBoySuper.push(new Car());
        assertEquals(1,parket.getParkingList().get(0).getAvailableSpace());
        assertEquals(1,parket.getParkingList().get(1).getAvailableSpace());
    }

}
