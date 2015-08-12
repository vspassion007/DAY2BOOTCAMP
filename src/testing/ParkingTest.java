package testing;

import static org.junit.Assert.*;

import org.junit.Test;

import vehicleSystem.Car;
import vehicleSystem.ParkingLot;
import vehicleSystem.Token;

public class ParkingTest {
	
	@Test
	public void testWhetherCarParksInParkingLot() {
		Car car = new Car("101","Vikas");
		ParkingLot parkingLot = new ParkingLot(20);
		
		assertEquals(true, parkingLot.park(car) instanceof Token);
		
	}
	@Test
	public void testWhetherCarUnparks()
	{
		Car car = new Car("101","Vikas");
		ParkingLot parkingLot = new ParkingLot(20);
		Token token = parkingLot.park(car);
		
		assertEquals(car, parkingLot.unPark(token));
	}
	@Test
	public void shouldNotUnparkNonParkedCar()
	{
		Car car = new Car("101","Vikas");
		ParkingLot parkingLot = new ParkingLot(20);
		
		assertEquals(null, parkingLot.unPark(new Token()));
	}
	
	@Test
	public void shouldParkUnparkPark()
	{
		Car car = new Car("101","Vikas");
		ParkingLot parkingLot = new ParkingLot(1);
		Token token = parkingLot.park(car);
		car = parkingLot.unPark(token);
		
		assertEquals(true,	parkingLot.park(car) instanceof Token);
	}

	@Test
	public void shouldNotParkDuplicateCars()
	{
		Car car = new Car("101","Vikas");
		ParkingLot parkingLot = new ParkingLot(1);
		Token token = parkingLot.park(car);
		
		assertEquals(null,	parkingLot.park(car));
	}
	
}
