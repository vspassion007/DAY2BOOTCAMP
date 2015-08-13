package vehicleSystem;

import java.util.List;

public class MaximumVacantParkingLot implements ParkingLotSearchCriteria {

	@Override
	public ParkingLot getRequiredParkingLot(List<ParkingLot> parkingLots) {
		int maximumParkingLotVacancy = 0,index=0;
		for (ParkingLot parkingLot : parkingLots) {
			if (maximumParkingLotVacancy < parkingLot.getVacancy())
			{
				index = parkingLots.indexOf(parkingLot);
				maximumParkingLotVacancy = parkingLot.getVacancy();
			}
		}
		return parkingLots.get(index);
	}

}
