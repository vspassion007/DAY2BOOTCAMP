package vehicleSystem;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
	private final int parkingLotSize;
	private List<Car> parkedCarList = new ArrayList();
	
	public ParkingLot(int parkingLotSize){
		this.parkingLotSize=parkingLotSize;
	}
	
	public boolean park(Car car) {
		
		if(parkedCarList.size() < parkingLotSize && !parkedCarList.contains(car))
		{
			parkedCarList.add(car);			
			return true;
		}
		return false;
	}

	public boolean unPark(Car car) {
		if(parkedCarList.contains(car))
			return car == parkedCarList.remove(parkedCarList.indexOf(car));
		else
			return false;	
	}
	

}