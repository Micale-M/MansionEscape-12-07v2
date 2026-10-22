import java.util.Stack;
import java.util.Random;
import java.util.ArrayList;
import java.util.*;
import java.util.Arrays;

/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 7.0
 */

public class Game 
{
    private Parser parser;
    //private Room currentRoom;
    private Stack<Room> previousRoom;
    private Player player;
    private Random gen;
    private TuneCreator pianoMusic;
    private HashMap<String, Integer> PoemKey;
    private ArrayList<String> Poem;
    private Integer[] PoemNums;
    private ArrayList<String> Flowers;
    private boolean frontdoorLock;
    private boolean passcode;
    private int actions;
    
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        player = new Player();
        gen = new Random();
        pianoMusic = new TuneCreator();
        pianoMusic.setPianoNotes();
        PoemKey = new HashMap<>();
        Poem = new ArrayList<>();
        PoemNums = new Integer[3];
        Flowers = new ArrayList<>();
        createRooms();
        parser = new Parser();
        previousRoom = new Stack<>();
        frontdoorLock = true;
        passcode = true;
        actions = 0;
    }

    /**
     * Create all the rooms and link their exits together.
     */
    public void createRooms()
    {
        //8.4b
        Room foyer, mechanical, dungeon, cellar, kitchen, greenhouse, pantry;
        Room diningRoom, floorhall, livingRoom, library, upperhall, masterRoom, masterHall;
        Room closet, masterBathroom, cupboard, bedroom, tub, mainHall, bathroom, frontDoor;
        //Item diary, Goldkey, Silverkey
        Item backpack, poem, wine, screw, tool, lever, Bronzekey;
        Item food, ladder, sheetMusic, cup1, cup2, cup3, sleepPill;
      
        // create the rooms
        foyer = new Room("in the foyer of the basement");
        //mechanical = new Room("in the mechanical room that controls the power to the house");
        mechanical = new Room(
        """
        in the mechanical room.
        On the wall is a red flower with an equal sign to the number one.
        Under it lies the letter A. """);
        dungeon = new Room("in a run down cell, reminiscent of an old dungeon.");
        cellar = new Room("in a cellar, holding the house's finest wines.");
        kitchen = new Room("in the kitchen. It's filthy dirty.");
        greenhouse = new Room("in a greenhouse. There's a dry patch of dirt with three sprouts sticking out.");
        pantry = new Room("in the pantry. Maybe you can grab a snack.");
        diningRoom = new Room("""
        in the diningRoom. It's unsettling to see so many chairs unattended.
        On the wall is a yellow flower with an equal sign to the number 2.
        Under it lies the letter D. """);
        floorhall = new Room("in the hall of the ground floor of the house.");
        livingRoom = new Room("in the living room. In the corner lies a piano.");
        library = new Room("in the library. It holds a numerous number of books.");
        upperhall = new Room("in the hall of the first floor.");
        masterRoom = new Room("in the master bedroom. The bed looks old, covered in dust.");
        masterHall = new Room("in the hall of the master bedroom.");
        closet = new Room("");
        masterBathroom = new Room("in the master bathroom. The walls of the room are moldy.");
        cupboard = new Room("");
        bedroom = new Room("in a bedroom. Inside resides one small, child-like bed.");
        tub = new Room("""
        in the tub. A rubber duck sits inside. One of the eyes are missing.
        On the wall is a blue flower with an equal sign to the number 2.
        Under it lies the letter D.""");
        mainHall = new Room("in another hall on the first floor.");
        bathroom = new Room("in a bathroom. Your reflection in the mirror is haunting.");
        frontDoor = new Room("at the front door. It appears it requires a key and a 3 digit code to escape.");
        
        // creates items
        //diary = new Item("a diary with a gold lock");
        //diary.setSecondDesciption("You can't open it without a key");
        backpack = new Item("a backpack.");
        backpack.setSecondDesciption("It allows you to hold up to 3 more items.");
        poem = new Item("a poem.");
        int a = 0;
        for(int i = 0; i < 3; i++) {
            String f = "";
            switch(gen.nextInt(3)){
            case 0 -> f = "red";
            case 1 -> f = "yellow";
            case 2 -> f = "blue";
            }
            Flowers.add(f);
            switch(f) {
                case "red" -> a = a+1;
                case "yellow" -> a = a+2;
                case "blue" -> a = a+3;
            }
        }
        
        String t = "The poem claims the house clings to those it captures: \n";
        PoemKey.put("of flower that bloom for its chosen, ", a);
        PoemKey.put("of cobwebs that write in dust, ", gen.nextInt(1, 10));
        PoemKey.put("of claw marks left by its keeper, ", gen.nextInt(1, 6));
        for(String s: PoemKey.keySet()) {
            Poem.add(s);
        }
        Collections.shuffle(Poem);
         for(String s: Poem) {
            t += s + "\n";
        }
        int pop = 0;
        for(String s: Poem){
            PoemNums[pop] = (PoemKey.get(s));
            pop++;
        }
        poem.setSecondDesciption(t.substring(0, t.length() - 3));
        cupboard.setDescription("in the cupboard. There are " + PoemKey.get("of claw marks left by its keeper, ") + " claw marks on the wall.");
        closet.setDescription("in the closet. In the corner lies some webs, you can sort of make out the number " + PoemKey.get("of cobwebs that write in dust, "));
        
        Item food1 = new Item("sugar cubes");
        Item food2= new Item("a jar of jam");
        switch(gen.nextInt(2)) {
            case 0 -> pantry.addItem(food1);
            case 1 -> diningRoom.addItem(food1);
        }
        switch(gen.nextInt(2)) {
            case 0 -> pantry.addItem(food2);
            case 1 -> diningRoom.addItem(food2);
        }
        // switch(gen.nextInt(2)) {
            // case 0 -> food = new Item("sugar cubes");
            // case 1 -> food = new Item("a jar of jam");
            // case 2 -> food = new Item("moldy fruits");
        // }
        wine = new Item("a bottle filled with wine");
        food1.setEdible(true);
        food2.setEdible(true);
        wine.setEdible(true);
        // wine.setSecondDesciption("It looks like the monster enjoys drinking it");
        // sleepPill = new Item("a sleeping pill");
        // sleepPill.setSecondDesciption("Maybe you can trick the monster into taking it");
        // screw = new Item("a loose screw");
        // screw.setSecondDesciption("You wonder where it's missing from");
        // tool = new Item("a handy tool");
        // tool.setSecondDesciption("It looks like you can screw in any missing screws with it");
        // lever = new Item("a handle");
        // lever.setSecondDesciption("You wonder where it's missing from");
        //Goldkey = new Item("a gold key");
        //Silverkey = new Item("a silver key.");
        //Bronzekey = new Item("a bronze key");
        //ladder = new Item("a ladder");
        //ladder.setSecondDesciption("You can probably use it to reach high places");
        sheetMusic = new Item("a sheet with music notes.");
        sheetMusic.setSecondDesciption("The sheet reads the following: \n Notes:" + pianoMusic.getPianoNotes());
        cup1 = new Item("a glass cup.");
        cup1.setSecondDesciption("It can hold water.");
        // cup2 = new Item("a medium glass cup");
        // cup2.setSecondDesciption("It can hold a decent amount of water");
        // cup3 = new Item("a large glass cup");
        // cup3.setSecondDesciption("It can hold a large amount of water");
        
        // adds items to rooms
        //masterRoom.addItem(diary);
        switch(gen.nextInt(3)) {
            case 0 -> bedroom.addItem(backpack);
            case 1 -> cupboard.addItem(backpack);
            case 2 -> closet.addItem(backpack); 
        }
        library.addItem(poem);
        switch(gen.nextInt(2)) {
            case 0 -> cellar.addItem(wine);
            case 1 -> kitchen.addItem(wine);
        }
        // switch(gen.nextInt(2)) {
            // case 0 -> pantry.addItem(food);
            // case 1 -> diningRoom.addItem(food);
        // }
        // switch(gen.nextInt(6)) {
            // case 0 -> bathroom.addItem(screw);
            // case 1 -> masterBathroom.addItem(screw);
            // case 2 -> masterHall.addItem(screw);
            // case 3 -> floorhall.addItem(screw);
            // case 4 -> upperhall.addItem(screw);
            // case 5 -> mainHall.addItem(screw);
        // }
        // switch(gen.nextInt(6)) {
            // case 0 -> greenhouse.addItem(tool);
            // case 1 -> tub.addItem(tool);
            // case 2 -> masterHall.addItem(tool);
            // case 3 -> floorhall.addItem(tool);
            // case 4 -> upperhall.addItem(tool);
            // case 5 -> mainHall.addItem(tool);
        // }
        // switch(gen.nextInt(3)) {
            // case 0 -> tub.addItem(lever);
            // case 1 -> greenhouse.addItem(lever);
            // case 2 -> foyer.addItem(lever); 
        // }
        //mechanical.addItem(Bronzekey);
        switch(gen.nextInt(3)) {
            case 0 -> library.addItem(sheetMusic);
            case 1 -> bedroom.addItem(sheetMusic);
            case 2 -> masterRoom.addItem(sheetMusic); 
        }
        // switch(gen.nextInt(2)) {
            // case 0 -> foyer.addItem(ladder);
            // case 1 -> livingRoom.addItem(ladder);
        // }
        switch(gen.nextInt(2)) {
            case 0 -> diningRoom.addItem(cup1);
            case 1 -> kitchen.addItem(cup1);
        }
        cup1.setUsableRoom("bathroom");
        // switch(gen.nextInt(1)) {
            // case 0 -> diningRoom.addItem(cup2);
            // case 1 -> kitchen.addItem(cup2);
        // }
        // switch(gen.nextInt(1)) {
            // case 0 -> diningRoom.addItem(cup3);
            // case 1 -> kitchen.addItem(cup3);
        // }
        

        // assign images
        foyer.setImage("foyer.jpeg");
        mechanical.setImage("mech.jpeg");
        dungeon.setImage("dung.jpeg"); 
        cellar.setImage("cellar.jpeg");
        kitchen.setImage("kit.png");
        greenhouse.setImage("green.jpeg"); 
        pantry.setImage("pantry.jpeg"); 
        diningRoom.setImage("dindin.jpeg");
        floorhall.setImage("floorhall.jpeg");
        livingRoom.setImage("liv.jpeg");
        library.setImage("lib.jpeg");
        upperhall.setImage("upper.jpg");
        masterRoom.setImage("master.jpg"); 
        masterHall.setImage("last.jpg");
        closet.setImage("closet.jpg");
        masterBathroom.setImage("mold.jpg");
        cupboard.setImage("cup.jpg");
        bedroom.setImage("child.jpg");
        tub.setImage("tub.png");
        mainHall.setImage("main.jpg");
        bathroom.setImage("mirror.jpg"); 
        frontDoor.setImage("front.png");
        
        // outside.setImage("deakinsign.jpg");
        // theater.setImage("lecture-hall.jpg");
        // pub.setImage("cozy-little-pub.jpg");
        // lab.setImage("computer-lab.jpg");
        // office.setImage("cluttered-office.jpg");
        
        // assign sounds
        // office.setAudio("cricket.mp3");
        // pub.setAudio("bar-chatter.mp3");
        
        // initialise room exits
        // foyer.setExits(null, null, ballroom, null);
        // ballroom.setExits(foyer, library, exit, diningRoom);
        // diningRoom.setExits(null, ballroom, null, null);
        // library.setExits(null, null, null, ballroom);
        // exit.setExits(ballroom, null, null, null);
        //Basement
        foyer.setExit("up", floorhall);
        foyer.setExit("south", cellar);
        foyer.setExit("north", mechanical);
        foyer.setExit("west", dungeon);
        mechanical.setExit("south", foyer);
        dungeon.setExit("east", foyer);
        cellar.setExit("north", foyer);
        //Ground Floor
        kitchen.setExit("east", pantry);
        kitchen.setExit("north", greenhouse);
        greenhouse.setExit("north", livingRoom);
        greenhouse.setExit("south", kitchen);
        pantry.setExit("west", kitchen);
        pantry.setExit("east", diningRoom);
        diningRoom.setExit("west", pantry);
        diningRoom.setExit("north", floorhall);
        floorhall.setExit("up", upperhall);
        floorhall.setExit("down", foyer);
        floorhall.setExit("south", diningRoom);
        floorhall.setExit("north", livingRoom);
        livingRoom.setExit("east", library);
        library.setExit("west", livingRoom);
        livingRoom.setExit("southwest", greenhouse);
        livingRoom.setExit("southeast", floorhall);
        livingRoom.setExit("north", frontDoor);
        frontDoor.setExit("south", livingRoom);

        //Upstairs
        upperhall.setExit("down", floorhall);
        upperhall.setExit("south", masterRoom);
        masterRoom.setExit("west", masterHall);
        masterRoom.setExit("north", upperhall);
        masterHall.setExit("west", masterBathroom);
        masterHall.setExit("east", masterRoom);
        masterHall.setExit("south", closet);
        closet.setExit("north", masterHall);
        masterBathroom.setExit("east", masterHall);
        upperhall.setExit("north", mainHall);
        mainHall.setExit("south", upperhall);
        mainHall.setExit("northwest", bedroom);
        mainHall.setExit("northeast", bathroom);
        bedroom.setExit("west", cupboard);
        bedroom.setExit("south", mainHall);
        cupboard.setExit("east", bedroom);
        bathroom.setExit("south", mainHall);
        bathroom.setExit("east", tub);
        tub.setExit("west", bathroom);
        
        player.setRoom(dungeon); // start game outside
    }
    

    /**
     * Print out the opening message for the player.
     */
        private void printWelcome()
    {
        println();
        println("You are lost. You are alone. You wander.");
        println("Your goal is to escape.");
        println("Type 'help' if you need help.");
        println();
        println(player.getRoom().getLongDescription());
        // println("You are " + currentRoom.getDescription());
        // print("Exits: ");
        // if(currentRoom.northExit != null) {
            // print("north ");
        // }
        // if(currentRoom.eastExit != null) {
            // print("east ");
        // }
        // if(currentRoom.southExit != null) {
            // print("south ");
        // }
        // if(currentRoom.westExit != null) {
            // print("west ");
        // }
        // println();
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private void processCommand(Command command) 
    {
        if(command.isUnknown()) {
            println("I don't know what you mean...");
            return;
        }

        String commandWord = command.getCommandWord();
        switch(commandWord){
            case "help" -> printHelp();
            case "go" -> goRoom(command);
            case "quit" -> quit(command);
            case "back" -> back();
            case "look" -> look();
            case "take" -> take(command);
            case "drop" -> drop(command);
            case "inventory" -> println(player.viewInventory());
            case "inspect" -> inspect(command);
            case "use" -> use(command);
            case "piano" -> piano(command);
            case "keypad" -> keypad(command);
            case "eat" -> eat(command);
            default -> println("Command not found");
        }
        // if (commandWord.equals("help")) {
            // printHelp();
        // }
        // else if (commandWord.equals("go")) {
            // goRoom(command);
        // }
        // else if (commandWord.equals("quit")) {
            // quit(command);
        // }
        // else if (commandWord.equals("look")) {
            // look();
        // }
        // else if (commandWord.equals("eat")) {
            // eat();
        // }
            // else if (commandWord.equals("back")) {
            // back();
        // }
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        println("Your command words are:");
        println(parser.showCommands());
        println();
    }

    /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            println("Go where?");
            return;
        }
         

        String direction = command.getSecondWord();
        //8.6
        Room nextRoom = player.getRoom().getExit(direction);
        if(nextRoom != null) {
            previousRoom.push(player.getRoom());
            player.setRoom(nextRoom);
            println(player.getRoom().getLongDescription());
            actions++;
        } else {
            println("There is no door!");
        }
        

        // Try to leave current room.
        // Room nextRoom = null;
        // if(direction.equals("north")) {
            // nextRoom = currentRoom.northExit;
        // }
        // if(direction.equals("east")) {
            // nextRoom = currentRoom.eastExit;
        // }
        // if(direction.equals("south")) {
            // nextRoom = currentRoom.southExit;
        // }
        // if(direction.equals("west")) {
            // nextRoom = currentRoom.westExit;
        // }

        // if (nextRoom == null) {
            // println("There is no door!");
        // }
        // else {
            // currentRoom = nextRoom;
            // // println("You are " + currentRoom.getDescription());
            // // print("Exits: ");
            // // if(currentRoom.northExit != null) {
                // // print("north ");
            // // }
            // // if(currentRoom.eastExit != null) {
                // // print("east ");
            // // }
            // // if(currentRoom.southExit != null) {
                // // print("south ");
            // // }
            // // if(currentRoom.westExit != null) {
                // // print("west ");
            // // }
            // // println();
        // }
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private void quit(Command command) 
    {
        if(command.hasSecondWord()) {
            println("Quit what?");
            return;
        } else {
            wantToQuit = true;// signal that we want to quit
            if(this.wonTheGame() == false){
            player.getRoom().setAudio("super-mario-death-sound-sound-effect.mp3");
            println("It took you " + actions + " actions to meet your fate");
            println("In a state of shock you find a corner to lie in");
            println("As the light around you begins to fade,");
            println("The final words you hear are..");
        } else{
            println("It might have taken a while but you finally managed to escape the mansion.");
            println("It took you " + actions + " actions to escape.");
            player.getRoom().setAudio("06-caught-a-pokemon.mp3");
        }
        }
    }
    
    private void look(){
        println("You look around the room and find " + player.getRoom().getItemString());
        actions++;
    }
    
    private void back(){
        if(previousRoom.empty() == false) {
        player.setRoom(previousRoom.peek());
        println(player.getRoom().getLongDescription());
        previousRoom.pop();
        actions++;
     } else {
         println("You can't go back anymore");
     }
    }
    
    private void take(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know what to take...
            println("Take what?");
            return;
        }
        
        String partial = command.getSecondWord();
        Item toTake = player.getRoom().getItem(partial);
        if(toTake != null) {
        if((player.getWeight() + toTake.getWeight()) <= player.getWeightLimit()) {
            player.addItem(toTake);
            player.getRoom().removeItem(toTake);
            toTake.setHeld(true);
            //player.setWeightLimit(toTake.getWeight());
            println("You picked up the " + toTake.getDescription());
            if(toTake.getDescription().contains("backpack")){
                player.setWeightLimit(4);
            }
            actions++;
            } else {
            println("You can't carry any more items.");
            }
        } else {
            println("You can't pick up an item that doesn't exist silly.");
        }
    }
        
        private void drop(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know what to drop...
            println("Drop what?");
            return;
        }
        String partial = command.getSecondWord();
        Item toDrop = player.getItem(partial);
        if(toDrop != null) {
            if(toDrop.getDescription().contains("backpack")){
                println("You shouldn't drop this.");
                return;
            }
            player.removeItem(toDrop);
            player.getRoom().addItem(toDrop);
            player.setWeight(player.getWeight());
            toDrop.setHeld(false);
            println("You dropped the " + toDrop.getDescription());
            actions++;
        } else {
            println("You can't drop an item you don't have.");
        }
    }
    
    private void inspect(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know what to inspect...
            println("Inspect what?");
            return;
        }
        
        String partial = command.getSecondWord();
        Item toInspect = player.getItem(partial);
        if((toInspect != null) && (toInspect.getHeld() == true)) {
            println("You inspect the object");
            if(toInspect.getsecondDescription() != null) {
                println(toInspect.getsecondDescription());
            }else {
                println("You can't gain anything by looking at it closer.");
            }
            actions++;
        } else { println("You can't inspect an item you don't have.");
        }
    }
    
    private void eat(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know what to inspect...
            println("Eat what?");
            return;
        }
        
        String partial = command.getSecondWord();
        Item toEat = player.getItem(partial);
        if((toEat != null) && (toEat.getEdible() == true)) {
            println("You eat the object");
            player.addHealth();
            println("You feel rejuventated");
            player.removeItem(toEat);
            actions++;
        } else { println("You can't eat that.");
        }
    }
    
    
    private void use(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know what to inspect...
            println("Use what?");
            return;
        }
        
        String partial = command.getSecondWord();
        Item toUse = player.getItem(partial);
        if((toUse != null) && (toUse.getHeld() == true) && (player.getRoom().getDescription().contains(toUse.getUsableRoom()))) {
            println("You use the object");
            if(toUse.getDescription().contains("cup")){
                println("You fill the cup with water.");
                player.removeItem(toUse);
                Item water = new Item("a vessel filled with murky water.");
                water.setUsableRoom("greenhouse");
                water.setSecondDesciption("Maybe you can pour this water over something.");
                water.setHeld(true);
                player.addItem(water);
                actions++;
                return;
            }
            if(toUse.getDescription().contains("water")){
                println("You pour the water onto the patch.");
                player.removeItem(toUse);
                Item cup = new Item("a glass cup.");
                cup.setUsableRoom("bathroom");
                cup.setSecondDesciption("It can hold water");
                cup.setHeld(true);
                player.addItem(cup);
                player.getRoom().setDescription("in the greenhouse. In the patch of dirt lies withered petals, you can't tell the color"); 
                println("Three flowers bloom:");
                for(String s: Flowers){
                    println(s);
                }
                actions++;
                return;
            }
            if(toUse.getDescription().contains("silver")){
                println("You get rid of the lock on the door");
                frontdoorLock = false;
                player.getRoom().setDescription("at the front door. It still needs a a 3 digit code to escape.");
                actions++;
                if(passcode == false){
                    this.processInput("quit");
                }
            }
        } else {
            println("You can't use that object here.");
        }
    }
    
    private void piano(Command command){
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know what to inspect...
            println("With what notes?");
            return;
        }
        String word = command.getSecondWord();
        String[] noteList = word.split(",");

        // String notes = "";
        // int counter = 0;
        // for(String s: noteList){
            // notes = notes + "\"" + s + "\"" + "," + " ";
        // }
        // for(String s: noteList){
            // if(counter > 0){
                // notes += ", ";
            // }
            // notes += "\"" + s.trim() +"\"";
            // counter++;
            // //notes = notes  + s  + "," + " ";
        // }
        for(int i = 0; i< noteList.length; i++){
            noteList[i] = noteList[i].trim();
        }
        // return notes;
        //String l = notes.substring(1, notes.length() - 1);
        if(player.getRoom().getDescription().contains("piano")) {
            println("You play the piano.");
            pianoMusic.createAndPlay(noteList);
            if(pianoMusic.Same() == true){
                Item Silverkey= new Item("a silver key.");
                player.getRoom().addItem(Silverkey);
                Silverkey.setUsableRoom("front door");
                println("The piano opens and a silver key pops out");
            }
        } else {
           println("You can't play the piano in this room.");
        }
    }
    
    private void keypad(Command command){
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know what to inspect...
            println("With what numbers?");
            return;
        }
        String numbers = command.getSecondWord();
        //String[] numList = numbers.split(",");

        // for(int i = 0; i< numList.length; i++){
            // numList[i] = numList[i].trim();
        // }
        
        if(player.getRoom().getDescription().contains("front door")) {
            println("You type in the following code");
            //String one = "";
            String two = "";
            // for(String guess: numList) {
                // one += guess;
            // }
             for(Integer actual: PoemNums) {
                two += actual;
            }
            if(numbers.equals(two)){
                println("It's a success");
                passcode = false;
                actions++;
                player.getRoom().setDescription("at the front door. You still need a key to escape.");
                if(this.wonTheGame() == true) {
                this.processInput("quit");
            }
        } else {
                if((player.getHealth() - 1) > 0) {
                println("You are shocked by the door, you don't know how many more times you can take such a shock");
                player.minusHealth();
                actions++;
            } else {
                println("You face your final shock");
                actions++;
                this.processInput("quit");
            }}
        } else {
           println("There's no keypad in this room.");
        }
    }
           
    
   
    
    
    /****************************************************************
     * If you want to launch an Applet
     ****************************************************************/
    
    /**
     * @return an Image from the current room
     * @see Image
     */
    public String getImage()
    {
        return player.getRoom().getImage();
    }
    
    /**
     * @return an audio clip from the current room
     * @see AudioClip
     */
    public String getAudio()
    {
        return player.getRoom().getAudio();
    }
    
    /****************************************************************
     * Variables & Methods added 2018-04-16 by William H. Hooper
     ****************************************************************/
    
    private String messages;
    private boolean wantToQuit;
    
    /**
     * Initialize the new variables and begin the game.
     */
    private void start()
    {
        messages = "";
        wantToQuit = false;
        printWelcome();
    }
    
    /**
     * process commands or queries to the game
     * @param input user-supplied input
     */
    public void processInput(String input)
    {
        if(finished() == true) {
            println("This game is over. Please go away.");
            return;
        }
        
        Command command = parser.getCommand(input);
        processCommand(command);
    }
    
    /**
     * clear and return the output messages
     * @return current contents of the messages.
     */
    public String readMessages()
    {
        if(messages == null) {
            start();
        }
        String oldMessages = messages;
        messages = "";
        return oldMessages;
    }
    
    /**
     * @return true when the game is over.
     */
    public boolean finished()
    {
        return wantToQuit;
    }

    /**
     * add a message to the output list.
     * @param message the string to be displayed
     */
    private void print(String message)
    {
        messages += message;
    }
    
    /**
     * add a message to the output list, 
     * followed by newline.
     * @param message the string to be displayed
     * @see readMessages
     */
    private void println(String message)
    {
        print(message + "\n");
    }
    
    /**
     * add a blank line to the output list.
     */
    private void println()
    {
        println("");
    }
    
    /**
     * Retruns true if the game is won and false other wise.
     */
    public boolean wonTheGame(){
        if((passcode == false) && (frontdoorLock == false )){
            return true;
        } else { 
            return false;
        }
    }
}
