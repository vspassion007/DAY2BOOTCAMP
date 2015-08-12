package vehicleSystem;

public interface ParkingVacantNotification {
	void addToParkingVacantNotificationList(Subscriber subscriber);
	void notifyParkingVacantToAll();
}
