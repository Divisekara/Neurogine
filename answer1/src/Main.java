import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;



public class Main {
    private static final String comma =  ",";
    public static void main(String[] args) {
        // answer for the 1.a
        convertString();

        // answer for the 1.b
        countStrings();
    }

    private static void convertString(){
        String[] input = {"a", "b"};
        List<String> output= new ArrayList<>();
        Arrays.stream(input).map((item) -> item.toUpperCase()).collect(Collectors.toCollection(() -> output));
        System.out.println(String.join(comma, output));
    }

    private static void countStrings(){
        String[] words = {"abc", "an", "", "apple", "bcd", "", "jk", "bad", " acd"};
        int emptyCount =0 ;
        int aCount = 0;
        for (int i = 0; i<=words.length-1; i++){
            if (words[i].length() == 0){
                emptyCount++;
            } else if(words[i].charAt(0) == 'a'){
                aCount++;
            }
        }
        System.out.println("Number of empty string = " + emptyCount);
        System.out.println("Number of strings starting from 'a' = " + aCount);
    }
}