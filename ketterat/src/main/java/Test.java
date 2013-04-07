public class Test {

	public void testStyleChecks() {
		if(true) {
			System.out.println("foo");
		}
		if (!false) {
			if (false) {
				System.out.println("false");
			} else if (true) {
				System.out.println("true");
			}
		}
		for (int i=0; i<5; i++) {
			for (int j=0; j<2; j++) {
				for (int k=0;k<2; k++) {
					System.out.println("-----------------------------------------------------------------");
				}
			}
		}
		if(1>2 || 2> 3 && 3>4) {
			System.out.println();
		}
		System.out.println("11th line");
		System.out.println("12th line");
	}


}
