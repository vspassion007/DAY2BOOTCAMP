package vehicleSystem;

import java.util.List;

public class MaximumCapacityParkingLot implements ParkingLotSearchCriteria{

	@Override
	public ParkingLot getRequiredParkingLot(List<ParkingLot> parkingLots) {
		int maximumParkingLotVacancy = 0,index=0;
		for (ParkingLot parkingLot : parkingLots) {
			if (maximumParkingLotVacancy < parkingLot.getCapacity())
			{
				index = parkingLots.indexOf(parkingLot);
				maximumParkingLotVacancy = parkingLot.getCapacity();
			}
		}
		return parkingLots.get(index);
	}

}
