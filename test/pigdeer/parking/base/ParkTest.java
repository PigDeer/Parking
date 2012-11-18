package pigdeer.parking.base;

import org.junit.Before;
import org.junit.Test;
import pigdeer.parking.errors.NoCarException;
import pigdeer.parking.errors.NoSpaceException;

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
    private Parking p;

    @Before
    public void initPark(){
        p = new Parking();
    }

    @Test
    public void should_push_a_car_if_has_space(){
        Ticket ticket = p.push(new Car());
        assertNotNull(ticket);
    }

    @Test
    public void should_pull_a_car_if_had_park_it(){
        Car car = new Car();
        Ticket ticket = p.push(car);
        assertSame(car,p.pull(ticket));
    }

    @Test (expected = NoSpaceException.class)
    public void should_not_push_a_car_if_has_no_space(){
        p.setTotalSpace(0);
        p.push(new Car());
    }

    @Test (expected = NoCarException.class)
    public void should_not_pull_a_car_if_has_no_car(){
        Ticket ticket = new Ticket();
        p.pull(ticket);
    }

}
