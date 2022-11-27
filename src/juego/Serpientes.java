package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Serpientes {
	private int x;
	private int y;
	private int alto;
	private int ancho;
	private boolean bajando;
	private boolean subiendo;
	
	Image serpiente1;
	Image serpiente2;
	Image serpiente3;
	Image serpiente4;
	Image serpiente5;
	Image serpiente6;

	//te dejo el tick camina si queres darle movimiento a la serpienta asi no se queda estatica
	private int tickCamina;


	public Serpientes(int x, int y, int alto, int ancho) {
		this.y = y;
		this.x = x;
		this.alto = alto;
		this.ancho = ancho;

		serpiente1 = Herramientas.cargarImagen("serpiente1.png");
		serpiente2 = Herramientas.cargarImagen("serpiente2.png");
		serpiente3 = Herramientas.cargarImagen("serpiente3.png");
		serpiente4 = Herramientas.cargarImagen("serpiente4.png");
		serpiente5 = Herramientas.cargarImagen("serpiente5.png");
		serpiente6 = Herramientas.cargarImagen("serpiente6.png");
	}

	public void dibujarse(Entorno e){
//		e.dibujarRectangulo(x, y, ancho, alto, 0, null); // HITBOX
		if (this.tickCamina <= 9) {
			e.dibujarImagen(this.serpiente1, this.x, this.y, 0, 2);
			this.tickCamina ++;
		}else if (this.tickCamina <= 9 * 2 ) {
			e.dibujarImagen(this.serpiente2, this.x, this.y, 0, 2);
			this.tickCamina ++;
		}else if (this.tickCamina <= 9 * 3) {
			e.dibujarImagen(this.serpiente3, this.x, this.y, 0, 2);
			this.tickCamina ++;
		}else if (this.tickCamina <= 9 * 4) {
			e.dibujarImagen(this.serpiente4, this.x, this.y, 0, 2);
			this.tickCamina ++;
		}else if (this.tickCamina <= 9 * 5) {
			e.dibujarImagen(this.serpiente5, this.x, this.y, 0, 2);
			this.tickCamina ++;
		}else if (this.tickCamina <= 9 * 6) {
			e.dibujarImagen(this.serpiente6, this.x, this.y, 0, 2);
			this.tickCamina ++;
			if (this.tickCamina == 9 * 6) {
				this.tickCamina = 0;
			}
		}
	}
	public void moverSerpiente(int maximo, int xArbol){
		this.x -= 4;
		// para que se mueva de arriba a abajo
		if(this.y == 400) {
			this.subiendo = true;
			this.bajando = false;
		}
		if(this.y == maximo) {
			this.subiendo = false;
			this.bajando = true;

		}
		if(this.subiendo) {
			this.bajando = false;
			this.y -= 1;
		}
		if(this.bajando) {
			this.subiendo = false;
			this.y += 1;
		}
		// para reposicionar cuando sale de la pantalla
		if (this.x < -60) {
			if (xArbol > 850) {
				this.x = xArbol;
			}
		}
	}
	public int getxSerpiente() {
		return this.x;
	}
	public int getySerpiente() {
		return this.y;
	}
	public int getAltoSerpiente() {
		return this.alto;
	}
	public int getAnchoSerpiente() {
		return this.ancho;
	}
}