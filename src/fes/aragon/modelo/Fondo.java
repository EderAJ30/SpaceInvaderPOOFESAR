package fes.aragon.modelo;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;

public class Fondo extends ComponentesJuego {
	
	private ArrayList<Image> imagenes=new ArrayList<>();
	private int indice=0;
	private double tiempo;
	private double tiempoFrame=.2;
	private int frames;
	

	public Fondo(int x, int y, String imagen, int velocidad,int frames) {
		super(x, y, imagen, velocidad);
		this.frames=frames;
		String ruta="";
		for(int i=1;i<=frames;i++) {
			ruta=imagen.replace("1", i+"");
			imagenes.add(new Image(ruta));
		}
	}

	@Override
	public void pintar(GraphicsContext graficos) {
		// TODO Auto-generated method stub
		graficos.drawImage(imagenes.get(indice),0,0);

	}

	@Override
	public void teclado(KeyEvent evento,boolean presiona) {
		// TODO Auto-generated method stub

	}

	@Override
	public void raton(KeyEvent evento) {

	}

	@Override
	public void logicaCalculos() {
		
		if((tiempo>tiempoFrame)) {
			if(this.indice<imagenes.size()-1) {
				indice++;
				tiempoFrame+=.2;
			}else {
				indice=0;
			}	
		}
	}

	public void setTiempo(double tiempo) {
		this.tiempo = tiempo;
	}
	

}
