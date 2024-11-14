import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Room class to represent a room in the hotel
class Room {
    private int roomNumber;
    private String category;
    private double price;
    private boolean isAvailable;

    public Room(int roomNumber, String category, double price) {
        this.roomNumber = roomNumber;
        this.category = category;
        this.price = price;
        this.isAvailable = true;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    @Override
    public String toString() {
        return "Room Number: " + roomNumber + ", Category: " + category + ", Price: $" + price + ", Available: " + isAvailable;
    }
}

// Reservation class to represent a booking
class Reservation {
    private Room room;
    private String customerName;
    private String checkInDate;
    private String checkOutDate;

    public Reservation(Room room, String customerName, String checkInDate, String checkOutDate) {
        this.room = room;
        this.customerName = customerName;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    public Room getRoom() {
        return room;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCheckInDate() {
        return checkInDate;
    }

    public String getCheckOutDate() {
        return checkOutDate;
    }

    @Override
    public String toString() {
        return "Reservation for " + customerName + ", Room: " + room.getRoomNumber() +
                ", Check-in: " + checkInDate + ", Check-out: " + checkOutDate;
    }
}

// Hotel class to manage rooms and reservations
class Hotel {
    private List<Room> rooms;
    private List<Reservation> reservations;

    public Hotel() {
        rooms = new ArrayList<>();
        reservations = new ArrayList<>();
        populateRooms();
    }

    private void populateRooms() {
        rooms.add(new Room(101, "Single", 50.0));
        rooms.add(new Room(102, "Double", 80.0));
        rooms.add(new Room(103, "Suite", 150.0));
        rooms.add(new Room(104, "Single", 50.0));
        rooms.add(new Room(105, "Double", 80.0));
    }

    public void showAvailableRooms(String category) {
        System.out.println("Available rooms in category: " + category);
        for (Room room : rooms) {
            if (room.isAvailable() && room.getCategory().equalsIgnoreCase(category)) {
                System.out.println(room);
            }
        }
    }

    public Room findRoom(int roomNumber) {
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber && room.isAvailable()) {
                return room;
            }
        }
        return null;
    }

    public void makeReservation(String customerName, int roomNumber, String checkInDate, String checkOutDate) {
        Room room = findRoom(roomNumber);
        if (room != null) {
            room.setAvailable(false);
            reservations.add(new Reservation(room, customerName, checkInDate, checkOutDate));
            System.out.println("Reservation successful for " + customerName);
        } else {
            System.out.println("Room not available or already booked.");
        }
    }

    public void showReservations() {
        System.out.println("Current Reservations:");
        for (Reservation reservation : reservations) {
            System.out.println(reservation);
        }
    }

    // Placeholder for payment processing
    public void processPayment(double amount) {
        System.out.println("Processing payment of $" + amount);
        System.out.println("Payment successful!");
    }
}

// Main class to run the hotel reservation system
public class HotelReservationSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Hotel hotel = new Hotel();
        boolean exit = false;

        while (!exit) {
            System.out.println("\nHotel Reservation System");
            System.out.println("1. Search for available rooms");
            System.out.println("2. Make a reservation");
            System.out.println("3. View all reservations");
            System.out.println("4. Process payment");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter room category (Single, Double, Suite): ");
                    String category = scanner.nextLine();
                    hotel.showAvailableRooms(category);
                    break;

                case 2:
                    System.out.print("Enter your name: ");
                    String customerName = scanner.nextLine();
                    System.out.print("Enter room number: ");
                    int roomNumber = scanner.nextInt();
                    scanner.nextLine(); // consume newline
                    System.out.print("Enter check-in date (YYYY-MM-DD): ");
                    String checkInDate = scanner.nextLine();
                    System.out.print("Enter check-out date (YYYY-MM-DD): ");
                    String checkOutDate = scanner.nextLine();
                    hotel.makeReservation(customerName, roomNumber, checkInDate, checkOutDate);
                    break;

                case 3:
                    hotel.showReservations();
                    break;

                case 4:
                    System.out.print("Enter payment amount: ");
                    double amount = scanner.nextDouble();
                    hotel.processPayment(amount);
                    break;

                case 5:
                    exit = true;
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
        System.out.println("Thank you for using the Hotel Reservation System.");
    }
}
