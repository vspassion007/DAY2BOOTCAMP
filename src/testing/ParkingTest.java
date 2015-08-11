package testing;

import static org.junit.Assert.*;

import org.junit.Test;

import vehicleSystem.Car;
import vehicleSystem.ParkingLot;

public class ParkingTest {
	
	@Test
	public void testWhetherCarParksInParkingLot() {
		Car car = new Car("101","Vikas");
		ParkingLot parkingLot = ParkingLot.getInstance();
		assertEquals(true, parkingLot.park(car));
	}
	

}
