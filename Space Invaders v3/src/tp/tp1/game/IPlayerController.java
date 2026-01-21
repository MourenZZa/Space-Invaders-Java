package tp.tp1.game;

import tp.tp1.Exceptions.AlreadyShootException;
import tp.tp1.Exceptions.CommandExecuteException;
import tp.tp1.Exceptions.NoShockWaveException;
import tp.tp1.Exceptions.OutOfWorldEception;

public interface IPlayerController {
	// Player actions
	public boolean move (int numCells) throws CommandExecuteException, OutOfWorldEception;
	public boolean shootMissile() throws CommandExecuteException, AlreadyShootException;
	public boolean shockWave() throws CommandExecuteException, NoShockWaveException;

	// Callbacks
	public void receivePoints(int points) throws CommandExecuteException;
	public void enableShockWave() throws CommandExecuteException;
	public void enableMissile() throws CommandExecuteException;
}
