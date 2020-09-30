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
            File out = new File("output1.txt");
            PrintWriter pw = new PrintWriter(out);
            ArrayList<BigInteger> ezd = new ArrayList<>();
            ArrayList<BigInteger> ab = new ArrayList<>();
            //scan files add items into correct arraylists
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
            System.out.println(ab);
            System.out.println(ezd);
            for(int i = 1; i < ezd.size(); i++){
                System.out.println("Getting points for: " + ezd.get(i));
                int sumPoints = 0;
                for(int j = 0; j < ab.size()-1; j+=2){
                    sumPoints += gcd(ab.get(j), ab.get(j+1), ezd.get(i));
                    System.out.println("Current points for: " + ezd.get(i) + ": " + sumPoints);
                }
                System.out.println("Total points for: " + ezd.get(i) + ": " + sumPoints);
                pw.write(ezd.get(i).toString() + " " + sumPoints + "\n");
            }
            pw.close();

        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        }

    }

    public static int gcd(BigInteger a, BigInteger b, BigInteger n){
        int points = 0;
        return gcd(a, b, n, points);
    }

    private static int gcd(BigInteger a, BigInteger b, BigInteger n, int p){
        //Variable to see whats currently being tested
        String current = "[" + a.toString() + ", " + b.toString() + "]" + " Current Points: " + p;
        System.out.println(current);
        if(a.compareTo(BigInteger.ZERO) == 0){
            return p;

        } else if(b.compareTo(BigInteger.ZERO) == 0) {
            return p;

        } else if((a.mod(n)).compareTo(BigInteger.ZERO) == 0 && (b.mod(n)).compareTo(BigInteger.ZERO) == 0){
            System.out.println("BOTH DIVISIBLE ADD 2 POINTS");
            BigInteger na = a.divide(n);
            BigInteger nb = b.divide(n);
            int np = p+2;
            return gcd(na, nb, n, np);

        } else if((a.mod(n)).compareTo(BigInteger.ZERO) == 0) {
            System.out.println("A DIVISIBLE B IS NOT ADD 1 POINT");
            BigInteger na = a.divide(n);
            int np = p+1;
            if(na.equals(b)){
                System.out.println("LAST ITERATION NO POINTS AWARDED");
                np -= 1;
            }
            return gcd(na, b, n, np);

        } else if((b.mod(n)).compareTo(BigInteger.ZERO) == 0){
            System.out.println("A IS NOT B DIVISIBLE ADD 1 POINT");
            BigInteger nb = b.divide(n);
            int np = p+1;
            if(nb.equals(a)){
                System.out.println("LAST ITERATION NO POINTS AWARDED");
                np -= 1;
            }
            return gcd(a, nb, n, np);
        } else {
            System.out.println("NEITHER DIVISIBLE");
            return p;
        }

    }

    /*
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
     *//*
    private static int gcd(BigInteger a, BigInteger b, BigInteger n, int p){

        if (a.compareTo(BigInteger.ZERO) == 0){
            // #1 gcd(0,b) = b
            return p;

        } else if (b.compareTo(BigInteger.ZERO) == 0){
            // #1 gcd(a,0) = a
            return p;
            
        } else if ((a.mod(n)).compareTo(BigInteger.ZERO) == 0 && (b.mod(n)).compareTo(BigInteger.ZERO) == 0){
            // #n if a & b are both even then gcd(a,b) = gcd(a/n,b/n)
            p += 2;
            return gcd(a.divide(n), b.divide(n), n, p);

        } else if ((a.mod(n)).compareTo(BigInteger.ZERO)  == 0 && (b.mod(n)).compareTo(BigInteger.ZERO) != 0){
            // #3 if a IS even and b IS NOT then gcd(a,b) = gcd(a/n,b)
            BigInteger an = a.divide(n);
            if(an.compareTo(BigInteger.ZERO) != 0){
                p += 1;
            }
            return gcd(an, b, n, p);

        } else if ((a.mod(n)).compareTo(BigInteger.ZERO) != 0 && (b.mod(n)).compareTo(BigInteger.ZERO) == 0){
            // #3 if a IS NOT even and b IS then gcd(a,b) = gcd(a,b/n)
            BigInteger bn = b.divide(n);
            if(bn.compareTo(BigInteger.ZERO) != 0){
                p += 1;
            }
            return gcd(a, bn, n, p);

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
    }*/

}
