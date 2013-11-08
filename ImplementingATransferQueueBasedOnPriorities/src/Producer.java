
public class Producer implements Runnable {
	
	private MyPriorityTansferQueue<Event> buffer;

	public Producer(MyPriorityTansferQueue<Event> buffer) {
		this.buffer = buffer;
	}

	@Override
	public void run() {
		
		for (int i = 0; i < 100; i++) {
			Event event = new Event(Thread.currentThread().getName(), i);
			buffer.put(event);
		}
	}

}
