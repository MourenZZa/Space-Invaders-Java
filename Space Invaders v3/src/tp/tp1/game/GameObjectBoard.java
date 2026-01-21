package tp.tp1.game;
import tp.tp1.game.characters.*;

public class GameObjectBoard {
	private GameObject[] objects;
	private int currentObjects;

	//Crea el array de objetos
	public GameObjectBoard (int width, int height) {
		currentObjects = 0;
		objects = new GameObject[width * height];
	}

	//Añade un objeto al array
	public void add (GameObject object) {
		objects[currentObjects] = object;
		currentObjects++;
	}

	//Dada una fila y columna devuelve el objeto del array
	private GameObject getObjectInPosition (int row, int col) {
		int pos = getIndex(row,col);
		if( pos >= 0)
			return objects[pos];
		return null;
	}

	//Dada una fila y columna devuelve la posición del objeto en el array
	private int getIndex(int row, int col) {
		for(int i = 0; i < currentObjects; i++) {
			if(objects[i].isOnPosition(row, col))
				return i;
		}
		return -1;
	}

	//Elimina el objeto 
	private void remove (GameObject object) {
		for(int i = 0; i < currentObjects; i++) {
			if(objects[i] == object) {
				object.onDelete();
				removeDead(i);
			}	
		}
	}

	public void update() {
		//Comprueba si hay naves en los bordes del mapa
		for(int i = currentObjects-1; i >= 0 ;i--) {	
			objects[i].isOnBorder();	
			}
			
		//Mueve los proyectiles y las naves y comprueba los impactos
		for(int i = currentObjects-1; i >= 0 ;i--) {	
			if(objects[i] != null ) {
				objects[i].move(0);
				checkAttacks(objects[i]); 
				if(objects[i] != null && !objects[i].isAlive()) { 
					remove(objects[i]);
				}
			}
		}
		//Comprueba si al moverse las naves han sido golpeadas
		for(int i = currentObjects-1; i >= 0 ;i--) {
			checkAttacks(objects[i]); 
			if(objects[i] != null && !objects[i].isAlive()) {  
					remove(objects[i]);
				}
		}
	}

	//Comprueba si los objetos han sido atacados
	private void checkAttacks(GameObject object) {
		int i = 0;
		boolean removed = false;
			while(i < currentObjects  && !removed) {
				if(object.performAttack(objects[i])) {
					remove(object);
					removed = true;
				}
				i++;	
			}
		}

	public void computerAction() {
		for(int i = 0; i < currentObjects; i++) {
			objects[i].computerAction();
			} 
	}
    
	//Elimina el objeto del array
	private void removeDead(int i) {
		for(int k = i; k < currentObjects;k++) {
			objects[k] = objects[k+1];
		}
		currentObjects--;
	}

	//Devuelve el objeto del array parseado
	public String toString(int row, int col) {
		int pos = getIndex(row,col);
		if( pos >= 0)
			return objects[pos].toString();
		return "";

	}
	
	//Realiza la explosion de la nave explosiva
	public void explodeHere(int f, int c) {
		GameObject object;
		
		for(int i = f-1; i <= f+1;i++) {
			for(int j = c-1; j <= c+1; j++) {
				if( i != f || j != c) {
				object = getObjectInPosition(i,j);
					if(object != null && object.isAlive()) {
						object.receiveExplosion(ExplosiveShip.getExplosionDamage());
						if(!object.isAlive()) { 
							remove(object);
						}
					}
				}
			}	
		}	
	}
	
	//Convierte una nave regular a explosiva
	public void convertToExplosive(RegularShip regularShip, Game g) {
		for(int i = 0; i < currentObjects; i++ ) {
			if(objects[i] == regularShip) {
				ExplosiveShip expShip = new ExplosiveShip(g,regularShip.get_row(),regularShip.get_col(),regularShip.getLive(), regularShip.get_Cycles());
				objects[i] = expShip;
				break;
			}
			
		}
		
	}


	
	//Busca el laser
	public boolean searchLaser() {
		for(int i = currentObjects-1; i >= 0;i--) {
			if(objects[i].isLaser() && objects[i].isAlive()) {
				return true;
			}
		}
		return false;
	}
	
	//Dada una bomba busca a su creador
	public void searchBombOwner(GameObject bomb) {
		int i = currentObjects - 1;
		boolean found = false;
			while(i >= 0 && !found) {
				if(objects[i].BombOwner(bomb)) {
					found = true;
				}
				i--;	
			}
	}
	
	public String serialize() {
		StringBuilder str = new StringBuilder();
		String tmp;
		for(int i = 0; i < currentObjects;i++) {
			tmp = objects[i].serialize();
			if(tmp != null) {
			str.append(tmp);
			str.append(System.getProperty("line.separator"));
			}
		}
		return str.toString();
	}

	public void linkBomb(Bomb bomb) {
		objects[currentObjects-1].addBomb(bomb);
	}
}
