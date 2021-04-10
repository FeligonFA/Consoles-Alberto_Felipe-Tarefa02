package corrida;

public class ThreadProcessor extends Thread {

	Corredor corredor;
	short chegada;
	
	public ThreadProcessor(Corredor corredor) {
	this.corredor = corredor;	
	}
	@Override
	public void run() {
		corredor.Mover();
	}
}
