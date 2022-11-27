package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Arbol {
	int x;
	int y;
	double alto;
	Image arbolo;
	
	
	public Arbol(int x, int y) {
		arbolo = Herramientas.cargarImagen("arbolo.png");
		
		this.alto = arbolo.getHeight(null)* 1.5;
		this.y = y;
		this.x = x;
	}
	public void dibujarse(Entorno e){
		e.dibujarImagen(this.arbolo, this.x, this.y, 0, 1.5);
	}
	public void moverArbol() {
		this.x -= 4;
	}
	public int getxArbolo() {
		return this.x;
	}
	public int getyArbolo() {
		return this.y;
	}
	public double getAlto() {
		return this.alto;
	}
	// el alto - 120 es el arbol sin copa
	

}
