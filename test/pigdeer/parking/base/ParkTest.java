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

    @Before
    public void initPark(){
        ArrayList<ParkLot> pl = new ArrayList<ParkLot>();
        pl.add(new ParkLot(10));
        pl.add(new ParkLot(10));
        ArrayList<ParkBoy> pb = new ArrayList<ParkBoy>();
        pb.add(new ParkBoy("smart"));
        pb.add(new ParkBoy("normal"));
        parket = new Parket(pl,pb);
    }

    @Test
    public void should_push_a_car_if_has_space() {
        Ticket ticket = parket.push(new Car());
        assertNotNull(ticket);
    }

    @Test
    public void should_pull_a_car_if_had_park_it(){
        Car car = new Car();
        Ticket ticket = parket.push(car);
        assertSame(car, parket.pull(ticket));
    }

    @Test (expected = NoSpaceForCarException.class)
    public void should_not_push_a_car_if_has_no_space(){
        parket.push(new Car());
        parket.push(new Car());
        parket.push(new Car());
        parket.push(new Car());
    }

    @Test (expected = NoCarForTicketException.class)
    public void should_not_pull_a_car_if_has_no_car(){
        Ticket ticket = new Ticket();
        parket.pull(ticket);
    }

    @Test
    public void should_park_random_if_face_the_old_boy(){
        ParkBoy pb = parket.findBoy("normal");
        pb.push(new Car());
        pb.push(new Car());
        assertEquals(8,parket.getParkingList().get(0).getAvailableSpace());
        assertEquals(10,parket.getParkingList().get(1).getAvailableSpace());
    }

    @Test
    public void should_park_more_space_if_face_the_smart_boy(){
        ParkBoy pb = parket.findBoy("smart");
        pb.push(new Car());
        pb.push(new Car());
        assertEquals(9,parket.getParkingList().get(0).getAvailableSpace());
        assertEquals(9,parket.getParkingList().get(1).getAvailableSpace());
    }

}
