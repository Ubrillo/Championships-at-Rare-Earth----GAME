package cwk4;
import java.io.*;
import java.util.*;
/**
 * Command line interface
 * 
 * @author A.A.Marczyk
 * @version 10/03/2024
 */
public class GameUI
{
    
    private Scanner myIn = new Scanner(System.in);

    public void runGame()
    {
        Tournament tr ;
        int choice;
        String vizierName;
        String output = "";
        int result = -1; 
        try
        {
            System.out.println("Enter vizier's name");
            String s = myIn.nextLine();
            //myIn.nextLine();
            tr = new Tournament(s); // create
            //tr = new Tournament(s,"challengesAM.txt"); // alternative create task 3.5
            choice = 100;
            while (choice != 0 )
            {
                choice = getMenuItem();
                if (choice == 1)
                {
                    System.out.println(tr.getReserve());
                }
                else if (choice == 2)
                {
                    System.out.println(tr.getTeam());
                }
                else if (choice == 3)
                {
                    System.out.println("Enter Champion name");
                    String ref = (myIn.nextLine()).trim();
                    System.out.println(tr.getChampionDetails(ref));
                }
                else if (choice == 4)
                {
                    System.out.println("Enter Champion name: ");
                    String data = (myIn.nextLine()).trim();
                    int outcome = tr.enterChampion(data);
                    if (outcome == 0){
                        System.out.println("Champion successfully added to team");
                    } else if (outcome == 1){
                        System.out.println("Champion not found in reserved!!!");
                    } else if(outcome == 2){
                        System.out.println("Not enough money in treasury to enter champion");
                    } else if (outcome == -1){
                        System.out.println("There is no such champion"+" "+data);
                    }else{
                        System.out.println("Something else is wrong...");
                    }
                }
                else if (choice == 5)
                {
                    System.out.println("Enter challenge number: ");
                    int no = myIn.nextInt();
                    int outcome = tr.meetChallenge(no);
                    if (outcome == 0){
                        System.out.println("you won!!");
                    }else if (outcome == 1){
                        System.out.println("you lost! no skill match!");
                    }else if (outcome == 2){
                        System.out.println("challenge lost! you didnt have an available champion");
                    }else if(outcome == 3){
                        System.out.println("your team has lost completely.");
                    }else if(outcome == -1){
                        System.out.println("no such challenge");
                    }
                }
                else if (choice==6)
                {
                    System.out.println("Enter Champions' name: ");
                    String data  = (myIn.nextLine()).trim();
                    int outcome = tr.retireChampion(data);
                    if (outcome == 0){
                        System.out.println("Champion successfully retired");
                    }else if (outcome == 1){
                        System.out.println("Champion not retired because champion is disqualified");
                    }else if(outcome == 2){
                        System.out.println("champion not retired because champion is  not in team");
                    }else if (outcome == -1){
                        System.out.println("if no such champion");
                    }
                }  
                else if (choice==7)
                {
                    System.out.println(tr.toString());
                }
                else if (choice==8)
                {
                    System.out.println(tr.getAllChallenges());
                }
                else if (choice == 9) // Task 3.5 only
                {
                    System.out.println("Write to file");
                    System.out.println("Enter file name");
                    String filename = myIn.nextLine();
                    tr.saveGame(filename);
                }
                else if (choice == 10) // Task 3.5 only
                {
                    System.out.println("Restore from file");
                    System.out.println("Enter file name");
                    String filename = myIn.nextLine();
                    CARE tr2= tr.loadGame(filename);
                    if (tr2 != null)
                    {
                        System.out.println(tr2.toString());
                    }
                    else
                    {
                        System.out.println("No such file");
                    }
                }
            }     
        }
        catch (IOException e) {System.out.println (e);}   
        System.out.println("Thank-you");
    }
    
    private int getMenuItem()throws IOException
    {   int choice = 100;  
        System.out.println("\nMain Menu");
        System.out.println("0. Quit");
        System.out.println("1. List champions in reserve");
        System.out.println("2. List champions in viziers team"); 
        System.out.println("3. View a champion");
        System.out.println("4. Enter champion into vizier's team");
        System.out.println("5. Meet a challenge");
        System.out.println("6. Retire a champion");
        System.out.println("7. View game state");
        System.out.println("8. See all challenges");
        System.out.println("9. Save this game");
        System.out.println("10. Load this game");
       
        
        while (choice < 0 || choice  > 10)
        {
            System.out.println("Enter the number of your choice");
            choice =  myIn.nextInt();
        }
        myIn.nextLine();
        return choice;        
    }  
    
    private String processChallengeResult(int res) {
    
        String out;
        if (res ==0)
        {
            out = "Challenge won";
        }
        else if (res ==1)
        {
            out = "Challenge lost on skill level";
        }
        else if (res ==2)
        {
            out = "Challenge lost as no champion available";
        }
        else if (res ==3)
        {
            out = "Challenge lost with no further resources. You lose the game ";
        }
        else if (res == -1)
        {
            out = "No such challenge";
        }
        else
        {
            out = "No such result";
        }
        return out; 
    }
    
    public static void main(String[] args)
    {
        new GameUI().runGame();
    }
}
