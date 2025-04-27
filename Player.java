import java.awt.*;
import java.awt.image.BufferedImage;

/**
	Esta classe representa os jogadores (players) do jogo. A classe principal do jogo
	instancia dois objetos deste tipo quando a execução é iniciada.
*/
public class Player implements ISolid {
	private double direction;
	private double cx, cy, width, height, speed;
	private Color color;
	private String id;
	private BufferedImage img;
	private boolean dead = false; // It was added to indicate that in the beginning the player is not dead
	private long lastShotTime = 0; // last shot time
	private long shotCooldown = 300; // period between shots (in ms)

	/**
		Construtor da classe Player.

		@param cx coordenada x da posição inicial do player (centro do retângulo que o representa).
		@param cy coordenada y da posição inicial do player (centro do retângulo que o representa).
		@param width largura do retângulo que representa o player.
		@param height altura do retângulo que representa o player.
		@param angle direção apontada pelo player
		@param sprite gráfico do player
		@param color cor do player.
		@param id uma string que identifica o player
		@param speed velocidade do movimento do player.
	*/
	public Player(double cx, double cy, double width, double height, double angle, BufferedImage sprite, Color color, String id, double speed){
		this.cx = cx;
		this.cy = cy;
		this.width = width;
		this.height = height;
		this.color = color;
		this.id = id;
		this.direction = angle;
		this.speed = speed;
		this.img = sprite;
	}

	/**
		Método chamado sempre que o player precisa ser (re)desenhado.
	*/
	public void draw(){
		GameLib.setColor(color);

		if (isDead()) {
			long now = System.currentTimeMillis();
			GameLib.drawImage(img, cx, cy, direction + now % 60 * 60, width / img.getWidth());
			return;
		}

		GameLib.drawImage(img, cx, cy, direction, width / img.getWidth());
	}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
		Método chamado quando se deseja mover para a frente na direção atual. 
		Este método é chamado sempre que a tecla associada à ação 
		de mover o player para frente estiver pressionada.

		@param delta quantidade de milissegundos que se passou entre o ciclo anterior de atualização do jogo e o atual.
	*/
	public void moveForwards(long delta){ // Fiz a modificacao 27/04
		if(isDead()) return; // Do not move when player is dead

		double distance = speed * delta / 1000.0;
		cx += Math.cos(direction) * distance;
		cx += Math.sin(direction) * distance;
	}

	/**
		Método chamado quando se deseja mover o player para trás na direção atual. 
		Este método é chamado sempre que a tecla associada à ação 
		de mover o player para trás estiver pressionada.

		@param delta quantidade de milissegundos que se passou entre o ciclo anterior de atualização do jogo e o atual.
	*/
	public void moveBackwards(long delta){ // Fiz a modificacao 27/04
		if(isDead()) return; // Do not move when player is dead

		double distance = speed * delta / 1000.0;
		cx -= Math.cos(direction) * distance;
		cy -= Math.sin(direction) * distance;
	}

	/**
		Método chamado quando se deseja girar o player para a esquerda (sentido anti-horário). 
		Este método é chamado sempre que a tecla associada à ação 
		de rotacionar o player à esquerda estiver pressionada.

		@param delta quantidade de milissegundos que se passou entre o ciclo anterior de atualização do jogo e o atual.
	 */
	public void rotateLeft(long delta) { // Fiz a modificacao 27/04
		if(isDead()) return; // Do not move when player is dead

		double rotationSpeed = 2.0; // Talvez usar a mesma velocidade de andar?
		double rotation = rotationSpeed * delta / 1000.0;
		direction += rotation;
	}

	/**
		Método chamado quando se deseja girar o player para a direita (sentido horário). 
		Este método é chamado sempre que a tecla associada à ação 
		de rotacionar o player à direita estiver pressionada.

		@param delta quantidade de milissegundos que se passou entre o ciclo anterior de atualização do jogo e o atual.
	 */
	public void rotateRight(long delta) { // Fiz a modificacao 27/04
		if(isDead()) return; // Do not move when player is dead

		double rotationSpeed = 2.0; // Maybe can we use the same speed as move?
		double rotation = rotationSpeed * delta / 1000.0;
		direction -= rotation; 
	}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * Retorna se o jogador pode realizar um disparo ou não. Chamado sempre que a ação de disparar
	 * desse player for acionada.
	 */
	public boolean canFire() { // Fiz a modificacao 27/04
		if(isDead()) return false; // Do not move when player is dead

		long now = System.currentTimeMillis();

		if(now - lastShotTime >= shotCooldown) return true;
		else return false;
	}

	/**
	 * Cria um disparo vindo desse player na mesma direção apontada pelo player.
	 * Esse método cria o disparo e o adiciona ao jogo.
	 */
	public void fire() {
		if(isDead()) return; // Do not move when player is dead

		if(canFire()) {
			lastShotTime = System.currentTimeMillis();

			Shot shot = new Shot(0.5);
		}
	}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * Retorna se o player acabou de ser destruído. Enquanto o player estiver destruído, ele não
	 * poderá ser danificado novamente.
	 */
	public boolean isDead() { // Fiz a modificacao 27/04
		return dead;
	}

	/**
	 * Chamado sempre que o player for destruído com um disparo. 
	 */
	public void die() { // Fiz a modificacao 27/04
		dead = true;
	}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
		Método que devolve a string de identificação do player.
		@return a String de identificação.
	*/
	public String getId() {  // Fiz a modificacao 27/04
		return id; 
	}

	/**
	 * Teleporta o player para essa coordenada do mapa.
	 */
	public void setPosition(double cx, double cy) { // Fiz a modificacao 27/04
		this.cx = cx;
		this.cy = cy;
	}

	/**
		Método que devolve a largura do retângulo que representa o player.
		@return um double com o valor da largura.
	*/
	public double getWidth() { // Fiz a modificacao 27/04
		return width; 
	}

	/**
		Método que devolve a altura do retângulo que representa o player.
		@return um double com o valor da altura.
	*/
	public double getHeight() { // Fiz a modificacao 27/04
		return height;
	}

	/**
		Método que devolve a coordenada x do centro do retângulo que representa o player.
		@return o valor double da coordenada x.
	*/
	public double getCx() { // Fiz a modificacao 27/04
		return cx;
	}

	/**
		Método que devolve a coordenada y do centro do retângulo que representa o player.
		@return o valor double da coordenada y.
	*/
	public double getCy() { // Fiz a modificacao 27/04
		return cy;
	}

	/**
	 * Obtém a cor do player.
	 */
	public Color getColor() { // Fiz a modificacao 27/04
		return color;
	}
}

