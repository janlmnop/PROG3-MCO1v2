import java.util.*;

public class Driver
{
    public static void main(String[] args) 
    {    
        Scanner mc = new Scanner(System.in); 
        Scanner pName = new Scanner(System.in); 
        Scanner pc = new Scanner(System.in); 
        Scanner view = new Scanner(System.in); 
        Scanner playerChar = new Scanner(System.in); 

        GameMethods gm = new GameMethods();

        int menuChoice = 0;
        int pChoice;
        int i; //for pname loop

        boolean endGame = false;

        Player player1 = new Player();
        Player player2 = new Player();

        while(!endGame)
        {
            System.out.println("Welcome to Fatal Fantasy!");
            System.out.println("_________________________");
            System.out.println("[1] start game");
            System.out.println("[2] exit game");

            do { 

                System.out.print("Enter Choice : ");
                menuChoice = mc.nextInt();

                if(menuChoice != 1 && menuChoice != 2)
                    System.err.println("Incorrect option. Pls retry");
                
            } while (menuChoice != 1 && menuChoice != 2);

            if(menuChoice == 1)
            {

                //character manager for p1 and p2
               for (i = 0; i < 2; i++) {
                    System.out.print("\n\nEnter Player #" + (i+1) + " Name : ");

                    if(i == 0)
                    {
                        player1.setName(pName.nextLine());
                        System.out.println("Hi! Player #" + (i+1) + " " + player1.getUserName());
                        System.out.println("                                                ");
                    }
                        
                    if(i == 1)
                    {
                        player2.setName(pName.nextLine());
                        System.out.println("Hi! Player #" + (i+1) + " " + player2.getUserName());
                        System.out.println("                                                ");
                    }

                    pChoice = 0;

                    while(pChoice != 1)
                    {
                        System.out.println("Player #" + (i+1) + " Character Manager Menu");
                        System.out.println("[1] choose character");
                        System.out.println("[2] view characters");
                        System.out.println("[3] create characters");
                        System.out.println("[4] edit characters");
                        System.out.println("[5] delete characters");

                        do { 

                            System.out.print("Enter Choice : ");
                            pChoice = pc.nextInt();

                            if(pChoice < 0 || pChoice > 5)
                                System.err.println("Incorrect option. Pls retry");
                            
                        } while (pChoice < 0 || pChoice > 5);

                        if(pChoice == 1) //choose
                        {
                            
                            if(i == 0)
                               chooseCharacter(player1, gm, playerChar, view);
                                
                            if(i == 1)
                                chooseCharacter(player2, gm, playerChar, view);

                        } if(pChoice == 2) // view
                        {
                            if(i == 0)
                                viewCharacters(player1, gm, view);
                                
                            if(i == 1)
                                viewCharacters(player2, gm, view);
                                

                        } if(pChoice == 3) //create
                        {
                            if(i == 0)
                                createCharacters(player1, gm);
                            
                            if(i == 1)
                                createCharacters(player2, gm);
        
                            
                        } if(pChoice == 4) //edit
                        {   
                            if (i == 0)
                                editCharacter(player1, gm, playerChar);

                            if (i == 1)
                                editCharacter(player2, gm, playerChar);
                        
                        } if(pChoice == 5) //delete
                        {
                            if(i == 0)
                                deleteCharacter(player1, gm);
                                
                            if(i == 1)
                                deleteCharacter(player2, gm);
  
                        }
                    }
                }

                //game proper

                gm.playGame(player1.getChosen(), player2.getChosen(), player1.getUserName(), player2.getUserName());
                
            } if (menuChoice == 2)
                endGame = true;
            
        }
    }

    public static void chooseCharacter(Player player, GameMethods gm, Scanner playerChar, Scanner view) 
    {
        player.setList(gm.loadCharacters(player.getUserName()));
            int k;
            String viewChoice;
            String playerChosenChar;


            System.out.print(" Compact[C] or Detailed[D] View? > ");
            viewChoice = view.nextLine();
            gm.displayCharacters(player.getList(), viewChoice);

            System.out.println("\n");

            // checks whether characters exist in a player's list
            boolean flag = false;
            do {
                System.out.print("Enter chosen character name > ");
                playerChosenChar = playerChar.nextLine();

                for (k = 0; k < player.getList().size(); k++) 
                {
                    if(playerChosenChar.equals(player.getList().get(k).getName()))
                    {
                        player.setChosen(player.getList().get(k));
                        System.out.println("Your Character = " + player.getChosen()); 
                        flag = true; 
                    }   
                }
                if(flag == false)
                    System.out.println("Invalid selection! Try again.");
            } while (flag == false); 
    }

    public static void viewCharacters(Player player, GameMethods gm, Scanner view) 
    {
        String viewChoice;

        player.setList(gm.loadCharacters(player.getUserName()));

        System.out.print(" Compact[C] or Detailed[D] View? > ");
        viewChoice = view.nextLine();
        gm.displayCharacters(player.getList(), viewChoice);

        System.out.println("\n");
    }

    public static void createCharacters(Player player, GameMethods gm) 
    {
        int j;

        player.setList(gm.loadCharacters(player.getUserName()));

        if (!player.getList().isEmpty()) {
            System.out.println("\nLoaded " + player.getList().size() + " existing character(s)");
        }

        System.out.print("\nHow many new characters to add? ");
        int count = gm.getPositiveInteger();
        if(count + player.getList().size() > 6)
            System.out.println("Character list is at its capacity! Cannot add more characters.\n");
        else {
            for (j = 0; j < count; j++) {
                System.out.println("\nCharacter #" + (j + 1));
                player.getList().add(gm.createCharacter(player));
            }

            gm.saveCharacters(player.getList(), player.getUserName());
        }
    }

    public static void editCharacter(Player player, GameMethods gm, Scanner playerChar) 
    {
        Character ch = null;
        System.out.print("Enter character name to edit > ");
        String charToEdit = playerChar.nextLine();

        //for(Character c : player1.getList().get(i))
        for(int l = 0; l < player.getList().size(); l++)
        {
            if(charToEdit.equals(player.getList().get(l).getName()))
            {
                //System.out.println("OldList " + player1.getList());
                //System.out.println("Name " + player1.getList().get(l).getName());
                //System.out.println("Char " + player1.getList().get(l));

                player.getList().get(l).getCharAbs().clear();

                ch = gm.editCharacter(player.getList().get(l));
                player.getList().remove(player.getList().get(l));
                player.getList().add(ch);

                gm.saveCharacters(player.getList(), player.getUserName());
                System.out.println("Saved Abilities in Character" + ch.getName());

                //System.out.println("NewChar " + ch);
                //System.out.println("NewList " + player1.getList());
                l = 100;
            }
               
        }
        System.out.println("No such character exists.");
    }

    public static void deleteCharacter(Player player, GameMethods gm)
    {
        player.setList(gm.loadCharacters(player.getUserName()));
        gm.deleteCharacter(player.getList());
        gm.saveCharacters(player.getList(), player.getUserName());
        gm.displayCharacters(player.getList(), "C");
        System.out.println("\n");
    }

}