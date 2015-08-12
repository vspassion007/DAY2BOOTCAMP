package vehicleSystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingLot {
	private final int parkingLotSize;
	//private List<Car> parkedCarList = new ArrayList();
	private Map<Token,Car> parkedCarList = new HashMap();
	
	public ParkingLot(int parkingLotSize){
		this.parkingLotSize=parkingLotSize;
	}
	
	public Token park(Car car) {
		
		if(parkedCarList.size() < parkingLotSize && !parkedCarList.containsValue(car))
		{
			Token token = new Token();
			parkedCarList.put(token, car);
			return token;
		}
		return null;
	}

	public Car unPark(Token token) {
		if(parkedCarList.containsKey(token)){
			Car car = parkedCarList.get(token);
			parkedCarList.remove(token);
			return car;
		}			
		else
			return null;
	}
	

}