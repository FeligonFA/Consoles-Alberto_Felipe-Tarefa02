package corrida;

public class ThreadProcessor extends Thread {

	Corredor corredor;
	TeamManager teamManager;
	short chegada;
	
	public ThreadProcessor(Corredor corredor, TeamManager teamManager) 
	{
		this.corredor = corredor;	
		this.teamManager = teamManager;
		teamManager.JoinTeam(corredor);
	}
	@Override
	public void run()
	{
		corredor.Mover();
	}
}
