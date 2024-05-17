package com.geoffdraper.ohare3;

import android.content.DialogInterface;

public class SecretSauce {

    private Room startingLocation;
    private Room treasureRoom;
    private Room epilogue1;

    private boolean mailboxOpen = false;
    private boolean trapdoor1Open = false;
    private boolean trapdoor2Open = false;
    private boolean rugMoved = false;
    private boolean coffinOpen = false;
    private boolean coffinMoved = false;
    private boolean trunkOpen = false;
    private boolean frontDoorOpen = false;
    private boolean vampireDead = false;
    private boolean goblinDead = false;
    private boolean trollDead = false;
    private boolean deskOpen = false;
    private boolean blueBookTransformed = false;
    private boolean wineSmashed = false;

    public SecretSauce(/*Player testing*/) {
        ////////////////////////////////
        // Here, we define all the rooms
        ////////////////////////////////
        var path1 = Room.lit("You're on an old worn path.");
        var path2 = Room.lit("You're walking along an old path.");
        var house = Room.lit("You're in front of a big spooky house. There's a mailbox here with the name \"M. Untel\" written on the side.");
        var forest1 = Room.lit("You're in a creepy dark forest with gnarled trees all around.");
        var forest2 = Room.lit("You're in a dark gnarled forest with creepy trees all around.");
        var forest3 = Room.lit("You're in a gnarled creepy forest. All around you are dark trees.");
        var porch = Room.lit("You're on the porch of the old house. There is a large sign here which reads: LEAVE *TREASURE* HERE. (Tap the \"Check Score\" option in your device's toolbar to see your score.)");
        porch.setAdditionalDescription("The front door is locked with a heavy-looking padlock.");
        var livingroom = Room.lit("You're in an old dusty living room. Cobwebs cover the furniture.");
        var dining = Room.lit("You're in the dining room.");
        var kitchen = Room.lit("You're in the kitchen.");
        var pantry = Room.lit("You're in a musty old pantry.");
        var basement = new Room("You're in the basement. There is a large open door to the south; a smaller passage leads west.");
        var laundry = new Room("You're in a laundry room.");
        var damp = new Room("You're in a chilly, damp room.");
        damp.setAdditionalDescription("A closed wooden coffin sits in the center of the room.");
        var mustyold = new Room("You're in a musty, old room in the basement.");
        var cellar = new Room("You're in an old wine cellar.");
        var familyroom = Room.lit("You're in the family room.");
        var alcove = Room.lit("You're in a small alcove. A winding staircase leads up.");
        var middlecorridor = Room.lit("You're in the middle of a corridor.");
        var eastcorridor = Room.lit("You're at the east end of a corridor.");
        var guestroom = Room.lit("You're in the Guest Room. There is a walk-in closet on the west wall.");
        var closet = Room.lit("You're in a dusty closet. Old clothes, most of little value, rest on hangers.");
        var study = Room.lit("You're in the study. An oak desk sits, unused, in a corner.");
        var library = Room.lit("You're in the library.");
        var westcorridor = Room.lit("You're at the west end of a corridor.");
        var den = Room.lit("You're in the Den.");
        var stairwell = Room.lit("You're standing in an elegant spiral stairwell.");
        var nursery = Room.lit("You're in an old dusty Nursery.");
        var easthall = Room.lit("You're at the east end of a hallway. An open door to the north leads to a bathroom; a bedroom is to your south.");
        var bathroom = Room.lit("You're in a large bathroom. There is a toilet and a sink here.");
        var whitebed = Room.lit("You're in a white bedroom.");
        var yellowbed = Room.lit("You're in a yellow bedroom.");
        var westhall = Room.lit("You're at the west end of a hallway.");
        var masterbed = Room.lit("You're in the master bedroom. A dark closet is on the west wall.");
        var closet2 = Room.lit("You're in a dark, dusty closet. A ladder leads up here.");
        var ladder = Room.lit("You're halfway up (or down?) the ladder.");
        ladder.setAdditionalDescription("There is a locked trapdoor directly above your head, in the ceiling.");
        var westattic = new Room("You're in the west end of the attic. It looks like nobody's been up here for years; cobwebs abound.");
        var eastattic = new Room("You're in the east end of the attic.");
        var storeroom = new Room("You're in a dirty old storeroom. You recoil as you see a body hanging from a noose. Perhaps it is Monsieur Untel?");
        var trophy = Room.lit("You're in the trophy room.");
        var cavern = new Room("You're in a secret underground cavern.");
        var narrow = new Room("You're in a narrow passage.");
        var drafty = new Room("You're in a drafty chamber.");
        drafty.setAdditionalDescription("There is a ferocious-looking troll here. Behind the troll you can see a passageway leading east.");
        var circular = new Room("You're in a large circular chamber.");
        var rough = new Room("You're in a roughly carved room. A deep pit leads down.");
        var cold = new Room("You're in a cold damp room. To your amazement, you see a giant oyster sitting in the middle of the room, its shell clamped tightly shut.");
        var pit1 = new Room("You're in a pit.");
        var pit2 = new Room("You're even deeper in the pit.");
        var pit3 = new Room("You're all the way in the pit. There are no exits.");
        var splendid = new Room("You're in a splendid chamber 10 meters high.");
        var junction = new Room("You're at a junction of three passages.");
        var dustypassage = new Room("You're in a dusty passage.");
        var broken = new Room("You're in an old broken passage.");
        var torture = new Room("You're in what appears to be a medieval torture chamber.");
        var turn = new Room("You're at a turn in the passage.");
        var deadend = new Room("You're in a dead-end passage.");

        var intro1 = Room.lit("""
                You've been looking forward to this camping trip for weeks. Just you, your parents, and Grandpa John in the woods. After a tinfoil dinner, you and your mom and dad sit around the fading campfire. What could make this night even better? Dessert!
                                
                At that moment, Grandpa John walks up with a bag of marshmallows. "OK, who wants some s'mores?" he calls.
                                
                "Oh, I do!" you say.
                                
                "Thanks Dad, I'll take some too!" your father tells Grandpa, who hands marshmallows all around. Soon everyone is warming marshmallows around the fire pit. Once it's perfectly toasted, you carefully remove your marshmallow from the skewer.
                                
                "Peanut butter?" Grandpa asks you, holding out a jar out to you.
                                
                "Peanut butter on s'mores? That's just weird," your dad laughs.
                                
                "No, it's adventurous," Grandpa smiles. "How about it, Jane?"           
                """);

        var eatPeanutButter = """
                You put a dab of peanut butter on your cracker before closing the sandwich. Your grandfather looks at your expectantly as you take your first bite.
                                
                "Omigosh, this is amazing!" you say enthusiastically. Grandpa looks over at your dad triumphantly.
                                
                "To each their own," your dad says.
                
                """;
        var noPeanutButter = """
                Simpler is better, you always say. You put your toasted marshmallow between two graham crackers with a square of chocolate. You take a bite; delicious.
                
                """;

        var intro2 = Room.lit("""
                Your mother is the next to speak. "I have an idea. Why don't we tell scary stories around the campfire!"
                                
                "That sounds fun," your dad agrees. "Who wants to go first?"
                                
                What do you say?
                """);

        var intro3 = Room.lit("""
                Your mother and father look at Grandpa expectantly.
                                
                With all eyes on him, Grandpa John blushes slightly. "Well, okay. It just so happens that I do have a spooky story.
                                
                "It was a long time ago. There was a full moon out, like tonight. It was the night I explored the haunted mansion. The owner, who had mysteriously died years before, was a Frenchman by the name of Monsieur Untel."
                                
                "That's sounds like a made-up name," you interrupt.
                                
                "Well, what did you expect me to do — steal characters from a Stephen King novel? Can you say 'copyright infringement?'"
                                
                You smile and roll your eyes. "OK whatever, Grandpa."
                                
                He continues. "I had braved forbidding caverns and mysterious pyramids, but this was my most terrifying adventure yet. It all started on lonely path near a dark forest..."
                                
                You sigh. "A forest? Sounds like another word for MAZE. You know I don't like mazes."
                                
                "Neither do I. In fact, in this story, you don't even need to go through the maze at all. Just go west twice on the path, open the mailbox, then go south to the mansion."
                                
                "Got it. Let's do this!"
                """);

        epilogue1 = Room.lit("""
                "Wow, Grandpa, that was a fun story!"
                                
                "Yeah," Mom agrees, "I liked how you turned that troll into a frog."
                                
                "And how that book just transformed into gold; that was crazy," Dad pipes in.
                
                Which part did YOU like best?                                
                """);

        var likedTheCommodore = """
                "The Commodore 64, of course! You always told me it was the best computer ever made."
                
                "Indeed it was, child. Indeed it was," Grandpa murmurs with a faraway look in his eyes.
                
                """;

        var likedTheOyster = """
                "I thought it was cool how you thought to feed the hamburger to the oyster. I mean, where did you get the idea?"
                
                "Intuition, I suppose. Besides, it seemed more fun than reading it to sleep."
                
                """;

        var likedTheVampyre = """
                "I thought it was absolutely wicked how the vampire just turned to dust and blew away after you stabbed it."
                
                "Yes, I think he must have had an allergic reaction to the silver."
                
                """;

        var epilogue2 = Room.lit("""
                "So, when do I get to hear your fourth book?" you ask.
                                
                He shrugs and says, "There is no fourth book; I only wrote three."
                                
                That's not the answer you were expecting. "But you were the world's greatest adventurer! What happened?"
                                
                Grandpa smiles at you for a moment, then looks over at your dad before continuing. "Well, not long after returning home from the Untel mansion, I met your grandmother. And before I knew it we got married and started a family."
                                
                "I miss Grandma," you say.
                                
                "So do I," Grandpa says. After a brief pause, he continues. "Sure, it was fun to go traipsing on treasure-hunting escapades in my youth. But... I found that it was too easy."
                                
                "Too easy?" you say in amazement. "Grandpa, you defeated a giant snake, a mummy, and a vampire! How can you call that easy?"
                                
                Grandpa waves his hand dismissively. "Easy is relative. Parenthood is the most challenging thing I've ever done. But also the most rewarding. Luckily I didn't have to do it alone. Your grandmother was a gem."
                                
                You look over at your dad, who nods in agreement.
                                
                "Those trinkets I collected from my adventures, they didn't last. But my family: my kids and " -- he nods in your direction -- " my grandkids... you are the greatest treasure of all because you're mine forever."
                                
                "But Grandpa, couldn't you have done both? Have adventures, and raise a family too?"
                                
                "Jane, life with your grandmother was the grandest adventure of all. I wouldn't trade my time with her for all the treasure-hunting vacation trips in the world."
                                
                "I guess that makes sense, Grandpa. But I still wish you could have had a fourth adventure."
                                
                There is silence around the campfire for a few moments. You look up as you hear the sound of Grandpa rummaging through his duffel bag. He pulls out a book and holds it out to you.
                """);
        var epilogue3 = Room.lit("""
                "Perhaps someday there will be another O'Hare's Adventure," he says with a wink. "But it will be yours to tell, not mine."
                                
                You take the book and open it. The pages are blank. You look at Grandpa questioningly.
                                
                "The future is an open book, Jane O'Hare. So explore; dream; discover! Wherever life takes you, make it the best one possible."
                                
                You hold Grandpa's storybook in your hands, your parents looking on with pride.
                                
                "So, what will be, Jane? What will be your O'Hare's Adventure?"
                                
                "I'm not sure, " you say. A smile crosses your face as you close the book, a million possibilities ahead of you. "But I'll think of something."
                """);

        ///////////////////////////////////////////////////////////////
        // Here, we state which locations lead to which other locations
        ///////////////////////////////////////////////////////////////
        path1.addExit(Direction.NORTH, forest1).addExit(Direction.EAST, path1).addExit(Direction.WEST, path2);
        path2.addExit(Direction.NORTH, forest3).addExit(Direction.EAST, path1).addExit(Direction.WEST, house);
        house.addExit(Direction.SOUTH, porch).addExit(Direction.EAST, path2).addExit(Direction.WEST, forest2);
        forest3.addExit(Direction.NORTH, forest2).addExit(Direction.SOUTH, forest2).addExit(Direction.EAST, forest1).addExit(Direction.WEST, forest2);
        forest2.addExit(Direction.NORTH, forest3).addExit(Direction.SOUTH, forest2).addExit(Direction.EAST, forest2).addExit(Direction.WEST, forest3);
        forest1.addExit(Direction.NORTH, forest3).addExit(Direction.SOUTH, forest2).addExit(Direction.EAST, path1).addExit(Direction.WEST, forest3);
        porch.addExit(Direction.NORTH, house);
        livingroom.addExit(Direction.NORTH, porch).addExit(Direction.SOUTH, familyroom).addExit(Direction.EAST, dining);
        dining.addExit(Direction.SOUTH, alcove).addExit(Direction.EAST, kitchen).addExit(Direction.WEST, livingroom);
        kitchen.addExit(Direction.EAST, pantry).addExit(Direction.WEST, dining);
        pantry.addExit(Direction.WEST, kitchen);
        basement.addExit(Direction.SOUTH, damp).addExit(Direction.WEST, laundry).addExit(Direction.UP, kitchen);
        laundry.addExit(Direction.EAST, basement).addExit(Direction.WEST, mustyold);
        damp.addExit(Direction.NORTH, basement);
        mustyold.addExit(Direction.EAST, laundry).addExit(Direction.SOUTH, cellar);
        cellar.addExit(Direction.NORTH, mustyold);
        familyroom.addExit(Direction.NORTH, livingroom).addExit(Direction.EAST, alcove);
        alcove.addExit(Direction.NORTH, dining).addExit(Direction.SOUTH, middlecorridor).addExit(Direction.WEST, familyroom).addExit(Direction.UP, stairwell);
        middlecorridor.addExit(Direction.NORTH, alcove).addExit(Direction.SOUTH, library).addExit(Direction.EAST, eastcorridor).addExit(Direction.WEST, westcorridor);
        eastcorridor.addExit(Direction.NORTH, study).addExit(Direction.SOUTH, guestroom).addExit(Direction.WEST, middlecorridor);
        guestroom.addExit(Direction.NORTH, eastcorridor).addExit(Direction.WEST, closet);
        closet.addExit(Direction.EAST, guestroom);
        study.addExit(Direction.SOUTH, eastcorridor);
        library.addExit(Direction.NORTH, middlecorridor);
        westcorridor.addExit(Direction.SOUTH, den).addExit(Direction.EAST, middlecorridor);
        den.addExit(Direction.NORTH, westcorridor);
        stairwell.addExit(Direction.DOWN, alcove).addExit(Direction.NORTH, nursery).addExit(Direction.SOUTH, yellowbed).addExit(Direction.EAST, easthall).addExit(Direction.WEST, westhall);
        nursery.addExit(Direction.SOUTH, stairwell);
        easthall.addExit(Direction.NORTH, bathroom).addExit(Direction.SOUTH, whitebed).addExit(Direction.WEST, stairwell);
        bathroom.addExit(Direction.SOUTH, easthall);
        whitebed.addExit(Direction.NORTH, easthall);
        yellowbed.addExit(Direction.NORTH, stairwell);
        westhall.addExit(Direction.NORTH, trophy).addExit(Direction.SOUTH, masterbed).addExit(Direction.EAST, stairwell);
        masterbed.addExit(Direction.NORTH, westhall).addExit(Direction.WEST, closet2);
        closet2.addExit(Direction.EAST, masterbed).addExit(Direction.UP, ladder);
        ladder.addExit(Direction.DOWN, closet2);
        westattic.addExit(Direction.EAST, eastattic).addExit(Direction.DOWN, ladder);
        eastattic.addExit(Direction.WEST, westattic).addExit(Direction.SOUTH, storeroom);
        storeroom.addExit(Direction.NORTH, eastattic);
        trophy.addExit(Direction.SOUTH, westhall);
        cavern.addExit(Direction.SOUTH, narrow).addExit(Direction.UP, damp);
        narrow.addExit(Direction.NORTH, cavern).addExit(Direction.EAST, drafty);
        drafty.addExit(Direction.WEST, narrow);
        circular.addExit(Direction.WEST, drafty).addExit(Direction.EAST, rough).addExit(Direction.NORTH, splendid);
        rough.addExit(Direction.SOUTH, cold).addExit(Direction.WEST, circular).addExit(Direction.DOWN, pit1);
        cold.addExit(Direction.NORTH, rough);
        pit1.addExit(Direction.DOWN, pit2);
        pit2.addExit(Direction.DOWN, pit3);
        splendid.addExit(Direction.SOUTH, circular);
        junction.addExit(Direction.SOUTH, splendid).addExit(Direction.EAST, dustypassage).addExit(Direction.WEST, deadend);
        dustypassage.addExit(Direction.EAST, broken).addExit(Direction.WEST, junction);
        broken.addExit(Direction.EAST, turn).addExit(Direction.WEST, dustypassage);
        turn.addExit(Direction.WEST, broken).addExit(Direction.SOUTH, torture);
        torture.addExit(Direction.NORTH, turn);
        deadend.addExit(Direction.EAST, junction);


        ////////////////////////////////////////
        // Here, we create all the items in the game that the player can interact with
        ////////////////////////////////////////
        var flashlight = new LightSource("Flashlight");
        var doormat = new Item("Doormat");
        var keys = new Item("Ring of keys");
        var letter = new Item("Old letter");
        var furniture = new Item("Furniture");
        furniture.setNotLuggable("The furniture is too bulky to move.");
        var candlesticks = new Item("*Silver Candlesticks*");
        var knife = new Item("*Silver Knife*");
        var hamburger = new Item("Tasty-looking hamburger");
        var vial = new Item("Vial of strange liquid");
        var coffin = new Item("Old wooden coffin");
        coffin.setNotLuggable("The coffin is too heavy to carry.");
        var vino = new SnarkyItem("*Bottle of rare imported wine*", """
                "Grandpa! Why is there a bottle of wine in this story?" you protest. "Our family doesn't drink!"
                
                Your grandfather shrugs. "It has historical value. It's not for drinking."
                
                "Okay, whatever you say, Grandpa."
                
                "Just be careful not to drop it on the floor, or else it will shatter. Give it something soft to land on."
                """);
        var wallet = new Item("*Wallet full of $100 bills*");
        var largeRug = new Item("Large Rug");
        largeRug.setNotLuggable("The rug is too heavy to carry, but you could move it.");
        var orientalRug = new Item("*Oriental Rug*");
        var mink = new SnarkyItem("*Mink Coat*", """
                "Hold on Grandpa. It's not a REAL fur coat, is it? That's cruel."
                
                "No more cruel than a real green dress."
                
                "Huh?"
                
                "Never mind. Shall we continue the story?"
                """);
        var desk = new Item("Desk");
        desk.setNotLuggable("It is stuck to the floor.");
        var gun = new Item("Magic Gun");
        var redbook = new Item("Red Book");
        var bluebook = new Item("Blue Book");
        var greenbook = new Item("Green Book");
        var watch = new Item("*Gold Watch*");
        var tableau = new Item("*Rare Painting*");
        var crib = new Item("Crib");
        crib.setNotLuggable("It's too awkward to carry around. Just leave it alone.");
        var bond = new Item("*$1000 Savings Bond*");
        var potion = new Item("Bubbling Potion");
        var c64 = new Item("*Commodore 64*");
        var necklace = new Item("*Pearl Necklace*");
        var pillow = new Item("*Velvet Pillow*");
        var jewels = new Item("*Rare family jewels*");
        var body = new Item("Dead body");
        body.setNotLuggable("No way am I touching that!");
        var trunk = new Item("Old trunk");
        trunk.setNotLuggable("It's too heavy to move.");
        var coins = new Item("*Rare Coins*");
        var medal = new Item("*Gold Medal*");
        // = new Item("Mean Troll");
        //troll.setNotLuggable("Engaging in hand-to-hand combat with the troll would not be wise.");
        var oyster = new Item("Giant Oyster");
        oyster.setNotLuggable("The oyster is too big to move. Besides, it might eat you if you tried.");
        var pearl = new Item("*Giant Pearl*");
        var diamonds = new Item("*Sack of Diamonds*");
        //var goblin = new Item("Dangerous Goblin");
        //goblin.setNotLuggable("Best to keep a safe distance from the goblin.");
        var rack = new Item("Torture Rack");
        rack.setNotLuggable("It's too big to carry. And besides, why would you even want it?");
        var mask = new Item("*Gold Death Mask*");
        var ruby = new Item("*Large Ruby*");

        /////////////////////////////////////////////////////
        // Here, we add the items to their starting locations
        /////////////////////////////////////////////////////
        porch.addItem(doormat);
        livingroom.addItem(furniture);
        dining.addItem(candlesticks);
        kitchen.addItem(knife).addItem(hamburger).addItem(largeRug);
        pantry.addItem(vial).addItem(flashlight);
        damp.addItem(coffin);
        cellar.addItem(vino);
        familyroom.addItem(wallet);
        guestroom.addItem(orientalRug);
        closet.addItem(mink);
        library.addItem(redbook).addItem(bluebook).addItem(greenbook);
        nursery.addItem(bond).addItem(crib);
        bathroom.addItem(potion);
        whitebed.addItem(tableau);
        yellowbed.addItem(c64);
        masterbed.addItem(necklace).addItem(pillow);//TODO move one of these
        eastattic.addItem(jewels);
        storeroom.addItem(body).addItem(trunk);
        trophy.addItem(medal);
        //drafty.addItem(troll);
        cold.addItem(oyster);
        pit3.addItem(diamonds);
        //splendid.addItem(goblin);
        torture.addItem(rack).addItem(mask);
        deadend.addItem(ruby);
        den.addItem(watch);

        ////////////////////////////////////////////////
        // Here, we assign possible actions to each item
        ////////////////////////////////////////////////
        flashlight.addAction(new ItemAction() {
            @Override
            public String getName(Player p) {
                if (flashlight.isOn()) {
                    return "Turn off flashlight.";
                } else {
                    return "Turn on flashlight.";
                }
            }

            @Override
            public void thatThingYouDo(Player p) {
                if (flashlight.isOn()) {
                    flashlight.turnOff();
                    MainActivity.success(p.getContext(), "Your flashlight clicks off.");
                } else {
                    flashlight.turnOn();
                    MainActivity.success(p.getContext(), "Your flashlight clicks on.");
                }
                p.callForceRedisplay();
            }
        });

        keys.addAction(new ItemAction() {
            @Override
            public String getName(Player p) {
                if (p.isIn(porch)) {
                    return "Try to unlock the front door.";
                } else if ((p.isIn(kitchen) && rugMoved) || p.isIn(ladder)) {
                    return "Try to unlock the trap door.";
                } else if (p.isIn(damp)) {
                    return "Try to unlock the coffin.";
                } else if (p.isIn(storeroom)) {
                    return "Try to unlock the trunk.";
                } else if (p.isIn(cold)) {
                    return "Try to unlock the oyster.";
                } else {
                    return "Try to unlock something.";
                }
            }

            @Override
            public void thatThingYouDo(Player p) {
                if (p.isIn(porch)) {
                    if (frontDoorOpen) {
                        MainActivity.failure(p.getContext(), "No need, it's already open.");
                    } else {
                        frontDoorOpen = true;
                        porch.addExit(Direction.SOUTH, livingroom);
                        MainActivity.success(p.getContext(), "Using your ring of keys, you unlock the door. It opens with a creak.");
                        porch.setAdditionalDescription("The open front door leads south.");
                        p.callForceRedisplay();
                    }
                } else if (p.isIn(kitchen)) {
                    if (rugMoved && !trapdoor1Open) {
                        trapdoor1Open = true;
                        kitchen.addExit(Direction.DOWN, basement);
                        MainActivity.success(p.getContext(), "Using your ring of keys, you unlock the trap door, and pull it open. You can see a set of wooden stairs leading downward into darkness.");
                        kitchen.setAdditionalDescription("There is an open trap door in the floor, revealing a set of wooden stairs leading downward.");
                        p.callForceRedisplay();
                    } else {
                        MainActivity.failure(p.getContext(), "No need, it's already open.");
                    }
                } else if (p.isIn(ladder)) {
                    if (trapdoor2Open) {
                        MainActivity.failure(p.getContext(), "No need, it's already open.");
                    } else {
                        trapdoor2Open = true;
                        ladder.addExit(Direction.UP, westattic);
                        MainActivity.success(p.getContext(), "Using your ring of keys, you unlock the trap door, and push it open.");
                    }
                } else if (p.isIn(damp)) {
                    if (coffinOpen) {
                        MainActivity.failure(p.getContext(), "No need, it's already open.");
                    } else {
                        MainActivity.failure(p.getContext(), "No need, it's already unlocked. Just try opening it.");
                    }
                } else if (p.isIn(storeroom)) {
                    if (trunkOpen) {
                        MainActivity.failure(p.getContext(), "No need, it's already open.");
                    } else {
                        MainActivity.failure(p.getContext(), "No need, it's already unlocked. Just try opening it.");
                    }
                } else if (p.isIn(cold)) {
                    MainActivity.failure(p.getContext(), "Interesting idea, but that's not really how oysters work.");
                } else {
                    MainActivity.failure(p.getContext(), "There is nothing here to unlock.");
                }

            }
        });

        letter.addAction(new ItemAction() {
            @Override
            public String getName(Player p) {
                return "Read the letter";
            }

            @Override
            public void thatThingYouDo(Player p) {
                MainActivity.success(p.getContext(), "The letter reads:\n\"Bonjour mes amis, My house is haunted by dark creatures. I can no longer live in it. Anyone who can purge this house of these monsters is welcome to my entire collection of treasures. I do not need them anymore.\"\nAdieu, Monsieur Untel");
            }
        });

        knife.addAction(new ItemAction() {
            @Override
            public String getName(Player p) {
                if (p.isIn(damp) && coffinOpen && !vampireDead) {
                    return "Stab the vampire";
                } else if (p.isIn(splendid) && !goblinDead) {
                    return "Stab the goblin";
                } else if (p.isIn(drafty) && !trollDead) {
                    return "Stab the troll";
                } else if (p.isIn(cold)) {
                    return "Pry open the oyster";
                } else {
                    return "Examine the knife";
                }
            }

            @Override
            public void thatThingYouDo(Player p) {
                if (p.isIn(damp) && coffinOpen && !vampireDead) {
                    vampireDead = true;
                    damp.setAdditionalDescription("An open wooden coffin sits in the center of the room.");
                    MainActivity.success(p.getContext(), "Acting quickly, you leap forward and plunge the silver blade into the vampire's heart. To your amazement, the vampire turns to dust and blows away. He won't bother you again.");
                } else if (p.isIn(splendid) && !goblinDead) {
                    MainActivity.failure(p.getContext(), "Showing surprising agility, the goblin dodges your attack. Can you defeat him from a distance?");
                } else if (p.isIn(drafty) && !trollDead) {
                    MainActivity.failure(p.getContext(), "Showing surprising agility, the troll dodges your attack. Can you defeat him without fighting?");
                } else if (p.isIn(cold)) {
                    MainActivity.failure(p.getContext(), "You try to pry open the oyster shell with your silver knife, but it's stuck shut. Maybe try gentle persuasion rather than force.");
                } else {
                    MainActivity.success(p.getContext(), "The workmanship of this knife is very fine. It will be a nice addition to your treasure collection.");
                }
            }
        });

        candlesticks.addAction(new ItemAction() {
            @Override
            public String getName(Player p) {
                if (p.isIn(damp) && coffinOpen && !vampireDead) {
                    return "Stab the vampire";
                } else {
                    return "Examine the candlesticks";
                }
            }

            @Override
            public void thatThingYouDo(Player p) {
                if (p.isIn(damp) && coffinOpen && !vampireDead) {
                    MainActivity.failure(p.getContext(), "True, the candlesticks are made out of silver, but no way are they sharp enough to hurt the vampire. Try something else.");
                } else {
                    MainActivity.success(p.getContext(), "The candlesticks have bowls made after the fashion of almonds, a knop and a flower.");
                }
            }
        });

        hamburger.addAction(new ItemAction() {
            @Override
            public String getName(Player p) {
                if (p.isIn(cold)) {
                    return "Feed the hamburger to the oyster";
                } else if (p.isIn(splendid) && !goblinDead) {
                    return "Feed the hamburger to the goblin";
                } else if (p.isIn(drafty) && !trollDead) {
                    return "Feed the hamburger to the troll";
                } else if (p.isIn(damp) && coffinOpen && !vampireDead) {
                    return "Feed the hamburger to the vampire";
                } else {
                    return "Eat the hamburger";
                }
            }

            @Override
            public void thatThingYouDo(Player p) {
                if (p.isIn(cold)) {
                    MainActivity.success(p.getContext(), "The oyster opens up to eat the hamburger and a pearl rolls out!");
                    cold.addItem(pearl);
                    p.consume(hamburger);
                    p.callForceRedisplay();
                } else if (p.isIn(splendid) && !goblinDead) {
                    MainActivity.failure(p.getContext(), "The goblin ignores your offer, and instead shakes his axe threateningly at you.");
                } else if (p.isIn(drafty) && !trollDead) {
                    MainActivity.failure(p.getContext(), "The troll wrinkles his nose at your offer, and says: \"Thirsty, not hungry!\"");
                } else if (p.isIn(damp) && coffinOpen && !vampireDead) {
                    MainActivity.failure(p.getContext(), "The vampire only hisses at your offer. \"I will not be bribed!\"");
                } else {
                    MainActivity.success(p.getContext(), "You take a small nibble from the hamburger, then decide against eating it. It might come in handy later on.");
                }
            }
        });

        vial.addAction(new ItemAction() {
            @Override
            public String getName(Player p) {
                if (p.isIn(splendid) && !goblinDead) {
                    return "Give the vial to the goblin";
                } else if (p.isIn(drafty) && !trollDead) {
                    return "Give the vial to the troll";
                } else if (p.isIn(damp) && coffinOpen && !vampireDead) {
                    return "Give the vial to the vampire";
                } else {
                    return "Read the label on the vial";
                }
            }

            @Override
            public void thatThingYouDo(Player p) {
                if (p.isIn(splendid) && !goblinDead) {
                    MainActivity.failure(p.getContext(), "The goblin ignores your offer, and instead shakes his axe threateningly at you.");
                } else if (p.isIn(drafty) && !trollDead) {
                    MainActivity.failure(p.getContext(), "The troll shakes his head sadly and says: \"I'm trying to cut back.\"");
                } else if (p.isIn(damp) && coffinOpen && !vampireDead) {
                    MainActivity.failure(p.getContext(), "The vampire sniffs the vial and mutters: \"Not blood. Not interested.\"");
                } else {
                    MainActivity.success(p.getContext(), "Written in fine print on the vial's label, you can read the words: DRINK ONLY WHEN TRAPPED WITH NO WAY OUT.");
                }
            }
        });
        vial.addAction(new ItemAction() {
            @Override
            public String getName(Player p) {
                return "Drink the liquid in the vial";
            }

            @Override
            public void thatThingYouDo(Player p) {
                if (p.isIn(pit3)) {
                    p.removeFromInventory(vial);
                    MainActivity.success(p.getContext(), "Feeling trapped, you uncork the vial of strange liquid and take a sip. Immediately, you feel yourself rising up through the air. A moment later, you find yourself standing at the edge of the pit.");
                    p.go(rough);
                } else {
                    MainActivity.failure(p.getContext(), "Before you drink, you check the label on the bottle to see if it's poison. You find the words DRINK ONLY WHEN TRAPPED WITH NO WAY OUT. Your present situation, though difficult, does not (yet) qualify.");
                }
            }
        });

        vino.setCustomDrop(new ItemAction() {
            @Override
            public String getName(Player p) {
                return "Drop the " + vino;
            }

            @Override
            public void thatThingYouDo(Player p) {
                p.removeFromInventory(vino);
                vino.unSnarkify();
                if (!wineSmashed) {
                    if (p.getCurrentLocation().contains(pillow)) {
                        MainActivity.success(p.getContext(), "The bottle lands softly on the " + pillow + ".");
                    } else {
                        wineSmashed = true;
                        vino.changeDescription("*Sweet-smelling broken glass*");
                        MainActivity.alert(p.getContext(), "Oh no!", "The bottle crashes to the floor and shatters!", (a, b) ->
                                MainActivity.alert(p.getContext(), "Hey, wait a minute!", "\"That's not fair, Grandpa!\" you protest.\nHe chuckles. \"You're right; that was kind of cruel. I'll tell you what: I won't count it against your score.\"\nYou consider this. \"I guess it's okay. I wouldn't know what to do with a bottle of wine anyway,\"", null)
                        );
                    }
                }
            }
        });

        gun.addAction(new ItemAction() {
            @Override
            public String getName(Player p) {
                if  (p.isIn(damp) && coffinOpen && !vampireDead) {
                    return "Shoot the vampire";
                } else if (p.isIn(splendid) && !goblinDead) {
                    return "Shoot the goblin";
                } else if (p.isIn(drafty) && !trollDead) {
                    return "Shoot the troll";
                } else {
                    return "Fire the gun";
                }
            }

            @Override
            public void thatThingYouDo(Player p) {
                if  (p.isIn(damp) && coffinOpen && !vampireDead) {
                    MainActivity.failure(p.getContext(), "You fire the magic gun at the vampire. \"A pathetic attempt,\" he sneers.");
                } else if (p.isIn(splendid) && !goblinDead) {
                    goblinDead = true;
                    splendid.addExit(Direction.NORTH, junction);
                    //p.getCurrentLocation().removeFromVisibleItems(goblin);
                    MainActivity.success(p.getContext(), "You got him! The goblin disappears in a cloud of black smoke.");
                    p.callForceRedisplay();
                } else if (p.isIn(drafty) && !trollDead) {
                    MainActivity.failure(p.getContext(), "You fire the magic gun at the troll, but he only laughs at you. Try something else!");
                } else {
                    MainActivity.failure(p.getContext(), "You squeeze the trigger, but nothing happens. Maybe this magic gun only works in the presence of an enemy?");
                }
            }
        });

        bluebook.addAction(new ItemAction() {
            @Override
            public String getName(Player p) {
                if (!blueBookTransformed) {
                    return "Read the Blue Book";
                } else {
                    return "Read the *Gold Book*";
                }
            }

            @Override
            public void thatThingYouDo(Player p) {
                if (!blueBookTransformed) {
                    blueBookTransformed = true;
                    MainActivity.success(p.getContext(), "As you open the blue book to read, it starts to feel very heavy in your hands. The book has magically transformed into *solid gold*!");
                    bluebook.changeDescription("*Gold Book*");
                } else {
                    MainActivity.failure(p.getContext(), "Now that the book has turned to gold, it's too stiff to open up and read.");
                }
            }
        });

        redbook.addAction(new ItemAction() {
            @Override
            public String getName(Player p) {
                return "Read the Red Book";
            }

            @Override
            public void thatThingYouDo(Player p) {
                MainActivity.success(p.getContext(), "There is only one page that has legible writing. It says:\n1. Some treasures may be weapons.\n2. Shoot goblin.\n3. Troll potion.");
            }
        });

        greenbook.addAction(new ItemAction() {
            @Override
            public String getName(Player p) {
                return "Read the Green Book";
            }

            @Override
            public void thatThingYouDo(Player p) {
                MainActivity.success(p.getContext(), "Most of the book is undecipherable, but in the margins someone has scrawled the words:\n1. Kill a vampire with *silver*.\n2. When trapped, take a drink.");
            }
        });

        potion.addAction(new ItemAction() {
             @Override
             public String getName(Player p) {
                 if (p.isIn(splendid) && !goblinDead) {
                     return "Give the potion to the goblin";
                 } else if (p.isIn(drafty) && !trollDead) {
                     return "Give the potion to the troll";
                 } else if (p.isIn(damp) && coffinOpen && !vampireDead) {
                     return "Give the potion to the vampire";
                 } else {
                     return "Read the label on the potion bottle";
                 }
             }

             @Override
             public void thatThingYouDo(Player p) {
                 if (p.isIn(splendid) && !goblinDead) {
                     MainActivity.failure(p.getContext(), "The goblin ignores your offer, and instead shakes his axe threateningly at you.");
                 } else if (p.isIn(drafty) && !trollDead) {
                     MainActivity.success(p.getContext(), "Ever curious, the troll takes the potion and without pausing to think, uncorks it and chugs it all down. A moment later, his eyes bulge out his skin turns a particular shade of green. As you stare in amazement, the troll transforms into a tiny frog! The frog hops away out of sight.");
                     trollDead = true;
                     drafty.addExit(Direction.EAST, circular);
                     drafty.setAdditionalDescription("You can see a passageway leading east.");
                     p.consume(potion);
                     p.callForceRedisplay();
                 } else if (p.isIn(damp) && coffinOpen && !vampireDead) {
                     MainActivity.failure(p.getContext(), "The vampire sniffs the potion and mutters: \"Not blood. Not interested.\"");
                 } else {
                     MainActivity.success(p.getContext(), "Printed in a large font on the potion bottle are the words: \"TROLL-B-GONE\"");
                 }

             }
        });

        storeroom.addAction(new ItemAction() {
            @Override
            public String getName(Player p) {
                return "Open the old trunk";
            }

            @Override
            public void thatThingYouDo(Player p) {
                if (trunkOpen) {
                    MainActivity.failure(p.getContext(), "No need; the trunk is already open.");
                } else {
                    trunkOpen = true;
                    storeroom.addItem(coins);
                    MainActivity.success(p.getContext(), "The trunk opens with a loud creak! Inside you find some *Rare Coins*.");
                    p.callForceRedisplay();
                }
            }
        });

        //TODO maybe add a "read wine bottle" action?


        ///////////////////////////////////////////////////////////////////
        // Here, we add special actions that are specific to certain rooms
        ///////////////////////////////////////////////////////////////////
        house.addAction(new ItemAction() {
            @Override
            public String getName(Player p) {
                return "Open the mailbox";
            }

            @Override
            public void thatThingYouDo(Player p) {
                if (mailboxOpen) {
                    MainActivity.failure(p.getContext(), "No need - it's already open.");
                } else {
                    mailboxOpen = true;
                    MainActivity.success(p.getContext(), "As you open the mailbox, you notice a couple of things fall out.");
                    house.addItem(keys).addItem(letter);
                    p.callForceRedisplay();
                }
            }
        });

        porch.addAction(new ItemAction() {
            @Override
            public String getName(Player p) {
                return "Ring the doorbell";
            }

            @Override
            public void thatThingYouDo(Player p) {
                MainActivity.success(p.getContext(), "As you push the button, you hear a weird laughing sound from within the house. Creepy!");
            }
        });

        kitchen.addAction(new ItemAction() {
            @Override
            public String getName(Player p) {
                return "Move the rug";
            }

            @Override
            public void thatThingYouDo(Player p) {
                if (rugMoved) {
                    MainActivity.failure(p.getContext(), "You move the rug around a bit, but do not discover anything new.");
                } else {
                    rugMoved = true;
                    kitchen.setAdditionalDescription("There is a closed trap door in the floor");
                    MainActivity.success(p.getContext(), "As you move the rug, you discover a previously-hidden trap door in the floor! The trap door is currently closed.");
                    p.callForceRedisplay();
                }
            }
        });

        damp.addAction(new ItemAction() {
            @Override
            public String getName(Player p) {
                if (!coffinOpen) {
                    return "Open the coffin";
                } else if (vampireDead) {
                    return "Move the coffin";
                } else {
                    return "Look at the vampire";
                }
            }

            @Override
            public void thatThingYouDo(Player p) {
                if (!coffinOpen) {
                    coffinOpen = true;
                    damp.setAdditionalDescription("An sinister vampire stands nearby, ready to attack.");
                    MainActivity.success(p.getContext(), "Nervously, you open the coffin. A vampire lies inside with his arms crossed, apparently sleeping.\n\nSuddenly his eyes open, and he rises up out of the coffin. His open mouth reveals sharp fangs, and he keeps staring at your neck in a way that makes you uncomfortable.");
                } else if (vampireDead) {
                    if (coffinMoved) {
                        MainActivity.failure(p.getContext(), "No need to push the coffin around anymore. You've already found the passage downward.");
                    } else {
                        coffinMoved = true;
                        damp.addExit(Direction.DOWN, cavern);
                        damp.setAdditionalDescription("Next to an open coffin, there is a passageway leading downward into darkness.");
                        MainActivity.success(p.getContext(), "You push the coffin to the side. Underneath, you discover a passageway leading down!");
                    }
                } else {
                    MainActivity.success(p.getContext(), "The vampire definitely looks the part: black suit, pasty skin, slick hair. He seems to be staring hungrily at your neck.");
                }
                p.callForceRedisplay();
            }
        });

        study.addAction(new ItemAction() {
            @Override
            public String getName(Player p) {
                return "Open the desk drawer";
            }

            @Override
            public void thatThingYouDo(Player p) {
                if (deskOpen) {
                    MainActivity.failure(p.getContext(), "No need; the desk is already open.");
                } else {
                    deskOpen = true;
                    study.addItem(gun);
                    MainActivity.success(p.getContext(), "As you open the desk, something falls out.");
                    p.callForceRedisplay();
                }
            }
        });

        bathroom.addAction(new ItemAction() {
            @Override
            public String getName(Player p) {
                return "Flush the toilet";
            }

            @Override
            public void thatThingYouDo(Player p) {
                MainActivity.success(p.getContext(), "FLUSH! You watch in amazement as the water swirls down the bottom of the toilet bowl. It never gets old, does it?");
            }
        });

        bathroom.addAction(new ItemAction() {
            @Override
            public String getName(Player p) {
                return "Wash your hands";
            }

            @Override
            public void thatThingYouDo(Player p) {
                MainActivity.success(p.getContext(), "Even treasure-hunting adventurers need to observe basic hygiene. Good for you!");
            }
        });

        rough.addAction(new ItemAction() {
            @Override
            public String getName(Player p) {
                return "Look into the pit";
            }

            @Override
            public void thatThingYouDo(Player p) {
                MainActivity.failure(p.getContext(), "As you cautiously peer over the edge of the pit, you see only darkness. Even with your flashlight, you cannot see the bottom of the pit.");
            }
        });

        intro1.addAction(new ItemAction() {
            @Override
            public String getName(Player p) {
                return "\"Sure, I'll try some.\"";
            }

            @Override
            public void thatThingYouDo(Player p) {
                intro2.prependDescription(eatPeanutButter);
                p.go(intro2);
                p.callForceRedisplay();
            }
        });

        intro1.addAction(new ItemAction() {
            @Override
            public String getName(Player p) {
                return "\"No, thanks. I'm a purist.\"";
            }

            @Override
            public void thatThingYouDo(Player p) {
                intro2.prependDescription(noPeanutButter);
                p.go(intro2);
                p.callForceRedisplay();
            }
        });

        intro2.addAction(new ItemAction() {
            @Override
            public String getName(Player p) {
                return "\"I like Grandpa's stories.\"";
            }

            @Override
            public void thatThingYouDo(Player p) {
                p.go(intro3);
                p.callForceRedisplay();
            }
        });

        intro3.addAction(new ItemAction() {
            @Override
            public String getName(Player p) {
                return "Begin";
            }

            @Override
            public void thatThingYouDo(Player p) {
                p.go(path1);
                p.callForceRedisplay();
            }
        });

        epilogue1.addAction(new ItemAction() {
            @Override
            public String getName(Player p) {
                return "The Commodore 64";
            }

            @Override
            public void thatThingYouDo(Player p) {
                epilogue2.prependDescription(likedTheCommodore);
                p.go(epilogue2);
                p.callForceRedisplay();
            }
        });

        epilogue1.addAction(new ItemAction() {
            @Override
            public String getName(Player p) {
                return "Feeding the Oyster";
            }

            @Override
            public void thatThingYouDo(Player p) {
                epilogue2.prependDescription(likedTheOyster);
                p.go(epilogue2);
                p.callForceRedisplay();
            }
        });

        epilogue1.addAction(new ItemAction() {
            @Override
            public String getName(Player p) {
                return "Defeating the Vampire";
            }

            @Override
            public void thatThingYouDo(Player p) {
                epilogue2.prependDescription(likedTheVampyre);
                p.go(epilogue2);
                p.callForceRedisplay();
            }
        });

        epilogue2.addAction(new ItemAction() {
            @Override
            public String getName(Player p) {
                return "Take the Book";
            }

            @Override
            public void thatThingYouDo(Player p) {
                p.go(epilogue3);
                p.callForceRedisplay();
            }
        });

        epilogue3.addAction(new ItemAction() {

            @Override
            public String getName(Player p) {
                return "The End ?";
            }

            @Override
            public void thatThingYouDo(Player p) {
                ((MainActivity)(p.getContext())).finish();
            }
        });

        ////////////////////////////////////////////////
        // Here, we mark the "special locations" as such
        ////////////////////////////////////////////////
        startingLocation = intro1;
        treasureRoom = porch;


        //TESTING
//        lamp.turnOn();
//        testing.testing_setStartRoom(r9);
//        testing.addToInventory(lamp);
    }

    public Room getStartingLocation() {
        return startingLocation;
    }

    public int reportScore() {
        return treasureRoom.combienDeTrésors();
    }

    public int totalPossibleTreasures() {
        return 20;
    }

    public String getAboutText() {
        return """
                Based on the public domain text adventure game for Commodore computers: "O'Hare's Adventure #3: The Haunted Mansion" by John O'Hare.
                
                Adapted for Android devices by Geoff Draper.
                
                The laptop on which this game was developed was powered in part by electricity derived from photovoltaic solar panels.
                """;
    }

    public Room getEpilogue() {
        return epilogue1;
    }

//    public String getFullName() {
//        return "O'Hare's Adventure I: The Cavern of Riches";
//    }
}
