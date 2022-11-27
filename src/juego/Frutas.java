package juego;

import java.awt.Image;

import javax.sound.sampled.Clip;

import entorno.Entorno;
import entorno.Herramientas;

public class Frutas {
	private int x;
	private int y;
	private int alto;
	private int ancho;
	boolean apareciendo = true;
	int tickCamina;

	Image bananaAparece1;
	Image bananaAparece2;
	Image bananaAparece3;
	Image bananaAparece4;
	Image bananaAparece5;
	Image banana1;
	Image banana2;
	Image banana3;
	Image banana4;
	Image banana5;
	Image banana6;
	Image banana7;
	Image banana8;

	Clip impactoFruta;


	public Frutas(int x, int y, int alto, int ancho) {
		this.y = y;
		this.x = x;
		this.alto = alto;
		this.ancho = ancho;
		tickCamina = 0;

		bananaAparece1 = Herramientas.cargarImagen("bananaaparece1.png");
		bananaAparece2 = Herramientas.cargarImagen("bananaaparece2.png");
		bananaAparece3 = Herramientas.cargarImagen("bananaaparece3.png");
		bananaAparece4 = Herramientas.cargarImagen("bananaaparece4.png");
		bananaAparece5 = Herramientas.cargarImagen("bananaaparece5.png");
		banana1 = Herramientas.cargarImagen("banana1.png");
		banana2 = Herramientas.cargarImagen("banana2.png");
		banana3 = Herramientas.cargarImagen("banana3.png");
		banana4 = Herramientas.cargarImagen("banana4.png");
		banana5 = Herramientas.cargarImagen("banana5.png");
		banana6 = Herramientas.cargarImagen("banana6.png");
		banana7 = Herramientas.cargarImagen("banana7.png");
		banana8 = Herramientas.cargarImagen("banana8.png");

		impactoFruta = Herramientas.cargarSonido("tomarFruta.wav");

	}

	public void dibujarse(Entorno e){
		//		e.dibujarRectangulo(x, y, ancho, alto, 0, null); //HITBOX
		if (this.apareciendo) {
			if (this.tickCamina <= 10) {
				e.dibujarImagen(this.bananaAparece5, this.x, this.y, 0, 1);
				this.tickCamina ++;
			}else if (this.tickCamina <= 15 * 2 ) {
				e.dibujarImagen(this.bananaAparece4, this.x, this.y, 0, 1);
				this.tickCamina ++;
			}else if (this.tickCamina <= 15 * 3 ) {
				e.dibujarImagen(this.bananaAparece3, this.x, this.y, 0, 1);
				this.tickCamina ++;
			}else if (this.tickCamina <= 15 * 4 ) {
				e.dibujarImagen(this.bananaAparece2, this.x, this.y, 0, 1);
				this.tickCamina ++;
			}else if (this.tickCamina <= 15 * 5 ) {
				e.dibujarImagen(this.bananaAparece1, this.x, this.y, 0, 1);
				this.tickCamina ++;
				this.tickCamina = 0;
				this.apareciendo = false;
			}
		}
		else {
			if (this.tickCamina <= 10 ) {
				e.dibujarImagen(this.banana1, this.x, this.y, 0, 1);
				this.tickCamina ++;
			}else if (this.tickCamina <= 10 * 2 ) {
				e.dibujarImagen(this.banana2, this.x, this.y, 0, 1);
				this.tickCamina ++;
			}else if (this.tickCamina <= 10 * 3) {
				e.dibujarImagen(this.banana3, this.x, this.y, 0, 1);
				this.tickCamina ++;
			}else if (this.tickCamina <= 10 * 4) {
				e.dibujarImagen(this.banana4, this.x, this.y, 0, 1);
				this.tickCamina ++;
			}else if (this.tickCamina <= 10 * 5) {
				e.dibujarImagen(this.banana5, this.x, this.y, 0, 1);
				this.tickCamina ++;
			}else if (this.tickCamina <= 10 * 6) {
				e.dibujarImagen(this.banana6, this.x, this.y, 0, 1);
				this.tickCamina ++;
			}else if (this.tickCamina <= 10 * 7) {
				e.dibujarImagen(this.banana7, this.x, this.y, 0, 1);
				this.tickCamina ++;
			}else if (this.tickCamina <= 10 * 8) {
				e.dibujarImagen(this.banana8, this.x, this.y, 0, 1);
				this.tickCamina ++;
			}
			if(this.tickCamina >= 10 * 8) {
				this.tickCamina = 0;
			}
		}

	}
	public void moverFruta(int xRama, int yRama) {
		this.x -= 4;
		//para reposicionar la fruta si sale de la pantalla
		if (this.x < -60) {
			// para que la fruta no aparezca cerca del mono
			if (xRama > 300) {
				this.apareciendo = true;
				this.x = xRama;
				this.y = yRama - 30;
			}
		}
	}
	public int getxFruta() {
		return this.x;
	}
	public int getyFruta() {
		return this.y;
	}
	public int getAltoFruta() {
		return this.alto;
	}
	public int getAnchoFruta() {
		return this.ancho;
	}

}

