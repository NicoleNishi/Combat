<<<<<<< HEAD:src/Shot.java
/**
 * Esta classe representa um dos disparos ativos usado no jogo. Essa classe sempre deve ser
 * instanciada quando um disparo for feito por algum dos jogadores.
 */
public class Shot implements ISolid {
	private Player owner;
	private double cx, cy, size, vx, vy, speed;
	private boolean active;

	/**
		Construtor da classe Shot.

		@param player dono do disparo.
		@param cx coordenada x da posição inicial do disparo (centro do quadrado que a representa).
		@param cy coordenada y da posição inicial do disparo (centro do quadrado que a representa).
		@param size tamanho do quadrado que representa o disparo
		@param direction ângulo do disparo em graus
		@param speed velocidade da bola (em pixels por milissegundo).
	*/
	public Shot(Player player, double cx, double cy, double size, double direction, double speed){
		this.owner = player;
		this.cx = cx;
		this.cy = cy;
		this.size = size;
		this.speed = speed;
		active = true;

		vx = Math.cos(Math.toRadians(direction));
		vy = Math.sin(Math.toRadians(direction));
	}

	/**
		Método chamado sempre que o disparo precisa ser (re)desenhada.
	*/
	public void draw(){
		GameLib.setColor(owner.getColor());
		GameLib.fillRect(cx, cy, size, size);
	}

	/**
		Método chamado quando o estado (posição) do disparo precisa ser atualizado.
		
		@param delta quantidade de milissegundos que se passou entre o ciclo anterior de atualização do jogo e o atual.
	*/
	public void update(long delta){
		double distance = speed * delta;

		cx += vx * distance;
		cy += vy * distance;
	}

	/**
		Método chamado quando detecta-se uma colisão do disparo com um jogador.
	
		@param player o jogador que foi acertado.
	*/
	public void onPlayerCollision(Player player){
		active = false;
		player.die();
	}

	/**
		Método chamado quando detecta-se uma colisão do disparo com alguma parede.
	*/
	public void onWallCollision(){
		active = false;
	}

	/**
		Método que verifica se houve colisão ou não do disparo com uma parede.

		@param wall referência para uma instância de Wall contra a qual será verificada a ocorrência de colisão da bola.
		@return um valor booleano que indica a ocorrência (true) ou não (false) de colisão.
	*/
	public boolean checkCollision(Wall wall){
		// Coordenadas da borda do shot
    double shotLeft = cx - size / 2;
    double shotRight = cx + size / 2;
    double shotTop = cy - size / 2;
    double shotBottom = cy + size / 2;

    // Coordenadas da borda da parede
    double wallLeft = wall.getCx() - wall.getWidth() / 2;
    double wallRight = wall.getCx() + wall.getWidth() / 2;
    double wallTop = wall.getCy() - wall.getHeight() / 2;
    double wallBottom = wall.getCy() + wall.getHeight() / 2;

    // Verifica se os retângulos se sobrepõem
    boolean overlapX = shotRight > wallLeft && shotLeft < wallRight;
    boolean overlapY = shotBottom > wallTop && shotTop < wallBottom;

    return overlapX && overlapY;
	}

	/**
		Método que verifica se houve colisão do disparo algum jogador.

		@param player referência para uma instância de Player contra o qual será verificada a ocorrência de colisão do disparo.
		@return um valor booleano que indica a ocorrência (true) ou não (false) de colisão.
	*/	
	public boolean checkCollision(Player player){
		if (player != owner) {
			// Coordenadas da borda do shot
			double shotLeft = cx - size / 2;
			double shotRight = cx + size / 2;
			double shotTop = cy - size / 2;
			double shotBottom = cy + size / 2;

			// Coordenadas da borda do player
			double playerLeft = player.getCx() - player.getWidth() / 2;
			double playerRight = player.getCx() + player.getWidth() / 2;
			double playerTop = player.getCy() - player.getHeight() / 2;
			double playerBottom = player.getCy() + player.getHeight() / 2;

			// Verifica se os retângulos se sobrepõem
			boolean overlapX = shotRight > playerLeft && shotLeft < playerRight;
			boolean overlapY = shotBottom > playerTop && shotTop < playerBottom;

			return overlapX && overlapY;
		}
		return false;
	}

	/**
	 * Esse método diz se o disparo está ativo ainda ou não. Um disparo ativo ainda interage com os
	 * elementos do jogo.
	 */
	public boolean isActive() {
		return active;
	}

	/**
		Método que devolve a coordenada x do centro do quadrado que representa o disparo.
		@return o valor double da coordenada x.
	*/
	public double getCx(){
		return cx;
	}

	/**
		Método que devolve a coordenada y do centro do quadrado que representa o disparo.
		@return o valor double da coordenada y.
	*/
	public double getCy(){
		return cy;
	}

	/**
		Método que devolve a velocidade do disparo.
		@return o valor double da velocidade.

	*/
	public double getSpeed(){
		return speed;
	}

	/** Obtém a largura do retângulo que representa o disparo. */
	public double getWidth() {
		return size;
	}

	/** Obtém a altura do retângulo que representa o disparo. */
	public double getHeight() {
		return size;
	}

	/**
	 * Obtém o dono desse disparo.
	 * @return o player que efetuou o disparo
	 */
	public Player getOwner() {
		return owner;
	}
}
=======
/**
 * Esta classe representa um dos disparos ativos usado no jogo. Essa classe sempre deve ser
 * instanciada quando um disparo for feito por algum dos jogadores.
 */
public class Shot implements ISolid {
	private Player owner;
	private double cx, cy, size, vx, vy, speed;
	private boolean active;

	// Atributos adicionados
	private double overlapX = 0;
	private double overlapY = 0;
	private long shotTime = System.currentTimeMillis();

	/**
		Construtor da classe Shot.

		@param player dono do disparo.
		@param cx coordenada x da posição inicial do disparo (centro do quadrado que a representa).
		@param cy coordenada y da posição inicial do disparo (centro do quadrado que a representa).
		@param size tamanho do quadrado que representa o disparo
		@param direction ângulo do disparo em graus
		@param speed velocidade da bola (em pixels por milissegundo).
	*/
	public Shot(Player player, double cx, double cy, double size, double direction, double speed){
		this.owner = player;
		this.cx = cx;
		this.cy = cy;
		this.size = size;
		this.speed = speed;
		active = true;

		vx = Math.cos(Math.toRadians(direction));
		vy = Math.sin(Math.toRadians(direction));
	}

	/**
		Método chamado sempre que o disparo precisa ser (re)desenhada.
	*/
	public void draw(){
		GameLib.setColor(owner.getColor());
		GameLib.fillRect(cx, cy, size, size);
	}

	/**
		Método chamado quando o estado (posição) do disparo precisa ser atualizado.
		
		@param delta quantidade de milissegundos que se passou entre o ciclo anterior de atualização do jogo e o atual.
	*/
	public void update(long delta){
		double distance = speed * delta;

		cx += vx * distance;
		cy += vy * distance;

		if (System.currentTimeMillis() - shotTime > 3000) active = false;
	}

	/**
		Método chamado quando detecta-se uma colisão do disparo com um jogador.
	
		@param player o jogador que foi acertado.
	*/
	public void onPlayerCollision(Player player){
		active = false;
		player.die();
	}

	/**
		Método chamado quando detecta-se uma colisão do disparo com alguma parede.
	*/
	public void onWallCollision(){
		if (overlapX < overlapY) {
			vx = -vx;
		}
		else {
			vy = -vy;
		}
	}

	/**
		Método que verifica se houve colisão ou não do disparo com uma parede.

		@param wall referência para uma instância de Wall contra a qual será verificada a ocorrência de colisão da bola.
		@return um valor booleano que indica a ocorrência (true) ou não (false) de colisão.
	*/
	public boolean checkCollision(Wall wall){
		// Coordenadas da borda do shot
		double shotLeft = cx - size / 2;
		double shotRight = cx + size / 2;
		double shotTop = cy - size / 2;
		double shotBottom = cy + size / 2;

		// Coordenadas da borda da parede
		double wallLeft = wall.getCx() - wall.getWidth() / 2;
		double wallRight = wall.getCx() + wall.getWidth() / 2;
		double wallTop = wall.getCy() - wall.getHeight() / 2;
		double wallBottom = wall.getCy() + wall.getHeight() / 2;

		// Diferença dos centros
		double dx = Math.abs(cx - wall.getCx());
		double dy = Math.abs(cy - wall.getCy());

		// Quanto se sobrepõem (necessário pra saber em que sentido o objeto será refletido)
		overlapX = ((size + wall.getWidth())/2) - dx;
		overlapY = ((size + wall.getHeight())/2) - dy;

		return Collision.checkCollision(this, wall);
	}

	/**
		Método que verifica se houve colisão do disparo algum jogador.

		@param player referência para uma instância de Player contra o qual será verificada a ocorrência de colisão do disparo.
		@return um valor booleano que indica a ocorrência (true) ou não (false) de colisão.
	*/	
	public boolean checkCollision(Player player){
		if (player != owner) return Collision.checkCollision(this, player);
		return false;
	}

	/**
	 * Esse método diz se o disparo está ativo ainda ou não. Um disparo ativo ainda interage com os
	 * elementos do jogo.
	 */
	public boolean isActive() {
		return active;
	}

	/**
		Método que devolve a coordenada x do centro do quadrado que representa o disparo.
		@return o valor double da coordenada x.
	*/
	public double getCx(){
		return cx;
	}

	/**
		Método que devolve a coordenada y do centro do quadrado que representa o disparo.
		@return o valor double da coordenada y.
	*/
	public double getCy(){
		return cy;
	}

	/**
		Método que devolve a velocidade do disparo.
		@return o valor double da velocidade.

	*/
	public double getSpeed(){
		return speed;
	}

	/** Obtém a largura do retângulo que representa o disparo. */
	public double getWidth() {
		return size;
	}

	/** Obtém a altura do retângulo que representa o disparo. */
	public double getHeight() {
		return size;
	}

	/**
	 * Obtém o dono desse disparo.
	 * @return o player que efetuou o disparo
	 */
	public Player getOwner() {
		return owner;
	}
}
>>>>>>> e2d5835bdad8b1553746756573fd4c2e17c9d8fb:Shot.java
