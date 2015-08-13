package vehicleSystem;

import java.util.List;

public interface ParkingLotSearchCriteria {
	ParkingLot getRequiredParkingLot(List<ParkingLot> parkingLots);
}
