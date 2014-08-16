package pigdeer.parking.base;

/**
 * Created with IntelliJ IDEA.
 * User: software
 * Date: 14-8-16
 * Time: 下午4:15
 * To change this template use File | Settings | File Templates.
 */
public class Car {

    private String number;
    private int userId;

    public Car(){

    }
    public Car(String number, int userId){
        this.setNumber(number);
        this.setUserId(userId);
    }
    public Car(String number){
        this.setNumber(number);
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public boolean equals(Car car){
        return number.equals(car.number);
    }
    public int hashCode(){
        return this.userId;
    }


}
