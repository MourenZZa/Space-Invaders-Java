package tp.tp1.game;
import tp.tp1.game.characters.*;

import tp.tp1.Exceptions.*;

public class GameObjectGenerator {
	private static GameObject[] availableGameObjects = {
			new UCMShip(),
			new Ovni(),
			new RegularShip(),
			new DestroyerShip(),
			new ExplosiveShip(),
			new Shockwave(),
			new Bomb(),
			new UCMShipLaser(),
			new SuperMissile(),	
	};
	
	public static GameObject parse(String stringFromFile,Game game, FileContentsVerifier verifier, Game newgame) throws FileContentsException {
		GameObject gameObject = null;
		for(GameObject go: availableGameObjects) {
			gameObject = go.parse(stringFromFile, game, verifier, newgame);
			if( gameObject != null) break;
		}
		return gameObject;	
	}
}
