package corrida;

public class Team {
	
	Corredor[] corredores = new Corredor[3];
	int[] distances = new int[3];
	int[] jumps = new int[3];
	int[] memberID = new int[3];
	int nMembers, teamID;
	
	
	public void Join(Corredor corredor, int teamID)
	{
		this.teamID = teamID;
		corredores[nMembers] = corredor;
		corredor.team = this;
		memberID[nMembers] = nMembers;
		corredor.ID = nMembers;
		System.out.println("A posição " + nMembers + " do time " + teamID + " está sendo ocupada por " + corredor.nome);
		nMembers++;
	}

	public int GetDistances()
	{
		int sumDistances = 0;
		
		for (int i = 0; i < distances.length; i++) 
		{
			sumDistances += distances[i];
		}
		return sumDistances;
	}
	
	public int GetJumps()
	{
		int sumJumps = 0;
		
		for (int i = 0; i < jumps.length; i++) 
		{
			sumJumps += jumps[i];
		}
		return sumJumps;
	}
}
