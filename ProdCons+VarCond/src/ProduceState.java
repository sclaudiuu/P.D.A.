

public class ProduceState {
	public ProduceReady produce() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ProduceReady();
	}
}
