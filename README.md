# Cinema Management System

A Java console application for managing cinema seat bookings.

## 📊 System Specifications

- **Cinema Layout**: 3 rows × 16 seats = 48 total seats
- **Pricing**: 
  - Row 1: £12
  - Row 2: £10  
  - Row 3: £8

## 📁 Files Required

- `CinemaManagement.java` - Main application class
- `Person.java` - Customer information class
- `Ticket.java` - Ticket information class

## ⚙️ Compilation and Execution

```bash
javac CinemaManagement.java Person.java Ticket.java
java CinemaManagement
```

## 📋 Menu Options

The application displays the following menu:

```
Please Select an Option:
    1) Buy a ticket
    2) Cancel ticket
    3) See seating plan
    4) Find first seat available
    5) Print tickets information and total price
    6) Search ticket
    7) Sort tickets by price
    8) Exit
```

## ⚡ Functionality

### 🎫 Buy a ticket
- Prompts for row number (1-3)
- Prompts for seat number (1-16)
- Requests customer information: name, surname, email
- Assigns price based on row number
- Creates and stores ticket if seat is available
- **Success Output**: `"The seat has been booked."`
- **Error Output**: `"This seat is not available."`

**Sample Output:**
```
Enter the row number: 2
Enter the seat number: 5
Enter Your Name: John
Enter Your Surname: Doe
Enter Your Email: john.doe@email.com
The seat has been booked.
```

### ❌ Cancel ticket
- Prompts for row and seat number
- Removes ticket from system if seat is booked
- Sets seat status to available
- **Success Output**: `"The seat has been cancelled."`
- **Error Output**: `"This seat is already available."`

**Sample Output:**
```
Enter the row number: 2
Enter the seat number: 5
The seat has been cancelled.
```

### 🗺️ See seating plan
- Shows screen representation at top
- Displays seat layout using:
  - `O` for available seats
  - `X` for booked seats
- Adds space after 8th seat in each row

**Sample Output:**
```
*****************
*     SCREEN    *
*****************
OOOOOOOO OOOOOOOO
OXOOOOOO OOOOOOOO
OOOOOOOO OOOOOOOO
```

### 🔍 Find first seat available
- Searches from row 1, seat 1 onwards
- Returns first available seat coordinates
- **Success Output**: `"First available seat : Row [X] Seat [Y]"`
- **No Seats Output**: `"Sorry, No available seats."`

**Sample Output:**
```
First available seat : Row 1 Seat 1
```

### 📋 Print tickets information and total price
- Lists all sold tickets with customer details
- Shows total revenue from all tickets
- **No Tickets Output**: `"No tickets have been sold yet."`

**Sample Output:**
```
Ticket Information and Total Price.

Ticket and Person Information: 
Row: 2
Seat: 5
Price: £10.0
Name: John
Surname: Doe
Email: john.doe@email.com

Total price of tickets sold: £10.0
```

### 🔎 Search ticket
- Prompts for row and seat number
- **Available Seat Output**: `"This seat is available."`
- Shows ticket and customer information if seat is booked
- **Error Output**: `"No ticket found for this seat."`

**Sample Output (Available Seat):**
```
Enter the row number: 1
Enter the seat number: 1
This seat is available.
```

**Sample Output (Booked Seat):**
```
Enter the row number: 2
Enter the seat number: 5

Ticket and Person Information: 
Row: 2
Seat: 5
Price: £10.0
Name: Meril
Surname: Lamahewa
Email: merild@email.com
```

### 📈 Sort tickets by price
- Sorts all tickets in ascending price order using bubble sort
- Displays sorted ticket information
- **No Tickets Output**: `"No tickets to sort."`

**Sample Output:**
```
Tickets sorted ascending order by price: 

Ticket and Person Information: 
Row: 3
Seat: 1
Price: £8.0
Name: Dulen
Surname: Lamahewa
Email: dulenk@email.com

Ticket and Person Information: 
Row: 2
Seat: 5
Price: £10.0
Name: Meril
Surname: Lamahewa
Email: merild@email.com
```


## ✅ Input Validation

### 📍 Row Number Validation
- Must be integer between 1-3
- **Invalid Range Output**: `"Invalid row number. Please enter a number between 1 and 3."`
- **Non-Integer Output**: `"Invalid input. Please enter a valid integer for the row number."`

### 💺 Seat Number Validation  
- Must be integer between 1-16
- **Invalid Range Output**: `"Invalid seat number. Please enter a number between 1 and 16."`
- **Non-Integer Output**: `"Invalid input. Please enter a valid integer for the seat number."`

### 📋 Menu Option Validation
- Must be integer between 1-8
- **Invalid Option Output**: `"Invalid Option. Please select a number between 1 and 8."`
- **Non-Integer Output**: `"Invalid Option. Please enter a valid option."`

## 🏗️ Data Structures

### 🎬 CinemaManagement Class
- `rowLines = 3` - Number of cinema rows
- `rowSeats = 16` - Number of seats per row
- `seats[3][16]` - 2D array tracking seat availability (0=available, 1=booked)
- `storeTickets[48]` - Array storing Ticket objects
- `totalTicketsSold` - Counter for number of tickets sold

### 👤 Person Class
**Fields:**
- `name` (String)
- `surname` (String) 
- `email` (String)

**Methods:**
- Constructor: `Person(String name, String surname, String email)`
- Getters: `getName()`, `getSurname()`, `getEmail()`
- Setters: `setName()`, `setSurname()`, `setEmail()`
- `print_person_info()` - Prints name, surname, and email

**Output Format:**
```
Name: John
Surname: Doe
Email: john.doe@email.com
```

### 🎫 Ticket Class
**Fields:**
- `row` (int)
- `seat` (int)
- `price` (double)
- `person` (Person)

**Methods:**
- Constructor: `Ticket(int row, int seat, double price, Person person)`
- Getters: `getRow()`, `getSeat()`, `getPrice()`, `getPerson()`
- Setters: `setRow()`, `setSeat()`, `setPrice()`, `setPerson()`
- `print_ticket_info()` - Prints ticket details and calls person.print_person_info()

**Output Format:**
```
Ticket and Person Information: 
Row: 2
Seat: 5
Price: £10.0
Name: John
Surname: Doe
Email: john.doe@email.com
```

## 🚀 Application Behavior

### 🎯 Initialization
- All seats initialized to 0 (available)
- **Welcome Output**: `"Welcome to The London Lumiere"`
- Menu displayed in continuous loop until exit

### 🎭 Seating Plan Display Format
```
*****************
*     SCREEN    *
*****************
OOOOOOOO OOOOOOOO
OOOOOOOO OOOOOOOO  
OOOOOOOO OOOOOOOO
```

### 📄 Ticket Information Output Format
```
Ticket and Person Information: 
Row: [number]
Seat: [number]
Price: £[amount]
Name: [customer name]
Surname: [customer surname]
Email: [customer email]
```

### ⚠️ Error Handling
- Uses `InputMismatchException` for non-integer inputs
- `scanner.next()` called to clear invalid input
- Continues prompting until valid input received
- All user inputs validated before processing

## ⚠️ Limitations

- Data not persistent between program runs
- Fixed cinema size (3×16)
- Tickets stored in array with fixed size limit
- Bubble sort algorithm used (O(n²) complexity)
- No file I/O operations
- Console-only interface
