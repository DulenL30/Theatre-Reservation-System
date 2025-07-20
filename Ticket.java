public class Ticket {
    private int row;
    private int seat;
    private double price;
    private Person person;


    //Constructor to Ticket object.
    public Ticket(int row, int seat, double price, Person person){
        this.row = row;
        this.seat = seat;
        this.price = price;
        this.person = person;
    }


    // Getters and Setters for return the values and set the values.
    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    
    //Method to print ticket information and person information.
    public void print_ticket_info(){
        System.out.println("\nTicket and Person Information: ");
        System.out.println("Row: " + row);
        System.out.println("Seat: " + seat);
        System.out.println("Price: £" + price);
        person.print_person_info();
    }
}
