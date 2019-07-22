/**
 *  Tests the Body constructor.
 */
public class TestBodyConstructor {

    /**
     *  Tests the Body constructor to make sure it's working correctly.
     */
    public static void main(String[] args) {
        checkBodyConstructor();
    }

    /**
     *  Checks whether or not two Doubles are equal and prints the result.
     *
     *  @param  expected    Expected double
     *  @param  actual      Double received
     *  @param  label   Label for the 'test' case
     */
    private static void checkEquals(double expected, double actual, String label) {
        if (expected == actual) {
            System.out.println("PASS: " + label + ": Expected " + expected + " and you gave " + actual);
        } else {
            System.out.println("FAIL: " + label + ": Expected " + expected + " and you gave " + actual);
        }
    }

    /**
     *  Checks whether or not two Strings are equal and prints the result.
     *  @param  expected    Expected String
     *  @param  actual      String received
     *  @param  label   Label for the 'test' case
     */
    private static void checkStringEquals(String expected, String actual, String label) {
        if (expected.equals(actual)) {
            System.out.println("PASS: " + label + ": Expected " + expected + " and you gave " + actual);
        } else {
            System.out.println("FAIL: " + label + ": Expected " + expected + " and you gave " + actual);
        }
    }

    /**
     *  Checks Body constructors to make sure they are setting instance
     *  variables correctly.
     */
    private static void checkBodyConstructor() {
        System.out.println("Checking Body constructor...");

        double xxPos = 1.0,
               yyPos = 2.0,
               xxVel = 3.0,
               yyVel = 4.0,
               mass = 5.0;

        String imgFileName = "jupiter.gif";

        Body p = new Body(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);

        checkEquals(xxPos, p.myXPos, "x");
        checkEquals(yyPos, p.myYPos, "y");
        checkEquals(xxVel ,p.myXVel, "xVelocity");
        checkEquals(yyVel, p.myYVel, "yVelocity");
        checkEquals(mass, p.myMass, "mass");
        checkStringEquals(imgFileName, p.myFileName, "path to image");

        Body pCopy = new Body(p);
        checkEquals(p.myXPos, pCopy.myXPos, "x");
        checkEquals(p.myYPos, pCopy.myYPos, "y");
        checkEquals(p.myXVel, pCopy.myXVel, "xVelocity");
        checkEquals(p.myYVel, pCopy.myYVel, "yVelocity");
        checkEquals(p.myMass, pCopy.myMass, "mass");
        checkStringEquals(p.myFileName, pCopy.myFileName, "path to image");
    }
}
