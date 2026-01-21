 package tp.tp1;

public enum Level {
	/*El primer numero es la cantidad de naves normales
	 *El segundo es la cantidad  de destructoras
	 *El tercero es la frecuencia de disparo de las anteriores
	 *El cuarto es la frecuencia del ovni
	 *Y por último loss ciclos que tardan en moverse las naves
	 */
	EASY(4,2,1,0.5,3,"EASY"), HARD(8,2,0.3,0.2,2,"HARD"), INSANE(8,4,0.5,0.1,1,"INSANE");
	
	private int regular;
	private int destroyer;
	private double freq_Shoot;
	private double freq_OvniM;
	private int ships_speed;
	private String levelString;
	
	Level(int r, int d, double fs, double fo, int s, String lvlS){
		this.setRegular(r);
		this.setDestroyer(d);
		this.setFreq_Shoot(fs);
		this.setFreq_OvniM(fo);
		this.setShips_speed(s);
		this.setLvlString(lvlS);
	}

	//Getters
	public int getRegular() {
		return regular;
	}
	public int getDestroyer() {
		return destroyer;
	}
	
	public int getAliens() {
		return regular + destroyer;
	}
	
	public double getFreq_Shoot() {
		return freq_Shoot;
	}
	public double getFreq_OvniM() {
		return freq_OvniM;
	}
	public int getShips_speed() {
		return ships_speed;
	}

	//Setters
	public void setRegular(int regular) {
		this.regular = regular;
	}
	public void setDestroyer(int destroyer) {
		this.destroyer = destroyer;
	}
	public void setFreq_Shoot(double freq_Shoot) {
		this.freq_Shoot = freq_Shoot;
	}
	public void setFreq_OvniM(double freq_OvniM) {
		this.freq_OvniM = freq_OvniM;
	}
	public void setShips_speed(int ships_speed) {
		this.ships_speed = ships_speed;
	}
	
	public void setLvlString(String lvlS) {
		this.levelString = lvlS;
	}
	
	public Double getTurnExplodeFreq(){
		return 0.05;
	}
	
	public String getLvLtoString() {
		return levelString;
	}
}
