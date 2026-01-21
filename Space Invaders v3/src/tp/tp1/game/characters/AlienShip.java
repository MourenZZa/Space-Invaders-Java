package tp.tp1.game.characters;

import tp.tp1.game.Game;

public abstract class AlienShip extends EnemyShip {

	private static int direction = -1;					//Dirrecion de las naves
	private static boolean haveLanded = false;			//Indica si las naves han aterrizado
	private int Cycles;									//Numero de ciclos para que se muevan las naves
		
	private static int aliens = 0;						//Numero de Aliens
	private static int jumping_aliens;					//Numero de Aliens que tienen que bajar 
	private static boolean jump = true;					//Inidica si las naves tiene que bajar
	
	//Constructor
	public AlienShip(Game game, int f, int c,int live) {
		super(game, f, c,live);
		aliens++;
		Cycles = game.l.getShips_speed();
		direction = 1;
		jumping_aliens = 0;
	}
	
	//Constructor del GameObjectGenerator
	public AlienShip() {}
	
	//Constructor de la nave explosiva
	public AlienShip(Game game, int fil, int col,int live, int Cycles) {	
		super(game, fil, col, live);
		this.Cycles = Cycles ;
	}
	
	//Constructor del load
	public AlienShip(Game game, int fil, int col, int live, int Cycles, int direction, String n_bomb) {	
		super(game, fil, col, live);
		game.saveStaticAlienValues(direction);
		this.Cycles = Cycles;
	}

	//Devuelve el numero de ciclos
	public int get_Cycles () {
		return Cycles;
	}
	
	//Devuelve el numero de naves alienígenas
	public static int get_aliens() {
		return aliens;
	}
	
	//Devuelve si un alien ha alcanzado el final del tablero
	public static boolean haveLanded() {
		return haveLanded;
	}
	
	//Devuelve si hay naves alienigenas vivas
	public static boolean No_Aliens_Alive() {
		if(aliens == 0) {
			return true;
		}
		return false;
	}
	
	//Elimina los aliens
		public static void resetAliens() {
			aliens = 0;
		}
	
	//Carga los valores de las naves alienigenas de la nueva partida
	public static void loadStaticValues(int newnAliens, int newDirection) {
		aliens = newnAliens;
		direction = newDirection;
	}
	
	//GameObject methods--------------------------------------------------

		public void computerAction() {}

		public void onDelete() {
			aliens--;
		}
		
		public void move(int n) {
			if(jumping_aliens != 0) {
				this.fil++;
				if(this.fil == Game.getMaxFil() - 1){
					haveLanded = true;
				}
				jumping_aliens--;
			} else if(Cycles == 1){
				this.col += direction;
				jump = true;
				Cycles = game.l.getShips_speed();
			} else
				Cycles--;
			}
		
		public String toString() {return null;}
		
		protected String serialize(int bomb) {
			String str;
			str = fil + "," + col + ";" + live + ";" + Cycles + ";" + bomb + ";";
			if(direction == -1) {
				str = str + "left";
			}else
				str = str + "right";	
			return str;
		}
		
		@Override
		public boolean isOnBorder() {
			boolean isOnBorder = super.isOnBorder();
			if(isOnBorder && jump) {
				jumping_aliens = aliens;
				jump = false;
				direction = -direction;
			}
			return isOnBorder; 
		}
}

