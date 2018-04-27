


public class ApplicationMain {

	public static void main(String[] args) throws InterruptedException {
		new ApplicationMain().start();

	}

	private void start() throws InterruptedException {
		Stack stack = new Stack(5);
		ProduceState startState = new ProduceState();
		
		Thread producer = new Thread(new ProducerFunction(stack, startState));
		Thread consumer = new Thread(new ConsumerFunction(stack));
		
		producer.start();
		consumer.start();
		
		producer.join();
		consumer.join();
		
	}

}
