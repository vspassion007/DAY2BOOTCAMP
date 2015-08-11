package testing;

import static org.junit.Assert.*;

import org.junit.Test;

import vehicleSystem.Car;
import vehicleSystem.ParkingLot;

public class ParkingTest {
	
	@Test
	public void testWhetherCarParksInParkingLot() {
		Car car = new Car("101","Vikas");
		ParkingLot parkingLot = new ParkingLot(20);
		assertEquals(true, parkingLot.park(car));
		
	}
	@Test
	public void testWhetherCarUnparks()
	{
		Car car = new Car("101","Vikas");
		ParkingLot parkingLot = new ParkingLot(20);
		parkingLot.park(car);
		assertEquals(true, parkingLot.unPark(car));
	}
	@Test
	public void shouldNotUnparkNonParkedCar()
	{
		Car car = new Car("101","Vikas");
		ParkingLot parkingLot = new ParkingLot(20);
		assertEquals(false, parkingLot.unPark(car));
	}
	
	@Test
	public void shouldParkUnparkPark()
	{
		Car car = new Car("101","Vikas");
		ParkingLot parkingLot = new ParkingLot(1);
		parkingLot.park(car);
		parkingLot.unPark(car);
		
		assertEquals(true,	parkingLot.park(car));
	}

	@Test
	public void shouldNotParkDuplicateCars()
	{
		Car car = new Car("101","Vikas");
		ParkingLot parkingLot = new ParkingLot(1);
		parkingLot.park(car);
		
		assertEquals(false,	parkingLot.park(car));
	}
	
}
