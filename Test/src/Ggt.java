
public class Ggt {

	public static void main(String[] args) {
		ggt(512, 10);
	}
	
	private static void ggt(int a, int b) {
		if (0 != a % b) {
			ggt(b, a % b);
		}
		System.out.println(b);
	}
}
