package pigdeer.parking.interfaces;

import pigdeer.parking.base.Car;
import pigdeer.parking.base.Ticket;

/**
 * Created with IntelliJ IDEA.
 * User: Zhutao
 * Date: 12-11-25
 * Time: 下午7:11
 * To change this template use File | Settings | File Templates.
 */
public interface ParkingInterface {

    public Ticket push(Car car);

    public Car pull(Ticket ticket);
}