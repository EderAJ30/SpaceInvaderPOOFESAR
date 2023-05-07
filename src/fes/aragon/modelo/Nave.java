package fes.aragon.modelo;

import java.util.ArrayList;

import fes.aragon.extras.EfectosMusica;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Rectangle;

public class Nave extends ComponentesJuego {
	
	private Rectangle r;
	private ArrayList<Rectangle> rEnemigo;
	private Disparos disparos;
	private boolean derecha = false;
	private boolean izquierda = false;
	private boolean arriba = false;
	private boolean abajo = false;
	private Image imagen;
	private boolean disparo=false;


	public Nave(int x, int y, String imagen, int velocidad) {
		super(x, y, imagen, velocidad);
		// TODO Auto-generated constructor stub
		
		this.imagen = new Image(imagen);
		r = new Rectangle(x, y, this.imagen.getWidth(), this.imagen.getHeight());
	}

	@Override
	public void pintar(GraphicsContext graficos) {
		// TODO Auto-generated method stub
		graficos.drawImage(imagen, x, y);
		graficos.strokeRect(r.getX(), r.getY(), r.getWidth(), r.getHeight());
	}

	@Override
	public void teclado(KeyEvent evento, boolean presiona) {
		// TODO Auto-generated method stub
		
		if (presiona) {
			switch (evento.getCode().toString()) {
			
			case "RIGHT":
				derecha = true;
				break;
			case "LEFT":
				izquierda = true;
				break;
			case "UP":
				arriba = true;
				break;
			case "DOWN":
				abajo = true;
				break;
			case "SPACE":
				disparo=true;
				break;

			}
		} else {
			switch (evento.getCode().toString()) {
			case "RIGHT":
				derecha = false;
				break;
			case "LEFT":
				izquierda = false;
				break;
			case "UP":
				arriba = false;
				break;
			case "DOWN":
				abajo = false;
				break;

			}
		}

	}

	@Override
	public void raton(KeyEvent evento) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void logicaCalculos() {
		// TODO Auto-generated method stub
		if (derecha) {
			x += velocidad;
		}
		if (izquierda) {
			x -= velocidad;
		}
		if (arriba) {
			y -= velocidad;
		}
		if (abajo) {
			y += velocidad;
		}
		this.r.setX(x);
		this.r.setY(y);
		
		//ver si hay coliciones nave y enemigos
		int i=0;
		boolean col=false;
		for (Rectangle rec : rEnemigo) {			
			if (this.r.getBoundsInLocal().intersects((rec.getBoundsInLocal()))) {
				System.out.println("Colision");
				col=true;
				break;
			}
			i++;
		}	
		
		
		if(col==true) {
			rEnemigo.remove(i);	
		}
		
		//ver si hay colisiones entre el disparo y enemigos
		int indiceDisparo=0;
		int indiceEnemigo=0;
		boolean contacto=false;
		for (Rectangle rec : rEnemigo) {			
			for(Rectangle recDisparo:disparos.getDisparo()) {
				if (rec.getBoundsInLocal().intersects((recDisparo.getBoundsInLocal()))) {
					contacto=true;
					break;
				}
				indiceDisparo++;
			}
			if(contacto) {
				disparos.getDisparo().remove(indiceDisparo);
				rEnemigo.remove(indiceEnemigo);
				EfectosMusica efecto=new EfectosMusica("disparo");
				
				Thread efectoColision=new Thread(efecto);
				efectoColision.start();
				break;
			}
			indiceDisparo=0;
			indiceEnemigo++;			
		}
		
		//agregar el disparo
		if(disparo) {
			this.disparos.agregarDisparo(new Rectangle(x+r.getWidth()/2,y,10,10));
			disparo=false;
		}
		
		
	}

	public ArrayList<Rectangle> getrEnemigo() {
		return rEnemigo;
	}

	public void setrEnemigo(ArrayList<Rectangle> rEnemigo) {
		this.rEnemigo = rEnemigo;
	}

	public void setDisparos(Disparos disparos) {
		this.disparos = disparos;
	}
	
	

}
