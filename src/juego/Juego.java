package juego;

import java.awt.Color;
import java.awt.Image;
import java.util.Random;

import javax.sound.sampled.Clip;

import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego {
	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;


	// Variables y métodos propios de cada grupo\
	Image marcoPunto;
	Image marcoMetros;
	Image fin;
	private Clip sonido;
	private Fondo fondo;
	private Suelo suelo;
	private Monono monono;
	private Piedra piedra;
	private Arbol arbolo[];
	private Ramas ramas[];
	private Leones leones[];
	private Serpientes serpiente;
	private Aguila aguila;
	private Frutas fruta;
	private int metros;
	private int puntos;
	private int contLeones;
	private int contSerpientes;
	private int contAguilas;
	private int contFrutas;
	private int contRamas;
	private Random random = new Random();
	private boolean yaSumoPuntosRama = false;
	private boolean yaSono = false;
	private boolean leonTrampa;
	private boolean leonTrampa2;



	//	public boolean caminoLeon;
	// ...


	Juego() {
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "monono EL JUEGO 2", 800, 600);


		// Inicializar lo que haga falta para el juego
		// ...
		this.marcoPunto = Herramientas.cargarImagen("marco.png");
		this.marcoMetros = Herramientas.cargarImagen("marcoMetros.png");
		this.fin = Herramientas.cargarImagen("final.png");
		this.sonido = Herramientas.cargarSonido("musicaFondo.wav");
		this.puntos = 0;
		this.metros = 0;
		this.contLeones = 0;
		this.contSerpientes = 0;
		this.contAguilas = 0;
		this.contFrutas = 0;
		this.contRamas = 0;

		this.fondo= new Fondo(0, 170);
		this.suelo = new Suelo(0, 540);

		this.monono = new Monono(100, 480, 70, 70);
		this.piedra = new Piedra(100, 480, 20, 20);

		this.arbolo = new Arbol[6];
		int sumador = 300;
		for (int i = 0; i < this.arbolo.length; i++) {
			this.arbolo[i] = this.nuevoArbol(900+sumador , 1100+sumador);
			sumador += sumador;
		}
		this.ramas = new Ramas[6];
		for (int i = 0; i < this.ramas.length; i++) {
			this.ramas[i] = nuevaRama(this.arbolo[i].getxArbolo(), this.arbolo[i].getyArbolo());
		}
		this.fruta = new Frutas(this.ramas[1].getxRama(), this.ramas[1].getyRama() - 30, 30, 30);

		this.leones = new Leones [2];
		this.leones[0] = this.nuevoLeon(1500, 1800);
		this.leones[1] = this.nuevoLeon(2000, 2300);
		this.serpiente = new Serpientes(arbolo[2].getxArbolo(), 400, 70, 10);
		this.aguila = new Aguila(7800, -4600, 40, 40);




		//	
		// la x del suelo es 0 pq en suelo definimos la x
		// getWidth toma el largo de la imagen, al divirdirlo en 2 la imagen se muestra desde el primer pixel


		// Inicia el juego!
		this.entorno.iniciar();
	}
	/**
	 * Durante el juego, el método tick() será ejecutado en cada instante y por lo
	 * tanto es el método más importante de esta clase. Aquí se debe actualizar el
	 * estado interno del juego para simular el paso del tiempo (ver el enunciado
	 * del TP para mayor detalle).
	 */
	@Override
	public void tick() {
		if (this.monono != null) {
			this.metros ++;
			this.sonido.loop(999999);

			this.fondo.dibujarse(entorno);
			this.fondo.moverFondo();

			this.dibujarArboles();
			this.moverArboles();
			this.arbolSeFue();
			this.arbolRellenoSeFue();
			this.dibujarArbolosNuevos();

			this.dibujarRamas();
			this.moverRamas();
			this.ramasSeFue();
			this.ramasRellenoSeFue();
			this.dibujarRamasNuevas();

			this.dibujarLeones();
			this.moverLeones();
			this.leonEmbiste();
			this.separarLeones();
			this.leonSeFue();
			int arbolRandom = random.nextInt(0, this.arbolo.length);

			this.serpiente.moverSerpiente(300, this.arbolo[arbolRandom].getxArbolo());
			this.serpiente.dibujarse(entorno);


			this.fruta.moverFruta(this.ramas[arbolRandom].getxRama(), this.ramas[arbolRandom].getyRama());
			this.fruta.dibujarse(entorno);

			this.suelo.dibujarse(entorno);
			this.suelo.moverSuelo();

			this.aguila.dibujarse(entorno);
			this.aguila.alertaAguila(entorno);

			this.entorno.dibujarImagen(this.marcoPunto, 600, 50, 0, 0.8);
			this.entorno.dibujarImagen(this.marcoMetros, 200, 50, 0, 0.8);
			Color colorPuntos=new Color(120, 96, 19);

			this.entorno.cambiarFont("", 30, Color.black);
			this.entorno.escribirTexto(""+metros/10 , 212, 62);
			this.entorno.escribirTexto(""+puntos , 612, 62);
			this.entorno.cambiarFont("", 30, colorPuntos);
			this.entorno.escribirTexto(""+metros/10 , 210, 60);
			this.entorno.escribirTexto(""+puntos , 610, 60);

			this.monono.dibujarse(entorno);
			this.monono.saltar();
			this.monono.actualizarEstado();
			this.aguila.moverAguila(this.monono.getxMonono(), this.monono.getyMonono(), 7800, -4600);

			if(!this.monono.disparando) {
				reproducirSonido(this.piedra.tomarPiedra);
				entorno.dibujarImagen(this.piedra.piedra, this.monono.getxMonono()-10, this.monono.getyMonono()+20, 0 , 0.7);
			}

			if (this.monono.disparando) {


				this.piedra.dibujarse(entorno);
				this.piedra.moverPiedra(monono.angulo);

				if(colisionPiedraLeon(0)) {
					this.piedra.impactoPiedra.start();
					this.puntos += 3;
					this.contLeones ++;
					//para que haya un porcentaje de que un leon sea trampa y embista al jugador
					int leonTrampaRan = random.nextInt(0, 21);
					if(leonTrampaRan != 0) {
						//para que el leon desarezca y se cree en otra posicion fuera de pantalla
						this.leones[0] = null;
						this.leones[0] = nuevoLeon(1300, 1400);
						this.piedra.volviendo = true;
					}else {
						this.leonTrampa = true;
						this.piedra.volviendo = true;
					}
				}else if(this.colisionPiedraLeon(1)) {
					this.piedra.impactoPiedra.start();
					this.puntos += 3;
					this.contLeones ++;
					int leonTrampaRan2 = random.nextInt(0, 21);
					if(leonTrampaRan2 != 0) {
						this.leones[1] = null;
						this.leones[1] = nuevoLeon(1300, 1400);
						this.piedra.volviendo = true;
					}else{
						this.leonTrampa2 = true;
						this.piedra.volviendo = true;
					}
				}
				if(colisionPiedraSerpiente()) {
					this.piedra.impactoPiedra.start();
					this.puntos += 7;
					this.contSerpientes ++;
					this.serpiente = null;
					this.piedra.volviendo = true;
					for (int i = 0; i < this.arbolo.length; i++) {
						if (this.arbolo[i].getxArbolo() > 800) {
							this.serpiente = new Serpientes(this.arbolo[i].getxArbolo(), 300, 70, 10);
						}else {
							this.serpiente = new Serpientes(-50 , 300, 70, 10);
						}
					}
				}
				if(this.colisionPiedraAguila()) {
					this.piedra.impactoPiedra.start();
					this.puntos += 25;
					this.contAguilas ++;
					this.aguila = null;
					this.piedra.volviendo = true;
					this.aguila = new Aguila(7800, -4600, 50, 50);
				}
				// para saber si la piedra salio de la pantalla y que el jugar pueda volver a lanzarla
				if(this.piedra.getxPiedra() > 1400 || this.piedra.getxPiedra() < -400) {
					this.monono.disparando = false;
					this.yaSono = false;
				}

			}

			// para que la piedra no colisione al salir 
			if (this.colisionPiedraMonono() && this.piedra.volviendo) {
				this.monono.disparando = false;
				this.piedra.volviendo = false;
				this.yaSono = false;

			}

			if (colisionMononoRama()) {
				//para saber si ya sumo puntos al tocar la rama
				if (!this.yaSumoPuntosRama && this.monono.caminando) {
					this.puntos += 5;
					this.contRamas ++;
					this.yaSumoPuntosRama = true;
				}
				//para que detecte que esta parado en la ramas solo cuando esta cayendo
				if(this.monono.cayendo) {
					this.monono.tocandoSuelo = true;
					this.monono.saltando = false;
					//para aumentar el salto si esta sobre la ramas
					if (this.monono.limiteSalto > 0) {
						this.monono.limiteSalto = this.monono.getyMonono() - 175;
					}
				}
				//para volver al salto normal cuando baja de la plataforma
			}else if (!this.colisionMononoRama() && !this.monono.saltando){
				this.monono.limiteSalto = 295;
				this.yaSumoPuntosRama = false;
				//para que sume puntos al saltar de plataforma a plataforma
			}else if (this.monono.saltando) {
				this.yaSumoPuntosRama = false;
			}

			// para que el monon caiga al salir de la plataforma
			if (!this.monono.tocandoSuelo && !this.monono.saltando) {
				this.monono.saltando= true;
				this.monono.cayendo = true;
			}

			if (this.colisionMonoFruta()) {
				this.fruta.impactoFruta.start();
				this.puntos += 15;
				this.contFrutas ++;
				this.fruta = null;
				for (int i = 0; i < this.ramas.length; i++) {
					// para que dibuje la nueva fruta lejos del mono y no agarre muchas veces la misma
					if (this.ramas[i].getxRama() > 300) {
						this.fruta = new Frutas(this.ramas[i].getxRama(), this.ramas[i].getyRama() - 30, 30, 30);
					}
				}
			}

			//para saltar
			if (this.entorno.sePresiono(this.entorno.TECLA_ARRIBA)){
				this.monono.saltando = true;
			}
			// para bajar de las ramass sin que terminen
			if (this.entorno.sePresiono(this.entorno.TECLA_ABAJO)&& colisionMononoRama()) {
				this.monono.bajandoRama = true;
				this.monono.cayendo = false;
			}
			//para disparar
			if (this.entorno.sePresiono(this.entorno.TECLA_ESPACIO)){
				//para que solo dispare si no esta efectuando un disparo
				if (this.monono.disparando == false) {
					this.piedra = new Piedra(100, this.monono.getyMonono(), 20, 20);
				}
				this.piedra.subiendo = true;
				this.monono.disparando = true;
			}
			// para variar el angulo de la piedra
			if (this.entorno.estaPresionada(this.entorno.TECLA_IZQUIERDA)) {
				this.monono.angulo += 0.1;
				this.monono.anguloMira -= 0.5;
				if (this.monono.anguloMira <= 55) {
					this.monono.anguloMira = 55;
				}
				if (this.monono.angulo >= 7) {
					this.monono.angulo = 7;
				}
			}
			//para variar el angulo de la piedra
			if (this.entorno.estaPresionada(this.entorno.TECLA_DERECHA)) {
				this.monono.angulo -= 0.1;
				this.monono.anguloMira += 0.5;
				if (this.monono.anguloMira >= 90) {
					this.monono.anguloMira = 90;
				}
				if (this.monono.angulo <= 0) {
					this.monono.angulo = 0;
				}
			}
			// para que el mono deje de aparecer si toca un leon, una serpiente o un aguila
			if(this.colisionMonoAguila() || this.colisionMononoLeon() || this.colisionMonoSerpiente() ) {
				this.monono = null;
				this.sonido.stop();
			}
			// si el leon muere
		}else {
			// dibuja la pantalla final con todas las estadisticas
			this.entorno.dibujarImagen(this.fin, 425, 250, 0, 1);

			Color colorPuntos=new Color(120, 96, 19);

			this.entorno.cambiarFont("", 40, Color.BLACK);
			this.entorno.escribirTexto(this.metros/10+ "", 402, 122);
			this.entorno.escribirTexto(""+this.puntos , 402, 482);


			this.entorno.cambiarFont("", 40, colorPuntos);
			this.entorno.escribirTexto(""+this.metros/10 , 400, 120);
			this.entorno.escribirTexto(""+this.puntos , 400, 480);

			this.entorno.cambiarFont("", 25, Color.BLACK);
			this.entorno.escribirTexto(""+contLeones , 382, 202);
			this.entorno.escribirTexto(""+contSerpientes , 432, 229);
			this.entorno.escribirTexto(""+contAguilas , 392, 256);
			this.entorno.escribirTexto(""+contRamas , 372, 357);
			this.entorno.escribirTexto(""+contFrutas , 382, 382);

			this.entorno.cambiarFont("", 25, colorPuntos);
			this.entorno.escribirTexto(""+contLeones , 380, 200);
			this.entorno.escribirTexto(""+contSerpientes , 430, 227);
			this.entorno.escribirTexto(""+contAguilas , 390, 254);
			this.entorno.escribirTexto(""+contRamas , 370, 355);
			this.entorno.escribirTexto(""+contFrutas , 380, 380);



			//para reiniciar el juego
			if (this.entorno.sePresiono(this.entorno.TECLA_ENTER)) {
				this.puntos = 0;
				this.metros = 0;
				this.contLeones = 0;
				this.contSerpientes = 0;
				this.contAguilas = 0;
				this.contFrutas = 0;
				this.contRamas = 0;
				this.monono = new Monono(100, 480, 70, 70);
				this.piedra = new Piedra(100, 480, 20, 20);
				this.arbolo = new Arbol[6];
				int sumador = 300;
				for (int i = 0; i < this.arbolo.length; i++) {
					this.arbolo[i] = this.nuevoArbol(900+sumador , 1100+sumador);
					sumador += sumador;
				}
				this.ramas = new Ramas[6];
				for (int i = 0; i < this.ramas.length; i++) {
					this.ramas[i] = this.nuevaRama(this.arbolo[i].getxArbolo(), this.arbolo[i].getyArbolo());
				}
				this.fruta = new Frutas(ramas[1].getxRama(), ramas[1].getyRama() - 30, 30, 30);
				this.leones = new Leones [2];
				this.leones[0] = this.nuevoLeon(1000, 1300);
				this.leones[1] = this.nuevoLeon(1500, 1800);
				this.serpiente = new Serpientes(this.arbolo[0].getxArbolo(), 400, 70, 10);
				this.aguila = new Aguila(7800, -4600, 40, 40);
				this.leonTrampa = false;
				this.leonTrampa2 = false;
			}
			//para cerrar la ventana del juego
			if (this.entorno.sePresiono(this.entorno.TECLA_FIN)) {
				System.exit(0);
			}
		}

	}

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Juego juego = new Juego();
	}


	// para crear los arboles de forma aleatoria
	private Arbol nuevoArbol(int randomInicio, int randomFinal) {
		int x = random.nextInt(randomInicio , randomFinal);
		int y = random.nextInt(295 , 500);
		return new Arbol(x ,y);
	}
	// para mostrarlos en pantalla
	private void dibujarArboles() {
		for(int i = 0; i < this.arbolo.length ; i++){
			this.arbolo[i].dibujarse(this.entorno);
		}
	}
	//para que se muevan
	private void moverArboles(){
		for(int i = 0; i < this.arbolo.length ; i++){
			this.arbolo[i].moverArbol();
		}
	}
	// para saber cuando se va el primero de la pantalla
	public boolean arbolSeFue() {
		int xarbol;
		xarbol = this.arbolo[0].getxArbolo();
		return (xarbol > -65 && xarbol < -60 );
	}
	// para saber cuando se va el tercero de la pantalla
	public boolean arbolRellenoSeFue() {
		int xarbol;
		xarbol = this.arbolo[3].getxArbolo();
		return (xarbol > -65 && xarbol < -60 ); 
	}
	// para crear nuevos arboles cuando unos se vayan
	public void dibujarArbolosNuevos() {
		if (arbolRellenoSeFue()) {
			this.arbolo[2] = nuevoArbol(1800, 2000);
			this.arbolo[1] = nuevoArbol(1400, 1600);
			this.arbolo[0] = nuevoArbol(1100, 1101);
		}
		if (arbolSeFue()){
			this.arbolo[5] = nuevoArbol(1800, 2000);
			this.arbolo[4] = nuevoArbol(1400, 1600);
			this.arbolo[3] = nuevoArbol(1100, 1101);
		}
	}

	/////////////////////////////////////////////////////////////
	// mismo que nuevoArbol
	private Leones nuevoLeon(int randomInicio, int randomFinal) {
		int x = random.nextInt(randomInicio , randomFinal);
		int y = 485;
		int alto = 50;
		int ancho = 70;
		return new Leones(x ,y , alto , ancho);
	}
	//mismo que dibujarArbol
	private void dibujarLeones() {
		for(int i = 0; i < this.leones.length ; i++){
			this.leones[i].dibujarse(this.entorno);
		}
	}
	//mismo ""
	private void moverLeones(){
		for(int i = 0; i < this.leones.length ; i++){
			this.leones[i].moverLeon();
		}
	}
	// para aumentar la velocidad del leon "trampa" golpeado por una piedra
	public void leonEmbiste() {
		if (this.leonTrampa) {
			this.leones[0].setxLeon(3);
		}
		if (this.leonTrampa2) {
			this.leones[1].setxLeon(3);
		}
	}
	//mismo "" y desactiva el leon trampa al irse de la pantalla
	public void leonSeFue() {
		int xleon;
		for(int i = 0; i < this.leones.length ; i++) {
			xleon = this.leones[i].getxLeon();
			if (xleon > -80 && xleon < -60 ) {
				this.leonTrampa = false;
				this.leonTrampa2 = false;
				this.leones[i] = nuevoLeon(1200, 1201);
			}
		}
	}
	// para evitar que los leones se sobrepongan
	public void separarLeones() {
		int xleon;
		int xsiguienteLeon;
		xleon = this.leones[0].getxLeon();
		xsiguienteLeon = this.leones[1].getxLeon();
		if (xleon + 300 >= xsiguienteLeon && xsiguienteLeon + 300 > xleon) {
			this.leones[0] = nuevoLeon(1500, 1600);
		}

	}
	/////////////////////////////////////////////////////////


	private void dibujarRamas() {
		for(int i = 0; i < this.ramas.length ; i++){
			this.ramas[i].dibujarse(this.entorno);
		}
	}
	private void moverRamas(){
		for(int i = 0; i < this.ramas.length ; i++){
			this.ramas[i].moverRama();
		}
	}
	private Ramas nuevaRama(int xarbol, int yarbol) {
		int yrandom = random.nextInt(yarbol -120, 450);
		int alto = 4;
		int ancho = 100;
		return new Ramas(xarbol, yrandom, alto, ancho );
	}
	public boolean ramasSeFue() {
		int xramas;
		xramas = this.ramas[0].getxRama();
		return (xramas > -135 && xramas < -130 );
	}
	public boolean ramasRellenoSeFue() {
		int xramas;
		xramas = this.ramas[3].getxRama();
		return (xramas > -135 && xramas < -130 );
	}

	public void dibujarRamasNuevas() {
		if (ramasRellenoSeFue()) {
			this.ramas[2] = nuevaRama(this.arbolo[2].getxArbolo(), this.arbolo[2].getyArbolo());
			this.ramas[1] = nuevaRama(this.arbolo[1].getxArbolo(), this.arbolo[1].getyArbolo());
			this.ramas[0] = nuevaRama(this.arbolo[0].getxArbolo(), this.arbolo[0].getyArbolo());
		}
		if (ramasSeFue()){
			this.ramas[5] = nuevaRama(this.arbolo[5].getxArbolo(), this.arbolo[5].getyArbolo());
			this.ramas[4] = nuevaRama(this.arbolo[4].getxArbolo(), this.arbolo[4].getyArbolo());
			this.ramas[3] = nuevaRama(this.arbolo[3].getxArbolo(), this.arbolo[3].getyArbolo());
		}
	}

	////////////////////////////////////////////////////////////
	//COLISIONES//

	public boolean colisionMononoRama() {
		for (int i = 0; i < this.ramas.length; i++) {

			int xMonono =  this.monono.getxMonono();
			int yMonono = this.monono.getyMonono();
			int anchoMonono = this.monono.getAnchoMonono();
			int altoMonono = this.monono.getAltoMonono();
			int xRama = this.ramas[i].getxRama();
			int yRama = this.ramas[i].getyRama();
			int anchoRama = this.ramas[i].getAnchoRama();
			int altoRama = this.ramas[i].getAltoRama();

			if(xMonono + anchoMonono - 40 /2 >= xRama - anchoRama/2 &&
					xMonono - anchoMonono - 40 /2 <= xRama + anchoRama/2 && 
					yMonono + altoMonono - 40 /2 >= yRama - altoRama/2 && 
					yMonono + altoMonono - 40 /2 <= yRama + altoRama/2
					) {
				return true;
			}
		}
		return false;
	}

	public boolean colisionMonoFruta() {
		int xMonono = this.monono.getxMonono();
		int yMonono = this.monono.getyMonono();
		int anchoMonono = this.monono.getAnchoMonono();
		int altoMonono = this.monono.getAltoMonono();
		int xFruta = this.fruta.getxFruta();
		int yFruta = this.fruta.getyFruta();
		int anchoFruta = this.fruta.getAnchoFruta();
		int altoFruta = this.fruta.getAltoFruta();

		return(xMonono + anchoMonono/2 > xFruta - anchoFruta/2 &&
				xMonono - anchoMonono/2 < xFruta + anchoFruta/2 && 
				yMonono + altoMonono/2 > yFruta - altoFruta/2 && 
				yMonono - altoMonono/2 < yFruta + altoFruta/2);
	}

	public boolean colisionMononoLeon() {
		for (int i = 0; i < leones.length; i++) {

			int xMonono = this.monono.getxMonono();
			int yMonono = this.monono.getyMonono();
			int anchoMonono = this.monono.getAnchoMonono();
			int altoMonono = this.monono.getAltoMonono();
			int xLeon = this.leones[i].getxLeon();
			int yLeon = this.leones[i].getyLeon();
			int anchoLeon = this.leones[i].getAnchoLeon();
			int altoLeon = this.leones[i].getAltoLeon();

			if(xMonono + anchoMonono/2 > xLeon - anchoLeon/2 &&
					xMonono - anchoMonono/2 < xLeon + anchoLeon/2 && 
					yMonono + altoMonono/2 > yLeon - altoLeon/2 && 
					yMonono - altoMonono/2 < yLeon + altoLeon/2
					) {
				return true;
			}
		}
		return false;
	}

	public boolean colisionMonoSerpiente() {
		int xMonono = this.monono.getxMonono();
		int yMonono = this.monono.getyMonono();
		int anchoMonono = this.monono.getAnchoMonono();
		int altoMonono = this.monono.getAltoMonono();
		int xSerpiente = this.serpiente.getxSerpiente();
		int ySerpiente = this.serpiente.getySerpiente();
		int anchoSerpiente = this.serpiente.getAnchoSerpiente();
		int altoSerpiente = this.serpiente.getAltoSerpiente();

		return(xMonono + anchoMonono/2 > xSerpiente - anchoSerpiente/2 &&
				xMonono - anchoMonono/2 < xSerpiente + anchoSerpiente/2 && 
				yMonono + altoMonono/2 > ySerpiente - altoSerpiente/2 && 
				yMonono - altoMonono/2 < ySerpiente + altoSerpiente/2);
	}

	public boolean colisionMonoAguila() {
		int xMonono = this.monono.getxMonono();
		int yMonono = this.monono.getyMonono();
		int anchoMonono = this.monono.getAnchoMonono();
		int altoMonono = this.monono.getAltoMonono();
		int xAguila = this.aguila.getxAguila();
		int yAguila = this.aguila.getyAguila();
		int anchoAguila = this.aguila.getAnchoAguila();
		int altoAguila = this.aguila.getAltoAguila();

		return(xMonono + anchoMonono/2 > xAguila - anchoAguila/2 &&
				xMonono - anchoMonono/2 < xAguila + anchoAguila/2 && 
				yMonono + altoMonono/2 > yAguila - altoAguila/2 && 
				yMonono - altoMonono/2 < yAguila + altoAguila/2);
	}

	public boolean colisionPiedraLeon(int numeroLeon) {

		int xPiedra = this.piedra.getxPiedra();
		int yPiedra = this.piedra.getyPiedra();
		int anchoPiedra = this.piedra.getAnchoPiedra();
		int altoPiedra = this.piedra.getAltoPiedra();
		int xLeon = this.leones[numeroLeon].getxLeon();
		int yLeon = this.leones[numeroLeon].getyLeon();
		int anchoLeon = this.leones[numeroLeon].getAnchoLeon();
		int altoLeon = this.leones[numeroLeon].getAltoLeon();

		if (!this.piedra.volviendo) {
			return (xPiedra + anchoPiedra/2 + 50 > xLeon - anchoLeon/2 &&
					xPiedra - anchoPiedra/2 + 50 < xLeon + anchoLeon/2 && 
					yPiedra + altoPiedra/2 > yLeon - altoLeon/2 && 
					yPiedra - altoPiedra/2 < yLeon + altoLeon/2);
		}
		return false;
	}

	public boolean colisionPiedraSerpiente() {
		int xPiedra = this.piedra.getxPiedra();
		int yPiedra = this.piedra.getyPiedra();
		int anchoPiedra = this.piedra.getAnchoPiedra();
		int altoPiedra = this.piedra.getAltoPiedra();
		int xSerpiente = this.serpiente.getxSerpiente();
		int ySerpiente = this.serpiente.getySerpiente();
		int anchoSerpiente = this.serpiente.getAnchoSerpiente();
		int altoSerpiente = this.serpiente.getAltoSerpiente();

		return(xPiedra + anchoPiedra/2 > xSerpiente - anchoSerpiente/2 &&
				xPiedra - anchoPiedra/2 < xSerpiente + anchoSerpiente/2 && 
				yPiedra + altoPiedra/2 > ySerpiente - altoSerpiente/2 && 
				yPiedra - altoPiedra/2 < ySerpiente + altoSerpiente/2);
	}

	public boolean colisionPiedraAguila() {
		int xPiedra = this.piedra.getxPiedra();
		int yPiedra = this.piedra.getyPiedra();
		int anchoPiedra = this.piedra.getAnchoPiedra();
		int altoPiedra = this.piedra.getAltoPiedra();
		int xAguila = this.aguila.getxAguila();
		int yAguila = this.aguila.getyAguila();
		int anchoAguila = this.aguila.getAnchoAguila();
		int altoAguila = this.aguila.getAltoAguila();

		return(xPiedra + anchoPiedra/2 > xAguila - anchoAguila/2 &&
				xPiedra - anchoPiedra/2 < xAguila + anchoAguila/2 && 
				yPiedra + altoPiedra/2 > yAguila - altoAguila/2 && 
				yPiedra - altoPiedra/2 < yAguila + altoAguila/2);
	}

	public boolean colisionPiedraMonono() {
		int xMonono = this.monono.getxMonono();
		int yMonono = this.monono.getyMonono();
		int anchoMonono = this.monono.getAnchoMonono();
		int altoMonono = this.monono.getAltoMonono();
		int xPiedra = this.piedra.getxPiedra();
		int yPiedra = this.piedra.getyPiedra();
		int anchoPiedra = this.piedra.getAnchoPiedra();
		int altoPiedra = this.piedra.getAltoPiedra();

		return(xMonono + anchoMonono/2 > xPiedra - anchoPiedra/2 &&
				xMonono - anchoMonono/2 < xPiedra + anchoPiedra/2 && 
				yMonono + altoMonono/2 > yPiedra - altoPiedra && 
				yMonono - altoMonono/2 < yPiedra + altoPiedra);
	}
	// para reprodicir sonido solo una vez dentro del tick
	public void reproducirSonido(Clip sonido) {
		if(!this.yaSono) {
			sonido.start();
			this.yaSono = true;
		}
	}
}
