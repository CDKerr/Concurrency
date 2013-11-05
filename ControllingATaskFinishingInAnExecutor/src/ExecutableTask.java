import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;


public class ExecutableTask implements Callable<String> {
	
	private String name;

	public String getName() {
		return name;
	}

	public ExecutableTask(String name) {
		this.name = name;
	}

	@Override
	public String call() throws Exception {
	
		try {
			long duration = (long)(Math.random()*10);
			System.out.println(this.name + " waiting " + duration + " seconds for results.");
			TimeUnit.SECONDS.sleep(duration);
		} catch (InterruptedException e) {
		}
		return "Hello World. I'm " + name;
	}

}
