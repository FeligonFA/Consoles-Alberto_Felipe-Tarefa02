package corrida;

public abstract class Corredor implements Movimentar {
	String nome;
	Team team;
	int ID;
	short pos, movimentos, terminou;
}
