package tp.tp1.game.characters;

import tp.tp1.game.FileContentsVerifier;
import tp.tp1.game.Game;
import tp.tp1.game.GameObject;
import tp.tp1.game.IExecuteRandomActions;

public class Ovni extends EnemyShip {
	
	public static int punt = 25;           //Puntuacion por destruir la nave
	public static int init_live = 1;	   //Vida inicial de la nave
	public boolean ovni;				   //Indica si existe el ovni
	
	//Constructor
	public Ovni (Game g){	
		super(g,0,-1,init_live);
		ovni = false;
	}
	
	//Constructor del GameObjectGenerator
	public Ovni() {}
	
	//Constructor del load
	public Ovni(Game game, String fil, String col, String live) {
		super(game,Integer.parseInt(fil),Integer.parseInt(col),Integer.parseInt(live));
		ovni = true;
	}
	
	//Devuelve un string con las estadisticas de la nave
	public static String showStats() {
		return "[O]vni: Points: "+ punt +" - Harm: 0 - Shield: " + init_live + "\n";
	}
	
	public GameObject parse(String stringFromFile, Game game, FileContentsVerifier verifier, Game newgame) {
		String[] words = stringFromFile.split(FileContentsVerifier.separator1);
		if( words[0].equals("O")) {
			if( verifier.verifyOvniString(stringFromFile, game, init_live)) {
				String[] coords = words[1].split(FileContentsVerifier.separator2);
				return new Ovni(newgame,coords[0],coords[1],words[2]);
			}
		}
		return null;
	}
	
	//GameObject methods--------------------------------------------------
	
		public void computerAction() {
			if(IExecuteRandomActions.canGenerateRandomOvni(game) && ovni == false) {
				this.col = Game.getMaxCol() + 1;
				ovni = true;
			}
			if(ovni == true && this.col < 0) {
				ovni = false;
			}	
		}
		
		public void onDelete() {
			game.addShockWave();
			Ovni newOvni = new Ovni(this.game);
			game.addObject(newOvni);
			game.receivePoints(punt);	
		}
		
		public void move(int n) {
			if(ovni) {
				this.col--;
			}
		}
		
		public String toString() {
			return "O[" + this.live + "]";
		}
		
		public String serialize() {
			if(ovni) {
			return "O;"+fil+","+col+";"+live;
			} else
				return null;
		}
}
