package testing;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import vehicleSystem.Car;
import vehicleSystem.FirstAvailableParkingLot;
import vehicleSystem.MaximumCapacityParkingLot;
import vehicleSystem.MaximumVacantParkingLot;
import vehicleSystem.NotificationType;
import vehicleSystem.ParkingLot;
import vehicleSystem.ParkingLotAttendant;
import vehicleSystem.ParkingLotOwner;
import vehicleSystem.ParkingLotSearchCriteria;
import vehicleSystem.Token;
import vehicleSystemExceptions.ParkingLotNotAvailableException;

public class ParkingLotAttendantTest {

	@Test
	public void testWhetherAttendantDirectsToMaxVacantParkingLotSuccessfully(){
		ParkingLotOwner parkingLotOwner = new ParkingLotOwner();
		ParkingLot parkingLot_1 = new ParkingLot(1, parkingLotOwner);
		ParkingLot parkingLot_2 = new ParkingLot(3, parkingLotOwner);
		List<ParkingLot> parkingLots = new ArrayList<ParkingLot>();
		ParkingLotSearchCriteria searchCriteria = new MaximumVacantParkingLot();
		parkingLots.add(parkingLot_1);
		parkingLots.add(parkingLot_2);
		ParkingLotAttendant parkingLotAttendant = new ParkingLotAttendant(parkingLots);
		
		assertEquals(parkingLot_2, parkingLotAttendant.getParkingLot(searchCriteria));		
	}
	
	
	@Test
	public void testWhetherAttendantDirectsToFirstVacantParkingLotSuccessfully(){
		ParkingLotOwner parkingLotOwner = new ParkingLotOwner();
		ParkingLot parkingLot_1 = new ParkingLot(1, parkingLotOwner);
		ParkingLot parkingLot_2 = new ParkingLot(3, parkingLotOwner);
		List<ParkingLot> parkingLots = new ArrayList<ParkingLot>();
		ParkingLotSearchCriteria searchCriteria = new FirstAvailableParkingLot();
		parkingLots.add(parkingLot_1);
		parkingLots.add(parkingLot_2);
		ParkingLotAttendant parkingLotAttendant = new ParkingLotAttendant(parkingLots);
		
		assertEquals(parkingLot_1, parkingLotAttendant.getParkingLot(searchCriteria));		
	}
	
	
	@Test(expected = ParkingLotNotAvailableException.class)
	public void testWhetherAttendantDoesntDirectsToFullParkingLot(){
		ParkingLotOwner parkingLotOwner = new ParkingLotOwner();
		ParkingLot parkingLot_1 = new ParkingLot(1, parkingLotOwner);
		List<ParkingLot> parkingLots = new ArrayList<ParkingLot>();
		parkingLots.add(parkingLot_1);
		ParkingLotAttendant parkingLotAttendant = new ParkingLotAttendant(parkingLots);
		ParkingLotSearchCriteria searchCriteria = new FirstAvailableParkingLot();
		Car car_1 = new Car("101", "Vikas");
		Car car_2 = new Car("102", "Supriya");
		parkingLotAttendant.getParkingLot(searchCriteria).park(car_1);
		
		parkingLotAttendant.getParkingLot(searchCriteria).park(car_2);		
	}
	
	@Test
	public void testWhetherAttendantDirectsToMaxCapacityParkingLotSuccessfully(){
		ParkingLotOwner parkingLotOwner = new ParkingLotOwner();
		ParkingLot parkingLot_1 = new ParkingLot(1, parkingLotOwner);
		ParkingLot parkingLot_2 = new ParkingLot(3, parkingLotOwner);
		List<ParkingLot> parkingLots = new ArrayList<ParkingLot>();
		ParkingLotSearchCriteria searchCriteria = new MaximumCapacityParkingLot();
		parkingLots.add(parkingLot_1);
		parkingLots.add(parkingLot_2);
		ParkingLotAttendant parkingLotAttendant = new ParkingLotAttendant(parkingLots);
		
		assertEquals(parkingLot_2, parkingLotAttendant.getParkingLot(searchCriteria));		
	}
	

}
