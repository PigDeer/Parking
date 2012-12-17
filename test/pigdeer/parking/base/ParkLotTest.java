package pigdeer.parking.base;

import org.junit.Before;
import org.junit.Test;
import pigdeer.parking.errors.NoCarInLotException;
import pigdeer.parking.errors.NoSpaceInLotException;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertSame;

/**
 * Created with IntelliJ IDEA.
 * User: Zhutao
 * Date: 12-12-17
 * Time: 下午12:02
 * To change this template use File | Settings | File Templates.
 */
public class ParkLotTest {
    private ParkLot parkLot;

    @Before
    public void initParkLot(){
        parkLot = new ParkLot(3,"No.001");
    }

    @Test
    public void should_push_a_car_if_has_space() {
        Ticket ticket = parkLot.push(new Car());
        assertNotNull(ticket);
    }

    @Test
    public void should_pull_a_car_if_had_park_it(){
        Car car = new Car();
        Ticket ticket = parkLot.push(car);
        assertSame(car, parkLot.pull(ticket));
    }

    @Test (expected = NoSpaceInLotException.class)
    public void should_not_push_a_car_if_has_no_space(){
        parkLot.push(new Car());
        parkLot.push(new Car());
        parkLot.push(new Car());
        parkLot.push(new Car());
    }

    @Test (expected = NoCarInLotException.class)
    public void should_not_pull_a_car_if_has_no_car(){
        Ticket ticket = new Ticket();
        parkLot.pull(ticket);
    }

    @Test
    public void should_print_info_has_one_car(){
        Car car = new Car();
        parkLot.push(car);
        String message = "停车场编号：No.001\n" +"\t车位数：3\n" +"\t空位数：2\n";
        assertEquals(message,parkLot.printInfo());
    }
}
