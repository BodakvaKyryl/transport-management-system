public abstract class Vehicle {
	private String name;
	private String license;
	private int drivingInterval;
	private boolean isBroken;

	public Vehicle(String name, String license, int drivingInterval, boolean isBroken) {
		if (name == null || name.isEmpty()) {
			throw new IllegalArgumentException("Vehicle name cannot be null or empty");
		}
		if (license == null || license.isEmpty() || !license.matches("[A-Z]\\d{3}")) {
			throw new IllegalArgumentException(
					"Invalid license format. It should be one letter followed by three digits.");
		}
		if (drivingInterval <= 0) {
			throw new IllegalArgumentException("Driving interval must be greater than zero");
		}
		this.name = name;
		this.license = license;
		this.drivingInterval = drivingInterval;
		this.isBroken = isBroken;
	}

	public void breakdown() {
		System.out.println(name + " with license " + license + " has broken down.");
	}

	public String getName() {
		return name;
	}

	public String getLicense() {
		return license;
	}

	public void setLicense(String license) {
		if (license == null || license.isEmpty() || !license.matches("[A-Z]\\d{3}")) {
			throw new IllegalArgumentException(
					"Invalid license format. It should be one letter followed by three digits.");
		}
		this.license = license;
	}

	public int getDrivingInterval() {
		return drivingInterval;
	}

	public boolean isBroken() {
		return isBroken;
	}

	public void setBroken(boolean broken) {
		this.isBroken = broken;
	}

	public abstract String getType();
}