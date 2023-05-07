package fes.aragon;
	
import fes.aragon.controller.InicioController;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader root=new FXMLLoader(getClass().getResource("/fes/aragon/fxml/Inicio.fxml"));
			Scene scene = new Scene(root.load());
			scene.getStylesheets().add(getClass().getResource("/fes/aragon/css/application.css").toExternalForm());
			InicioController inicio=root.getController();
			inicio.setEscena(scene);
			inicio.iniciar();
			primaryStage.setScene(scene);
			inicio.eventosVentana();
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
