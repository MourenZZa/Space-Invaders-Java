package tp.tp1.game.characters;

import tp.tp1.game.Game;

public abstract class EnemyShip extends Ship {

	//Constructor
	public EnemyShip(Game g, int f, int c, int live) {
		super(g, f, c, live);
	}
	
	//Constructor del GameObjectGenerator
	public EnemyShip() {}
	
	//IAttack methods-----------------------------------------------------
	
		public boolean receiveBombAttack(int damage) {return false;}
		
		public boolean receiveMissileAttack(int damage) {
			this.live -= damage;
			return true;
		}
		
		public boolean receiveShockWaveAttack(int damage) {
			this.live -= damage;
			return true;
		}
		
		public void receiveExplosion(int damage) {this.live -= damage;}
	
	//GameObject methods--------------------------------------------------
		
		@Override
		public boolean isOnBorder() {
			return this.col == 0|| this.col == Game.getMaxCol() - 1;
		}
}
