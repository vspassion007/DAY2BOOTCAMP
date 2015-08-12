package vehicleSystem;

public interface ParkingFullNotification {
	void addToParkingFullNotificationList(
			Subscriber subscriber);
	void notifyParkingFullToAll();
}
