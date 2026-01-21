package tp.tp1.game.characters;
import tp.tp1.game.Game;
import tp.tp1.game.GameObject;

public abstract class Weapon extends GameObject {

	protected int damage;
	
	//Constructor
	public Weapon(int f, int c, Game game, int damage) {
		super(game, f, c, 0);
		this.damage = damage;
	}
	
	//Constructor para el GameObjectGenerator
	public Weapon() {}
	
	//Devuelve el daño que realiza el arma
	public int getDamage() {
		return damage;
	}
}

