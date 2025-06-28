import java.io.*;
import java.util.*;
import java.time.*; 
import java.time.format.DateTimeFormatter;
import java.lang.Math;

public class GameMethods {

    private Scanner input = new Scanner(System.in);
    private Scanner pMove = new Scanner(System.in);

    public Character createCharacter(Player player) 
    {
        String name;
        String className;
        int hp; 
        int ep;
        Class cls = null;
        ArrayList<Ability> abs = new ArrayList<>();

        // checks if character name already exists in list
        boolean flag = false;
        do {
            System.out.print("Name: ");
            name = input.nextLine();

            flag = true;
            for (int k = 0; k < player.getList().size(); k++) 
                if(name.equals(player.getList().get(k).getName()))
                    flag = false; 

            if(!flag)
                System.out.println(name + " is already in the list. Try again.");
        } while (!flag); 
    
        // checks class name    
        do {
            System.out.print("Class (Mage, Rogue, Warrior): ");
            className = input.nextLine();
        } while (!className.equals("Mage") && !className.equals("Rogue") && !className.equals("Warrior"));

        if("Mage".equals(className))
        {
             cls = GameClasses.Mage();
            System.out.println("Mage Abilities:");
            abilitySelection(cls, abs);
        }
            
        if("Rogue".equals(className))
        {
            cls = GameClasses.Rogue();
            System.out.println("Rogue Abilities:");
            abilitySelection(cls, abs); 
        }

        if("Warrior".equals(className))
        {
            cls = GameClasses.Warrior();
            System.out.println("Warrior Abilities:");
            abilitySelection(cls, abs);
        }

        // add recharge and defend to character moves
        abs.add(new Ability("Defend", "The character takes on a defensive stance and takes only half damage.", 5, 0));
        abs.add(new Ability("Recharge", "The character does nothing during the round but regains 5 EP.", 0, 5));
        
       do {
            System.out.print("HP: ");
            hp = getPositiveInteger(); 
        } while(hp > 100); 
        
        do {
            System.out.print("EP: ");
            ep = getPositiveInteger();
        } while (ep > 50);

        return new Character(name, cls, hp, ep, abs);
    }

    //sub-method for createCharacter
    public void abilitySelection(Class cls, ArrayList<Ability> abs) 
    { 
        //System.out.println(cls.getAb());
            int j = 1;
            int i = 1;
            int choice;
            
                for (Ability a : cls.getAb()) {
                    System.out.printf("%d. %s\n", i++, a);
                }

            while(j < 4)
            {
                do {
                    System.out.print("Enter Ability " + j + " number to add: ");
                    choice = input.nextInt();
                    input.nextLine(); // Clear buffer
                } while (choice > cls.getAb().size() || choice < 1);

                abs.add(cls.getAb().get(choice-1));
                j++;
            }

            System.out.println("Chosen Abilities:");
            for (Ability ability : abs) {
                System.out.println(" - " + ability);
            }
    }

    public Character editCharacter(Character ch) 
    {
        Class cls;
        ArrayList<Ability> abs = new ArrayList<>();

        System.out.println("ClassChar in Edit" + ch.getCl().getName());
        if("Mage".equals(ch.getCl().getName()))
        {
            cls = GameClasses.Mage();
            abs = cls.getAb();
            System.out.println("Mage Abilities:");
            changeAbilities(cls, ch, abs);
        }

        if("Rogue".equals(ch.getCl().getName()))
        {
            cls = GameClasses.Rogue();
            abs = cls.getAb();
            System.out.println("Rogue Abilities:");
            changeAbilities(cls, ch, abs); 
        }

        if("Warrior".equals(ch.getCl().getName()))
        {
            cls = GameClasses.Warrior();
            abs = cls.getAb();
            System.out.println("Warrior Abilities:");
            changeAbilities(cls, ch, abs);
        }

        return ch;
    }
    
    //sub-method for editCharacter
    public void changeAbilities(Class cls, Character ch, ArrayList<Ability> abs) 
    {
        Ability a1, a2, a3;
        int i = 1;
        for (Ability a : cls.getAb())
            System.out.printf("%d. %s\n", i++, a);
            
        System.out.print("Enter Ability 1 number to add: ");
        a1 = abs.get(input.nextInt()-1);
        input.nextLine(); // Clear buffer

        System.out.print("Enter Ability 2 number to add: ");
        a2 = abs.get(input.nextInt()-1);
        input.nextLine(); // Clear buffer

        System.out.print("Enter Ability 3 number to add: ");
        a3 = abs.get(input.nextInt()-1);
        input.nextLine(); // Clear buffer

        System.out.println("Chosen Abilities:");
        System.out.println(" - " + a1);
        System.out.println(" - " + a2);
        System.out.println(" - " + a3);

        // add defend and recharge to list of moves
        abs.add(new Ability("Defend", "The character takes on a defensive stance and takes only half damage.", 5, 0));
        abs.add(new Ability("Recharge", "The character does nothing during the round but regains 5 EP.", 0, 5));

        ch.setCharAbs(abs);
    }

    // read / view file contents -- I don't think we need this na
    public ArrayList<Character> loadCharacters(String filename) {
        ArrayList<Character> container = new ArrayList<>();
        ArrayList<String> another = new ArrayList<>();

        File file = new File(filename + ".txt");
        if (!file.exists()) return container;

        try {
            Scanner reader = new Scanner(file);

            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                another.add(line);
            }

            for(int i=0; i<container.size(); i++) {
                container.get(i).setName(another.get(i));
            }

            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error has occurred.");
            e.printStackTrace();
        }

        return container;
    }

    // this method reads the player's character name, type, abilities, hp, and ep
    public void readFile(Player player) {
        try {
            File Obj = new File(player.getUserName() + ".txt");
            Scanner stringrdr = new Scanner(Obj);
            Scanner intrdr = new Scanner(Obj);
          
            while (stringrdr.hasNextLine()) {
                // read name, type, abilities, hp, and ep from a text file
                String name = stringrdr.nextLine();
             
                String type = stringrdr.nextLine();
                Class class1 = new Class(type);

                // read abilites and assign them to the character's ability pool
                ArrayList<Ability> newA = new ArrayList<>();
                for (int i = 0; i < 5; i++) {
                    if (stringrdr.hasNextLine()) {
                        String ability = stringrdr.nextLine();
                        newA.add(new Ability(ability));
                    }
                }

                int hp = stringrdr.nextInt();
                int ep = stringrdr.nextInt();

                // Move to next line after reading numbers (if needed)
                if (stringrdr.hasNextLine()) stringrdr.nextLine();
                if (stringrdr.hasNextLine()) stringrdr.nextLine();

                // Add character
                Character newChar = new Character(name, class1, hp, ep, newA);
                player.getList().add(newChar);
            
            
            }
            stringrdr.close();
            intrdr.close();  
        }
        catch (FileNotFoundException e) {
            System.out.println("An error has occurred.");
            e.printStackTrace();
        }
        
    }
    
    // write / add to file
    public void saveCharacters(ArrayList<Character> characters, String filename) {

        try (Writer writer = new FileWriter(filename + ".txt")) {
            for(int i=0; i<characters.size(); i++) {
                writer.write(characters.get(i).getName() + "\n");
                writer.write(characters.get(i).getCl() + "\n");
                for(int j=0; j<5; j++)
                    writer.write(characters.get(i).getCharAbs().get(j).getName() + "\n");
                writer.write(characters.get(i).getHp() + "\n");
                writer.write(characters.get(i).getEp() + "\n\n");

            }
            System.out.println("\nSaved " + characters.size() + " characters to " + filename + ".txt");

        } catch (IOException e) {
            System.out.println("Error saving: " + e.getMessage());
        }
    }

    public void displayCharacters(ArrayList<Character> characters, String mode) 
    {
        if (characters.isEmpty())
            System.out.println("\nNo characters available.");
        
        if(!characters.isEmpty())
        {
            System.out.println("\nCurrent Characters");
            System.out.println("----------------------------------------");
            
            for (Character c : characters) {
                if ("D".equalsIgnoreCase(mode)) {
                    // Detailed view with abilities
                    System.out.println(c.toDetailedString());
                } else if("C".equalsIgnoreCase(mode)) {
                    // Compact single-line view
                    System.out.println(c.toCompactString());
                } else 
                    System.out.println("Invalid input!");
                System.out.println("----------------------------------------");
            }
        }
        
    }

    public int getPositiveInteger() 
    {
        while (true) 
        {
            try 
            {
                int value = Integer.parseInt(input.nextLine());
                if (value > 0) return value;
                System.out.print("Must be positive. Try again: ");

            } catch (NumberFormatException e) 
            {
                System.out.print("Invalid number. Try again: ");
            }
        }
    }

    public ArrayList<Character> deleteCharacter(ArrayList<Character> list) 
    {

        String name;
        int i, idx = -1;

        if (list.isEmpty()) 
            System.out.println("No characters to delete!");
            
        else {

            System.out.print("What Character you want to delete? > ");
            name = input.nextLine();

            for (i = 0; i < list.size(); i++) 
            {
                if(name.equals(list.get(i).getName()))
                    idx = i;
            }

            if(idx != -1)
                list.remove(idx);
            if(idx == -1)
                System.out.println("Character not found!"); 
        }

        return list;
    }

    public ArrayList<Ability> readMageAbilities() {
        ArrayList<Ability> abilities = new ArrayList<>();

        try {
            File file = new File("MageAbilities.txt");
            Scanner reader = new Scanner(file);

            while (reader.hasNextLine()) {
                String name = reader.nextLine();
                String desc = reader.nextLine();
                int epCost = reader.nextInt();
                int effectVal = reader.nextInt();

                if (reader.hasNextLine()) reader.nextLine();
                if (reader.hasNextLine()) reader.nextLine();

                abilities.add(new Ability(name, desc, epCost, effectVal));
            }

            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Ability file not found.");
            e.printStackTrace();
        }

        abilities.add(new Ability("Defend", "The character takes on a defensive stance and takes only half damage.", 5, 0));
        abilities.add(new Ability("Recharge", "The character does nothing during the round but regains 5 EP.", 0, 5));
        return abilities;
    }

    public ArrayList<Ability> readRogueAbilities() {
        ArrayList<Ability> abilities = new ArrayList<>();

        try {
            File file = new File("RogueAbilities.txt");
            Scanner reader = new Scanner(file);

            while (reader.hasNextLine()) {
                String name = reader.nextLine();
                String desc = reader.nextLine();
                int epCost = reader.nextInt();
                int effectVal = reader.nextInt();

                if (reader.hasNextLine()) reader.nextLine();
                if (reader.hasNextLine()) reader.nextLine();

                abilities.add(new Ability(name, desc, epCost, effectVal));
            }

            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Ability file not found.");
            e.printStackTrace();
        }

        abilities.add(new Ability("Defend", "The character takes on a defensive stance and takes only half damage.", 5, 0));
        abilities.add(new Ability("Recharge", "The character does nothing during the round but regains 5 EP.", 0, 5));
        return abilities;
    }

    public ArrayList<Ability> readWarriorAbilities() {
        ArrayList<Ability> abilities = new ArrayList<>();

        try {
            File file = new File("WarriorAbilities.txt");
            Scanner reader = new Scanner(file);

            while (reader.hasNextLine()) {
                String name = reader.nextLine();
                String desc = reader.nextLine();
                int epCost = reader.nextInt();
                int effectVal = reader.nextInt();

                if (reader.hasNextLine()) reader.nextLine();
                if (reader.hasNextLine()) reader.nextLine();

                abilities.add(new Ability(name, desc, epCost, effectVal));
            }

            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Ability file not found.");
            e.printStackTrace();
        }

        abilities.add(new Ability("Defend", "The character takes on a defensive stance and takes only half damage.", 5, 0));
        abilities.add(new Ability("Recharge", "The character does nothing during the round but regains 5 EP.", 0, 5));
        return abilities;
    }

    // assigns ability info from (class_type)Abilities.txt to the player's character abilities
    public void assignAbilityInfo(Player player) {
        ArrayList<Ability> mageAbilities = readMageAbilities();
        ArrayList<Ability> rogueAbilities = readRogueAbilities();
        ArrayList<Ability> warriorAbilities = readWarriorAbilities();

        for (int i = 0; i < player.getList().size(); i++) {
            Character ch = player.getList().get(i);
            if (ch.getCl().getName().equals("Mage")) {
                for (int j = 0; j < ch.getCharAbs().size(); j++) {
                    String name = ch.getCharAbs().get(j).getName();
                    for (Ability ab : mageAbilities) {
                        if (ab.getName().equals(name)) {
                            ch.getCharAbs().get(j).setDescription(ab.getDesc());
                            ch.getCharAbs().get(j).setCost(ab.getEc());
                            ch.getCharAbs().get(j).setEffectVal(ab.getEv());
                        }
                    }
                }
            }
            if (ch.getCl().getName().equals("Rogue")) {
                for (int j = 0; j < ch.getCharAbs().size(); j++) {
                    String name = ch.getCharAbs().get(j).getName();
                    for (Ability ab : rogueAbilities) {
                        if (ab.getName().equals(name)) {
                            ch.getCharAbs().get(j).setDescription(ab.getDesc());
                            ch.getCharAbs().get(j).setCost(ab.getEc());
                            ch.getCharAbs().get(j).setEffectVal(ab.getEv());
                        }
                    }
                }
            }
            if (ch.getCl().getName().equals("Warrior")) {
                for (int j = 0; j < ch.getCharAbs().size(); j++) {
                    String name = ch.getCharAbs().get(j).getName();
                    for (Ability ab : warriorAbilities) {
                        if (ab.getName().equals(name)) {
                            ch.getCharAbs().get(j).setDescription(ab.getDesc());
                            ch.getCharAbs().get(j).setCost(ab.getEc());
                            ch.getCharAbs().get(j).setEffectVal(ab.getEv());
                        }
                    }
                }
            }
        }
    }

    public void playGame(Character p1, Character p2, String p1Name, String p2Name)
    {
        int i;
        int rounds = 1;
        String p1MoveC, p2MoveC;
        Ability p1MoveStore = null;
        Ability p2MoveStore = null;
        int[] p1MoveVal;
        int[] p2MoveVal;
        CombatLog combatLog = new CombatLog();

        while(p1.getHp() != 0 && p2.getHp() != 0)
        {
            System.out.println("\n\n---------------------R O U N D  " + rounds + "---------------------"); 
            if(rounds > 1) {
                p1.setEp(Math.min((p1.getEp()+5), 50));
                p2.setEp(Math.min((p2.getEp()+5), 50));
            }

            System.out.println("CURRENT STATS"); 
            System.out.println("P1 = " + p1);
            System.out.println("P2 = " + p2);

            System.out.println("------------------------------------------------------"); 

            System.out.println("\n\n" + p1.toDetailedString());
            // loops until input is valid: exists in list && EP cost > character EP
            boolean flag = false;
            do {
                System.out.print("Choose move to play > ");
                p1MoveC = pMove.nextLine();

                for (int k = 0; k < p1.getCharAbs().size(); k++) {
                    if(p1MoveC.equals(p1.getCharAbs().get(k).getName())) {
                        

                        if (p1.getEp() >= p1.getCharAbs().get(k).getEc()) {
                            p1MoveStore = p1.getCharAbs().get(k);
                            flag = true;
                        }
                    }
                }        
            } while (flag != true);

            System.out.println("\n\n" + p2.toDetailedString());
            // loops until input is valid: exists in list && EP cost > character EP
            do {
                System.out.print("Choose move to play > ");
                p2MoveC = pMove.nextLine();

                for (int k = 0; k < p2.getCharAbs().size(); k++) {
                    if(p2MoveC.equals(p2.getCharAbs().get(k).getName())) {
                        

                        if (p2.getEp() >= p2.getCharAbs().get(k).getEc()) {
                            p2MoveStore = p2.getCharAbs().get(k);
                            flag = true;
                        }
                    }
                }        
            } while (flag != true);

            p1MoveVal = p1.fight(p1MoveStore, p2MoveStore, p2.getHp(), p1.getHp(), p1.getEp());
            p2.setHp(p1MoveVal[0]);
            p1.setHp(p1MoveVal[1]);
            p1.setEp(p1MoveVal[2]);

            p2MoveVal = p2.fight(p2MoveStore, p1MoveStore, p1.getHp(), p2.getHp(), p2.getEp());
            p1.setHp(p2MoveVal[0]);
            p2.setHp(p2MoveVal[1]);
            p2.setEp(p2MoveVal[2]);

            if(p1.getHp() < 0) 
                p1.setHp(0);

            if(p2.getHp() < 0) 
                p2.setHp(0);

            if(p1.getEp() < 0) 
                p1.setEp(0);

            if(p2.getEp() < 0)
                p2.setEp(0);  

            System.out.println("\nRESULTS");
            System.out.println("P1 = " + p1);
            System.out.println("P2 = " + p2);

            combatLog.logRoundMoves(rounds, p1MoveStore.getName(), p2MoveStore.getName(), p1, p2);

            rounds++;
        }

        if(p1.getHp() > 0)
            System.out.println("\n WINNER PLAYER 1 : " + p1Name + " | WITH CHARACTER " + p1.getName());  

        if(p2.getHp() > 0)
            System.out.println("\n WINNER PLAYER 2 : " + p2Name + " | WITH CHARACTER " + p2.getName());

        if(p1.getHp() == 0 && p2.getHp() == 0)
            System.out.println("\n DRAW!");

        // combat log
        System.out.println("_______________Combat Log_______________"); 

        combatLog.printLog();

        String dateAndTime = "Gameplay DateTime = " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("(yyyy-MM-dd) HH.mm.ss"));
        

        try (Writer writer = new FileWriter(dateAndTime + ".txt")) 
        {
            for (String line : combatLog.getLog()) {
                writer.write(line);
            }

            System.out.println("\nCombat Log saved to" + dateAndTime + ".txt");

        } catch (IOException e) {
            System.out.println("Error creating combat log: " + e.getMessage());
        } 
    }
}