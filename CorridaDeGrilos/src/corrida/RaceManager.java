package corrida;
import java.util.Scanner;

public class RaceManager {
	Corredor[] corredores, winners;
	short nCorredores, chegada, finalistas;
	ThreadProcessor[] threads;
	TeamManager teamManager;
	
	public void InstanciarCorredores() 
	{
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in); 
		String input;
		System.out.println("Insira a quantidade de corredores.");
		input = scanner.next();
		nCorredores = (short) (Short.valueOf(input));
		System.out.println(input + " foi o número selecionado !");
		corredores = new Corredor[nCorredores];
		winners = new Corredor[nCorredores];
		
		teamManager = new TeamManager((int) Math.ceil(nCorredores/3.0));
		
		for (int i = 0; i < nCorredores; i++) 
		{
			corredores[i] = new Grilo();

			if (i < 9)
			{
				corredores[i].nome = "Grilo_0" + (1 + i);
			}
			else
			{
				corredores[i].nome = "Grilo_" + (1 + i);
			}
		}
		
	    scanner = new Scanner(System.in); 
		System.out.println("Insira a distância até a linha de chegada.");
		input = scanner.next();
		chegada = Short.valueOf(input);
		System.out.println("A linha de chegada ficará em " + input + "cm !");
		
		IniciarCorrida();
	}
	
	public void IniciarCorrida()
	{
		threads = new ThreadProcessor[nCorredores];

		for (int i = 0; i < nCorredores; i++) {
			threads[i] = new ThreadProcessor(corredores[i], teamManager);
		}
		
		ContinuarCorrida();
	}
	void ContinuarCorrida()
	{
		if (finalistas >= nCorredores)
		{
			return;
		}
		for (int i = 0; i < nCorredores; i++)
		{
			try 
			{
				threads[i].join();
			} catch (InterruptedException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		for (int i = 0; i < nCorredores; i++)
		{
			if (threads[i].corredor.terminou == 0) {
				if (threads[i].corredor.pos < chegada)
				{
					threads[i].run();		
				}
			else{
				threads[i].corredor.terminou = 1;
				finalistas++;
				threads[i].corredor.team.distances[threads[i].corredor.ID] = threads[i].corredor.pos;
				threads[i].corredor.team.jumps[threads[i].corredor.ID] = threads[i].corredor.movimentos;
			
					for (int j = 0; j < finalistas; j++) 
					{
						if (winners[j] == null) 
						{
							winners[j] = threads[i].corredor;
							System.out.println("O " + threads[i].corredor.nome + " Foi O " + j + " Colocado Com " + threads[i].corredor.movimentos + " Pulos.");
						}
					}
				}
			}
		}
		if (finalistas >= nCorredores) 
		{
			FinalizarCorrida();
		}
		ContinuarCorrida();
	}
	void FinalizarCorrida() 
	{
		for (int i = 0; i < teamManager.teams.length; i++) 
		{
			System.out.println("Time " + i + ": Total de Pulos: " + teamManager.teams[i].GetJumps() + " - Distância Percorrida: " + teamManager.teams[i].GetDistances());
		}
		System.out.println("O Time " + winners[0].team.teamID + " Foi O Vencedor !");			
	}
}

