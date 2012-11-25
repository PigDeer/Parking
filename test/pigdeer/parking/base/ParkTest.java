package pigdeer.parking.base;

import org.junit.Before;
import org.junit.Test;
import pigdeer.parking.errors.NoCarForTicketException;
import pigdeer.parking.errors.NoSpaceForCarException;

import java.util.ArrayList;

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
    private Parket pb;

    @Before
    public void initPark(){
        ArrayList<ParkLot> pl = new ArrayList<ParkLot>();
        pl.add(new ParkLot(1));
        pl.add(new ParkLot(2));
        pb = new Parket(pl);
    }

    @Test
    public void should_push_a_car_if_has_space() {
        Ticket ticket = pb.push(new Car());
        assertNotNull(ticket);
    }

    @Test
    public void should_pull_a_car_if_had_park_it(){
        Car car = new Car();
        Ticket ticket = pb.push(car);
        assertSame(car, pb.pull(ticket));
    }

    @Test (expected = NoSpaceForCarException.class)
    public void should_not_push_a_car_if_has_no_space(){
        pb.push(new Car());
        pb.push(new Car());
        pb.push(new Car());
        pb.push(new Car());
    }

    @Test (expected = NoCarForTicketException.class)
    public void should_not_pull_a_car_if_has_no_car(){
        Ticket ticket = new Ticket();
        pb.pull(ticket);
    }

}
