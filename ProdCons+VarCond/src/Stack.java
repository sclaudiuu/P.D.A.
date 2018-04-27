import java.util.LinkedList;
import java.util.Queue;


public class Stack {
	private int maxSize;
	private Queue<ProduceReady> queue;
	
	

	public Stack(int maxSize) {
		super();
		this.maxSize = maxSize;
		queue = new LinkedList<>();
	}


	public boolean isFull() {
		return queue.size() == maxSize;
	}


	public void push(ProduceReady produceReadyState) {
		queue.add(produceReadyState);
		notifyAll();
	}

	public boolean isEmpty() {
		return queue.isEmpty();
	}

	public ProduceReady pop() {
		notifyAll();
		return queue.remove();
		
	}
}


