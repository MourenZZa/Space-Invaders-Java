package tp.tp1.game.characters;

import tp.tp1.Exceptions.OutOfWorldEception;
import tp.tp1.game.FileContentsVerifier;
import tp.tp1.game.Game;
import tp.tp1.game.GameObject;

public class UCMShip extends Ship {
	
	protected static int init_lives;    //Vida inicial de la nave
	
	//Constructor
	public UCMShip(Game g, int f, int c, int live) {
		super(g, f, c, live);
		UCMShip.init_lives = live;
	}
	
	//Constructor del GameObjectGenerator
	public UCMShip() {}
	
	//Constructor del load
	public UCMShip(Game game, String fil, String col, String live, String punt, String shockwave,String supermissile) {
		super(game,Integer.parseInt(fil),Integer.parseInt(col),Integer.parseInt(live));
		if(shockwave.equals("READY")) {
			game.addShockWave();
		}
		game.setSuperMissiles(Integer.parseInt(supermissile));
		game.receivePoints(Integer.parseInt(punt));
		game.prepareUCM(this);
	}

	//Devuelve la vida de la nave
	public int get_live() {
		return live;
	}
	
	//Muestra las estadisticas de la nave
	public static String showStats() {
		return "^__^: Harm: " + UCMShipLaser.get_Damage() +"- Shield: " + init_lives + "\n";
	}
	
	//Dice si se puede mover en la dirección escrita por el jugador
	public boolean CanMove(int move, int CMax) throws OutOfWorldEception{
		
		if(move < 0 && (this.col + move < 0)) {
			throw new OutOfWorldEception("CommandExecuteException.OutOfWorldException: Cannot perform move: ship too near border");
		}
			
		else if(move > 0 && (this.col + move > CMax - 1)) {
			throw new OutOfWorldEception("CommandExecuteException.OutOfWorldException: Cannot perform move: ship too near border");
		}
		return true;
	}

	
	public GameObject parse(String stringFromFile, Game game, FileContentsVerifier verifier, Game newgame) {
		String[] words = stringFromFile.split(FileContentsVerifier.separator1);
		if(words[0].equals("P")) {
			if(verifier.verifyPlayerString(stringFromFile, game, init_lives)) {
				String[] coords = words[1].split(FileContentsVerifier.separator2);
				return new UCMShip(newgame,coords[0],coords[1],words[2],words[3],words[4],words[5]);
			}
		}
		return null;
	}
		
	//IAttack methods-----------------------------------------------------
		
		@Override
		public boolean receiveBombAttack(int damage) {
			this.live -= damage;
			return true;
		}
		
		public void receiveExplosion(int damage) {this.live -= damage;}
	
	//GameObject methods--------------------------------------------------
		
		public void computerAction() {}
	
		public void onDelete() {game.endGame();}
	
		public void move(int cells) {this.col += cells;}
	
		public String toString() {
		if(live > 0) {
		return "^__^";
		}
		else return "!__!";
		}
	
		public String serialize() {
			String str = "P;" + fil + "," + col + ";" + live + ";" + game.get_Punt() + ";";
			if(Shockwave.get_Exist()) {
				str = str + "READY";
			} else
				str = str + "NO";
			
			str = str + ";" + game.get_SuperMissiles();
			return str;
		}
		
		@Override
		public boolean isOnBorder() {return false;}
		
		@Override
		public boolean isAlive() {
			if(this.live == 0) {
				this.onDelete();
				return false;
			}
			return true;	
		}
}
