import java.util.Scanner;

public class ACSL
{
    private String[] sublists;
    
    public ACSL(String input)
    {        
        int count = 0;      
        
        for (int i = 0; i < input.length(); i++)
        {
            if (input.charAt(i) == '(')
            {
                count++;
            }
        }
        
        this.sublists = new String[count];
        count = 0;
        
        for (int i = 0; i < input.length(); i++)
        {           
            if (input.charAt(i) == '(')
            {
                int j = i;
                for (j = i; j < input.length(); j++)
                {
                    if (input.charAt(j) == ')')
                    {
                        this.sublists[count] = input.substring(i, j+1) + " ";
                        break;
                    }
                    else if (i < 5 && input.charAt(j) == ' ')
                    {
                        this.sublists[count] = input.substring(i, j+1);
                        break;
                    }
                }
                count++;
            }
        }
        
        //for (String i : this.sublists)
        //{
        //     System.out.print(i);
        //}        
        //System.out.println("");
    }
    
    public int count()
    {
        return this.sublists.length-1;
    }
    
    public String remove(String command)
    {
        String[] removedSubs = new String[this.sublists.length];
        for (int i = 0; i < removedSubs.length; i++)
        {
            removedSubs[i] = this.sublists[i];
        }
        
        String removed = "";
        int[] nums = this.getNums(command);
        
        for (int i = 0; i < removedSubs.length; i++)
        {
            if (i >= nums[0] && i <= nums[1])
            {
                removedSubs[i] = "";
            }
        }
        
        removedSubs[removedSubs.length-1] = removedSubs[removedSubs.length-1].trim();
        
        for (int i = 0; i < removedSubs.length; i++)
        {
            removed += removedSubs[i];
        }       
        
        return removed + ")";
    }
    
    public String sort(String command)
    {
        String[] sortedSubs = new String[this.sublists.length];
        String sorted = "";
        
        for (int i = 0; i < sortedSubs.length; i++)
        {
            sortedSubs[i] = this.sublists[i];
        }
        
        int[] nums = this.getNums(command);
        
        boolean done = false;
        
        while (!done)
        {
            int unsorted = 0;
            for (int i = nums[0]; i < nums[1]; i++)
            {
                if ((sortedSubs[i].charAt(1) > sortedSubs[i+1].charAt(1)) 
                    || (sortedSubs[i].charAt(1) == 'S' && sortedSubs[i+1].charAt(1) == 'S' 
                        && sortedSubs[i].charAt(2) > sortedSubs[i+1].charAt(2)))
                {
                    String temp = sortedSubs[i];
                    sortedSubs[i] = sortedSubs[i+1];
                    sortedSubs[i+1] = temp;
                    
                    unsorted++;
                }
            }
            
            if (unsorted == 0)
            {
                done = true;
            }
        }
        
        sortedSubs[sortedSubs.length-1] = sortedSubs[sortedSubs.length-1].trim();
        
        for (int i = 0; i < sortedSubs.length; i++)
        {
            sorted += sortedSubs[i];
        }       
        
        return sorted + ")";
    }
    
    public String reverse(String command)
    {
        String[] reversedSubs = new String[this.sublists.length];
        String reversed = "";
        
        for (int i = 0; i < reversedSubs.length; i++)
        {
            reversedSubs[i] = this.sublists[i];
        }
        
        int[]nums = this.getNums(command);
        
        int shift = 0;
        if ((nums[1]-nums[0]+1)%2.0 == 0) //even
        {
            for (int i = nums[0]; i <= (nums[1]-nums[0]+1)/2; i++)
            {
                String temp = reversedSubs[nums[0] + shift];
                reversedSubs[nums[0] + shift] = reversedSubs[nums[1] - shift];
                reversedSubs[nums[1] - shift] = temp;
                
                shift++;
            }
        }
        else //odd
        {
            for (int i = nums[0]; i <= ((nums[1]-nums[0]+1)/2.0)-.5; i++)
            {
                String temp = reversedSubs[nums[0] + shift];
                reversedSubs[nums[0] + shift] = reversedSubs[nums[1] - shift];
                reversedSubs[nums[1] - shift] = temp;
                
                shift++;
            }
        }
        
        reversedSubs[reversedSubs.length-1] = reversedSubs[reversedSubs.length-1].trim();
        
        for (int i = 0; i < reversedSubs.length; i++)
        {
            reversed += reversedSubs[i];
        }       
        
        return reversed + ")";
    }
    
    public String maximum()
    {
        int[] count = new int[this.sublists.length];
        
        for (int i = 0; i < this.sublists.length; i++)
        {
            for (int j = 0; j < this.sublists[i].length()-1; j++)
            {
                if (this.sublists[i].charAt(j) == ' ')
                {
                    count[i]++;
                }
            }
        }
        
        int most = 0;
        String[] maximumSubs = new String[this.sublists.length];
        for (int i = 0; i < this.sublists.length; i++)
        {
            maximumSubs[i] = "";
        }
        String maximum = "";
        int max = 1;
        int maxSub = 0;
        
        for (int i = 0; i < count.length; i++)
        {
            if (count[i] > most)
            {
                most = count[i];
                maximumSubs[0] = this.sublists[i];
                maxSub = i;
            }
        }
        for (int i = 0; i < count.length; i++)
        {
            if (count[i] == most && i != maxSub)
            {
                maximumSubs[max] = this.sublists[i];
                max++;
            }
        }
        
        for (int i = 0; i < maximumSubs.length; i++)
        {
            maximum += maximumSubs[i];
        }
        
        return maximum;
    }
    
    private int[] getNums(String command)
    {
        int[] nums = new int[2];
        int count = 0;
        
        for (int i = 0; i < command.length(); i++)
        {
            if (command.charAt(i) == ' ')
            {
                Character num = new Character(command.charAt(i+1));
                nums[count] = num.getNumericValue(command.charAt(i+1));
                count++;
            }
        }
        
        return nums;
    }
    
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        
        System.out.print("LISP: ");
        ACSL object = new ACSL(scan.nextLine());
        
        for (int i = 0; i < 5; i++)
        {
            System.out.print("\nCommand: ");
            String command = scan.nextLine();
            
            if (command.charAt(0) == 'C')
            {
                System.out.println(object.count());
            }
            else if (command.charAt(0) == 'R')
            {
                if (command.charAt(2) == 'M')
                {
                    System.out.println(object.remove(command));
                }
                else if (command.charAt(2) == 'V')
                {
                    System.out.println(object.reverse(command));
                }
            }
            else if (command.charAt(0) == 'S')
            {
                System.out.println(object.sort(command));
            }
            else if (command.charAt(0) == 'M')
            {
                System.out.println(object.maximum());
            }
            else
            {
                System.out.println(command + " is not a valid command!");
                System.out.println("Valid commands:\nCOUNT\nREMOVE J K\nSORT J K\nREVERSE J K\nMAXIMUM");
                i--;
            }
        }
        
        //Testing
        //ACSL object = new ACSL("(ADD (EXP -3 2) (SQR 5) (SUB 6 2) (MULT 6 7 -2 3) (DIV 15 5))");
        //System.out.println(object.sort("SORT 3 5"));
        //System.out.println(object.reverse("REVERSE 1 4"));
        //System.out.println(object.count());
        //System.out.println(object.remove("REMOVE 3 4"));
        //System.out.println(object.maximum());
    }
}    