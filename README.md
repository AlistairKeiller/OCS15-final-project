# OCS15-final-project

**Running the code:**

To run the code, run EvilHangmna.java in a terminal of size 253 wide x 55 tall. The UI will probobly looks very odd if the your terminal's size is not configured correctly in Display.java. If you decrease the width, it will probobly throw a index out of bounds error when trying to render the "you lost, but keep trying!" text.

**Core Functioanlity Design Prosses:**

First I designed the game functionality, which was fairly straightforward. I designed the getFamilyHash function to be easily multithreadable, but because I thought that it would feel slow, especially on such a large dataset. After some testing without the UI, I determined that the Evil Hangman bits were not going to be the performance bottlneck, so I decided againts multithreading.

**UI Design Prosses:**

I had a clear idea of what the game was going to look like: hangman on the left, the word information top center, and the keyboard in middle center. I wanted there to be no buffered input, so it could register the user's keypresses without the user pressing enter, and I wanted it to be fullscreen. Out of those, the most doubnting was no buffered input because I could not imagine how to do that. After some reaserch, I found that it required OS specific libraries, whichI did not want to do. So I scrapped the idea of no buffered input, but was able to finish everything else. I was able to find an image of the hangman stages and turn it into the perfect sized ascii art with an online converter, and found a library of a ascii art font. After everything was designed, the game still looked empty, the start was abrubpt, and there was no indication that the user could conntinue after the hangman was finished. So I added a title screen for a less abrupt start, and I added the text infromation in the bottom left to make the UI feel more full and so it could tell the user to contiue after the hangman was finished.

**Overall Thoughts:**

I think this assignemnt went really well! I really liked how the UI turned out, especially given it is a terminal based interface. I really wanted to have unbuffered input so that the user does not ened to press enter, but that appears to be verry difficult to do in java.

https://user-images.githubusercontent.com/43255248/169668898-6e0ce53b-fadc-42e9-b6b1-bdcbc7f2c09f.mp4
