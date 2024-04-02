public class Bus extends Vehicle {
	public Bus(String name, String license, int drivingInterval, boolean isBroken) {
		super(name, license, drivingInterval, isBroken);
	}

	@Override
	public String getType() {
		return "Bus";
	}
}