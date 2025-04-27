import java.awt.*;

/**
 * Esta classe representa as paredes do jogo que delimitam a quadra e são obstáculos no mapa.
 */
public class Wall implements ISolid {
	private double cx, cy, width, height;
	private Color color;
	private String id;

	/** Construtor da classe Wall. */
	public Wall(double cx, double cy, double width, double height, Color color, String id){
		this.cx = cx;
		this.cy = cy;
		this.width = width;
		this.height = height;
		this.color = color;
		this.id = id;
	}

	/** Chamado sempre que uma parede precisa ser redesenhada. */
	public void draw(){

		GameLib.setColor(color);
		GameLib.fillRect(cx, cy, width, height);
	}

	/** Obtém a largura do retângulo que representa a parede. */
	public double getWidth() { 

		return 0; 
	}

	/** Obtém a altura do retângulo que representa a parede. */
	public double getHeight() { 

		return 0;
	}

	/** Obtém a coordenada x do centro do retângulo da parede. */
	public double getCx() { 
		
		return 0;
	}

	/** Obtém a coordenada y do centro do retângulo da parede. */
	public double getCy() { 
	
		return 0;
	}

	/** Obtém o nome identificador dessa parede. */
	public String getId(){
		return null;
	}
}

