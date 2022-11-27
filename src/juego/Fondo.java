package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;


public class Fondo 
{
	// Variables de instancia
	private double x;
	private double y;
	
	Image fondo;

	public Fondo(int x, int y){
		
		fondo = Herramientas.cargarImagen("fondo2.png");
		this.y = y;
		this.x = fondo.getWidth(null)/2;
		
	}
	public void dibujarse(Entorno e){
		e.dibujarImagen(this.fondo, this.x, this.y+60, 0, 1);
	}
	public void moverFondo() {
		this.x -= 3;
		//para dar la sensacion de que el fondo nunca termina
		if(this.x < 1) {
			this.x = fondo.getWidth(null)/4;
		}

	}

}