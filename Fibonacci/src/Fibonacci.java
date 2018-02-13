
public class Fibonacci {

	public static void main(String[] args) {
		
		for (int i = 0; i < 50; i++) {
			System.out.println(rechnen(i));
		}
		
	}

	public static int rechnen(int n) {
		if (0 == n) {
			return 0;
		}
		if (1 == n) {
			return 1;
		}
		
		return (rechnen(n-1) + rechnen(n-2));
	}
}
