# sensiLight-Java
A version of [ambilight from Philips] using Java for the system end.
It uses the a led strip with 30 LEDs. Works with using a SPI like communication to the strip.
The [Arduino Mega 2650] connects to the java interface using Serial Communication using the library for [serial port].
This should work with a performance of 40 - 50 milli seconds. Should work pretty good for 24 - 30 FPS video playback.
The java program uses the following packages to finish get the ambilight to work.
  - [ColorThief by Sven Woltmann]
  - [JSSC by scream3r on google code] or [JSSC by scream3r]
  - [Java AWT package]
  

It uses one dominant color from the image. The output is formated for the arduino as 
  - R,G,B.
  - R is red with 8bit digit (0-255) and so is G and B of the RGB color pallette
  - the dot in the end acts as a delimiter for the arduino for everytime it gets a new input.

#TODO
  - To split 30 leds for the regions of the screen
  - Create faster interface that is also platfrom independent
  
  
[ambilight from Philips]:https://en.wikipedia.org/wiki/Ambilight
[ColorThief by Sven Woltmann]:https://github.com/SvenWoltmann/color-thief-java
[JSSC by scream3r]:https://github.com/scream3r/java-simple-serial-connector
[JSSC by scream3r on google code]:https://code.google.com/p/java-simple-serial-connector/
[Arduino Mega 2650]:https://www.arduino.cc/en/Main/arduinoBoardMega2560
[serial port]:https://www.arduino.cc/en/Reference/Serial
[Java AWT package]:http://docs.oracle.com/javase/7/docs/api/java/awt/package-summary.html
