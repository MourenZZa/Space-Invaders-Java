package tp.tp1.game.characters;

import tp.tp1.game.FileContentsVerifier;
import tp.tp1.game.Game;
import tp.tp1.game.GameObject;

public class UCMShipLaser extends Weapon {
	
	private static int damage = 1; //Daño de la nave
	
	//Constructor
	public UCMShipLaser(Game g, int f, int c) {
		super(f, c, g, damage);
	}
	
	//Constructor del GameObjectGenerator
	public UCMShipLaser() {}
	
	//Devuelve el daño de la nave
	public static int get_Damage() {
		return damage;
	}
	
	public GameObject parse(String stringFromFile, Game game, FileContentsVerifier verifier, Game newgame) {
		String[] words = stringFromFile.split(FileContentsVerifier.separator1);
		if(words[0].equals("M")) {
				if(verifier.verifyWeaponString(stringFromFile, game)) {
				String[] coords = words[1].split(FileContentsVerifier.separator2);
				return new UCMShipLaser(newgame,Integer.parseInt(coords[0]),Integer.parseInt(coords[1]));
			}
		}
		return null;
	}

	//IAttack methods-----------------------------------------------------
	
		@Override
		public boolean performAttack(GameObject other) {
			if(other.isOnPosition(this.fil,this.col)) {
				if(other.receiveMissileAttack(damage)) {
					return true;
				}
				return false;
			}
			return false;
		}
	
		@Override
		public boolean receiveBombAttack(int damage) {
			onDelete();
			return true;
		}
		
		public void receiveExplosion(int damage) {onDelete();}
	
	//GameObject methods--------------------------------------------------
	
		public void computerAction() {}

		public void onDelete() {this.fil = 0;}
	
		public void move(int numCells) {this.fil--;}

		public String toString() {return "||";}

		public String serialize() {
			return "M;" + fil + "," + col;
		}
		
		@Override
		public boolean isLaser() {return true;}
		
		@Override
		public boolean isAlive() {
			if(this.fil == -1) {
				return false;
			}
			return true;
		}
}

