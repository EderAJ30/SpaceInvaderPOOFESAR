package fes.aragon.controller;

import fes.aragon.extras.MusicaCiclica;
import fes.aragon.modelo.Disparos;
import fes.aragon.modelo.Enemigos;
import fes.aragon.modelo.Fondo;
import fes.aragon.modelo.Nave;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class InicioController {
	private Scene escena;
	private GraphicsContext graficos;
	private Enemigos enemigos;
	private Fondo fondo;
	private Nave nave;
	private Disparos disparos;
	private Thread hiloFondo;

	@FXML
	private Canvas canvas;

	public void iniciar() {
		componentesIniciar();
		
		pintar();
		eventosTeclado();
		ciclo();
	}

	private void componentesIniciar() {
		graficos = canvas.getGraphicsContext2D();
		MusicaCiclica entrada = new MusicaCiclica("musica_entrada");
		hiloFondo = new Thread(entrada);
		hiloFondo.start();
		enemigos = new Enemigos(20, 20, null, 1);
		fondo=new Fondo(0, 0,"/fes/aragon/resource/fondo/img (1).gif" , 4,24);
		nave=new Nave(20,255,"/fes/aragon/resource/navefinal.png",2);
		nave.setrEnemigo(enemigos.getEnemigos());
		disparos=new Disparos(0, 0, null, 2);
		nave.setDisparos(disparos);
		
	}

	public void ciclo() {
		long tiempoInicio = System.nanoTime();
		
		AnimationTimer tiempo = new AnimationTimer() {
			@Override
			public void handle(long tiempoActual) {
				double t = (tiempoActual - tiempoInicio) / 1000000000.00;
				fondo.setTiempo(t);
				calculosLogica();
				pintar();

			}

		};
		tiempo.start();
	}

	private void calculosLogica() {
		this.enemigos.logicaCalculos();
		this.fondo.logicaCalculos();
		this.nave.logicaCalculos();
		this.disparos.logicaCalculos();
	}

	private void pintar() {
		
		this.fondo.pintar(graficos);
		this.nave.pintar(graficos);
		this.enemigos.pintar(graficos);
		this.disparos.pintar(graficos);
		
	}

	public void setEscena(Scene escena) {
		this.escena = escena;
		
	}
	private void eventosTeclado() {
		escena.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent arg0) {				
				// TODO Auto-generated method stub
				nave.teclado(arg0,true);
				
			}			
		});
		escena.setOnKeyReleased(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent arg0) {
				// TODO Auto-generated method stub
				nave.teclado(arg0,false);
			
			}
			
		});
		
	}
	public void eventosVentana() {
		Stage escenario=(Stage)escena.getWindow();
		escenario.setOnCloseRequest(new EventHandler<WindowEvent>() {

			@Override
			public void handle(WindowEvent arg0) {
				// TODO Auto-generated method stub
				hiloFondo.stop();
			}
		});
	}
	
}
