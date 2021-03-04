package com.company;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static char[][] seatingChart;
    public static String[] rowLetters = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S",
                                         "T","U","V","W","X","Y","Z"};
    public static final int rows = 10;
    public static final int seats = 20;
    public static final int buffer = 3;
    public static String outputContent = "";

    public static void main(String[] args) throws IOException {
//        String fileName = "C:\\Users\\Alex\\Desktop\\CodingChallenges\\MovieTheaterSeatingChallenge\\movieInput.txt";
//        String fileName = "C:/Users/Alex/Desktop/CodingChallenges/MovieTheaterSeatingChallenge/movieInput.txt";
        String fileName = args[0];

        String[] pathList = fileName.split("/");
        String inputFileName = pathList[pathList.length-1];
        int parentPathLength = fileName.indexOf(inputFileName);
//        "C:/Users/Alex/Desktop/movieTheaterSeatingChallenge/movieSeatingOutput.TXT";
        String outputFileName = fileName.substring(0, parentPathLength) + "movieSeatingOutput.txt";

        FileWriter fileWriter = new FileWriter(outputFileName);
        File file = new File(fileName);
        Scanner scanner = new Scanner(file);

        // initialize seats, 0 seat is available
        seatingChart = new char[rows][seats];

        for (int i = 0; i < rows; i++){
            for (int j = 0; j < seats; j++){
                seatingChart[i][j] = '.';
            }
        }

        // Loop through input file lines and call method to handle line
        while (scanner.hasNextLine()){
            String reservation = scanner.nextLine();
            processReservation(reservation);
        }

        printSeatingChart();
        fileWriter.write(outputContent);
        fileWriter.close();

        System.out.println("OUTPUT FILE: " + outputFileName);
    }

    private static void processReservation(String line) {
        // Thought Process:
        // Loop through rows starting with the back. From personal experience, most movie watchers prefer the back.
        // Fill in seats starting from the left of the row. leaving a space of 3 seats in between reservations.
        // If the row is out of room for the reservation, move forward a row and check for space there.

        // Get reservation number and reservation size from line
        String[] lineContent = line.split(" ");
        int revNum = Integer.parseInt(lineContent[0].substring(1));
        int revSize = Integer.parseInt(lineContent[1]);

        // Call the fitting method for each row passing in reservation size.
        int rowIndex = 0;
        while (reservationFitting(revSize, rowIndex, revNum) == false && rowIndex < rows){
            rowIndex++;
        }
    }

    private static boolean reservationFitting(int revSize, int currentRow, int revNum){
        // This function returns a position to add the party if the reservation size will fit with the allowed buffer
        // in the current row.
        // return false if there is no space
        // return true if there is space

        // the position of the farthest left reservation
        int farLeftPos = 0;

        // visit each seat by going right
        for (int i = 0; i < seats; i++){
            // if seat is reserved, update position
            if (seatingChart[currentRow][i] == 'R'){
                farLeftPos = i;
            }
        }
        int startingPosition  = 0;
        // if row is not empty, add buffer to left most seat
        if (farLeftPos != 0){
            startingPosition = farLeftPos + buffer + 1;
        }
        int endingPosition = startingPosition + revSize;

        // if row has space for reservation, update seating chart and write to output file
        if (endingPosition > seats){
            return false;
        }
        else{
            updateSeatingChart(revSize, currentRow, revNum, startingPosition);
            writeReservation(revSize, currentRow, revNum, startingPosition);
        }
        return true;
    }

    private static void updateSeatingChart(int revSize, int currentRow, int reservationNum, int startPosition){
        // Update seating chart variable by changing available to reserved.
        for (int i = 0; i < revSize; i++) {
            seatingChart[currentRow][startPosition + i] = 'R';
        }
    }

    private static void writeReservation(int revSize, int currentRow, int reservationNum, int startPosition){
        // This function formats the Reservation number and reserved seats in preparation to write to an output file.

        // Formate 0's to the thousand's place
        String zeros = "";
        if (reservationNum>0 && reservationNum<10){
            zeros = "000";
        }
        else if (reservationNum >= 10 && reservationNum < 100){
            zeros = "00";
        }
        else if (reservationNum >= 100 && reservationNum < 1000){
            zeros = "0";
        }

        // Build string with reservation number
        String reservationRow = "R"+ zeros +reservationNum + " ";

        // Add reservations using rowLetters array
        for (int i = 0; i < revSize-1; i++) {
            String rowLetter = rowLetters[currentRow];
            int rowNum = startPosition + i + 1;
            reservationRow = reservationRow + rowLetter + rowNum + ",";
        }

        // Add final reservation seat without ","
        String rowLetter = rowLetters[currentRow];
        int rowNum = startPosition + revSize;
        reservationRow = reservationRow + rowLetter + rowNum;

        outputContent = outputContent.concat(reservationRow + "\n");
    }

    private static void printSeatingChart(){
        // For debugging, neatly print the reserved seats.
        // Available = .
        // Reserved = R
        System.out.println("-------------------------------------------");
        for (int i =0; i < seatingChart.length; i++){
            System.out.print(rowLetters[i] + ":  ");
            for (int j = 0; j < seatingChart[i].length; j++){
                System.out.print(seatingChart[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("[[                 SCREEN                 ]]");
        System.out.println("1...................ROW...................20");
        System.out.println("-------------------------------------------");
        System.out.println("\n");
    }

}
