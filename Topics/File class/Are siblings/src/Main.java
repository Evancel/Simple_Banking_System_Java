import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Main{
    public static void main(String[] args) {
        long maxDifference = 0;
        int resultingYear = 0;
        long previousPopulation = 0;

        try(Scanner sc = new Scanner(new File("C:\\Users\\Evancelj\\Desktop\\dataset_91069.txt"))){
            String title = sc.nextLine();
            while(sc.hasNext()){
                String[] arr = sc.nextLine().split("\\s");
                int year = Integer.parseInt(arr[0]);
                System.out.println("year = " + year);
                arr[1] = arr[1].replace(",","");
                long population = Long.parseLong(arr[1]);
                System.out.println("population = " + population);
                System.out.println("previousPopulation = " + previousPopulation);
                long difference = population - previousPopulation;
                System.out.println("difference = " + difference);
                previousPopulation = population;

                if(maxDifference < difference && difference != population){
                    maxDifference = difference;
                    resultingYear = year;
                }
                System.out.println("maxDifference = " + maxDifference);
            }
        } catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException " + e.getMessage());
        }
        System.out.println(resultingYear);
    }
}