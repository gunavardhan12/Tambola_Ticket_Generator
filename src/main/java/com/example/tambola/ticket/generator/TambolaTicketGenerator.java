package com.example.tambola.ticket.generator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class TambolaTicketGenerator {

    private static List<List<List<Integer>>> NSetOfTicketGenerator() {
        List<List<List<Integer>>> allTickets = new ArrayList<>();
        int [] arr = new int[91];
        for (int t = 0; t < 6; t++) {
            int game[][] = new int[3][9];
            int occupancy = 15;

            while (occupancy > 0) {
                int i = getRandomNumber(3);
                int j = getRandomNumber(9);
                int data = validateAndReturnNum(i, j, game);

                if (data > 0 && arr[data]==0) {
                    game[i][j] = data;
                    arr[data] =1;
                    valueGreater(game, i, j);
                    occupancy--;
                }
            }

            List<List<List<Integer>>> listOfLists = new ArrayList<>();
            for (int[] row : game) {
                List<List<Integer>> innerList1 = new ArrayList<>();
                for (int value : row) {
                    List<Integer> innerList2 = new ArrayList<>();
                    innerList2.add(value);
                    innerList1.add(innerList2);
                }
                listOfLists.add(innerList1);
            }

            allTickets.addAll(listOfLists);
        }
        System.out.println("End of the NSetGenerator");
        return allTickets;
    }
    private static int validateAndReturnNum(int i, int j, int[][] game) {
        if(game[i][j]!=0) {
            return -1;
        }
        int rowc =0;
        for(int r=0;r<3;r++) {
            if(game[r][j]!=0) {
                rowc++;
            }
        }
        if(rowc>=2) {
            return -1;
        }
        int colc =0;
        for(int c=0;c<9;c++) {
            if(game[i][c]!=0) {
                colc++;
            }
        }
        if(colc>5) {
            return -1;
        }
        int d =0;
        boolean isvalue=false;
        do {
            d = getRandomNumberForCol(j);
            isvalue = isValueExixtscol(game,i,j,d);
        }while(isvalue);

        return d;
    }

    private static void valueGreater(int[][] game, int i, int j) {
        for(int k=0;k<=i;k++) {
            if(game[i][j]<game[k][j]) {
                int temp = game[k][j];
                game[k][j] = game[i][j];
                game[i][j] = temp;
            }
        }
    }


    private static boolean isValueExixtscol(int[][] game, int i, int j, int d) {
        boolean s = false;
        for(int k=0;k<3;k++) {
            if(game[k][j]==d) {
                s = true;
                break;
            }
        }
        return s;
    }

    private static int getRandomNumberForCol(int high) {
        if(high==0) {
            high =10;
        }
        else {
            high = (high+1)*10;
        }
        int low = high-9;
        Random r =new Random();
        return r.nextInt(high-low)+low;
    }

    private static int getRandomNumber(int max) {
        return (int) (Math.random()*max);
    }

    public static List<List<List<Integer>>> generateSetOfTickets(int numberOfTicketsInSet) {
        List<List<List<Integer>>> ticketSet =new ArrayList<>();
        for (int i=0;i<numberOfTicketsInSet;i++){
            ticketSet.addAll(NSetOfTicketGenerator());
        }
        System.out.println("In GenerateSetOfTickets");
        return ticketSet;
    }

}
