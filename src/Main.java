import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileNotFoundException;

/**
 * Project 2 for CECS 328
 * @author Mark Garcia
 *         mark.garcia.8001@gmail.com
 */

public class Main {
    public static void main(String[] args){
        
    }

    /**
     * Recursive Method for Greatest Common Divisor
     * @param a = an int from file
     * @param b = an int from file
     * @param n = easily divisible number from file
     * @param p = points
     * @return the gcd
     */
    public static int gcd(int a, int b, int n, int p){

        if (a == 0){
            // #1 gcd(0,b) = b
            return b;

        } else if (b == 0){
            // #1 gcd(a,0) = a
            return a;

        } else if (a % n == 0 && b % n == 0){
            // #n if a & b are both even then gcd(a,b) = gcd(a/n,b/n)
            p += 2;
            return gcd(a / n, b / n, n, p);

        } else if (a % n == 0 && b % n != 0){
            // #3 if a IS even and b IS NOT then gcd(a,b) = gcd(a/n,b)
            p += 1;
            return gcd(a / n, b, n, p);

        } else if (a % n != 0 && b % n == 0){
            // #3 if a IS NOT even and b IS then gcd(a,b) = gcd(a,b/n)
            p += 1;
            return gcd(a, b / n, n, p);

        } else if (a % n != 0 && b % n != 0){
            // #4 if both a & b are odd then gcd(a,b) = gcd(max{a,b} - min{a,b}, min{a,b})
            return gcd((Math.max(a,b) - Math.min(a,b)), Math.min(a,b), n, p);

        }

        return -1;
    }

}
