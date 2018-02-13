
public class Dreieck {

	public static void main(String[] args) {
		zeichnen1();
		zeichnen2();
		zeichnen3();

	}
	
	public static void zeichnen1 () {
		for (int i = 0; i < 20; i++) {
			for (int j = (20-i); j > 0; j--) {
				System.out.print("*");
			}
			System.out.println();
		}
	}

	public static void zeichnen2 () {
		for (int i = 0; i < 20; i++) {
			for (int k = 0 ; k < i; k++) {
				System.out.print(" ");
			}
			for (int j = (20-(2*i)); j > 0; j--) {
				System.out.print("*");
			}
			System.out.println();
		}
	}
	
	public static void zeichnen3 () {
		for (int i = 0; i < 20; i++) {
			for (int k = 0 ; k < i; k++) {
				System.out.print(" ");
			}
			for (int j = (20-(2*i)); j >= 0; j--) {
				System.out.print("*");
			}
			System.out.println();
		}
	}
}
