
public class Consumer implements Runnable {
	
	private MyPriorityTansferQueue<Event> buffer;

	public Consumer(MyPriorityTansferQueue<Event> buffer) {
		this.buffer = buffer;
	}

	@Override
	public void run() {
		
		for (int i = 0; i < 1002; i++) {
			try {
				Event value = buffer.take();
				System.out.printf("Consumer: %s: %d\n", value.getThread(), value.getPriority());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
