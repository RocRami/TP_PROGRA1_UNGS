package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Monono {
	// estados que podriamos aplicar al mono
	boolean saltando;
	boolean saltoMaximo;
	boolean subiendo;
	boolean cayendo;
	boolean bajandoRama;
	
	boolean caminando;
	boolean disparando = false;
	// estados de colision del mono con el ambiente
	boolean tocandoSuelo;
	
	int limiteSalto;
	//animacion
	int velocidadCamina;
	int tickCamina;
	int arribaAnimacion;
	// coordenadas del mono
	int x;
	int y;
	int alto;
	int ancho;
	//mira del mono
	double anguloMira = 90;
	double angulo = 0;
	// imagenes del mono caminando
	Image camina1;
	Image camina2;
	Image camina3;
	Image camina4;
	Image camina5;
	// imagenes del mono saltando
	Image salta1;
	Image salta2;
	Image salta3;
	Image salta4;
	Image salta5;
	Image salta6;
	Image salta7;
	Image salta8;
	Image salta9;
	
	Image flechaMira;
	
	// clase para crear al mono
	public Monono(int x, int y, int alto, int ancho){
		this.limiteSalto = 295;
		this.arribaAnimacion = 0;
		this.tickCamina = 0;
		this.velocidadCamina = 12;
		this.x = x;
		this.y = y;
		this.alto = alto;
		this.ancho = ancho;

		camina1 = Herramientas.cargarImagen("camina1.png");
		camina2 = Herramientas.cargarImagen("camina2.png");
		camina3 = Herramientas.cargarImagen("camina3.png");
		camina4 = Herramientas.cargarImagen("camina4.png");
		camina5 = Herramientas.cargarImagen("camina5.png");
		
		salta1 = Herramientas.cargarImagen("salta1.png");
		salta2 = Herramientas.cargarImagen("salta2.png");
		salta3 = Herramientas.cargarImagen("salta3.png");
		salta4 = Herramientas.cargarImagen("salta4.png");
		salta5 = Herramientas.cargarImagen("salta5.png");
		salta6 = Herramientas.cargarImagen("salta6.png");
		salta7 = Herramientas.cargarImagen("salta7.png");
		salta8 = Herramientas.cargarImagen("salta8.png");
		salta9 = Herramientas.cargarImagen("salta9.png");
		
		flechaMira = Herramientas.cargarImagen("flechaMira.png");
	}

	// cosas que hace el mono 
	// cambia de estados
	public void actualizarEstado() {
		if (!this.saltando || this.tocandoSuelo) {
			this.caminando = true; 
		}
		if (this.saltando) {
			caminando = false; 
		}
		if (this.y >= 480) {
			this.tocandoSuelo = true;
			this.bajandoRama = false;
			this.saltando = false;
		}else {
			this.tocandoSuelo = false;
		}
		if (this.y == this.limiteSalto) {
			this.saltoMaximo = true;
		}else {
			this.saltoMaximo = false;
		}
	}
	// se dibuja
	public void dibujarse(Entorno e){
//		e.dibujarRectangulo(x, y, ancho, alto, 0, null); //HITBOX
		// dibuja la flecha de la mira
		e.dibujarImagen(flechaMira, x, y, Herramientas.radianes(anguloMira), 0.3);
		if (this.caminando) {
			if (this.tickCamina <= velocidadCamina) {
				e.dibujarImagen(this.camina1, this.x, this.y, 0, 2.5);
				this.tickCamina ++;
			}else if (this.tickCamina <= velocidadCamina * 2 ) {
				e.dibujarImagen(this.camina2, this.x, this.y, 0, 2.5);
				this.tickCamina ++;
			}else if (this.tickCamina <= velocidadCamina * 3) {
				e.dibujarImagen(this.camina3, this.x, this.y, 0, 2.5);
				this.tickCamina ++;
			}else if (this.tickCamina <= velocidadCamina * 4) {
				e.dibujarImagen(this.camina4, this.x, this.y, 0, 2.5);
				this.tickCamina ++;
			}else if (this.tickCamina <= velocidadCamina * 5) {
				e.dibujarImagen(this.camina5, this.x, this.y, 0, 2.5);
				this.tickCamina ++;
				if (this.tickCamina == velocidadCamina * 5) {
					this.tickCamina = 0;
				}
			}
			
		}
		if(this.saltando) {
			if (this.arribaAnimacion <= 9) {
				e.dibujarImagen(this.salta1, this.x, this.y, 0, 2.5);
				this.arribaAnimacion ++;
			}else if (this.arribaAnimacion < 9*2 ) {
				e.dibujarImagen(this.salta2, this.x, this.y, 0, 2.5);
				this.arribaAnimacion ++;
			}else if (this.arribaAnimacion < 9*3 ) {
				e.dibujarImagen(this.salta3, this.x, this.y, 0, 2.5);
				this.arribaAnimacion ++;
			}else if (this.arribaAnimacion < 9*4 ) {
				e.dibujarImagen(this.salta4, this.x, this.y, 0, 2.5);
				this.arribaAnimacion ++;
			}else if (this.arribaAnimacion < 9*5 ) {
				e.dibujarImagen(this.salta5, this.x, this.y, 0, 2.5);
				this.arribaAnimacion ++;
			}else if (this.arribaAnimacion < 9*6 ) {
				e.dibujarImagen(this.salta6, this.x, this.y, 0, 2.5);
				this.arribaAnimacion ++;
			}else if (this.arribaAnimacion < 9*7 ) {
				e.dibujarImagen(this.salta7, this.x, this.y, 0, 2.5);
				this.arribaAnimacion ++;
			}else if (this.arribaAnimacion < 9*8 ) {
				e.dibujarImagen(this.salta8, this.x, this.y, 0, 2.5);
				this.arribaAnimacion ++;
			}else if (this.arribaAnimacion < 9*9 ) {
				e.dibujarImagen(this.salta9, this.x, this.y, 0, 2.5);
				this.arribaAnimacion ++;
			}else if(this.arribaAnimacion >= 9*9 ) {
				this.arribaAnimacion = 0;
			}
		}
		
	}
	// salta
	public void saltar(){
		
		if (this.saltando) {
			if (this.tocandoSuelo) {
				this.subiendo = true;
				this.cayendo = false;
			}
			if (this.saltoMaximo) {
				this.subiendo = false;
				this.cayendo = true;
			}
			//si sube disminuye la y
			if (this.subiendo) {
				this.y -= 5;
			}
			// si esta cayendo aumenta la y 
			if (this.cayendo) {
				this.y += 5;
			}
			if (this.bajandoRama) {
				this.cayendo = false;
				this.y += 5;
			}
			
		}
	}
	public int getxMonono() {
		return this.x;
	}
	public int getyMonono() {
		return this.y;
	}
	public int getAltoMonono() {
		return this.alto;
	}
	public int getAnchoMonono() {
		return this.ancho;
	}

}


	


