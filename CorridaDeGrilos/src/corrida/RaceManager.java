package corrida;
import java.util.Scanner;

public class RaceManager {
	Corredor[] corredores;
	short nCorredores, chegada, finalistas;
	ThreadProcessor[] threads;
	
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
			threads[i] = new ThreadProcessor(corredores[i]);
			threads[i].start();			
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
		
		for (int i = 0; i < nCorredores; i++) {
			if (threads[i].corredor.terminou == 0) {
				if (threads[i].corredor.pos < chegada)
				{
					threads[i].run();		
				}
			else{
				System.out.println(threads[i].corredor.nome + " alcançou a linha de chegada com " + threads[i].corredor.movimentos + " pulos");
				threads[i].corredor.terminou = 1;
				finalistas++;
				}
			}
		}
		ContinuarCorrida();
	}
}

