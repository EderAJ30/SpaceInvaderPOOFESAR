package fes.aragon.modelo;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Rectangle;

public class Enemigos extends ComponentesJuego{
	
	private ArrayList<Rectangle> enemigos=new ArrayList<>();
	//si es true,mueve a la derecha
	//si es false, mueve a la izquierda
	private boolean movimiento=true;
	public Enemigos(int x, int y, String imagen, int velocidad) {
		super(x, y, imagen, velocidad);
		// TODO Auto-generated constructor stub
		int salto=0;
		int xx=20;
		int yy=20;
		for(int i=0;i<16;i++) {
			Rectangle r=new Rectangle(xx, yy, 20,20);
			enemigos.add(r);
			xx+=30;
			salto++;
			if(salto==4) {
				xx=20;
				yy+=30;
				salto=0;
			}
		}
		
	}

	@Override
	public void pintar(GraphicsContext graficos) {
		// TODO Auto-generated method stub
		for (Rectangle rectangulo : enemigos) {
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
		if(movimiento==true) {
			
			for (Rectangle rectangle : enemigos) {
				rectangle.setX(rectangle.getX()+1);
				if(rectangle.getX()>=450) {
					movimiento=false;
					for (Rectangle r : enemigos) {
						r.setY(r.getY()+1);
					}
				}
			}	
		}else {
			
			for (Rectangle rectangle : enemigos) {
				rectangle.setX(rectangle.getX()-1);
				if(rectangle.getX()<=20) {
					movimiento=true;
					for (Rectangle r : enemigos) {
						r.setY(r.getY()+1);
					}
				}
			}
		}
		
		
	}

	public ArrayList<Rectangle> getEnemigos() {
		return enemigos;
	}

	public void setEnemigos(ArrayList<Rectangle> enemigos) {
		this.enemigos = enemigos;
	}
	

}
