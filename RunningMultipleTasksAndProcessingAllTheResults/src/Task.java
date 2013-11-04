import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;


public class Task implements Callable<Result> {
	
	private String name;

	public Task(String name) {
		this.name = name;
	}

	public Task() {
	}

	@Override
	public Result call() throws Exception {
		
		System.out.println(this.name + ": Starting");
		
		try {
			long duration = (long)(Math.random()*10);
			System.out.println(this.name + ": Waiting " + duration + " seconds for results.");
			TimeUnit.SECONDS.sleep(duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		int value = 0;
		
		for (int i = 0; i < 5; i++) {
			value+=(int)(Math.random()*100);
		}
		
		Result result = new Result();
		result.setName(this.name);
		result.setValue(value);
		
		System.out.println(this.name + ": Ends.");
		return result;
	}

}
