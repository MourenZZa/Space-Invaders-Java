package tp.tp1.game.characters;

import tp.tp1.game.FileContentsVerifier;
import tp.tp1.game.Game;
import tp.tp1.game.GameObject;
import tp.tp1.game.IExecuteRandomActions;

public class DestroyerShip extends AlienShip{
	
	protected static int init_live = 1;		//Vida inicial de la nave
	protected static int punt = 10;			//Puntuacion por destruir la nave
	protected int n_bomb;
	public boolean shoot;
	
	private Bomb bomb = null;
	
	//Constructor
	public DestroyerShip(Game g, int f, int c, int l, int n) {
		super(g,f,c,init_live);
		n_bomb = n;
		shoot = false;
	}
	
	//Constructor del GameObjectGenerator
	public DestroyerShip() {}
	
	//Constructor del load
	public DestroyerShip(Game game, String fil, String col, String live, String Cycles, int direction, String n_bomb) {
		super(game, Integer.parseInt(fil), Integer.parseInt(col), Integer.parseInt(live), Integer.parseInt(Cycles), direction, n_bomb);
		//this.bomb = null;
		this.n_bomb = Integer.parseInt(n_bomb);
		if(this.n_bomb == -1) {
			shoot = false;
		}
		else shoot = true;
	}
	
	//Devuelve la bomba de la nave
	public Bomb getBomb() {
		return bomb;
	}
	
	//Muestra las estadisticas de la nave
	public static String showStats() {
		return "[D]estroyer ship: Points: " + punt + " - Harm: " + Bomb.get_Damage()  + " - Shield: " + init_live + "\n";	
	}
	
	public GameObject parse(String stringFromFile, Game game, FileContentsVerifier verifier, Game newgame) {
		String[] words = stringFromFile.split(FileContentsVerifier.separator1);
		if( words[0].equals("D")) {
			if( verifier.verifyAlienShipString(stringFromFile, game, init_live)) {
				String[] coords = words[1].split(FileContentsVerifier.separator2);
				if(words[4].equals("left"))
					return new DestroyerShip(newgame,coords[0],coords[1],words[2],words[3], -1, words[4]);
				else
					return new DestroyerShip(newgame,coords[0],coords[1],words[2],words[3],1, words[4]);			}
		}
		return null;
	}
	
	//Devuelve la nave en String
	public String toString() {
		return "D[" + this.live + "]";
	}
	
	//GameObject methods--------------------------------------------------

		public void computerAction() {
			if(IExecuteRandomActions.canGenerateRandomBomb(game) && !shoot) {
				bomb = new Bomb(game,this.fil ,this.col, n_bomb);
				shoot = true;
				game.addObject(bomb);
			}
		}
	
		public void onDelete() {
			super.onDelete();
			this.bomb = null;
			game.receivePoints(punt);
		}
		
		public String serialize() {
			String str;
			if(shoot == false) {
			str = "D;" + super.serialize(-1);
			}
			else str = "D;" + super.serialize(n_bomb);
			if(this.bomb != null) {	
				str = str + System.getProperty("line.separator") + this.bomb.serializeFromDestructor();
			}
			return str;
		}
		
		@Override
		public boolean BombOwner(GameObject bomb) {
			if(n_bomb == bomb.get_n_bomb() && shoot) {
				this.bomb = null;
				shoot = false;
				return true;
			}
			return false;
		}
}
