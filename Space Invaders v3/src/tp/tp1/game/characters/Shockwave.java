package tp.tp1.game.characters;

import tp.tp1.game.FileContentsVerifier;
import tp.tp1.game.Game;
import tp.tp1.game.GameObject;

public class Shockwave extends Weapon {
	
	public static boolean use;		//Indica si usar el superpoder o no
	public static boolean exist;	//Indica si existe el superpoder
	
	//Constructor
	public Shockwave(Game game) {
		super(-1, -1, game, 1);
		use = false;
		exist = true;
	}
	
	//Constructor del GameObjectGenerator
	public Shockwave() {}
	
	//Devuelve si existe el superpoder
	public static boolean get_Exist() {
		return exist;
	}
	
	//Activa el superpoder
	public static void EnableShockwave() {
		use = true;
	}
	
	//Devuelve el superpoder convertido a string
	public static String toStringSP() {
		if(exist) {
			return "POWER CHARGED";
		}
		return "NO";
	}
	
	public GameObject parse(String stringFromFile, Game game, FileContentsVerifier verifier, Game newgame) {
		return null;
	}	
	
	
	//IAttack methods-----------------------------------------------------
	
		@Override
		public boolean performAttack(GameObject other) {				
			if(use) {
				other.receiveShockWaveAttack(damage);
				exist = false;
			}
			return false;
		}
		
		@Override
		public boolean receiveShockWaveAttack(int damage) {
			onDelete();
			return true;
		}
		
	//GameObject methods--------------------------------------------------
	
		public void computerAction() {}
		
		public void onDelete() {use = false;}
		
		public void move(int numCells) {}
		
		public String toString() {return null;}
	
		protected String serialize() {
			return null;
		}
		
		@Override
		public boolean isLaser() {return false;}
		
		@Override
		public boolean isAlive() {return exist;}
}
