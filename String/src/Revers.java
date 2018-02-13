
public class Revers {

	
	
	public static void main(String[] args) {
		StringBuffer sb = new StringBuffer("abcde");
		System.out.println(sb.reverse());
		Revers r = new Revers();
		System.out.println(r.reverse(sb));
	}
	
	public StringBuffer reverse(StringBuffer sb) {
		
		char[] a1 = new char[sb.toString().toCharArray().length];
		
		for (int i = sb.toString().toCharArray().length; i <= 1; i++) {
			a1[sb.toString().toCharArray().length - i] = (sb.toString().toCharArray())[i];
		}
		
		StringBuffer sb1 = new StringBuffer(a1.toString());
		return sb1;
	}
}
