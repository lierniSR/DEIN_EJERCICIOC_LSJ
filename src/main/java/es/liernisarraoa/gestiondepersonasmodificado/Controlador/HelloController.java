package es.liernisarraoa.gestiondepersonasmodificado.Controlador;

import es.liernisarraoa.gestiondepersonasmodificado.HelloApplication;
import es.liernisarraoa.gestiondepersonasmodificado.Modelo.Personas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

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
     *
     * @param actionEvent evento de acción que se produce al pulsar el botón
     */
    public void aniadirPersona(ActionEvent actionEvent) {
        verificacionPersona();
        if (errores.isEmpty()) {
            aniadirPersonaTabla();
        } else {
            alertaError();
        }
    }

    /**
     * Valida los campos de texto y añade una nueva persona a la tabla si son válidos.
     */
    private void verificacionPersona() {
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

    /**
     * Método que se ejecuta al pulsar el botón "Modificar".
     * Moodifica una persona de la tabla de personas.
     * Comprueba si la persona ya existe en la tabla antes de modificarlo.
     *
     * @param actionEvent evento de acción que se produce al pulsar el botón
     */
    public void modificarPersona(ActionEvent actionEvent){
        Personas personaAEliminar = tablaPersonas.getSelectionModel().getSelectedItem();
        verificacionPersona();
        if (errores.isEmpty()) {
            Personas p = new Personas(nombreTextField.getText(), apellidoTextField.getText(), Integer.parseInt(edadTextField.getText()));
            if(personaAEliminar != null && !tablaPersonas.getItems().contains(p)){
                tablaPersonas.getItems().remove(personaAEliminar);
                tablaPersonas.getItems().add(p);
                alertaModificarPersona();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initOwner(escenaAplicacion.getWindow());
                alert.setHeaderText(null);
                alert.setTitle("Persona duplicada");
                alert.setContentText("La persona no se puede modificar ya que existe una Persona identica en la tabla.");
                alert.showAndWait();
            }
        } else {
            alertaError();
        }
    }

    /**
     * Método que se ejecuta al pulsar el botón "Eliminar".
     * Elimina una persona de la tabla
     *
     * @param actionEvent evento de acción que se produce al pulsar el botón
     */
    public void eliminarPersona(ActionEvent actionEvent){

    }

    /**
     * Método que se ejecuta al pulsar un Objeto de tipo Persona de la tabla.
     * Al clicar una persona de la tabla se muestra su informacion en los siguientes TextFiel:
     * nombreTextField, apellidoTextField, edadTextField
     *
     * @param mouseEvent evento de mouse, reacciona al clicar
     */
    public void clicarPersona(MouseEvent mouseEvent) {
        Personas personaSeleccionada = tablaPersonas.getSelectionModel().getSelectedItem();
        try{
            nombreTextField.setText(personaSeleccionada.getNombre());
            apellidoTextField.setText(personaSeleccionada.getApellido());
            edadTextField.setText(String.valueOf(personaSeleccionada.getEdad()));
        } catch(NullPointerException e){
            alertaErrorPersonaNula();
        }
    }

    /**
     * Muestra una alerta de error por si no se ha seleccionado una Persona.
     */
    private void alertaErrorPersonaNula() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initOwner(escenaAplicacion.getWindow());
        alert.setHeaderText(null);
        alert.setTitle("Error");
        alert.setContentText("No se ha seleccionado ninguna persona.");
        alert.showAndWait();
    }

    /**
     * Muestra una alerta de información para confirmar que se ha modificado una persona correctamente.
     */
    private void alertaModificarPersona() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initOwner(escenaAplicacion.getWindow());
        alert.setHeaderText(null);
        alert.setTitle("Persona modificada");
        alert.setContentText("Persona modificada correctamente.");
        alert.showAndWait();
    }

    /**
     * Método que se ejecuta al inicializar el controlador.
     * Configura la seleccion de la tabla como SINGLE.
     * Configura las columnas de la tabla de personas y establece sus anchos.
     * La parte de prefWifthProperty().bind(tabla.Personas.widthProperty().multiply(0.3))
     * y demás es para que las columnas se redimensionen.
     *
     * @param url        URL del recurso que se está cargando
     * @param resourceBundle recurso de configuración
     */

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tablaPersonas.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        columnaEdad.setCellValueFactory(new PropertyValueFactory<>("edad"));
        columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columnaApellido.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        columnaNombre.prefWidthProperty().bind(tablaPersonas.widthProperty().multiply(0.3));
        columnaApellido.prefWidthProperty().bind(tablaPersonas.widthProperty().multiply(0.4));
        columnaEdad.prefWidthProperty().bind(tablaPersonas.widthProperty().multiply(0.2));
    }
}