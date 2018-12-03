import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.lang.*;

public class Day2 {
    private ArrayList<String> ids;
    public Day2(){
        try {
            File input = new File("src/day2.txt");
            Scanner in = new Scanner(input);
            ids = new ArrayList<>();

            while (in.hasNextLine()){
                ids.add(in.nextLine());
            }
        } catch (FileNotFoundException e){
            System.out.println("Error: File Not Found");
        }

    }

    public static void main(String[] args){
        Day2 day2 = new Day2();
        System.out.println("Check Sum: " + day2.getCheckSum());
        System.out.println("Common Letters: " + day2.getCommonLetters());
    }

    public String getCommonLetters(){
        HashMap<String,Integer> temp = new HashMap<>();
        for (int i = 0; i < ids.get(0).length(); i++){
            for (int j = 0; j < ids.size(); j++){
                String str = ids.get(j).substring(0,i) + ids.get(j).substring(i+1,ids.get(0).length());
                if (temp.containsKey(str)){
                    return str;
                }
                else {
                    temp.put(str,1);
                }
            }
            temp = new HashMap<>();
        }
        return "";
    }

    public int getCheckSum(){
        int total2Count = 0;
        int total3Count = 0;

        for (int i = 0; i < ids.size(); i++){
            boolean twoFound = false;
            boolean threeFound = false;
            String temp = ids.get(i);
            int prevLength = temp.length();
            while (temp.length() > 0 && !(twoFound && threeFound)){
                char c = temp.charAt(0);
                temp = temp.replace(String.valueOf(c),"");
                if (prevLength - 2 == temp.length()){
                    twoFound = true;
                }
                else if (prevLength - 3 == temp.length()){
                    threeFound = true;
                }

                prevLength = temp.length();
            }
            if (twoFound){
                total2Count++;
            }
            if (threeFound){
                total3Count++;
            }

        }
        return total2Count * total3Count;
    }
}
