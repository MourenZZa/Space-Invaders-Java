package tp.tp1.game.characters;

import tp.tp1.game.FileContentsVerifier;
import tp.tp1.game.Game;
import tp.tp1.game.GameObject;
import tp.tp1.game.IExecuteRandomActions;

public class RegularShip extends AlienShip {

	protected static int init_live;        	//Vida inicial de la nave
	protected static int punt = 5;		   	//Puntuacion por destruir la nave
	protected static boolean explosive;		//Indica si es explosiva
	
	public int damage = 1;					//Daño de la nave	
	
	//Constructor
	public RegularShip(Game game, int f, int c,int live) {
		super(game,f,c, live);
		init_live = live;
		explosive = false;
	}
	
	//Constructor del GameObjectGenerator
	public RegularShip() {}
	
	//Constructor del load
	public RegularShip(Game game, String fil, String col, String live, String Cycles, int direction, String Shoot) {
		super(game, Integer.parseInt(fil), Integer.parseInt(col), Integer.parseInt(live), Integer.parseInt(Cycles), direction, Shoot);
	}
	
	//Desplaza la nave una posición hacia abajo
	public void set_F() {
		this.fil += 1;	
	}
	
	//Desplaza la nave una posición hacia un lado
	public void set_C(int direction) {
		this.col += direction;
	}
	
	//Muestra las estadisticas de la nave
	public static String showStats() {
		return "[R]egular ship: Points: " + punt + " - Harm: 0 - Shield: " + init_live + "\n";
	}
	
	public GameObject parse(String stringFromFile, Game game, FileContentsVerifier verifier, Game newgame) {
		String[] words = stringFromFile.split(FileContentsVerifier.separator1);
		if( words[0].equals("R")) {
			if( verifier.verifyAlienShipString(stringFromFile, game, init_live)) {
				String[] coords = words[1].split(FileContentsVerifier.separator2);
				if(words[5].equals("left"))
					return new RegularShip(newgame,coords[0],coords[1],words[2],words[3],-1, words[4]);
				else
					return new RegularShip(newgame,coords[0],coords[1],words[2],words[3],1, words[4]);
			}
		}
		return null;
	}
	
	//GameObject methods--------------------------------------------------
	
		public void computerAction() {
			if(IExecuteRandomActions.canGenerateRandomExplosiveShip(game) && !explosive) {
				game.convertToExplosive(this);
			}
		}
	
		public void onDelete() {
			if(!explosive)
				super.onDelete();
				game.receivePoints(punt);
		}
		
		public String toString() {
			 return "R[" + this.live + "]";
		}
		
		public String serialize() {
			return "R;" + super.serialize(-1);
		}
}
