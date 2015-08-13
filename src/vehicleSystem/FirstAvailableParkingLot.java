package vehicleSystem;

import java.util.List;

public class FirstAvailableParkingLot implements ParkingLotSearchCriteria{

	@Override
	public ParkingLot getRequiredParkingLot(List<ParkingLot> parkingLots) {
		return parkingLots.get(0);
	}

}
