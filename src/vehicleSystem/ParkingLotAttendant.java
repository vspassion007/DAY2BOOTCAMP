package vehicleSystem;

import java.util.ArrayList;
import java.util.List;

import vehicleSystemExceptions.ParkingLotNotAvailableException;

public class ParkingLotAttendant implements Subscriber {
	private List<ParkingLot> parkingLots = new ArrayList<ParkingLot>();

	public ParkingLotAttendant(List<ParkingLot> parkingLots) {
		this.parkingLots = parkingLots;
		initSubscriptions();
	}

	public ParkingLot getParkingLot() {
		if (parkingLots.size() > 0)
			return parkingLots.get(0);
		else
			throw new ParkingLotNotAvailableException(
					"Parking Lot Not Availble");
	}
	
	private void initSubscriptions(){
		for(ParkingLot parkingLot: parkingLots){
			parkingLot.subscribe(NotificationType.PARKINGLOTFULL, this);
		}
	}

	@Override
	public void notification(NotificationType subscriberType,
			ParkingLot parkingLot) {
		parkingLots.remove(parkingLot);
	}

}
