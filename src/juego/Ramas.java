package juego;

import java.awt.Image;
import java.util.Random;

import entorno.Entorno;
import entorno.Herramientas;

public class Ramas {
	private int x;
	private int y;
	private int alto;
	private int ancho;
	
	Image rama;

	
	
	public Ramas(int x, int y, int alto, int ancho) {
		this.y = y;
		this.x = x;
		this.alto = alto;
		this.ancho = ancho;
		
		Random random = new Random();
		int ran = random.nextInt(0, 4);
		//para que las ramas tengan aleatoriedad de estar a la derecha, izquierda, o en ambas posiciones
		if (ran == 0) {
			rama = Herramientas.cargarImagen("ramas.png");
		}else if (ran == 1) {
			rama = Herramientas.cargarImagen("ramaIzq.png");
			this.ancho = ancho/2;
			this.x +=70;
		}else if (ran == 2) {
			rama = Herramientas.cargarImagen("ramaDer.png");
			this.ancho = ancho/2;
			this.x -=70;
		}else {
			rama = Herramientas.cargarImagen("salta5.png");
			this.y += 3333;
			
			}
	}
	public void dibujarse(Entorno e){
//		e.dibujarRectangulo(x, y, ancho, alto, 0, null); // HITBOX
		e.dibujarImagen(this.rama, x, y, 0, 1.5);
	}
	public void moverRama() {
		this.x -= 4;
	}
	public int getxRama() {
		return this.x;
	}
	public int getyRama() {
		return this.y;
	}
	public int getAltoRama() {
		return this.alto;
	}
	public int getAnchoRama() {
		return this.ancho;
	}
}
