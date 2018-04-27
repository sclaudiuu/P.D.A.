
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Main {

	public static final int MAX_SIZE = 5;

	public static void main(String[] args) {
		new Main().start();
	}

	private void start() {
		startSemaphores();
	}
	
	private void startSemaphores() {
		List<Product> list = new ArrayList<Product>();
		
		Semaphore semCons = new Semaphore(0);
		Semaphore semProd = new Semaphore(MAX_SIZE);
		ProducerSemaphore producer = new ProducerSemaphore(semCons, semProd, list);
		ConsumerSemaphore consumer = new ConsumerSemaphore(semCons, semProd, list);
		
		producer.start();
		consumer.start();
	}

}
