package corrida;

public class Grilo extends Corredor {

	short jumpDistance;
	
	public Grilo() {
	}

	@Override
	public void Mover() {
		// TODO Auto-generated method stub
		jumpDistance = (short) (Math.random() * 70);
		pos += jumpDistance;
		movimentos++;
		System.out.println("O " + this.nome + " pulou por " + jumpDistance + "cm e já percorreu " + pos + "cm");
		
		
	}

}
