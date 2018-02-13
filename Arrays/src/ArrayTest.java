
public class ArrayTest {

	int ungerade [];
	int andersherum [];
	
	ArrayTest(){
		ungerade = new int[20];
		andersherum = new int[20];
		this.befuellen();
		this.rueckwaerts();
		
		for (int i = 0; i < ungerade.length; i++) {
			System.out.println(ungerade[i]);
		}
		for (int i = 0; i < andersherum.length; i++) {
			System.out.println(andersherum[i]);
		}
	}
	
	private void befuellen() {
		for (int i = 0; i < ungerade.length; i++) {
			if (i == 0) {
				ungerade[i] = 1;
			} else {
				ungerade[i] = ungerade[i-1] + 2;
			}
		}
	}
	
	private void rueckwaerts() {
		for (int i = 0; i < andersherum.length; i++) {
			andersherum[i] = ungerade[(ungerade.length - 1) - i]; 
		}
	}
	
	public static void main(String[] args) {
		ArrayTest at = new ArrayTest(); 
	}

}
