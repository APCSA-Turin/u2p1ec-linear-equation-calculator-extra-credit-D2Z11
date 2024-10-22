package com.example.project;

// REMEMBER NO WHILE/FOR LOOPS!!!!

// NO FOR LOOPS/ITERATION ALLOWED!!!!

public class LinearCalculator{
    //FOR EXTRA CREDIT 
    //you should copy and paste all of your code from the LinearCalculator class
    // but NOT printInfo(). Please update it below
    //INSTANCE VARIABLES 
    //4 INTEGER variables (name them: x1,x2,y1,y2) 
    private int x1;
    private int x2;
    private int y1;
    private int y2;

    // parseCoord - returns an integer pair of the firstNumber and secondNumber of the coordinates
    public int[] parseCoord(String coordinates) {
        // Assume that there is one parentheses at the beginning and get the substring from index 1 to the index before the comma
        int firstNum = Integer.parseInt(coordinates.substring(1, coordinates.indexOf(",")));
        // Get the substring from the index after the comma and the index right before the closing parenthese
        int secondNum = Integer.parseInt(coordinates.substring(coordinates.indexOf(",") + 1, coordinates.length() - 1));
        int[] pair = {firstNum, secondNum};
        return pair;
    }

    //CONSTRUCTOR
    //1 constructor with 2 String parameters. Each parameter represents a coordinate. 
    //For example, "(1,2)" and "(3,4)" would be two parameter values 
    //You will have to parse the string into 4 integers, representing the 2 points.
    public LinearCalculator(String coord1, String coord2){ // <--add 2 string parameters to this constructor
        // Create a "pair" array
        int[] pair1 = parseCoord(coord1);
        // Create a "pair" array
        int[] pair2 = parseCoord(coord2);
        // Assign the coordinates in each pair to our private variables x1, x2,...
        x1 = pair1[0];
        y1 = pair1[1];
        x2 = pair2[0];
        y2 = pair2[1];
    }



    //METHODS
    //getters and setters for the 4 instance variables (8 methods total) 
    public int getX1(){return x1;}
    public int getY1(){return y1;}
    public int getX2(){return x2;}
    public int getY2(){return y2;}
    public void setX1(int x1){this.x1 = x1;}
    public void setY1(int y1){this.y1 = y1;}
    public void setX2(int x2){this.x2 = x2;}
    public void setY2(int y2){this.y2 = y2;}


    //distance() -> returns a double. 
    //calculates the distance between the two points to the nearest HUNDREDTH and returns the value.
    public double distance(){
        // distanceFormula = radical((x2-x1)^2+(y2-y1)^2)
        // Math.pow(___, 2) is the same as squaring it
        // Math.sqrt is the square root of the number
        double distanceFormula = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
        System.out.println(distanceFormula);
        // Must round to the hundredth!
        return roundedToHundredth(distanceFormula);
    }
    //yInt() -> returns a double.
    //calculates the y intercept of the equation and returns the value to the nearest HUNDREDTH
    //if y-int if undefined, should return -999.99
    public double yInt(){ 
        // If x1 == x2 that means the line is undefined because it's not a line when all the points are at one x (multiple x's means that it's not a function)
        if (x1 == x2) {
            return -999.99;
        }
        // y = mx + b  | If we have the slope, then we can just solve for b by plugging in any of our coordinates (since those coordinates provide a y and x)
        // The equation to solve for b is y-mx
        double b = (y1 - (x1 * slope()));
        // Make sure to round it to the hundredth
        return roundedToHundredth(b);
    }

    //slope() -> returns a double. 
    //calculates the slope of the equations and returns the value to the nearest HUNDREDTH
    //if slope is undefined, should return -999.99
    public double slope() {
        // You can't divide by zero!
        if ((x2-x1) == 0) {
            return -999.99;
        }
        // Rise / run or (y2-y1)/(x2-x1) is equal to the slope
        // Also, one of them has to be casted to double to make sure the division returns a double when rounding to hundredth
        double slope = roundedToHundredth((double) (y2-y1) / (x2-x1));
        return slope;
    }

    //equations() -> returns a String.
    //calculates the final equation in y=mx+b form and returns the string
    //if the equation has no slope, the equation should return -> "undefined"
    //HINT: You may need other custom methods to decrease the amount of code in the equations() method
    public String equation(){
        // Append the x in advance so it's easy to remove the x if the slope is 0.0
        String slopePart = slope() + "x";
        // Return a "+" if y-int is greater than zero but ignore 0 and negative because negative sign is automatically added and 0 will be ignored in the below if statement anyways
        String operator = (yInt() > 0 ? "+" : "");
        // Append the + or - before the yintercept in advane to prevent annoyances with the if statements
        String yIntPart = operator + yInt();
        // If the y intercept is 0.0, ignore it in the equation by making the y-intercept part an empty string
        if (yInt() == 0.0) {
            yIntPart = "";
        } else if (slope() == 0.0) {
            // If the slope is 0, ignore it by making the slope part an empty string
            slopePart = "";
            // Change yIntPart to remove the + at the beginning so it's just y=___
            yIntPart = yIntPart.substring(1);
        } else if (yInt() == -999.99) {
            // If y-int is -999.99 the equation is undefined
            return "undefined";
        }
        // Return equation in standard form
        return "y=" + slopePart + yIntPart;
    }


    //roundedToHundredth(double x)-> returns double
    //calculates the input to the nearest hundredth and returns that value
    public double roundedToHundredth(double x){
        // When you multiply by 100.0, say if it's 2.50 it will become 250. Then, it will round 250
        // which just gives 250 and divides it by 100.0 to go back to the original number.
        // If it's say 2.566, it will be 256.6 which will round to 257 and when divided it becomes 2.57 which is what we want
        // Also, make sure to use Math.round instead of Math.floor because Math.floor can incorrectly round down in some cases
        x = Math.round(x * 100.0) / 100.0;
        return x;
    }

    //You will need to concatenate to the string 
    //the results from findSymmetry() and Midpoint()
    public String printInfo(){
        // Concatenate the two functions
        return findSymmetry() + Midpoint();
    }

    //findSymmetry()-> returns a string 
    //the method should determine if there is symmetry between the two points
    // there should be  4 return statements 
    // return "Symmetric about the x-axis";
    // return "Symmetric about the y-axis";
    //return "Symmetric about the origin";
    //return "No symmetry";
    public String findSymmetry(){
        // Check for origin symmetry by setting x and y to the negative versions and then checking if the negative coords are equal to the other coords
        int negX = -x1;
        int negY = -y1;
        if (negX == x2 && negY == y2) {
            return "Symmetric about the origin";
        }
        // Check if the y matches with the negative y to get x-axis symmetry
        if (y1 == -y2) {
            return "Symmetric about the x-axis";
        }
        // Check if the x matches with the negative x to get y-axis symmetry
        if (x1 == -x2){
            return "Symmetric about the y-axis";
        }
        // Otherwise, there is no symmetry
        return "No symmetry";
    }

    //Midpoint()->return a string 
    //the method should calculate the midpoint between the two points
    //it should return "The midpoint of this line is: (0,0)";
    public String Midpoint(){
        // midpoint formula: ((x1+x2)/2), ((y1+y2)/2) --- also divide by 2.0 to ensure it becoms a double
        String midpoint = "The midpoint of this line is: (" + ((x1+x2)/2.0) + "," + ((y1+y2)/2.0) + ")";
        return midpoint;
    }

}



