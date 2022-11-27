package juego;

import java.awt.Image;

import javax.sound.sampled.Clip;

import entorno.Entorno;
import entorno.Herramientas;


public class Piedra {
    private int x;
    private int y;
    private int alto;
    private int ancho;
    int anguloPiedra;
    int tiempoSubiendo = 0;
    public boolean volviendo;
    boolean subiendo;
    Image piedra;
    Clip tomarPiedra;
    Clip impactoPiedra;
    double bajada;
	

    public Piedra(int x, int y, int ancho, int alto) {
    	this.y = y;
    	this.x = x;
    	this.alto = alto;
    	this.ancho = ancho;
    	
    	
    	tomarPiedra = Herramientas.cargarSonido("tomarPiedra.wav");
    	impactoPiedra = Herramientas.cargarSonido("impactoPiedra.wav");
    	
    	piedra = Herramientas.cargarImagen("piedra.png");
    	
    	
    }

    public void dibujarse(Entorno e){
//    	e.dibujarRectangulo(x, y, ancho, alto, 0, null); // HITBOX
    	e.dibujarImagen(piedra, x, y, Herramientas.radianes(anguloPiedra), 0.8);
    }

    public void moverPiedra(double angulo) {
    	this.bajada = 2;
    	this.x += 10;
    	if (this.subiendo && !this.volviendo) {
    		this.tiempoSubiendo ++;
    		this.anguloPiedra += 10;
			this.y -= angulo;
			if (this.tiempoSubiendo > 20) {
				this.y += this.bajada ;
			}
			if (this.tiempoSubiendo > 20*2) {
				this.y += this.bajada;
			}
			if (this.tiempoSubiendo > 20*3) {
				this.y += this.bajada;
				
			}
			if (this.y >= 520) {
				this.volviendo = true;
			}
		} if (this.volviendo) {
			this.subiendo = false;
    		this.x -= 14;
    		if (this.y < 520) {
				this.y += 1;
			}
		}
    }
    //DEVUELVE X LEON 
    public int getxPiedra() {
    	return this.x;
    }

    //DEVUELVE Y LEON 
    public int getyPiedra() {
    	return this.y;
    }
    //DEVUELVE ALTO LEON 
    public int getAltoPiedra() {
    	return this.alto;
    }
    //DEVUELVE ANCHO LEON 
    public int getAnchoPiedra() {
    	return this.ancho;
    }







}