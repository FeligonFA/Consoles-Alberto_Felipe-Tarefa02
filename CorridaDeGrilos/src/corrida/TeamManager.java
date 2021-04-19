package corrida;

import java.util.concurrent.Semaphore;

public class TeamManager
{
	Team[] teams;
	Semaphore teamMembers = new Semaphore(3);
	int currentTeam = 0;
	boolean firstJoined = false;
	
	public TeamManager(int nTeams)
	{
		teams = new Team[nTeams];
		
		for (int i = 0; i < nTeams; i++) {
			teams[i] = new Team();
		}
	}
	
	public void JoinTeam(Corredor corredor) 
	{
		if (!firstJoined) 
		{
			System.out.println("O Time De N�mero 0 Est� Sendo Formado !");
			firstJoined = true;
		}
		if (teamMembers.tryAcquire()) 
		{
			teams[currentTeam].Join(corredor, currentTeam);
		}
		else
		{
			System.out.println("O Time De N�mero " + (currentTeam + 1) + " Est� Sendo Formado !");
			currentTeam++;
			teamMembers.release(3);
			JoinTeam(corredor);
		}
	}
}
