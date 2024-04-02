import java.util.List;

public class Route {
	private String number;
	private List<Vehicle> vehicles;

	public Route(String number, List<Vehicle> vehicles) {
		this.number = number;
		this.vehicles = vehicles;
	}

	public String getNumber() {
		return number;
	}

	public List<Vehicle> getVehicles() {
		return vehicles;
	}

	public void addVehicle(Vehicle vehicle) {
		vehicles.add(vehicle);
	}
}