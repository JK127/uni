
/**
 * 
 * @author jonasklein
 *
 */
public class Quadratwurzel {

	public static void main (String[]args) {
		System.out.println("Der gesuchte Wert ist: " + wurzeln(4));
	}
	
	private static double wurzeln(double a) {
		double xn = 1;
		int durchlauf = 0;
		while(xn != ((xn + ( a / xn )) / 2)) {
			durchlauf++;
			xn = (xn + ( a / xn )) / 2;
			System.out.println("Durchlauf: " + durchlauf + " xn: " + xn);
		}
		
		return xn;
	}
}
