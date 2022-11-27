package juego;
import java.awt.Image;

import javax.sound.sampled.Clip;

import entorno.Entorno;
import entorno.Herramientas;

public class Aguila {
	private int x;
	private int y;
	private int alto;
	private int ancho;
	boolean bajando;
	boolean subiendo;
	int tickCamina;
	 
	Image aguila1;
	Image aguila2;
	Image aguila3;
	Image aguila4;
	Image aguila5;
	Image aguila6;
	Image aguila7;
	Image aguila8;
	Image aguila9;
	Image aguila10;
	
	Image alerta;
	
	Clip aguilaSound;




	public Aguila(int x, int y, int alto, int ancho) {
		this.y = y;
		this.x = x;
		this.alto = alto;
		this.ancho = ancho;
		
		aguila1 = Herramientas.cargarImagen("aguila1.png");
		aguila2 = Herramientas.cargarImagen("aguila2.png");
		aguila3 = Herramientas.cargarImagen("aguila3.png");
		aguila4 = Herramientas.cargarImagen("aguila4.png");
		aguila5 = Herramientas.cargarImagen("aguila5.png");
		aguila6 = Herramientas.cargarImagen("aguila6.png");
		aguila7 = Herramientas.cargarImagen("aguila7.png");
		aguila8 = Herramientas.cargarImagen("aguila8.png");
		aguila9 = Herramientas.cargarImagen("aguila9.png");
		aguila10 = Herramientas.cargarImagen("aguila10.png");
		
		alerta = Herramientas.cargarImagen("simboloAlerta.png");
		aguilaSound = Herramientas.cargarSonido("aguila.wav");

	}

	public void dibujarse(Entorno e){
//		e.dibujarRectangulo(x, y, ancho, alto, 0, null); //HITBOX
		//animacion
		if (this.tickCamina <= 9) {
			e.dibujarImagen(this.aguila1, this.x, this.y, 0, 2);
			this.tickCamina ++;
		}else if (this.tickCamina <= 9 * 2 ) {
			e.dibujarImagen(this.aguila2, this.x, this.y, 0, 2);
			this.tickCamina ++;
		}else if (this.tickCamina <= 9 * 3) {
			e.dibujarImagen(this.aguila3, this.x, this.y, 0, 2);
			this.tickCamina ++;
		}else if (this.tickCamina <= 9 * 4) {
			e.dibujarImagen(this.aguila4, this.x, this.y, 0, 2);
			this.tickCamina ++;
		}else if (this.tickCamina <= 9 * 5) {
			e.dibujarImagen(this.aguila5, this.x, this.y, 0, 2);
			this.tickCamina ++;
		}else if (this.tickCamina <= 9 * 6) {
			e.dibujarImagen(this.aguila6, this.x, this.y, 0, 2);
			this.tickCamina ++;
		}else if (this.tickCamina <= 9 * 7) {
			e.dibujarImagen(this.aguila7, this.x, this.y, 0, 2);
			this.tickCamina ++;
		}else if (this.tickCamina <= 9 * 8) {
			e.dibujarImagen(this.aguila8, this.x, this.y, 0, 2);
			this.tickCamina ++;
		}else if (this.tickCamina <= 9 * 9) {
			e.dibujarImagen(this.aguila9, this.x, this.y, 0, 2);
			this.tickCamina ++;
		}else if (this.tickCamina <= 9 * 10) {
			e.dibujarImagen(this.aguila10, this.x, this.y, 0, 2);
			this.tickCamina ++;
			if (this.tickCamina == 9 * 10) {
				this.tickCamina = 0;
			}
		}
	}
	// para avisarle al jugador que el aguila esta por llegar
	public void alertaAguila(Entorno e) {
		if (this.y < 0 && this.y > -500) {
			e.dibujarImagen(this.alerta, 750, 50, 0, 0.1);
			if (this.y > -10) {
				this.aguilaSound.start();
			}
		}
	}
	// mueve el aguila y la reposiciona al salir de la pantalla
	public void moverAguila(int xObjetivo, int yObjetivo, int xNueva, int yNueva){
		if (this.x != xObjetivo) {
			this.x -= 6;
		}
		if (this.y < yObjetivo + 50) {
			this.y += 4;
		}else if (this.y > yObjetivo + 50) {
			this.y -= 4 ;
		}
		if (this.x < -4000) {
			this.x = xNueva;
			this.y = yNueva;
		}
		
	}
	public int getxAguila() {
		return this.x;
	}
	public int getyAguila() {
		return this.y;
	}
	public int getAltoAguila() {
		return this.alto;
	}
	public int getAnchoAguila() {
		return this.ancho;
	}

}