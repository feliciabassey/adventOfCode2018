import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Day3 {
    private String[][] fabric;
    private HashMap<String, Boolean> overlaps;
    public Day3 (){
        try {
            fabric = new String[1000][1000];

            File day3 = new File("src/day3.txt");
            Scanner in = new Scanner(day3);
            overlaps = new HashMap<>();
            while (in.hasNextLine()) {
                String claim = in.nextLine();
                String id = claim.substring(1,claim.indexOf("@") - 1);
                int x1 = Integer.parseInt(claim.substring(claim.indexOf("@") + 2, claim.indexOf(",")));
                int y1 = Integer.parseInt(claim.substring(claim.indexOf(",") + 1, claim.indexOf(":")));

                int x2 = Integer.parseInt(claim.substring(claim.indexOf(":") + 2, claim.indexOf("x")));
                int y2 = Integer.parseInt(claim.substring(claim.indexOf("x") + 1));

                for (int i = x1; i < x1 + x2; i++) {
                    for (int j = y1; j < y1 + y2; j++) {
                        String currentId = fabric[i][j];
                        if (currentId != null) {
                            String[] overlapIds = currentId.split(",");
                            for(String cId: overlapIds){
                                overlaps.put(cId, true);
                            }
                            overlaps.put(id,true);
                            fabric[i][j] +=  "," + id;
                        }
                        else{
                            fabric[i][j] = id;
                            if (!overlaps.containsKey(id)) {
                                overlaps.put(id, false);
                            }
                        }

                    }
                }
            }
        } catch (FileNotFoundException e){
            System.out.println("Error: File Not Found");
        }


    }

    public void getIdOfFullFabric(){
        for (Map.Entry<String, Boolean> id: overlaps.entrySet()) {
            if (!id.getValue()){
                System.out.println("ID: " + id.getKey());
            }
        }

    }

    public int getTotalOverlap(){
        int totalOverlap = 0;
        for (int i = 0; i < 1000; i++){
            for (int j = 0; j < 1000; j++){
                if (fabric[i][j] != null && fabric[i][j].contains(",")){
                    totalOverlap++;
                }
            }
        }
        return totalOverlap;
    }

    public static void main(String[] args){
        Day3 day3 = new Day3();
        System.out.println("Total Overlap: " + day3.getTotalOverlap());
        day3.getIdOfFullFabric();
    }
}
