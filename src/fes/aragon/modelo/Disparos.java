package fes.aragon.modelo;

import java.util.ArrayList;
import java.util.Iterator;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Rectangle;

public class Disparos extends ComponentesJuego {
	
	private ArrayList<Rectangle> disparo=new ArrayList<>();

	public Disparos(int x, int y, String imagen, int velocidad) {
		super(x, y, imagen, velocidad);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void pintar(GraphicsContext graficos) {
		// TODO Auto-generated method stub
		for (Rectangle rectangulo : disparo) {
			graficos.strokeRect(
					rectangulo.getX(), rectangulo.getY(), 
					rectangulo.getWidth(), rectangulo.getHeight());	
		}
		
		
	}

	@Override
	public void teclado(KeyEvent evento, boolean presiona) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void raton(KeyEvent evento) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void logicaCalculos() {
		// TODO Auto-generated method stub
		Iterator it=disparo.iterator();
		while(it.hasNext()) {
			Rectangle r=(Rectangle) it.next();
			if(r.getY()<0) {
				it.remove();
			}else {
				r.setY(r.getY()-velocidad);
			}
		}
		
	}
	
	public void agregarDisparo(Rectangle rec) {
		this.disparo.add(rec);
	}

	public ArrayList<Rectangle> getDisparo() {
		return disparo;
	}
	

}
