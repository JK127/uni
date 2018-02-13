import java.util.Scanner;

public class Rechner {
	static Scanner sc = new Scanner(System.in);
	
	public static void main (String[] args) {
		ausgeben(berechnen(eingeben()));
	}
	
	private static double eingeben() {
		System.out.println("Geben Sie eine Temperatur in °C ein");
		return (sc.nextDouble());
	}
	
	private static double berechnen (double c) {
		return ((c * 1.8) + 32);
	}
	
	private static void ausgeben(double f) {
		System.out.println("Diese Temperatur entspricht " + f + "°F");
	}
}
