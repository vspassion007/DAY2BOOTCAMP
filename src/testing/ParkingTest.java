package testing;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mockito.Mock;

import vehicleSystem.Car;
import vehicleSystem.FbiAgent;
import vehicleSystem.NotificationType;
import vehicleSystem.ParkingLot;
import vehicleSystem.ParkingLotAttendant;
import vehicleSystem.ParkingLotOwner;
import vehicleSystem.Token;
import vehicleSystemExceptions.CarNotParkedException;
import vehicleSystemExceptions.CarNotUnparkedException;
import vehicleSystemExceptions.ParkingLotNotAvailableException;
import static org.mockito.Mockito.*;

public class ParkingTest {
	@Test
	public void testWhetherCarParksInParkingLot() throws CarNotParkedException {
		Car car = new Car("101", "Vikas");
		ParkingLotOwner parkingLotOwner = new ParkingLotOwner();
		ParkingLot parkingLot = new ParkingLot(20, parkingLotOwner);

		assertEquals(true, parkingLot.park(car) instanceof Token);
	}

	@Test
	public void testWhetherCarUnparks() {
		Car car = new Car("101", "Vikas");
		ParkingLotOwner parkingLotOwner = new ParkingLotOwner();
		ParkingLot parkingLot = new ParkingLot(20, parkingLotOwner);
		Token token = parkingLot.park(car);

		assertEquals(car, parkingLot.unPark(token));
	}

	@Test(expected = CarNotUnparkedException.class)
	public void shouldNotUnparkNonParkedCar() {
		Car car = new Car("101", "Vikas");
		ParkingLotOwner parkingLotOwner = new ParkingLotOwner();
		ParkingLot parkingLot = new ParkingLot(20, parkingLotOwner);

		parkingLot.unPark(new Token());
	}

	@Test
	public void shouldParkUnparkPark() {
		Car car = new Car("101", "Vikas");
		ParkingLotOwner parkingLotOwner = new ParkingLotOwner();
		ParkingLot parkingLot = new ParkingLot(1, parkingLotOwner);
		Token token = parkingLot.park(car);
		car = parkingLot.unPark(token);

		assertEquals(true, parkingLot.park(car) instanceof Token);
	}

	@Test(expected = CarNotParkedException.class)
	public void shouldNotParkDuplicateCars() {
		Car car = new Car("101", "Vikas");
		ParkingLotOwner parkingLotOwner = new ParkingLotOwner();
		ParkingLot parkingLot = new ParkingLot(2, parkingLotOwner);
		Token token_1 = parkingLot.park(car);
		Token token_2 = parkingLot.park(car);
	}

	@Test
	public void shouldNotifyOwnerWhenParkingLotIsFull() {
		Car car = new Car("101", "Vikas");
		ParkingLotOwner parkingLotOwner = mock(ParkingLotOwner.class);
		ParkingLot parkingLot = new ParkingLot(1, parkingLotOwner);
		parkingLot.park(car);

		verify(parkingLotOwner).notification(NotificationType.PARKINGLOTFULL,parkingLot);
	}

	@Test
	public void shouldNotNotifyOwnerWhenParkingLotNotFull() {
		Car car = new Car("101", "Vikas");
		ParkingLotOwner parkingLotOwner = mock(ParkingLotOwner.class);
		ParkingLot parkingLot = new ParkingLot(2, parkingLotOwner);
		parkingLot.park(car);

		verify(parkingLotOwner, never()).notification(
				NotificationType.PARKINGLOTFULL,parkingLot);
	}

	@Test
	public void shouldNotNotifyOwnerWhenParkingLotAlreadyFull() {
		Car car_1 = new Car("101", "Vikas");
		Car car_2 = new Car("102", "Supriya");
		ParkingLotOwner parkingLotOwner = mock(ParkingLotOwner.class);
		ParkingLot parkingLot = new ParkingLot(1, parkingLotOwner);
		parkingLot.park(car_1);
		try {
			parkingLot.park(car_2);
		} catch (CarNotParkedException e) {

		} finally {
			verify(parkingLotOwner).notification(NotificationType.PARKINGLOTFULL,parkingLot);
		}
	}

	@Test
	public void shouldNotifyOwnerWhenParkingLotIsVacant() {
		Car car = new Car("101", "Vikas");
		ParkingLotOwner parkingLotOwner = mock(ParkingLotOwner.class);
		ParkingLot parkingLot = new ParkingLot(1, parkingLotOwner);
		Token token = parkingLot.park(car);
		parkingLot.unPark(token);

		verify(parkingLotOwner, times(1)).notification(
				NotificationType.PARKINGLOTVACANT,parkingLot);
	}

	@Test
	public void shouldNotNotifyOwnerWhenParkingLotIsVacantOnceNotified() {
		Car car_1 = new Car("101", "Vikas");
		Car car_2 = new Car("102", "Supriya");
		ParkingLotOwner parkingLotOwner = mock(ParkingLotOwner.class);
		ParkingLot parkingLot = new ParkingLot(2, parkingLotOwner);
		Token token_1 = parkingLot.park(car_1);
		Token token_2 = parkingLot.park(car_2);
		parkingLot.unPark(token_1);
		parkingLot.unPark(token_2);

		verify(parkingLotOwner, times(1)).notification(
				NotificationType.PARKINGLOTVACANT,parkingLot);
	}

	@Test
	public void shouldNotifyFbiAgentWhenParkingLotIsFull() {
		Car car_1 = new Car("101", "Vikas");
		ParkingLotOwner parkingLotOwner = mock(ParkingLotOwner.class);
		FbiAgent fbiAgent = mock(FbiAgent.class);
		ParkingLot parkingLot = new ParkingLot(1, parkingLotOwner);
		parkingLot.subscribe(NotificationType.PARKINGLOTFULL, fbiAgent);
		parkingLot.park(car_1);

		verify(fbiAgent, times(1))
				.notification(NotificationType.PARKINGLOTFULL,parkingLot);
	}

	@Test
	public void shouldNotNotifyFbiAgentWhenParkingLotIsVacant() {
		Car car_1 = new Car("101", "Vikas");
		ParkingLotOwner parkingLotOwner = new ParkingLotOwner();
		FbiAgent fbiAgent = mock(FbiAgent.class);
		ParkingLot parkingLot = new ParkingLot(2, parkingLotOwner);

		parkingLot.subscribe(NotificationType.PARKINGLOTFULL, fbiAgent);
		Token token_1 = parkingLot.park(car_1);
		parkingLot.unPark(token_1);

		verify(fbiAgent, never()).notification(NotificationType.PARKINGLOTFULL,parkingLot);
	}

	@Test
	public void shouldNotifyFbiAgentWhenParkingLotIs80percentFull() {
		ParkingLotOwner parkingLotOwner = new ParkingLotOwner();
		FbiAgent fbiAgent = mock(FbiAgent.class);
		ParkingLot parkingLot = new ParkingLot(4, parkingLotOwner);

		parkingLot.subscribe(NotificationType.PARKINGLOT80PERCENTFULL,fbiAgent);

		Car car_1 = new Car("101", "Vikas");
		parkingLot.park(car_1);
		Car car_2 = new Car("102", "Supriya");
		parkingLot.park(car_2);
		Car car_3 = new Car("103", "Tom");
		parkingLot.park(car_3);

		verify(fbiAgent, times(1)).notification(NotificationType.PARKINGLOT80PERCENTFULL,parkingLot);
	}
	
	@Test
	public void shouldNotNotifyFbiAgentWhenParkingLotIsNot80percentFull() {
		ParkingLotOwner parkingLotOwner = new ParkingLotOwner();
		FbiAgent fbiAgent = mock(FbiAgent.class);
		ParkingLot parkingLot = new ParkingLot(4, parkingLotOwner);

		parkingLot.subscribe(NotificationType.PARKINGLOT80PERCENTFULL,fbiAgent);

		Car car_1 = new Car("101", "Vikas");
		parkingLot.park(car_1);
		Car car_2 = new Car("102", "Supriya");

		verify(fbiAgent, never()).notification(NotificationType.PARKINGLOT80PERCENTFULL,parkingLot);
	}
	
	@Test
	public void shouldNotNotifyFbiAgentWhenParkingLotIs80percentFullOnceAlreadyNotified() {
		ParkingLotOwner parkingLotOwner = new ParkingLotOwner();
		FbiAgent fbiAgent = mock(FbiAgent.class);
		ParkingLot parkingLot = new ParkingLot(4, parkingLotOwner);

		parkingLot.subscribe(NotificationType.PARKINGLOT80PERCENTFULL,fbiAgent);

		Car car_1 = new Car("101", "Vikas");
		parkingLot.park(car_1);
		Car car_2 = new Car("102", "Supriya");
		parkingLot.park(car_2);
		Car car_3 = new Car("103", "Tom");
		parkingLot.park(car_3);
		Car car_4 = new Car("104", "Jerry");
		parkingLot.park(car_4);
		
		verify(fbiAgent, times(1)).notification(NotificationType.PARKINGLOT80PERCENTFULL,parkingLot);
	}

	
}
