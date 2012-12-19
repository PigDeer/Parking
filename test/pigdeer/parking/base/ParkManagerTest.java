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
public class ParkManagerTest {
    private ParkManager parkManager;

    @Before
    public void initParkManager(){
        ArrayList<ParkBoy> parkBoys = new ArrayList<ParkBoy>();
		
        ArrayList<ParkLot> parkBoyNormalLots = new ArrayList<ParkLot>();
        parkBoyNormalLots.add(new ParkLot(3,"PL.011"));
        parkBoyNormalLots.add(new ParkLot(2,"PL.012"));
		ParkBoy parkBoyNormal = new ParkBoy(new NormalChooser(), parkBoyNormalLots, "PB.001");
		parkBoys.add(parkBoyNormal);

        ArrayList<ParkLot> parkBoySmartLots = new ArrayList<ParkLot>();
        parkBoySmartLots.add(new ParkLot(3,"PL.021"));
        parkBoySmartLots.add(new ParkLot(2,"PL.022"));
		ParkBoy parkBoySmart = new ParkBoy(new SmartChooser(), parkBoySmartLots, "PB.002");
		parkBoys.add(parkBoySmart);

        ArrayList<ParkLot> parkBoySuperLots = new ArrayList<ParkLot>();
        parkBoySuperLots.add(new ParkLot(3,"PL.031"));
        parkBoySuperLots.add(new ParkLot(2,"PL.032"));
		ParkBoy parkBoySuper = new ParkBoy(new SuperChooser(), parkBoySuperLots, "PB.003");
        parkBoys.add(parkBoySuper);

        ArrayList<ParkLot> parkManagerLots = new ArrayList<ParkLot>();
        parkManagerLots.add(new ParkLot(3,"PL.001"));
        parkManagerLots.add(new ParkLot(2,"PL.002"));

        parkManager = new ParkManager(new NormalChooser(),parkManagerLots,"PM.001",parkBoys);

    }

	@Test
	public void should_park_random_if_command_normal_boy_push_car() {
        Ticket ticket1 = parkManager.command(parkBoyNormal, new Car());
		Ticket ticket2 = parkManager.command(parkBoyNormal, new Car());
        assertEquals(1,parkBoyNormal.getParkLots().get(0).getAvailableSpace());
        assertEquals(2,parkBoyNormal.getParkLots().get(1).getAvailableSpace());
        Ticket ticket3 = parkManager.command(parkBoyNormal, new Car());
        assertEquals(0,parkBoyNormal.getParkLots().get(0).getAvailableSpace());
        assertEquals(2,parkBoyNormal.getParkLots().get(1).getAvailableSpace());
		assertNotNull(ticket1);
		assertNotNull(ticket2);
		assertNotNull(ticket3);
    }

	@Test
	public void should_park_more_space_if_command_smart_boy_push_car() {
        Ticket ticket1 = parkManager.command(parkBoySmart, new Car());
		Ticket ticket2 = parkManager.command(parkBoySmart, new Car());
        assertEquals(1,parkBoySmart.getParkLots().get(0).getAvailableSpace());
        assertEquals(2,parkBoySmart.getParkLots().get(1).getAvailableSpace());
        Ticket ticket3 = parkManager.command(parkBoySmart, new Car());
        assertEquals(0,parkBoySmart.getParkLots().get(0).getAvailableSpace());
        assertEquals(2,parkBoySmart.getParkLots().get(1).getAvailableSpace());
		assertNotNull(ticket1);
		assertNotNull(ticket2);
		assertNotNull(ticket3);
    }

	@Test
	public void should_park_highest_free_rate_if_command_super_boy_push_car() {
        Ticket ticket1 = parkManager.command(parkBoySuper, new Car());
		Ticket ticket2 = parkManager.command(parkBoySuper, new Car());
        assertEquals(1,parkBoySuper.getParkLots().get(0).getAvailableSpace());
        assertEquals(2,parkBoySuper.getParkLots().get(1).getAvailableSpace());
        Ticket ticket3 = parkManager.command(parkBoySuper, new Car());
        assertEquals(0,parkBoySuper.getParkLots().get(0).getAvailableSpace());
        assertEquals(2,parkBoySuper.getParkLots().get(1).getAvailableSpace());
		assertNotNull(ticket1);
		assertNotNull(ticket2);
		assertNotNull(ticket3);
    }

    @Test
    public void should_push_a_car_by_himself_if_has_space() {
		Ticket ticket1 = parkManager.push(new Car());
        Ticket ticket2 = parkManager.push(new Car());
        assertEquals(1,parkManager.getParkLots().get(0).getAvailableSpace());
        assertEquals(2,parkManager.getParkLots().get(1).getAvailableSpace());
        Ticket ticket3 = parkManager.push(new Car());
        assertEquals(0,parkManager.getParkLots().get(0).getAvailableSpace());
        assertEquals(2,parkManager.getParkLots().get(1).getAvailableSpace());
		assertNotNull(ticket1);
		assertNotNull(ticket2);
		assertNotNull(ticket3);
    }

    @Test
    public void should_pull_a_car_if_had_park_it(){
        Car car = new Car();
        Ticket ticket = parkManager.push(car);
        assertSame(car, parkManager.pull(ticket));
    }

    @Test (expected = NoSpaceInBoyException.class)
    public void should_not_push_a_car_if_has_no_space(){
        for(int i=0;i<5;i++){
            parkManager.push(new Car());
        }
        parkManager.push(new Car());
    }

    @Test (expected = NoCarForTicketException.class)
    public void should_not_pull_a_car_if_has_no_car(){
        Ticket ticket = new Ticket();
        parkManager.pull(ticket);
    }

    @Test
    public void should_print_info_has_one_car(){
        Car car = new Car();
        parkManager.push(car);
        String message =
                "停车场编号：PL.001\n" +
                "\t车位数：3\n" +
                "\t空位数：3\n" +
                "停车场编号：PL.002\n" +
                "\t车位数：2\n" +
                "\t空位数：2\n" +
                "停车仔编号：PB.001\n" +
                    "\t停车场编号：PL.011\n" +
                    "\t\t车位数：3\n" +
                    "\t\t空位数：2\n" +
                    "\t停车场编号：PL.012\n" +
                    "\t\t车位数：2\n" +
                    "\t\t空位数：2\n" +
                    "\tTotal车位数：5\n" +
                    "\tTotal空位数：4\n" +
                "停车仔编号：PB.002\n" +
                    "\t停车场编号：PL.021\n" +
                    "\t\t车位数：3\n" +
                    "\t\t空位数：3\n" +
                    "\t停车场编号：PL.022\n" +
                    "\t\t车位数：2\n" +
                    "\t\t空位数：2\n" +
                    "\tTotal车位数：5\n" +
                    "\tTotal空位数：5\n" +
                "停车仔编号：PB.003\n" +
                    "\t停车场编号：PL.031\n" +
                    "\t\t车位数：3\n" +
                    "\t\t空位数：3\n" +
                    "\t停车场编号：PL.032\n" +
                    "\t\t车位数：2\n" +
                    "\t\t空位数：2\n" +
                    "\tTotal车位数：5\n" +
                    "\tTotal空位数：5\n" +
                "Total车位数：20\n" +
                "Total空位数：19\n";
        assertEquals(message,parkManager.printInfo());
    }

}
