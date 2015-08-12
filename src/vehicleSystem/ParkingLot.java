package vehicleSystem;

import java.util.HashMap;
import java.util.Map;

import vehicleSystemExceptions.CarNotParkedException;
import vehicleSystemExceptions.CarNotUnparkedException;
import vehicleSystemExceptions.ParkingLotFullException;

public class ParkingLot {
	private final int parkingLotSize;
	private Map<Token,Car> parkedCars = new HashMap<Token, Car>();
	
	public ParkingLot(int parkingLotSize){
		this.parkingLotSize=parkingLotSize;
	}
	
	public Token park(Car car) {		
		
		boolean isNotParked = !parkedCars.containsValue(car);
		if(!isParkingLotFull() && isNotParked)
		{
			Token token = new Token();
			parkedCars.put(token, car);
			return token;
		}
		else
			throw new CarNotParkedException("Car Not Parked.");
	}

	public Car unPark(Token token) {
		if(parkedCars.containsKey(token)){
			Car car = parkedCars.get(token);
			parkedCars.remove(token);
			return car;
		}			
		else
			throw new CarNotUnparkedException("Car Not In Parking Lot.");
	}
	
	public boolean isParkingLotFull()
	{
		if(parkedCars.size() >= parkingLotSize)
			throw new ParkingLotFullException("Parking Lot is full.");
		else
			return false;
	}
	

}