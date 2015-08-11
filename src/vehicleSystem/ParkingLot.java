package vehicleSystem;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
	private static ParkingLot parkingLotReference = new ParkingLot();
	private final int parkingLotSize = 2;
	private int currentSize = 0;
	private List<Car> parkedCarList = new ArrayList();
	
	private ParkingLot(){}
	
	public static ParkingLot getInstance(){
		return parkingLotReference;
	}

	public boolean park(Car car) {
		if(currentSize < parkingLotSize)
		{
			currentSize++;
			parkedCarList.add(car);
			return true;
		}
		return false;
	}
}
