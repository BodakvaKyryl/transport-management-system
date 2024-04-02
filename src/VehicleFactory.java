public class VehicleFactory {
	public static Vehicle createVehicle(String type, String license, boolean isBroken) {
		switch (type.toLowerCase()) {
			case "bus":
				return new Bus("Bus", license, 15, isBroken);
			case "trolleybus":
				return new Trolleybus("Trolleybus", license, 20, isBroken);
			case "tram":
				return new Tram("Tram", license, 10, isBroken);
			default:
				throw new IllegalArgumentException("Invalid vehicle type: " + type);
		}
	}

}
