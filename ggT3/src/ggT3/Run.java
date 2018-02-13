package ggT3;

import java.io.IOException;

public class Run {
	public static void main (String [] args) {
		rechnen(7, 8, 20);
	}
	
	private static void rechnen(int a, int b, int c) {
		int ergebnis = ggt(ggt(a, b), c);
		System.out.println(ergebnis);
	}
	
	private static int ggt(int a, int b) {
		if (a < b) {
			int x = a;
			a = b;
			b = x;
		}
		
		if (0 != (a % b)) {
			ggt(b, a % b);
		} 

		return b;
	}
}
