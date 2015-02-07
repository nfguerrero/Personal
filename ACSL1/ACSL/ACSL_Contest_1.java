import java.util.Scanner;

/**
 * ACSL Contest #1
 * 
 * @author nfguerrero 
 * @version 11/8/14
 */
public class ACSL_Contest_1
{
   /**
    * Main method
    */
   public static void main(String[] args)
   {
       final int AB = 450; //Distance in miles from city A to city B
       final int BC = 140; //Distance in miles from city B to city C
       final int CD = 125; //Distance in miles from city C to city D
       final int DE = 365; //Distance in miles from city D to city E
       final int EF = 250; //Distance in miles from city E to city F
       final int FG = 160; //Distance in miles from city F to city G
       final int GH = 380; //Distance in miles from city G to city H
       final int HJ = 235; //Distance in miles from city H to city J
       final int JK = 320; //Distance in miles from city J to city K
       
       System.out.println("Sample input: A, C, 1, PM, 2, PM, 50, 60\n");
       
       Scanner scan = new Scanner(System.in);
       for (int count = 0; count < 5; count++)
       {
           System.out.print("Input: ");
           String input = scan.nextLine();
           
           int coma1 = input.indexOf(",");
           String city1 = input.substring(0, coma1).trim();
           char c1 = city1.charAt(0);
           
           int coma2 = input.indexOf(",", coma1+1);
           String city2 = input.substring(coma1+1, coma2).trim();
           char c2 = city2.charAt(0);
           
           int coma3 = input.indexOf(",", coma2+1);
           int time1 = (int) Double.parseDouble(input.substring(coma2+1, coma3).trim());
           
           int coma4 = input.indexOf(",", coma3+1);
           String type1 = input.substring(coma3+1, coma4).trim();
           
           int coma5 = input.indexOf(",", coma4+1);
           int time2 = (int) Double.parseDouble(input.substring(coma4+1, coma5).trim());
           
           int coma6 = input.indexOf(",", coma5+1);
           String type2 = input.substring(coma5+1, coma6).trim();
           
           int coma7 = input.indexOf(",", coma6+1);
           double mph1 = Double.parseDouble(input.substring(coma6+1, coma7).trim());
           
           double mph2 = Double.parseDouble(input.substring(coma7+1).trim());
           
           
           double distance = 0;
           while (c1 < c2)
           {
               if (c1 == 'A')
               {
                   distance += AB;
               }
               else if (c1 == 'B')
               {
                   distance += BC;
               }
               else if (c1 == 'C')
               {
                   distance += CD;
               }
               else if (c1 == 'D')
               {
                   distance += DE;
               }
               else if (c1 == 'E')
               {
                   distance += EF;
               }
               else if (c1 == 'F')
               {
                   distance += FG;
               }
               else if (c1 == 'G')
               {
                   distance += GH;
               }
               else if (c1 == 'H')
               {
                   distance += HJ;
               }
               else if (c1 == 'J')
               {
                   distance += JK;
               }
               c1++;
           }
           
           
           int dif = 0;
           if (time1 < time2)
           {
               if (!(type1.equals(type2)))
               {
                   time1 += 12;
                   dif = time1 - time2;
               }
               else
               {
                   dif = time2 - time1;
               }
           }
           else
           {
               if (!(type2.equals(type1)))
               {
                   time2 += 12;
                   dif = time2 - time1;
               }
               else
               {
                   dif = time1 - time2;
               }
           }
         
           
           double final_time = 0; //final time in minutes
           for (int count2 = 0; count2 < dif; count2++)
           {
               if (time1 < time2)
               {
                   distance -= mph1;
                   final_time += 60;
               }
               else if (time2 < time1)
               {
                   distance -= mph2;
               }
           }
           
           
           
           while (distance > 0)
           {
               distance -= (mph1/480);
               distance -= (mph2/480);
               final_time += .125;
           }
           
           
           double hours = final_time/60;
           int final_hours = (int) hours;
           
           double minutes = final_time%60;
           int final_minutes = (int) Math.round(minutes);
           
           System.out.println(final_hours + ":" + final_minutes);
       }
   }
}
