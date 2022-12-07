package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.OptionalInt;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {

        System.out.println(Arrays.stream(getTopElves(1)).sum());
        System.out.println(Arrays.stream(getTopElves(3)).sum());
        System.out.println(rockPaperScissor());
        System.out.println(rockPaperScissor2());
    }

    public static int[] getTopElves(int topNumber) {
        int[] top = new int[topNumber];
        try {
            File myObj = new File("src/main/resources/adventCodeInput1.txt");
            Scanner myReader = new Scanner(myObj);

            int caloriesAmount = 0;
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();

                if(data.isBlank()) {
                    OptionalInt minValue = Arrays.stream(top).min();

                    if(minValue.isPresent() && caloriesAmount > minValue.getAsInt()) {
                        for(int i = 0; i < top.length; i ++) {
                            if(minValue.getAsInt() == top[i]) {
                                top[i] = caloriesAmount;
                                break;
                            }
                        }
                    }

                    caloriesAmount = 0;
                }
                else {
                    caloriesAmount += Integer.parseInt(data);
                }
            }

            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return top;
    }

    // Rock = A or X
    // Paper = B or Y
    // Scissor = C or Z
    public static int rockPaperScissor() {
        int score = 0;
        final String OPPONENT_ROCK = "A";
        final String OPPONENT_PAPER = "B";
        final String OPPONENT_SCISSOR = "C";

        final String PLAYER_ROCK = "X";
        final String PLAYER_PAPER = "Y";
        final String PLAYER_SCISSOR = "Z";

        try {
            File myObj = new File("src/main/resources/adventCodeInput2.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if(!data.isBlank()) {
                    String[] round = data.split(" ");
                    String opponentChoice = round[0];
                    String playerChoice = round[1];

                    if(playerChoice.equals(PLAYER_ROCK)) {
                        score += 1;

                        if(opponentChoice.equals(OPPONENT_ROCK))
                            score += 3;
                        else if(opponentChoice.equals(OPPONENT_SCISSOR))
                            score += 6;
                    }
                    else if(playerChoice.equals(PLAYER_PAPER)) {
                        score += 2;
                        if(opponentChoice.equals(OPPONENT_PAPER))
                            score += 3;
                        else if(opponentChoice.equals(OPPONENT_ROCK))
                            score += 6;
                    }
                    else {
                        score += 3;
                        if(opponentChoice.equals(OPPONENT_PAPER))
                            score += 6;
                        else if(opponentChoice.equals(OPPONENT_SCISSOR))
                            score += 3;
                    }
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return score;
    }

    public static int rockPaperScissor2() {
        int score = 0;
        final String OPPONENT_ROCK = "A";
        final String OPPONENT_PAPER = "B";

        final String NEED_DRAW = "Y";
        final String NEED_WIN = "Z";

        try {
            File myObj = new File("src/main/resources/adventCodeInput2.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if(!data.isBlank()) {
                    String[] round = data.split(" ");
                    String opponentChoice = round[0];
                    String playerChoice = round[1];

                    if(opponentChoice.equals(OPPONENT_ROCK)) {

                        if(playerChoice.equals(NEED_WIN))
                            score += 8;
                        else if(playerChoice.equals(NEED_DRAW))
                            score += 4;
                        else
                            score += 3;
                    }
                    // 2 + Paper draw(3) = 5, 2 + win(6) = 8, 2 + loose(0) = 2
                    else if(opponentChoice.equals(OPPONENT_PAPER)) {
                        if(playerChoice.equals(NEED_WIN))
                            score += 9;
                        else if(playerChoice.equals(NEED_DRAW))
                            score += 5;
                        else
                            score += 1;
                    }
                    // 3 + Scissor draw(3) = 6, 3 + win(6) = 9, 3 + loose(0) = 3
                    else {
                        if(playerChoice.equals(NEED_WIN))
                            score += 7;
                        else if(playerChoice.equals(NEED_DRAW))
                            score += 6;
                        else
                            score += 2;
                    }
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return score;
    }
}