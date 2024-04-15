Dungeon Riser: The Climb to Comedic Heights
Welcome to Dungeon Riser, where dungeons are twistier than a pretzel and monsters have a better sense of humor than most comedians! This text-based adventure game combines puzzle-solving, witty dialogue, and comedic elements to provide players with an entertaining and challenging experience.

Table of Contents:

Introduction
Game Features
Installation
How to Play
Text files Structure
Map and Rooms Structure
Credits


Introduction:
---------------------
In Dungeon Riser, you play as an accidental hero on a quest to retrieve the legendary "Scepter of Silliness" from the Dungeon of Delirium.
Armed with nothing but your wits and a sense of humor, you'll navigate through perplexing puzzles, encounter pun-loving monsters, and collect hilarious items along the way.


Game Features:
---------------------
Engaging storyline filled with comedy and adventure.
Text-based gameplay with descriptive room environments.
Challenging puzzles and riddles to solve.
A variety of funny items including weapons, armor, and consumables.
Interactive combat with monsters, each with unique characteristics and humor.


Installation:
Clone or download the repository to your local machine.
Ensure you have Java installed on your system.
Compile and run the Main.java file to start the game.


How to Play:
---------------------
Use commands like "N" (North), "S" (South), "E" (East), and "W" (West) to navigate through rooms.
Interact with items and monsters using commands like "Pickup", "Attack", "Consume", "Equip", and more.
Solve puzzles and riddles to progress through the dungeon and uncover hidden treasures.
Explore each room thoroughly for clues, items, and humorous interactions.
Enter help when needed to view a list of available commands and actions.


Text files Structure:
---------------------
Rooms.txt: Room ID - Boolean isVisited - North direction - South direction - East direction - West direction - Room description - Puzzle ID
Items.txt: Item type - Item ID - Item Name - Item Description - Item Room ID
Equipable/Consumable Items: item.txt format + Item strength - Item utility
Monsters.txt: Monster name - Monster description - Monster health - Monster health - Monster attack - Monster double attack - Monster threshold - Monster room ID
Puzzles.txt: Puzzle name - Puzzle description - Puzzle solution - Puzzle attempt - Puzzle room ID

Map and Rooms Structure:
---------------------
MAP1:
          _____     _____
          | 3 |-----| 5 |
          _____     _____
            |
            |
_____     _____
| 1 |-----| 2 |
_____     _____
            |
            |
          _____     _____
          | 4 |-----| 6 |
          _____     _____

Room 1: The Haphazard Hallway
Description: You stumble into a narrow hallway adorned with crooked torches and whimsical paintings.
The walls seem to whisper jokes as you pass, creating an eerie yet humorous ambiance.
-----
EAST - 2
Items in Room: 1 - Notes
Monsters in Room: 1 - N/A

Room 2: The Labyrinth of Laughter
Description: Entering this room feels like stepping onto a giant comedy stage.
The floor is checkered with punchlines, and the ceiling echoes with laughter.
Be cautious, though; behind the humor lurks hidden traps.
-----
NORTH - 3
SOUTH - 4
WEST - 1
Items in Room: 2 - Tickle wand
Monsters in Room: 2 - Punchline Pixie

Room 3: The Jovial Junction
Description: You find yourself at a crossroads where jests and jokes intersect.
Signs point in every direction, each promising a different kind of mirthful adventure.
Choose wisely; not all paths lead to laughter.
-----
EAST - 5
SOUTH - 2
Items in Room: 3 - Laughing Potion
Monsters in Room: 3 - Giggling Goblin

Room 4: The Chuckle Chamber
Description: As you enter, a wave of giggles washes over you.
This chamber is filled with jesters' masks and prankster props.
Beware of the mischievous spirits that haunt this humorous haven.
-----
EAST - 5
NORTH - 2
Items in Room: 4 - Giggle vest
Monsters in Room: 4 - N/A

Room 5: The Wit's End
Description: This room is a playground of puns and wordplay.
Every step triggers a clever quip, and the walls seem to challenge your intellect with riddles.
Keep your wits about you to navigate this cerebral comedy zone.
-----
SOUTH - 2
WEST - 3
Items in Room: 5 - None
Monsters in Room: 5 - Hilarious Harpy

Room 6: The Whimsical Workshop
Description: You've stumbled into the laboratory of a mad comedic genius.
Gadgets that defy logic and gags waiting to be unleashed fill this room.
Tread lightly; one wrong move could set off a chain reaction of hilarity.
-----
EAST - 1
WEST - 4
Items in Room: 6 - Banana peel
Monsters in Room: 6 - Jester Juggernaut


Credits:
---------------------
Game Design and Development: Lincoln Bruce