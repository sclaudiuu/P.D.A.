

public class ProducerFunction implements Runnable {

	private Stack stack;
	private ProduceState startState;
	
	public ProducerFunction(Stack stack, ProduceState startState) {
		super();
		this.stack = stack;
		this.startState = startState;
	}


	@Override
	public void run() {
		while (true) {
			System.out.println("Produce is producing");
			ProduceReady produceReady = startState.produce();
			System.out.println("Produce is ready");

			synchronized (stack) {

				while (stack.isFull()) {
					try {
						System.out.println("Stack is full");
						stack.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				stack.push(produceReady);
				System.out.println("Produce pushed");

			}
		
	}
	}

}
