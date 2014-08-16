package pigdeer.parking.base;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

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
    public void initalPark(){
        p = new Parking();
        p.setCurrentSpace(96);
        String[] cars = {"car1","car2","car3","car4"};
        p.setCars(cars);
    }

    @Test
    public void should_push_a_car_if_has_space(){
        boolean result = p.push("car5");
        int space = p.getCurrentSpace();
        assertEquals(result, true);
        assertEquals(space,95);
    }

    @Test
    public void should_pull_a_car_if_had_park_it(){
        boolean result = p.pull("car50");
        assertEquals(result, false);

        result = p.pull("car1");
        int space = p.getCurrentSpace();
        assertEquals(result, true);
        assertEquals(space,97);
    }

    @Test
    public void should_not_push_a_car_if_has_no_space(){
        p.setCurrentSpace(0);
        boolean result = p.push("car100");
        int space = p.getCurrentSpace();
        assertEquals(result, false);
        assertEquals(space,0);
    }

    @Test
    public void should_not_pull_a_car_if_has_no_car(){
        p.setCurrentSpace(p.getTotalSpace());
        String[] cars = {};
        p.setCars(cars);
        boolean result = p.pull("car100");
        int space = p.getCurrentSpace();
        assertEquals(result, false);
        assertEquals(space,100);
    }

}
