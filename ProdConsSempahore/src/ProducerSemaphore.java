
import java.util.List;
import java.util.concurrent.Semaphore;

public class ProducerSemaphore extends Thread{

	private Semaphore semProd;
	private Semaphore semCons;
	private List<Product> list;

	
	
	public ProducerSemaphore(Semaphore semCons, Semaphore semProd, List<Product> list) {
		super();
		this.semCons = semCons;
		this.semProd = semProd;
		this.list = list;
	}



	@Override
	public void run() {
		while(true) {
			try {
				semProd.acquire();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			Product product = new Product();
			synchronized (list) {
				list.add(product);
			}
			semCons.release();
		}
	}
}
