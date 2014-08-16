package pigdeer.parking.base;

import java.util.HashSet;

/**
 * Created with IntelliJ IDEA.
 * User: software
 * Date: 14-8-16
 * Time: 下午3:11
 * To change this template use File | Settings | File Templates.
 */
public class Parking {
    private int totalSpace;
    private int currentSpace;
    private HashSet cars;

    public int getCurrentSpace() {
        return currentSpace;
    }

    public void setCurrentSpace(int currentSpace) {
        this.currentSpace = currentSpace;
    }

    public int getTotalSpace() {
        return totalSpace;
    }

    public void setTotalSpace(int totalSpace) {
        this.totalSpace = totalSpace;
    }

    public HashSet getCars() {
        return cars;
    }

    public void setCars(String[] cars) {
        for(int i=0; i<cars.length; i++){
            this.cars.add(cars[i]);
        }
    }

    public Parking(){
        this.totalSpace = 100;
        this.currentSpace = 100;
        this.cars = new HashSet();
    }

    public boolean push(String mycar) {
        if(getCurrentSpace()==0)
            return false;
        cars.add(mycar);
        this.currentSpace--;
        return true;
    }

    public boolean pull(String mycar) {
        if(getCurrentSpace()==getTotalSpace()) return false;
        if(cars.contains(mycar)){
            cars.remove(mycar);
            this.currentSpace++;
            return true;
        }
        else
            return false;
    }
}
