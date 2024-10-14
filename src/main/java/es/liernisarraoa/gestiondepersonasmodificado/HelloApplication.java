package es.liernisarraoa.gestiondepersonasmodificado;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    private static Scene scene;

    /**
     * Inicializa la aplicación y configura la escena.
     *
     * @param stage la escena principal de la aplicación
     * @throws IOException si ocurre un error al cargar el archivo FXML
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("gestionDePersonasModificado.fxml"));
        scene = new Scene(fxmlLoader.load(), 600, 500);
        stage.setTitle("Gestion de Personas");
        /**
         * Insertar un icono a la escena, la carpeta de las Imagenes tiene que estar dentro de resources
         */
        stage.getIcons().add(new Image(String.valueOf(HelloApplication.class.getResource("/Imagenes/agenda.png"))));
        stage.setResizable(true);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Punto de entrada de la aplicación.
     *
     * @param args los argumentos de la línea de comandos
     */
    public static void main(String[] args) {
        launch();
    }

    /**
     * Obtiene la escena principal de la aplicación.
     *
     * @return la escena principal
     */
    public static Scene getScene(){
        return scene;
    }
}