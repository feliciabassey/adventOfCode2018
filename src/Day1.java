import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


public class Day1 {
    public  ArrayList<Integer> frequencies;

    public Day1(){
        try{
            File input = new File("src/day1.txt");
            Scanner in = new Scanner(input);
            frequencies = new ArrayList<>();

            while (in.hasNextLine()) {
                frequencies.add(Integer.parseInt(in.nextLine()));
            }

        } catch (FileNotFoundException e){
            System.out.println("Error: File Not Found");
        }

    }
    public static void main(String[] args) {
        Day1 day1 = new Day1();
        System.out.println("Total Frequency: " + day1.getTotalFrequency());
        System.out.println("First Repeated Frequency: " + day1.getFirstRepeatedFrequency());

    }

    public int getTotalFrequency(){
        int totalFrequency = 0;
        for (int i = 0; i < frequencies.size(); i++){
            totalFrequency += frequencies.get(i);
        }
        return totalFrequency;
    }

    public int getFirstRepeatedFrequency(){
        boolean found = false;
        int firstRepeatedFrequency = 0;
        int totalFrequency = 0;
        int count = 0;
        HashMap<Integer, Integer> frequencyCount = new HashMap<>();

        while (!found){
            if (frequencyCount.containsKey(totalFrequency)){
                found = true;
                firstRepeatedFrequency = totalFrequency;
            }
            else{
                frequencyCount.put(totalFrequency,1);

            }
            totalFrequency += frequencies.get(count % frequencies.size());
            count++;
        }
        return firstRepeatedFrequency;
    }
}
