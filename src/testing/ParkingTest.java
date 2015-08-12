package testing;

import static org.junit.Assert.*;

import org.junit.Test;

import vehicleSystem.Car;
import vehicleSystem.ParkingLot;
import vehicleSystem.Token;
import vehicleSystemExceptions.CarNotParkedException;
import vehicleSystemExceptions.CarNotUnparkedException;
import vehicleSystemExceptions.ParkingLotFullException;

public class ParkingTest {
	
	@Test
	public void testWhetherCarParksInParkingLot() throws CarNotParkedException {
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
	
	@Test(expected = CarNotUnparkedException.class)
	public void shouldNotUnparkNonParkedCar()
	{
		Car car = new Car("101","Vikas");
		ParkingLot parkingLot = new ParkingLot(20);
		
		parkingLot.unPark(new Token());
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

	@Test(expected = CarNotParkedException.class)
	public void shouldNotParkDuplicateCars()
	{
		Car car = new Car("101","Vikas");
		ParkingLot parkingLot = new ParkingLot(2);
		Token token_1 = parkingLot.park(car);		
		Token token_2 = parkingLot.park(car);
	}
	
	@Test(expected = ParkingLotFullException.class)
	public void checkWhetherParkingLotFull()
	{
		Car car_1 = new Car("101","Vikas");		
		ParkingLot parkingLot = new ParkingLot(1);
		Token token_1 = parkingLot.park(car_1);
		
		assertEquals(true, parkingLot.isParkingLotFull());
	}
	

	@Test(expected = ParkingLotFullException.class)
	public void shouldNotParkCarsWhenParkingLotFull()
	{
		Car car = new Car("101","Vikas");
		ParkingLot parkingLot = new ParkingLot(1);
		Token token_1 = parkingLot.park(car);		
		Token token_2 = parkingLot.park(car);
	}
}
