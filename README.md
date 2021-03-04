# MovieTheaterSeatingChallenge
 Walmart Coding Challenge

Walmart - Movie Theather Seating Challenge - 2020 - Alexander Le

Instructions:

1. Navigate to the Main.java file with terminal using cd to enter subfolders
2. Compile the code with "javac Main.java"
3. Run the code with "java Main.java *filepath*" where *filepath* is replaced by the test input file's path
   and the path is separated with '/' forward slash
4. The printed movie theater chart in the terminal shows available seats as a '.' and reserved seats as 'R'


Assumptions:

1. User will be able to understand the output file name and be able to navigate the path.

2. There will be no more than 9999 reservations. 
   If there are more, the output file layout structure will be affected. However, the file will still be 
   splittable with " ". 

3. There will be up to 26 rows in the movie theater. 
   If there are more, switch over to numbering system. In output file, Rows will be separated from the 
   seat number with "|" or a visible character. If the file is used later, the rows and seat numbers will 
   easily be splittable using "," and "|".

4. The user will input a path where folders and subfolder names are separated with forward slash ("/").

5. The customers don't mind about how close they are to the center. 
   If center seats raise customer satisfaction. Make change to algorithm that fills in large party sizes 
   in the middle, using up more center seats.

6. All reservations are 20 seats or less. 
   If more than 20, change algorithm to split them in half where each half are above and below each other
   in the theaters. Currently, code crashes. 

7. There will be no reservations that go over the capacity of the theater. 
   If there is no room for a reservation, the will not be seated. 
 


