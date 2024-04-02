public class Trolleybus extends Vehicle {
	public Trolleybus(String name, String license, int drivingInterval, boolean isBroken) {
		super(name, license, drivingInterval, isBroken);
	}

	@Override
	public String getType() {
		return "Trolleybus";
	}
}