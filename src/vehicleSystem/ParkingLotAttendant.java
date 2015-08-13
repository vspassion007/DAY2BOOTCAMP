package vehicleSystem;

import java.util.ArrayList;
import java.util.List;

import vehicleSystemExceptions.ParkingLotNotAvailableException;

public class ParkingLotAttendant {
	private List<ParkingLot> parkingLots = new ArrayList<ParkingLot>();

	public ParkingLotAttendant(List<ParkingLot> parkingLots) {
		this.parkingLots = parkingLots;
	}

	public ParkingLot getParkingLot() {
		for(ParkingLot parkingLot: parkingLots){
			if(!parkingLot.isParkingLotFull()){
				return parkingLot;
			}
		}
		throw new ParkingLotNotAvailableException("Parking Lot Not Availble");
	}

}
