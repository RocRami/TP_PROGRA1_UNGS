package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;


public class Suelo 
{
	// Variables de instancia
	double x;
	double y;
	
	Image suelo;

	public Suelo(int x, int y){
		
		suelo = Herramientas.cargarImagen("suelazo.png");
		this.y = y;
		this.x = suelo.getWidth(null)/2;
		
	}
	public void dibujarse(Entorno e){
		e.dibujarImagen(this.suelo, this.x, this.y + 60, 0, 1);
	}
	public void moverSuelo() {
		this.x -= 4;
		if(this.x == 0) {
			this.x = suelo.getWidth(null)/2;
		}

	}

}