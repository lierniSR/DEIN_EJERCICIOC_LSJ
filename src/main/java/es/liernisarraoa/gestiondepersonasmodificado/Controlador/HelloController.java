package es.liernisarraoa.gestiondepersonasmodificado.Controlador;

import es.liernisarraoa.gestiondepersonasmodificado.HelloApplication;
import es.liernisarraoa.gestiondepersonasmodificado.Modelo.Personas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    private String errores = "";
    private Scene escenaAplicacion;

    @FXML
    private TableView<Personas> tablaPersonas;

    @FXML
    private TableColumn<Personas, String> columnaNombre;

    @FXML
    private TableColumn<Personas, String> columnaApellido;

    @FXML
    private TableColumn<Personas, Integer> columnaEdad;

    @FXML
    private TextField nombreTextField;

    @FXML
    private TextField apellidoTextField;

    @FXML
    private TextField edadTextField;

    @FXML
    private Button btnAniadirPersona;

    /**
     * Método que se ejecuta al pulsar el botón "Añadir persona".
     * Valida los campos de texto y añade una nueva persona a la tabla si son válidos.
     *
     * @param actionEvent evento de acción que se produce al pulsar el botón
     */
    public void aniadirPersona(ActionEvent actionEvent) {
        errores = "";
        escenaAplicacion = HelloApplication.getScene();
        if (nombreTextField.getText().isEmpty()) {
            errores += "El campo nombre es obligatorio.\n";
        }
        if (apellidoTextField.getText().isEmpty()) {
            errores += "El campo apellidos es obligatorio.\n";
        }
        if (edadTextField.getText().isEmpty()) {
            errores += "El campo edad es obligatorio";
        } else {
            try {
                Integer edad = Integer.parseInt(edadTextField.getText());
            } catch (NumberFormatException e) {
                errores += "El campo edad tiene que ser númerico.\n";
            }
        }
        if (errores.isEmpty()) {
            aniadirPersonaTabla();
        } else {
            alertaError();
        }
    }

    /**
     * Añade una nueva persona a la tabla de personas.
     * Comprueba si la persona ya existe en la tabla antes de añadirla.
     */
    private void aniadirPersonaTabla() {
        Personas p = new Personas(nombreTextField.getText(), apellidoTextField.getText(), Integer.parseInt(edadTextField.getText()));
        if (!tablaPersonas.getItems().contains(p)) {
            tablaPersonas.getItems().add(p);
            alertaAniadirPersona();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(escenaAplicacion.getWindow());
            alert.setHeaderText(null);
            alert.setTitle("Persona duplicada");
            alert.setContentText("La persona no se puede añadir ya que existe en la tabla.");
            alert.showAndWait();
        }
    }

    /**
     * Muestra una alerta de error con los mensajes de error acumulados.
     */
    private void alertaError() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initOwner(escenaAplicacion.getWindow());
        alert.setHeaderText(null);
        alert.setTitle("Error");
        alert.setContentText(errores);
        alert.showAndWait();
    }

    /**
     * Muestra una alerta de información para confirmar que se ha añadido una persona correctamente.
     */
    private void alertaAniadirPersona() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initOwner(escenaAplicacion.getWindow());
        alert.setHeaderText(null);
        alert.setTitle("Persona añadida");
        alert.setContentText("Persona añadida correctamente.");
        alert.showAndWait();
    }

    public void modificarPersona(){

    }

    public void eliminarPersona(){

    }

    /**
     * Método que se ejecuta al inicializar el controlador.
     * Configura las columnas de la tabla de personas y establece sus anchos.
     * La parte de prefWifthProperty().bind(tabla.Personas.widthProperty().multiply(0.3))
     * y demás es para que las columnas se redimensionen.
     *
     * @param url        URL del recurso que se está cargando
     * @param resourceBundle recurso de configuración
     */

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        columnaEdad.setCellValueFactory(new PropertyValueFactory<>("edad"));
        columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columnaApellido.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        columnaNombre.prefWidthProperty().bind(tablaPersonas.widthProperty().multiply(0.3));
        columnaApellido.prefWidthProperty().bind(tablaPersonas.widthProperty().multiply(0.4));
        columnaEdad.prefWidthProperty().bind(tablaPersonas.widthProperty().multiply(0.2));
    }
}