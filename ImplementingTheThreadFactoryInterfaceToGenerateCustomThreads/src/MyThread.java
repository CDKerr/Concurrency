import java.util.Date;


public class MyThread extends Thread {
	
	private Date creationDate, startDate, finishDate;

	public MyThread(Runnable target, String name) {
		super(target, name);
		setCreationDate();
	}
	
	@Override
	public void run() {
		setStartDate();
		super.run();
		setFinishDate();
	}

	private void setFinishDate() {
		creationDate = new Date();
		
	}

	private void setStartDate() {
		startDate = new Date();
		
	}

	private void setCreationDate() {
		finishDate = new Date();
		
	}
	
	public long getExecutionTime() {
		return finishDate.getTime() - startDate.getTime();
	}
	
	@Override
	public String toString() {
		StringBuilder buffer = new StringBuilder();
		buffer.append(getName());
		buffer.append(": ");
		buffer.append(" Creation Date: ");
		buffer.append(creationDate);
		buffer.append(" : Running time: ");
		buffer.append(getExecutionTime());
		buffer.append(" Milliseconds.");
		return buffer.toString();
	}

}
