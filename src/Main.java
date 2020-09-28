import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.math.BigInteger;

/**
 * Project 2 for CECS 328
 * @author Mark Garcia
 *         mark.garcia.8001@gmail.com
 */

public class Main {
    public static void main(String[] args){
        try {
            File in = new File("inputTest.txt");
            Scanner scan = new Scanner(in);
            ArrayList<BigInteger> ezd = new ArrayList<>();
            ArrayList<BigInteger> ab = new ArrayList<>();
            while(scan.hasNextLine()){
                String line = scan.nextLine();
                if(line.contains(" ")){
                    Scanner s = new Scanner(line);
                    while(s.hasNext()){
                        ab.add(new BigInteger(s.next()));
                    }
                } else {
                    ezd.add(new BigInteger(line));
                }
            }
            //System.out.println(ab);
            //System.out.println(ezd);
            System.out.println(gcd(ab.get(0), ab.get(1), ezd.get(0)));

        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        }


    }

    public static int gcd(BigInteger a, BigInteger b, BigInteger n){
        int p = 0;
        return gcd(a, b, n, p);
    }

    /**
     * Recursive Method for Greatest Common Divisor
     * @param a = an int from file
     * @param b = an int from file
     * @param n = easily divisible number from file
     * @param p = points
     * @return the total "points" a number gets
     */
    private static int gcd(BigInteger a, BigInteger b, BigInteger n, int p){

        if (a.compareTo(BigInteger.ZERO) == 0){
            // #1 gcd(0,b) = b
            return p;

        } else if (b.compareTo(BigInteger.ZERO) == 0){
            // #1 gcd(a,0) = a
            int p1 = p;
            return p1;
            
        } else if ((a.mod(n)).compareTo(BigInteger.ZERO) == 0 && (b.mod(n)).compareTo(BigInteger.ZERO) == 0){
            // #n if a & b are both even then gcd(a,b) = gcd(a/n,b/n)
            p += 2;
            return gcd(a.divide(n), b.divide(n), n, p);

        } else if ((a.mod(n)).compareTo(BigInteger.ZERO)  == 0 && (b.mod(n)).compareTo(BigInteger.ZERO) != 0){
            // #3 if a IS even and b IS NOT then gcd(a,b) = gcd(a/n,b)
            p += 1;
            return gcd(a.divide(n), b, n, p);

        } else if ((a.mod(n)).compareTo(BigInteger.ZERO) != 0 && (b.mod(n)).compareTo(BigInteger.ZERO) == 0){
            // #3 if a IS NOT even and b IS then gcd(a,b) = gcd(a,b/n)
            p += 1;
            return gcd(a, b.divide(n), n, p);

        } else if ((a.mod(n)).compareTo(BigInteger.ZERO) != 0 && (b.mod(n)).compareTo(BigInteger.ZERO) != 0){
            // #4 if both a & b are odd then gcd(a,b) = gcd(max{a,b} - min{a,b}, min{a,b})
            BigInteger max;
            BigInteger min;
            if(a.compareTo(b) > 0){
                max = a;
                min = b;
            } else {
                max = b;
                min = a;
            }
            return gcd(max.subtract(min), min, n, p);

        }

        return p;
    }

}
