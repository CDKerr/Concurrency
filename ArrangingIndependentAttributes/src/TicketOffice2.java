
public class TicketOffice2 implements Runnable {

	private Cinema cinema;
	
	public TicketOffice2(Cinema cinema) {
		this.cinema = cinema;
	}

	@Override
	public void run() {
	cinema.sellTickets2(2);
	cinema.sellTickets2(3);
	cinema.sellTickets1(4);
	cinema.returnTickets1(2);
	cinema.sellTickets1(4);
	cinema.sellTickets1(5);
	cinema.sellTickets2(99);
	cinema.sellTickets1(52);
	}

}