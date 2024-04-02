import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class App {
	public static void main(String[] args) {
		List<Vehicle> vehicles = new ArrayList<>();
		List<Route> routes = new ArrayList<>();

		Scanner scanner = new Scanner(System.in);
		Random random = new Random();

		while (true) {
			System.out.println("\nMenu:");
			System.out.println("1. Create route");
			System.out.println("2. Create transport");
			System.out.println("3. Assign transport to route");
			System.out.println("4. Edit transport status");
			System.out.println("5. View routes and transport");
			System.out.println("6. Exit");

			int choice;
			if (args.length > 0 && args[0].equals("random")) {
				choice = random.nextInt(6) + 1;
				System.out.println("Random choice: " + choice);
			} else {
				System.out.print("Enter your choice: ");
				choice = scanner.nextInt();
			}

			switch (choice) {
				case 1:
					System.out.println("Creating route");
					System.out.print("Enter route number: ");
					String routeNumber = scanner.next();
					Route route = new Route(routeNumber, new ArrayList<>());
					routes.add(route);
					break;
				case 2:
					System.out.println("Creating transport");
					System.out.print("Enter transport type (Bus/Trolleybus/Tram): ");
					String transportType = scanner.next();
					System.out.print("Enter license number (1 letter + 3 digits): ");
					String license = scanner.next();
					System.out.print("Enter status (0 for not broken, 1 for broken): ");
					int status = scanner.nextInt();
					boolean isBroken = (status == 1);
					try {
						Vehicle vehicle = VehicleFactory.createVehicle(transportType, license, isBroken);
						vehicles.add(vehicle);
					} catch (IllegalArgumentException e) {
						System.out.println("Invalid vehicle type: " + transportType);
					}
					break;

				case 3:
					System.out.println("Assigning transport to route");
					System.out.print("Enter route number: ");
					String routeNum = scanner.next();
					System.out.print("Enter transport license number: ");
					String transport = scanner.next();
					boolean foundRoute = false;
					for (Route r : routes) {
						if (r.getNumber().equals(routeNum)) {
							foundRoute = true;
							boolean foundTransport = false;
							for (Vehicle v : vehicles) {
								if (v.getLicense().equals(transport)) {
									foundTransport = true;
									boolean alreadyAssigned = false;
									for (Route existingRoute : routes) {
										if (existingRoute.getVehicles().contains(v)) {
											alreadyAssigned = true;
											break;
										}
									}
									if (alreadyAssigned) {
										System.out.println("Transport with license " + transport
												+ " is already assigned to a route.");
									} else {
										r.addVehicle(v);
										System.out.println("Transport with license " + transport + " assigned to route "
												+ routeNum);
									}
									break;
								}
							}
							if (!foundTransport) {
								System.out.println("Transport with license " + transport + " not found");
							}
							break;
						}
					}
					if (!foundRoute) {
						System.out.println("Route " + routeNum + " not found");
					}
					break;

				case 4:
					System.out.println("Editing transport status");
					System.out.print("Enter transport license number: ");
					String transportLicenseEdit = scanner.next();
					for (Vehicle v : vehicles) {
						if (v.getLicense().equals(transportLicenseEdit)) {
							System.out.print("Enter new status (0 for not broken, 1 for broken): ");
							int newStatus = scanner.nextInt();
							v.setBroken(newStatus == 1);
							System.out
									.println("Status of transport with license " + transportLicenseEdit + " updated.");
							break;
						}
					}
					break;
				case 5:
					System.out.println("Viewing routes and transport");
					displayRoutesAndTransport(routes, vehicles);
					break;
				case 6:
					System.out.println("Exiting the program");
					scanner.close();
					return;
				default:
					System.out.println("Invalid choice. Please try again.");
			}
		}
	}

	private static void displayRoutesAndTransport(List<Route> routes, List<Vehicle> vehicles) {
		System.out.println("Routes and Transport:");
		for (Route route : routes) {
			System.out.println("Route: " + route.getNumber());
			System.out.println("Transport:");
			for (Vehicle vehicle : route.getVehicles()) {
				System.out.printf("\t|License: %-7s| Type: %-15s| Interval: %-3s| Status: %s%n",
						vehicle.getLicense(), vehicle.getType(), vehicle.getDrivingInterval(),
						(vehicle.isBroken() ? "Broken" : "Not Broken"));

			}
		}

		System.out.println("\nTransport without a route:");
		for (Vehicle vehicle : vehicles) {
			boolean assigned = false;
			for (Route route : routes) {
				if (route.getVehicles().contains(vehicle)) {
					assigned = true;
					break;
				}
			}
			if (!assigned) {
				System.out.printf("\t|License: %-7s| Type: %-15s| Interval: %-3d| Status: %s%n",
						vehicle.getLicense(), vehicle.getType(), vehicle.getDrivingInterval(),
						(vehicle.isBroken() ? "Broken" : "Not Broken"));
			}
		}
	}

}
