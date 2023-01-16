package org.ucm.tp1.logic;

import java.util.Random;
import org.ucm.tp1.exceptions.CommandExecuteException;
import org.ucm.tp1.exceptions.DraculaIsAliveException;
import org.ucm.tp1.exceptions.InvalidPositionException;
import org.ucm.tp1.exceptions.NoMoreVampiresException;
import org.ucm.tp1.exceptions.NotEnoughCoinsException;
import org.ucm.tp1.logic.gameobjects.BloodBank;
import org.ucm.tp1.logic.gameobjects.Dracula;
import org.ucm.tp1.logic.gameobjects.ExplosiveVampire;
import org.ucm.tp1.logic.gameobjects.IAttack;
import org.ucm.tp1.logic.gameobjects.Slayer;
import org.ucm.tp1.logic.gameobjects.Vampire;
import org.ucm.tp1.view.GamePrinter;
import org.ucm.tp1.view.IPrintable;

public class Game implements IPrintable{

		private int cycles;
		private GameObjectBoard board;
		private Player player;
		private GamePrinter printer;
		private Level ALevel;
		private Random random;
		private boolean end = false;
				
		
		public Game(long seed, Level level) {			//Constructor Game
			this.ALevel = level; 						//Asignación de level
			this.random = new Random(seed); 			//Asignación de random con semilla correspondiente
			reset(); 									
		}
		
		public String toString() { 					//Llamada al dibujo del tablero
			String s = "";
			s += this.printer.toString(); 			// llamamada al dibujo del tablero
			return s; 								//se returnea el tablero
		}
		
		public String getPositionToString(int y, int x) {	//para generar tablero a partir de coordenada x, y devuelve el string que corresponda
			return board.findObjectString(y, x);
		}
		
		public void inicializeList() {
			board.inicializeListBoard();
		}
		
		public String getInfo() {
			String s = "";
			s += "Number of cycles: " + this.cycles + "\n";
			s += "Coins: " + player.getCoins() + "\n";
			s += "Remaining vampires: " + Vampire.getRemainingVampires() + "\n"; 	//Vampiros que quedan por salir al tablero
			s += "Vampires on the board: " + Vampire.getVampiresBoard() + "\n"; 	//Vampiros vivos en el tablero
			if(Dracula.isDraculaAlive()) {
				s+= "Dracula is alive\n";
			}
			return s;
		}
		
		public String toSerialize() {
			String s = "";
			s += "Cycles: " + this.cycles + "\n";
			s += "Coins: " + player.getCoins() + "\n";
			s+="Level: " + this.ALevel+"\n";
			s+= "Remaining Vampires: " + Vampire.getRemainingVampires() + "\n";
			s+="Vampires on Board: "+Vampire.getVampiresBoard()+"\n\n";
			s += "Game Object List: "+ "\n";
			s += board.toSerialize();				
			return s;
		}
		
		public IAttack attackableInPosition(int y, int x) {
			return board.attackableInPositionBoard(y,x);
		}
		
		public boolean isCellOccupiedGame(int y, int x) {
			return  board.isCellOccupied(y, x);
		}
		
		public boolean isFinished() { 
			if(playerWins() || Vampire.isVampireEnd()) {
				return true;
			}
			return false;
		}
		
		public boolean invalidPosition(int y, int x) {									//Comprueba si la posicion esta dentro de las dimensiones del juego
			boolean invalid = false;
			if(y < 0 || y >= ALevel.getDim_y() || x < 0 || x >= ALevel.getDim_x()) {	//Si inserta una posicion inferior o superior en y o x es inválida
				invalid = true;
			}
			return invalid;
		}
		
		
		public boolean addVampire(String type, int y,int x) throws CommandExecuteException {								//Añadir vampiro
			boolean vampireAdded=false;
			if(Vampire.getRemainingVampires() > 0) {
				if(type.equals("d")) {
					if(Dracula.isDraculaAlive()) {
						throw new DraculaIsAliveException("[ERROR]: Dracula is already on board");
					}
				}
				if(!board.isCellOccupied(y, x) && !invalidPosition(y, x)) {						//comprueba si la posicion en la que se va a añadir esta vacia
					if(type.equals("")) {
						//if(Vampire.getRemainingVampires() > 0) {
						board.addVampireBoard(new Vampire(y, x, this));
						vampireAdded = true;
						//}
					}
					else if(type.equals("d")) {
							board.addVampireBoard(new Dracula(y, x, this));
							Dracula.setDraculaAlive(true);
							vampireAdded=true;
					}
					else if(type.contentEquals("e")) {
						//if(Vampire.getRemainingVampires() > 0) {
							board.addVampireBoard(new ExplosiveVampire(y, x, this));
							vampireAdded=true;
						//}
					}
					else
						throw new CommandExecuteException("[ERROR]: Unvalid type: [v]ampire [<type>] <x> <y>. Type = {\"\"|\"D\"|\"E\"}\r\n");
				}
				else
					throw new InvalidPositionException("[ERROR]: Position (" + x + ", " + y + "): Unvalid position");
				
				}
			else {
				throw new NoMoreVampiresException("[ERROR]: No more remaining vampires left");
			}
			return vampireAdded;
		}

		public boolean addSlayer(int y, int x) throws InvalidPositionException, NotEnoughCoinsException {							//Añadir slayer
			boolean added=false;
			if(board.isCellOccupied(y, x) || invalidPosition(y, x)) {
				throw new InvalidPositionException("[ERROR]: Position ("+x + ", " + y + "): Unvalid position");	
			}
			else if(coinsGame() < Slayer.money) {
				throw new NotEnoughCoinsException("[ERROR]: Slayer cost is 50: Not enough coins");
			}
			else {
				board.addSlayerBoard(new Slayer(y, x, this));	
				player.decreaseCoins(Slayer.money);
				added=true;
			}
			return added;
		}

		public boolean lightFlash() throws NotEnoughCoinsException {										//Light flash
			boolean lightFlashed=false;
			if(player.getCoins() >= 50) {
				board.lightFlashBoard();
				player.decreaseCoins(50);
				lightFlashed= true;
			}
			else {
				throw new NotEnoughCoinsException("[ERROR]: Light Flash cost is 50: Not enough coins");
			}
			return lightFlashed;
		}
		
		public boolean garlicPush() throws NotEnoughCoinsException {										//Garlic push
			boolean garlicPushed=false;
			if(player.getCoins() >= 10) {
				board.garlicPushBoard();
				player.decreaseCoins(10);
				garlicPushed= true;
			}
			else {
				throw new NotEnoughCoinsException("[ERROR]: Garlic Push cost is 10: Not enough coins");
			}
			return garlicPushed;
		}
		
		public boolean addBloodBank(int y, int x, int z) throws NotEnoughCoinsException, InvalidPositionException {					//Añadir bloodbank
			boolean added=false;
			if(!board.isCellOccupied(y, x) && !invalidPosition(y, x)) {
				if(player.getCoins() < z) {
					throw new NotEnoughCoinsException("[ERROR]: Blood Bank cost is " + z + ": Not enough coins");
				}
				else {
					board.addBloodBankBoard(new BloodBank(y, x, this, z));
					player.decreaseCoins(z);
					added=true;
				}
			}
			else {
				throw new InvalidPositionException("[ERROR]: Position (" + x + "," + y + ") : Unvalid position");	
			}
			return added;
		}
		
		public void updateGame() throws CommandExecuteException {
			int x,y;//Función que realiza el update del juego en orden
			board.updateBoard();
			addCoinsGame();
			double prob = random.nextDouble();											
				if(prob < ALevel.getVampireFrequency() && Vampire.getRemainingVampires() > 0) {
					x = ALevel.getDim_x() - 1;
					y = random.nextInt(ALevel.getDim_y());
					addVampire("", y, x);
				}
				prob = random.nextDouble();
				if(prob < ALevel.getVampireFrequency() && !Dracula.isDraculaAlive()) {
					x = ALevel.getDim_x() - 1;
					y = random.nextInt(ALevel.getDim_y());
					addVampire("d", y, x);
				}
				prob = random.nextDouble();
				if(prob < ALevel.getVampireFrequency() && Vampire.getRemainingVampires() > 0) {
					x = ALevel.getDim_x() - 1;
					y = random.nextInt(ALevel.getDim_y());
					addVampire("e", y, x);
				}
				board.removeDead(); 						
				if(!playerWins() && !vampireWins()) { 		
					this.cycles++;
				}
		}
		
		public int coinsGame() {
			return player.getCoins();
		}
		
		public void addCoinsGame() {
			float i = random.nextFloat();
			if(i>=0.5)
				player.addCoins(10);
		}
		
		public void addCoins(double coins) {
			player.addCoins(coins);
		}
		
		public boolean playerWins() {
			boolean end = false;
			if(Vampire.getVampiresBoard()<=0 && Vampire.getRemainingVampires()<=0) {
				end=true;
			}
			return end;
		}
		
	
		public boolean vampireWins() {
			boolean end = false;
			if(Vampire.isVampireEnd()) {
				end=true;
			}
			return end;
		}
		
		public void reset() {																				//resetear juego
			this.printer = new GamePrinter(this, this.ALevel.getDim_x(), this.ALevel.getDim_y());;
			this.player = new Player();
			this.board = new GameObjectBoard(ALevel.getNumberOfVampires());
			this.cycles = 0;
			Dracula.setDraculaAlive(false);
			Vampire.init(ALevel.getNumberOfVampires());
			board.inicializeListBoard();
		}

		public Level getALevel() {
			return ALevel;
		}
		
		public int getCols() {
			return ALevel.getDim_x();
		}
		
		public int getRows() {
			return ALevel.getDim_y();
		}

		public void setEnd(boolean change) {
			end = change;
		}

		public boolean isEnd() {
			return end;
		}
}