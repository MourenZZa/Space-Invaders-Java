package tp.tp1.game.characters;

import tp.tp1.game.FileContentsVerifier;
import tp.tp1.game.Game;
import tp.tp1.game.GameObject;

public class SuperMissile extends Weapon {

	//Constructor
	public SuperMissile(Game game, int f, int c) {
		super(f, c, game, 2);
	}
	
	//Constructor del GameObjectGenerator
	public SuperMissile() {}
	
	public GameObject parse(String stringFromFile, Game game, FileContentsVerifier verifier, Game newgame) {
		String[] words = stringFromFile.split(FileContentsVerifier.separator1);
		if( words[0].equals("SM")) {
			if( verifier.verifyWeaponString(stringFromFile, game)) {
				String[] coords = words[1].split(FileContentsVerifier.separator2);
				return new SuperMissile(newgame,Integer.parseInt(coords[0]),Integer.parseInt(coords[1]));
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
		
		public void onDelete() {fil = -1;}
		
		public void move(int numCells) {fil -= 1;}
		
		public String toString() {return "_||_";}
		
		public String serialize() {
			return "SM;" + fil + "," + col;
		}
		
		@Override
		public boolean isLaser() {return true;}	
	
		@Override
		public boolean isAlive() {
			if(fil <= -1) {
				return false;
			}
			return true;
		}
}
