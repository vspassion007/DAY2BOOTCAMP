package vehicleSystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import vehicleSystemExceptions.CarNotParkedException;
import vehicleSystemExceptions.CarNotUnparkedException;

public class ParkingLot implements ParkingFullNotification,ParkingVacantNotification{
	private final int parkingLotSize;
	private Map<Token, Car> parkedCars = new HashMap<Token, Car>();
	private ParkingLotOwner owner;
	private List<Subscriber> parkingFullNotificationSubscribers = new ArrayList<Subscriber>();
	private List<Subscriber> parkingVacantNotificationSubscribers = new ArrayList<Subscriber>();
	
	public ParkingLot(int parkingLotSize, ParkingLotOwner owner) {
		super();
		this.parkingLotSize = parkingLotSize;
		this.owner = owner;
		parkingFullNotificationSubscribers.add(owner);
		parkingVacantNotificationSubscribers.add(owner);
	}

	public Token park(Car car) {

		boolean isNotParked = !parkedCars.containsValue(car);
		if (!isParkingLotFull() && isNotParked) {
			Token token = new Token();
			parkedCars.put(token, car);

			if (parkedCars.size() == parkingLotSize)
				notifyParkingFullToAll();

			return token;
		} else
			throw new CarNotParkedException("Car Not Parked.");
	}
	
	@Override
	public void notifyParkingVacantToAll() {
		for (Subscriber subscriber : parkingVacantNotificationSubscribers) {
			subscriber.notification();
		}
	}

	public Car unPark(Token token) {
		if (parkedCars.containsKey(token)) {
			Car car = parkedCars.get(token);
			parkedCars.remove(token);

			if (parkedCars.size() == parkingLotSize - 1)
				notifyParkingVacantToAll();

			return car;
		} else
			throw new CarNotUnparkedException("Car Not In Parking Lot.");
	}

	public void notifyParkingFullToAll() {
		for (Subscriber subscriber : parkingFullNotificationSubscribers) {
			subscriber.notification();
		}
	}

	public boolean isParkingLotFull() {
		return parkedCars.size() >= parkingLotSize;

	}

	public void addToParkingFullNotificationList(
			Subscriber subscriber) {

		parkingFullNotificationSubscribers.add(subscriber);
	}

	public void addToParkingVacantNotificationList(Subscriber subscriber) {
		parkingVacantNotificationSubscribers.add(subscriber);
		
	}

}