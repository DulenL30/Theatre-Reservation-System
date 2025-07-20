//Imported libraries to the programme.
import java.util.Scanner;
import java.util.InputMismatchException;

public class CinemaManagement {
    //rowLine variable to number of rows in Cinema.
    private static final int rowLines = 3;
    //rowSeats variable to number of seats in Cinema.
    private static final int rowSeats = 16;
    //2D array to find seat availability.
    private static int[][] seats = new int[rowLines][rowSeats];
    //ticketsArray to store tickets.
    private static Ticket[] storeTickets = new Ticket[rowLines * rowSeats];
    private static int totalTicketsSold = 0;


    //Main method in the programme.
    public static void main(String[] args) {
        seat_initialization();
        System.out.println("\n           Welcome to The London Lumiere");
        Scanner scanner = new Scanner(System.in);
        int option;

        while (true) {
            try {
                display_menu();
                //Asking option from user.
                System.out.print("Select Option: ");
                option = scanner.nextInt();
                switch (option){
                    case 1:
                        buy_ticket(scanner);
                        break;
                    case 2:
                        cancel_ticket(scanner);
                        break;
                    case 3:
                        print_seating_area(seats);
                        break;
                    case 4:
                        find_first_available();
                        break;
                    case 5:
                        print_tickets_info();
                        break;
                    case 6:
                        search_ticket(scanner);
                        break;
                    case 7:
                        sort_tickets();
                        break;
                    case 8:
                        System.out.println("Exiting the Programme.Thank You.");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Invalid Option. Please select a number between 1 and 8.");
                        break;
                }
            } catch (InputMismatchException e){
                System.out.println("Invalid Option. Please enter a valid option.");
                scanner.next();
            }
        }
    }


    //Method created for display menu options.
    private static void display_menu() {
        System.out.println("""
                \n---------------------------------------------------
                Please Select an Option:
                    1) Buy a ticket
                    2) Cancel ticket
                    3) See seating plan
                    4) Find first seat available
                    5) Print tickets information and total price
                    6) Search ticket
                    7) Sort tickets by price
                    8) Exit
                ---------------------------------------------------""");

    }


    //Method created for initialize seat format.
    private static void seat_initialization(){
        for (int i = 0; i < rowLines; i++){
            for (int j = 0; j < rowSeats; j++){
                seats[i][j] = 0;
            }
        }
    }


    //Method Created for buying a ticket.
    private static void buy_ticket(Scanner scanner){
        int[] seatDetails = get_row_seat_details(scanner);
        int row = seatDetails[0] - 1;
        int seat = seatDetails[1] - 1;

        // Checking seat availability and get information from user.
        if (seats[row][seat] == 0){
            scanner.nextLine();
            System.out.print("Enter Your Name: ");
            String name = scanner.nextLine();
            System.out.print("Enter Your Surname: ");
            String surname = scanner.nextLine();
            System.out.print("Enter Your Email: ");
            String email = scanner.nextLine();

            //Create a Person object
            Person person = new Person(name, surname, email);

            // Logic for price calculate according to seat number.
            double price;
            if (row == 0){
                price = 12;
            } else if (row == 1) {
                price = 10;
            } else {
                price = 8;
            }

            // Create a Ticket object.
            Ticket ticket = new Ticket(row + 1, seat + 1, price, person);

            //Adding tickets to the ticketsArray.
            storeTickets[totalTicketsSold] = ticket;

            seats[row][seat] = 1;
            System.out.println("The seat has been booked.");

            totalTicketsSold++;

        } else {
            System.out.println("This seat is not available.");
        }
    }


    //Method created for cancel ticket.
    private static void cancel_ticket(Scanner scanner){
        int[] seatDetails = get_row_seat_details(scanner);
        int row = seatDetails[0] - 1;
        int seat = seatDetails[1] - 1;

        //Check if seat is booked.
        if (seats[row][seat] == 1){
            seats[row][seat] = 0;
            System.out.println("The seat has been cancelled.");
            totalTicketsSold--;

            //Remove ticket from ticketsArray.
            for(int i = 0; i < totalTicketsSold; i++){
                if (storeTickets[i] != null && storeTickets[i].getRow() == row + 1 && storeTickets[i].getSeat() == seat + 1){
                    for (int j = i; j < totalTicketsSold; j++){
                        storeTickets[j] = storeTickets[j + 1];
                    }
                    storeTickets[totalTicketsSold] = null;
                    break;
                }
            }
        } else{
            System.out.println("This seat is already available.");
        }
    }


    //Created method for show seating area show which is available or not.
    private static void print_seating_area(int[][] seats){
        System.out.println();
        System.out.println("""
                *****************
                *     SCREEN    *
                *****************""");
        for (int i = 0; i < seats.length; i++){
            for (int j = 0; j <seats[i].length; j++){
                if (j ==8){
                    System.out.print(" ");
                }
                if (seats[i][j] == 0){
                    System.out.print("O");
                } else {
                    System.out.print("X");
                }
            }
            System.out.println();
        }
    }


    //Created method for finding first available seat in the Cinema.
    private static void find_first_available(){
        for (int i = 0; i < rowLines; i++){
            for (int j = 0; j < rowSeats; j++){
                if (seats[i][j] == 0){
                    int availableRow = i + 1;
                    int availableSeat = j + 1;
                    System.out.println("First available seat : Row " +availableRow+" Seat "+availableSeat);
                    return;
                }
            }
        }
        System.out.println("Sorry, No available seats.");
    }


    //Method for print ticket information.
    private static void print_tickets_info(){
        System.out.println("\nTicket Information and Total Price.");
        if (totalTicketsSold == 0){
            System.out.println("No tickets have been sold yet.");
        } else {
            double totalSales = 0.0;
            for (int i = 0; i < totalTicketsSold; i++){
                if (storeTickets[i] != null){
                    //Print ticket information for each sold ticket in this step.
                    storeTickets[i].print_ticket_info();
                    totalSales += storeTickets[i].getPrice();
                }
            }
            System.out.println("\nTotal price of tickets sold: £" + totalSales);
        }
    }


    //Method for searching a ticket.
    private static void search_ticket(Scanner scanner){
        int[] seatDetails = get_row_seat_details(scanner);
        int row = seatDetails[0] - 1;
        int seat = seatDetails[1] - 1;

        //Check if the seat is available.
        if (seats[row][seat] == 0){
            // Print message if seat is available.
            System.out.println("This seat is available.");
        } else {
            //search for the ticket in ticketArray.
            boolean ticketFound = false;
            for (int i = 0; i < totalTicketsSold; i++){
                Ticket ticket = storeTickets[i];
                if (ticket != null && ticket.getRow() == row + 1 && ticket.getSeat() == seat +1){
                    //Print ticket information if ticket is sold one.
                    ticket.print_ticket_info();
                    ticketFound = true;
                    break;
                }
            }
            if (!ticketFound){
                System.out.println("No ticket found for this seat.");
            }
        }
    }


    //Method for sort  tickets by price in ascending order.
    private static void sort_tickets(){
        if (totalTicketsSold == 0){
            System.out.println("No tickets to sort.");
            return;
        }
        // Sort tickets.
        System.out.println("\nTickets sorted ascending order by price: ");
        for (int i = 0; i < totalTicketsSold - 1 - i; i++){
            for (int j = 0; j <totalTicketsSold - 1 - i; j++){
                if (storeTickets[j].getPrice() > storeTickets[j + 1].getPrice()){
                    Ticket store = storeTickets[j];
                    storeTickets[j] = storeTickets[j + 1];
                    storeTickets[j + 1] = store;
                }
            }
        }

        //Display sorted ticket information according to the sort.
        for (int i = 0; i < totalTicketsSold; i++){
            storeTickets[i].print_ticket_info();
        }
    }


    //Method for get seat details row, seat numbers from the user.
    private static int[] get_row_seat_details(Scanner scanner){
        // Array to store row and seat numbers.
        int[] seatDetails = new int[2];
        int row;
        int seat;

        // Get row number and check if the entered row number valid or not.
        while (true){
            try{
                System.out.print("Enter the row number: ");
                row = scanner.nextInt();

                if (row < 1 || row > rowLines){
                    System.out.println("Invalid row number. Please enter a number between 1 and 3.");
                } else {
                    seatDetails[0] = row;
                    break;
                }
            } catch (InputMismatchException e){
                System.out.println("Invalid input. Please enter a valid integer for the row number.");
                scanner.next();
            }
        }

        //Get seat number and check if the entered seat number valid or not.
        while (true){
            try{
                System.out.print("Enter the seat number: ");
                seat = scanner.nextInt();

                if (seat < 1 || seat > rowSeats){
                    System.out.println("Invalid seat number. Please enter a number between 1 and 16.");
                } else {
                    seatDetails[1] = seat;
                    break;
                }
            } catch (InputMismatchException e){
                System.out.println("Invalid input. Please enter a valid integer for the seat number.");
                scanner.next();
            }
        }
        return seatDetails;
    }
}
