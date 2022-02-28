import java.util.Scanner;

public class part5 
{
    public static void main(String[] args)
    {
        // Declare constatnts
        final double MIN_LENGTH = 5;
        final double MIN_WIDTH = 5;

        // Declare variables
        int shadeChoice = 0, roomsProcessed = 0;
        Double roomLength = 0.0, roomWidth = 0.0, area, btusPerHour;
        String name = "", shade = "";
        char goAgain;
        Scanner keyboard = new Scanner(System.in);

        // Call the displayTitle method
        displayTitle();

        do
        {
            // Get user input
            System.out.print("Please enter the name of the room: ");
            name = keyboard.nextLine();

            System.out.print("Please enter the length of the room (in feet): ");
            roomLength = keyboard.nextDouble();

            // Validate the input
            while (roomLength < MIN_LENGTH)
            {
                System.out.println("The room length can not be less that "+MIN_LENGTH+" feet.");
                System.out.print("Please enter the length of the room (in feet): ");
                roomLength = keyboard.nextDouble();
            }

            System.out.print("Please enter the width of the room (in feet): ");
            roomWidth = keyboard.nextDouble();

            // Validate the input
            while(roomWidth < MIN_WIDTH)
            {
                System.out.println("The room width can not be less than "+MIN_WIDTH+" feet.");
                System.out.print("Please enter the width of the room (in feet): ");
                roomWidth = keyboard.nextDouble();
            }

            // Call calculateArea method
            area = calculateArea(roomLength, roomWidth);

            // Get shade choice from user
            System.out.println("What is the amount of shade that this room recieves?");
            System.out.println("\t1. Little Shade");
            System.out.println("\t2. ModerateShade");
            System.out.println("\t3. Abundant Shade");
            System.out.print("Please select from the options above: ");
            shadeChoice = keyboard.nextInt();

            // Validate the input
            while (shadeChoice < 1 || shadeChoice > 3)
            {
                System.out.println("The  menu selection for the amount of shade should be 1, 2, or 3.");
                System.out.print("Please select from the options above: ");
                shadeChoice = keyboard.nextInt();
            }

            btusPerHour = calculateBTUsPerHour(shadeChoice, area);
            shade = translateShadeChoiceToString(shadeChoice);
            displayRoomInformation(name, shade, area, btusPerHour);

            // Clear buffer before reading next line
            keyboard.nextLine();

            // Ask user if they wish to enter information for another room
            System.out.println("Would you like to enter information for another room (Y/N)? ");
            goAgain = keyboard.nextLine().toUpperCase().charAt(0);

            // Increment counter for rooms processed
            roomsProcessed++;
            
        }while (goAgain == 'Y');

        System.out.println("The total number of rooms processed was: " + roomsProcessed);
    }

    /**
    *  The displayTitle method is a void method that displays the title "Air Conditioning
    *  Window Unit Cooling Capacity"
    */
    
    public static void displayTitle()
    {
        System.out.println("Air Conditioning Window Unit Cooling Capacity");
    }

    /**
        The calculateArea method is a double that accepts the length and width of the room 
        as arguments, and calculates and returns the area of the room.
    */

    public static double calculateArea(double length, double width)
    {
        double area;
        area = length * width;
        return area;
    }

    /**
        The translateShadeTypeToString method accepts and integer values (1, 2, or 3) that denotes
        the amount of shade. The method should return the appropriate String representation of the shade.
    */

    public static String translateShadeChoiceToString(int shadeChoice)
    {
        final int LITTLE_SHADE = 1;
        final int MODERATE_SHADE = 2;
        final int ABUNDANT_SHADE = 3;
        String shade;

        if (shadeChoice == LITTLE_SHADE)
        {
            shade = "Little";
        }
        else if (shadeChoice == ABUNDANT_SHADE)
        {
            shade = "Abundant";
        }
        else
        {
            shade = "Moderate";
        }

        return shade;
    }

    /**
        The calculateBTUsPerHour method accepts the area of a room and the amount of shade a room
        gets and returns the BTUs per Hour that are needed to adequately cool that room.
    */

    public static double calculateBTUsPerHour(int shade, double area)
    {
        final int LITTLE_SHADE = 1;
        final int MODERATE_SHADE = 2;
        final int ABUNDANT_SHADE = 3;
        final double PERCENTAGE_LITTLE_SHADE = 1.15;
        final double PERCENTAGE_ABUNDANT_SHADE = 0.9;
        final double AREA_SMALL_ROOM = 250;
        final double AREA_MEDIUM_ROOM = 500;
        final double AREA_LARGE_ROOM = 1000;
        final double BTUS_SMALL_ROOM = 5500;
        final double BTUS_MEDIUM_ROOM = 10000;
        final double BTUS_LARGE_ROOM = 17500;
        final double BTUS_XL_ROOM = 24000;
        double capacity;

        // Determine capacity of the air conditioning unit (BTU's)
        if (area < AREA_SMALL_ROOM)
        {
            capacity = BTUS_SMALL_ROOM;
        }
        else if (area <= AREA_MEDIUM_ROOM)
        {
            capacity = BTUS_MEDIUM_ROOM;
        }
        else if (area < AREA_LARGE_ROOM)
        {
            capacity = BTUS_LARGE_ROOM;
        }
        else
        {
            capacity = BTUS_XL_ROOM;
        }

        // Adjust capacity BTU's based on amount of shade
        if (shade == LITTLE_SHADE)
        {
            capacity = capacity * PERCENTAGE_LITTLE_SHADE;
        }
        else if (shade == ABUNDANT_SHADE)
        {
            capacity = capacity * PERCENTAGE_ABUNDANT_SHADE;
        }
        return capacity;
    }

    /**
        The displayRoomInformation method is a void method that prints out the informations for a 
        single room.
    */

    public static void displayRoomInformation(String roomName, String shade, double area, double btus)
    {
        System.out.println("\n"+displayTitle()+"\n");
        System.out.println("Room Name: " + roomName);
        System.out.println("Room Area(in square feet): " + area);
        System.out.println("Amount of Shade: " + shade);
        System.out.println("BTUs Per Hour needed: " + btus);
    }
}

