public class Tram extends Vehicle {
	public Tram(String name, String license, int drivingInterval, boolean isBroken) {
		super(name, license, drivingInterval, isBroken);
	}

	@Override
	public String getType() {
		return "Tram";
	}
}