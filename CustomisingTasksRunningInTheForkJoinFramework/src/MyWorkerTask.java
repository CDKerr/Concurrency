import java.util.Date;
import java.util.concurrent.ForkJoinTask;


public abstract class MyWorkerTask extends ForkJoinTask<Void> {

	private static final long serialVersionUID = 1L;
	private String name;
	
	public MyWorkerTask(String name){
		this.name = name;
	}
	@Override
	protected boolean exec() {
		Date startDate = new Date();
		compute();
		Date finishDate = new Date();
		long diff = finishDate.getTime() - startDate.getTime();
		System.out.printf("MyWorkerTask: %s : %d Milliseconds to complete.\n", name, diff);
		return true;
	}

	protected abstract void compute();
	
	@Override
	public Void getRawResult() {
		return null;
	}

	@Override
	protected void setRawResult(Void value) {

	}
	
	public String getName() {
		return name;
	}

}
