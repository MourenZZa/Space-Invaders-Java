package tp.tp1.game;

public interface IExecuteRandomActions {
	static boolean canGenerateRandomOvni(Game game){
		return game.getRandom().nextDouble() < game.getLevel().getFreq_OvniM();
		}
	static boolean canGenerateRandomBomb(Game game){
		return game.getRandom().nextDouble() < game.getLevel().getFreq_Shoot();
	}
	static boolean canGenerateRandomExplosiveShip(Game game) {
		return game.getRandom().nextDouble() < game.getLevel().getTurnExplodeFreq();
	}
}
