# OCS15-final-project

**Running the code:**

To run the code, run EvilHangmna.java in a terminal of size 253 wide x 55 tall ( by default ). The UI will probably look very odd if the your terminal's size is not configured correctly in Display.java. If you decrease the width, it will probobly throw a index out of bounds error when trying to render the "you lost, but keep trying!" text.



**Core Functioanlity Design Process:**

First I designed the game functionality, which was fairly straightforward. I designed the getFamilyHash function to be easily multithreadable because I thought that it would feel slow, especially on such a large dataset. After some testing without the UI, I determined that the Evil Hangman bits were not going to be the performance bottleneck, so I decided againts multithreading.



**UI Design Process:**

I had a clear idea of what the game was going to look like: hangman on the left, the word information top center, and the keyboard in middle center. I wanted there to be no buffered input, so it could register the user's key presses without the user pressing enter, and I wanted it to be fullscreen. Out of those, the most doubting was no buffered input because I could not imagine how to do that. After some reaserch, I found that it required OS specific libraries, which I did not want to do. So I scrapped the idea of no buffered input, but was able to finish everything else. I was able to find an image of the hangman stages and turn it into the perfect sized ASCII art with an online converter, and found a library of an ASCII art font. After everything was designed, the game still looked empty, the start was abrubpt, and there was no indication that the user could continue after the hangman game was finished. So I added a title screen for a less abrupt start, and I added the text infromation in the bottom left to make the UI feel more full and so it could tell the user to contiue after the hangman was finished.



**Overall Thoughts:**

I think this assignemnt went really well! I really liked how the UI turned out, especially given it is a terminal based interface. I really wanted to have unbuffered input so that the user does not need to press enter, but that appears to be very difficult to do in java.



**Credits:**

Keyboard art: https://www.asciiart.eu/computers/keyboards

ASCII art font: http://www.figlet.org/fonts/big.flf

Hangman original image: https://commons.wikimedia.org/w/index.php?search=hangman&title=Special:MediaSearch&go=Go&type=image

Hangman image prossesor (I used this to add a white background then invert the image before putting it through the ASCII art covnerter ): https://onlinepngtools.com/add-png-background

Image to ASCII art converter: https://www.ascii-art-generator.org/


**Video Demo:**

https://user-images.githubusercontent.com/43255248/169670313-44453542-9ff7-4c60-a9e3-3d3b1a0bd9ae.mp4
