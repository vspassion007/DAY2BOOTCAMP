package testing;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import vehicleSystem.Car;
import vehicleSystem.NotificationType;
import vehicleSystem.ParkingLot;
import vehicleSystem.ParkingLotAttendant;
import vehicleSystem.ParkingLotOwner;
import vehicleSystem.Token;
import vehicleSystemExceptions.ParkingLotNotAvailableException;

public class ParkingLotAttendantTest {

	@Test
	public void testWhetherAttendantDirectsToParkingLotSuccessfully(){
		ParkingLotOwner parkingLotOwner = new ParkingLotOwner();
		ParkingLot parkingLot_1 = new ParkingLot(1, parkingLotOwner);
		List<ParkingLot> parkingLots = new ArrayList<ParkingLot>();
		parkingLots.add(parkingLot_1);
		ParkingLotAttendant parkingLotAttendant = new ParkingLotAttendant(parkingLots);
		
		assertEquals(true, parkingLotAttendant.getParkingLot() instanceof ParkingLot);		
	}
	
	/*@Test
	public void testWhetherAttendantDirectsMultipleCarsToParkingLotSuccessfully(){
		ParkingLotOwner parkingLotOwner = new ParkingLotOwner();
		ParkingLot parkingLot_1 = new ParkingLot(1, parkingLotOwner);
		ParkingLot parkingLot_2 = new ParkingLot(1, parkingLotOwner);
		List<ParkingLot> parkingLots = new ArrayList<ParkingLot>();
		parkingLots.add(parkingLot_1);
		parkingLots.add(parkingLot_2);
		ParkingLotAttendant parkingLotAttendant = new ParkingLotAttendant(parkingLots);
		Car car_1 = new Car("101", "Vikas");
		parkingLotAttendant.getParkingLot().park(car_1);
		
		assertEquals(true, parkingLotAttendant.getParkingLot() instanceof ParkingLot);		
	}
	
	@Test(expected = ParkingLotNotAvailableException.class)
	public void testWhetherAttendantDoesntDirectsToFullParkingLot(){
		ParkingLotOwner parkingLotOwner = new ParkingLotOwner();
		ParkingLot parkingLot_1 = new ParkingLot(1, parkingLotOwner);
		List<ParkingLot> parkingLots = new ArrayList<ParkingLot>();
		parkingLots.add(parkingLot_1);
		ParkingLotAttendant parkingLotAttendant = new ParkingLotAttendant(parkingLots);
		Car car_1 = new Car("101", "Vikas");
		Car car_2 = new Car("102", "Supriya");
		parkingLotAttendant.getParkingLot().park(car_1);
		
		parkingLotAttendant.getParkingLot().park(car_2);		
	}*/

}
