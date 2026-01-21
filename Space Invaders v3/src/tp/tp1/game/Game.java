package tp.tp1.game;
import tp.tp1.*;
import tp.tp1.Exceptions.AlreadyShootException;
import tp.tp1.Exceptions.FileContentsException;
import tp.tp1.Exceptions.NoLevelException;
import tp.tp1.Exceptions.NoShockWaveException;
import tp.tp1.Exceptions.NotPoints;
import tp.tp1.Exceptions.OutOfWorldEception;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Random;

import tp.tp1.game.characters.*;

public class Game implements IPlayerController{
	
	public final static int DIM_X = 9;
	public final static int DIM_Y = 8;
	
	public Level l;					//Nivel
	private int seed;				//Semilla
	private int currentCycle;		//Ciclo del juego
	private Random rand;			//Generador numeros aleatorios
	private int punt;				//Puntuacion
	private int superMissiles;		//SuperMisiles
	private boolean ucmShoot;		//Disparo Ucm
	private boolean doExit;         //Indica si el jugador quiere dejar de jugar

	GameObjectBoard board;
	GameObjectBoard newBoard;
	private BoardInitializer initializer;
	private UCMShip player;					//Jugador
	
	private int newnAliens = 0; 			//Variables de los aliens del load
	private int newPointer = 0;

	//Constructor
	public Game (String level, String seed, boolean isSeed) throws NoLevelException{
		if(level.contentEquals("EASY")) {
			this.l = Level.EASY;
		}
		else if(level.contentEquals("HARD")) {
			this.l = Level.HARD;
		}
		else if(level.contentEquals("INSANE")) {
			this.l = Level.INSANE;
		}else throw new NoLevelException("Error: No level or wrong level selected.");
		try {
			if(isSeed == true) 
				this.seed = Integer.valueOf(seed);	
			else
				this.seed = (int) System.currentTimeMillis();
			
		} catch (NumberFormatException e) {
			System.out.println("Error: Wrong input seed . Using a random one");
			this.seed = (int) System.currentTimeMillis();
		} finally {
			initializer = new BoardInitializer();
			initGame();
		}
	}
	
	//Constructor del load
	public Game(GameObjectBoard newBoard, int currentCycles, Level l) {	
		board = newBoard;
		currentCycle = currentCycles;
		this.l = l;
		this.rand = new Random(System.currentTimeMillis());
		this.doExit = false;
		
		setAliensValue();
	}
	
	//Inicia la partida
	public void initGame () {
		currentCycle = 0;
		punt = 0;
		doExit = false;
		ucmShoot = false;
		superMissiles = 0;
		
		AlienShip.resetAliens();
		
		rand = new Random(Integer.valueOf(seed));
		initializer = new BoardInitializer();
		board = initializer.initialize (this, this.l);
		player = new UCMShip(this, DIM_Y - 1, DIM_X / 2, 3);
		board.add(player);
	}
	
	//Devuelve la puntuación del jugador
	public int get_Punt() {
		return punt;
	}
	
	//Devuelve el ciclo de la partida
	public int get_currentCycle(){
		return currentCycle;
	}
	
	//Devuelve la columna maxima del tablero
	public static int getMaxCol() {
		return DIM_X;
	}
	
	//Devuelve la fila maxima del tablero
	public static int getMaxFil() {
		return DIM_Y;
	}
	
	//Devuelve el numero generado aleatoriamente
	public Random getRandom() {
		return rand;
	}
	
	//Devuelve el nivel
	public Level getLevel() {
		return l;
	}
	
	public int get_SuperMissiles() {
		return superMissiles;
	}
	
	//Actuliza el numero de supermisiles
	public void setSuperMissiles(int n) {
		superMissiles = n;
	}

	//Añade un objeto al array
	public void addObject(GameObject object) {
		board.add(object);
	}
	
	//Reinicia la partida
	public void reset() {
		initGame();
	}
	
	public String characterAtToString(int f, int c) {
		return board.toString(f,c);
	}
	
	//Muestra la lista de naves
	public String listGame() {
		return "\n" + Ship.showStats();
	}
	
	//Termina la partida
	public void endGame() {
		this.doExit = true;
	}

	//Indica si la partida ha terminado
	public boolean isFinished() {
		return playerWin() || aliensWin() || doExit;
	}
	
	//Indica si el jugador ha perdido
	public boolean aliensWin() {
		return !player.isAlive () || AlienShip.haveLanded();
	}

	//Indica si el jugador ha ganado
	public boolean playerWin() {
		return AlienShip.No_Aliens_Alive();
	}
	
	//Controla la lógica del juego
	public void update() {
		board.computerAction();
		board.update();
		currentCycle ++;
	}

	//Devuelve si un objeto se encuentra en el tablero
	public boolean isOnBoard(int f, int c) {
		return (f >= 0 && c >= 0 && f < DIM_Y && c < DIM_X);
	}
	
	//Indica que el jugador quiere dejar de jugar
	public void exit() {
		doExit = true;
	}
	
	//Devuelve en String la informacion del juego
	public String infoToString() {
		return "Life: " + player.getLive() + "\n" + "Number of cycles: " + currentCycle + "\n" + "Points: " 
		+ punt + "\n" +"Remaining aliens: " + AlienShip.get_aliens() + "\n" + "ShockWave: " + Shockwave.toStringSP() + "\n" + "SUPERMISSILES: " + superMissiles;

	}
	
	//Devuelve los posibles mensajes que terminan una partida
	public String getWinnerMessage () {
		if (playerWin()) return " C O N G R A T U L A T I O N S,  P L A Y E R   W I N S !! \n";
		else if (aliensWin()) return " G A M E   O V E R !! - A L I E N S   W I N \n";
		else if (doExit) return "Leaving the game...";
		else return "This should not happen";
	}
	
	//Añade el superpoder al array polimorfico
	public void addShockWave() {
		if(!Shockwave.get_Exist()) {
			Shockwave sw = new Shockwave(this);
			board.add(sw);
		}
	}
	
	//Destruye una bomba
	public void destroyBomb(GameObject bomb) {
		board.searchBombOwner(bomb);	
	}
	
	//Compra un supermisil
	public boolean buySuperMissile()throws NotPoints {
		if(this.punt >= 20) {
			this.punt -= 20;
			this.superMissiles += 1;
			return true;
		}
		throw new NotPoints("CommandExecuteException.NotPoints: Cannot buy SuperMissile: Not Enought points.");
	}

	//Controla el movimiento del jugador
	public boolean move(int numCells) throws OutOfWorldEception {
		if(player.CanMove(numCells, DIM_X)) {
			player.move(numCells);
			return true;
		} else return false;
	}

	//Dispara el UCMLaser si es posible
	public boolean shootMissile() throws AlreadyShootException {
		if(ucmShoot) {
			throw new AlreadyShootException("CommandExecuteException.AlreadyShootException: Cannot Shoot Missil: There is another one already");
		}
		else if(superMissiles != 0) {
			SuperMissile supershoot = new SuperMissile(this,player.get_row(), player.get_col());
			addObject(supershoot);
			ucmShoot = true;
			superMissiles--;
			
			return true;
		} 
		else { 
			UCMShipLaser object = new UCMShipLaser(this,player.get_row(), player.get_col());
			board.add(object);
			ucmShoot = true;
			return true;
		}
	}

	//Activa el superpoder
	public boolean shockWave() throws NoShockWaveException {
		if(Shockwave.get_Exist()) {
			enableShockWave();
			return true;
		}
		throw new NoShockWaveException("CommandExecuteException.NoShockWaveException: There is not SW charged: Not Ovni Defeated");
	}

	//Actualiza los puntos del jugador
	public void receivePoints(int points) {
		this.punt += points;		
	}

	//Usa el superpoder
	public void enableShockWave() {
		Shockwave.EnableShockwave();
	}

	//Usa el UCMLaser
	public void enableMissile() {
		if(board.searchLaser()) {
			ucmShoot = true;
		}
		else ucmShoot = false;
	}
	
	//Transforma una nave en explosiva
	public void convertToExplosive(RegularShip regularShip) {
		board.convertToExplosive(regularShip,this);
	}
	
	//Realiza la explosion de la nave explosiva
	public void explodeHere(int f, int c) {
		board.explodeHere(f, c);
	}
	
	public String serialize() {
		StringBuilder str = new StringBuilder();
		
		str.append("-- Space Invaders v2.0 --");
		str.append(System.getProperty("line.separator"));
		str.append(System.getProperty("line.separator"));
		str.append("G;" + currentCycle);
		str.append(System.getProperty("line.separator"));
		str.append("L;" + l.getLvLtoString());
		str.append(System.getProperty("line.separator"));
		str.append(board.serialize());
		str.append(" "); //Necesario para luego cargar el juego sin fallos
		return str.toString();
	}


	public Game load(BufferedReader br) throws FileContentsException {
		
		Game newgame = null;
		newBoard = new GameObjectBoard(Game.DIM_Y, Game.DIM_X);
		Level lvl = null;
		int Cycles = 0;
		String line;
		FileContentsVerifier verifier = new FileContentsVerifier();
	try {
		line = br.readLine().trim();
		if(verifier.verifyCycleString(line)) {
			
			String[] words = line.split(FileContentsVerifier.separator1);
			Cycles = Integer.parseInt(words[1]);	//Guardamos los ciclos de aqui
			
			line = br.readLine().trim();
			if(verifier.verifyLevelString(line)) {
				words = line.split(FileContentsVerifier.separator1);
				
				if(words[1].contentEquals("EASY")) 
					lvl = Level.EASY;
				else if(words[1].contentEquals("HARD")) 
					lvl = Level.HARD;
				else if(words[1].contentEquals("INSANE")) 
					lvl = Level.INSANE;
				else
					throw new FileContentsException("Invalid file, unrecognised level");
					
				newgame = new Game(newBoard,Cycles,lvl);
				int i = 4;
				line = br.readLine().trim();
				while( line != null && !line.isEmpty() ) {
					GameObject gameObject = GameObjectGenerator.parse(line, this, verifier, newgame);
					if( gameObject == null) {
						throw new FileContentsException("Invalid file, unrecognised line prefix at file line " + i);
					}
					newBoard.add(gameObject);
					line = br.readLine().trim();
					i++;
				}

			} else throw new FileContentsException("Coudnt read the Level of the game");
		} else throw new FileContentsException("Invalid number of game cicles");
	}catch (IOException e){

	}
	newgame.loadValues();
	return newgame;
	}
	
	//Actualiza las variables de los aliens  despues del load
	public void setAliensValue() {
		newnAliens = 0; 
	    newPointer = 0;
	}
	
	public void loadValues() {
		AlienShip.loadStaticValues(newnAliens,newPointer);
	}
 
	public void prepareUCM(UCMShip player) {
		this.player = player;
	}

	
	public void linkBombFromFile(Bomb bomb) {
		newBoard.linkBomb(bomb);
	}
	
	public void saveStaticAlienValues(int pointer) {	
		newnAliens++;
		newPointer = pointer;
	}



}