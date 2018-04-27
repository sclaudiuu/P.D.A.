
import java.util.List;
import java.util.concurrent.Semaphore;

public class ConsumerSemaphore extends Thread{

	private Semaphore semCons;
	private Semaphore semProd;
	private List<Product> list;

	
	
	public ConsumerSemaphore(Semaphore semCons, Semaphore semProd, List<Product> list) {
		super();
		this.semCons = semCons;
		this.semProd = semProd;
		this.list = list;
	}



	@Override
	public void run() {
		while(true) {
			try {
				semCons.acquire();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			Product product;
			synchronized (list) {
				product = list.remove(0);
			}
			semProd.release();
			product.consume();
		}
	}

}
