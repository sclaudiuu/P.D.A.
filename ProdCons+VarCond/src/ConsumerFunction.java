
public class ConsumerFunction implements Runnable {
	private Stack stack;
	
	
	public ConsumerFunction(Stack stack) {
		super();
		this.stack = stack;
	}


	@Override
	public void run() {
		while (true) {
			ProduceReady dataReady;
			synchronized (stack) {
				while (stack.isEmpty()) {
					try {
						System.out.println("Stack is empty");
						stack.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				dataReady = stack.pop();
				System.out.println("Produce poped");
			}
			dataReady.consume();
			System.out.println("Produce consumed");
		}
	}

}


