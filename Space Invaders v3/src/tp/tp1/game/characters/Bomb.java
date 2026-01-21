package tp.tp1.game.characters;

import tp.tp1.game.FileContentsVerifier;
import tp.tp1.game.Game;
import tp.tp1.game.GameObject;

public class Bomb extends Weapon {
	
	private static int damage = 1;		//Daño de la bomba
	private int n_bomb;
	
	//Constructor
	public Bomb(Game g, int f, int c, int n) {
		super(f, c, g, 1);
		n_bomb = n; 
	}
	
	//Constructor del GameObjectGenerator
	public Bomb() {}
	
	//Devuelve el daño de la bomba
	public static int get_Damage() {
		return damage;
	}
	
	public GameObject parse(String stringFromFile, Game game, FileContentsVerifier verifier, Game newgame) {
		String[] words = stringFromFile.split(FileContentsVerifier.separator1);
		if( words[0].equals("B")) {
			if( verifier.verifyWeaponString(stringFromFile, game)) {
				String[] coords = words[1].split(FileContentsVerifier.separator2);
				Bomb bomb = new Bomb(newgame,Integer.parseInt(coords[0]),Integer.parseInt(coords[1]),Integer.parseInt(words[2]));
				game.linkBombFromFile(bomb);
				return bomb;
			}
		}
		return null;
	}
	
	//IAttack methods-----------------------------------------------------
	
		@Override
		public boolean performAttack(GameObject other) {
			if(other.isOnPosition(this.fil,this.col)) {
				if(other.receiveBombAttack(damage)) {
					return true;
				}
				return false;
			}
			return false;
		} 
	
		@Override
		public boolean receiveMissileAttack(int damage) {
			this.fil = Game.getMaxFil() + 1;
			return true;
		}
		
		public void receiveExplosion(int damage) {onDelete();}
		
	//GameObject methods--------------------------------------------------
	
		public void computerAction() {}
		
		public void onDelete() {game.destroyBomb(this);}
		
		public void move(int n) {this.fil += 1;}
	
		public String toString() {return " * ";}
		
		public String serialize() {return null;}
		
		public String serializeFromDestructor() {
			String str;
			str = "B;" + fil + "," + col + ";" + n_bomb;
			return str;
		}
		
		@Override
		public int get_n_bomb() {
			return n_bomb;
		}
		
		@Override
		public boolean isLaser() {return false;}
		
		@Override
		public boolean isAlive() {
			if(this.fil >= Game.getMaxFil()) {
				return false;
			}
			return true;
		}
}
