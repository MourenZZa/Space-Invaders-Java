package tp.tp1.game.characters;

import tp.tp1.game.FileContentsVerifier;
import tp.tp1.game.Game;
import tp.tp1.game.GameObject;

public class ExplosiveShip extends AlienShip {
	
	protected static int init_live = 2;			//Vida inicial de la nave
	protected static int points = 5;			//Puntuacion por destruir la nave
	protected static int damage = 1;			//Daño de la nave
	
	//Constructor
	public ExplosiveShip(Game game, int f, int c, int live, int cycles) {
		super(game, f, c, live, cycles);
	}
	
	//Constructor de la nave explosiva
	public ExplosiveShip(Game game, String fil, String col, String live, String Cycles, int direction, String Shoot) {
		super(game, Integer.parseInt(fil), Integer.parseInt(col), Integer.parseInt(live), Integer.parseInt(Cycles), direction, Shoot);
	}
	
	//Constructor del GameObjectGenerator
	public ExplosiveShip(){}
	
	//Muestra las estadisticas de la nave
	public static String showStats() {
		return "[E]xplosive ship: Points: " + points + " - Harm: 0 - Explosion Harm: " + damage + " - Shield: " + init_live + "\n";
	}
	
	public GameObject parse(String stringFromFile, Game game, FileContentsVerifier verifier, Game newgame) {
		String[] words = stringFromFile.split(FileContentsVerifier.separator1);
		if( words[0].equals("E")) {
			if( verifier.verifyAlienShipString(stringFromFile, game, init_live)) {
				String[] coords = words[1].split(FileContentsVerifier.separator2);
				if(words[4].equals("left"))
					return new ExplosiveShip(newgame,coords[0],coords[1],words[2],words[3],-1, words[4]);
				else
					return new ExplosiveShip(newgame,coords[0],coords[1],words[2],words[3],1, words[4]);
			}
		}
		return null;
	}
	
	//GameObject methods--------------------------------------------------
	
		public void computerAction() {}
	
		public void onDelete() {
			super.onDelete();
			game.receivePoints(points);
			game.explodeHere(fil,col);
		}
	
		public String toString() {
			return "E[" + this.live + "]";
		}
	
		public static int getExplosionDamage() {
			return damage;
		}
	
		public String serialize() {
			return "E;" + super.serialize(-1);
		}
}
