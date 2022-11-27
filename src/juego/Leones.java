package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Leones {
	private int x;
	private int y;
	private int alto;
	private int ancho;
	private int tickCamina;

	Image camina1;
	Image camina2;
	Image camina3;
	Image camina4;
	Image camina5;


	public Leones(int x, int y, int alto, int ancho) {
		this.y = y;
		this.x = x;
		this.alto = alto;
		this.ancho = ancho;
		tickCamina = 0;

		camina1 = Herramientas.cargarImagen("leoncamina1.png");
		camina2 = Herramientas.cargarImagen("leoncamina2.png");
		camina3 = Herramientas.cargarImagen("leoncamina3.png");
		camina4 = Herramientas.cargarImagen("leoncamina4.png");
		camina5 = Herramientas.cargarImagen("leoncamina5.png");
	}
	public void dibujarse(Entorno e){
//		e.dibujarRectangulo(x, y, ancho, alto, 0, null); // HITBOX
		if (this.tickCamina <= 9) {
			e.dibujarImagen(this.camina1, this.x, this.y, 0, 5);
			this.tickCamina ++;
		}else if (this.tickCamina <= 9 * 2 ) {
			e.dibujarImagen(this.camina2, this.x, this.y, 0, 5);
			this.tickCamina ++;
		}else if (this.tickCamina <= 9 * 3) {
			e.dibujarImagen(this.camina3, this.x, this.y, 0, 5);
			this.tickCamina ++;
		}else if (this.tickCamina <= 9 * 4) {
			e.dibujarImagen(this.camina4, this.x, this.y, 0, 5);
			this.tickCamina ++;
		}else if (this.tickCamina <= 9 * 5) {
			e.dibujarImagen(this.camina5, this.x, this.y, 0, 5);
			this.tickCamina ++;
			if (this.tickCamina == 9 * 5) {
				this.tickCamina = 0;
			}
		}

	}
	public void moverLeon() {
		this.x -= 5;
	}
	public int getxLeon() {
		return this.x;
	}
	public int getyLeon() {
		return this.y;
	}
	public int getAltoLeon() {
		return this.alto;
	}
	public int getAnchoLeon() {
		return this.ancho;
	}
	public void setxLeon(int xNueva) {
		this.x = this.x - xNueva;
	}
}
