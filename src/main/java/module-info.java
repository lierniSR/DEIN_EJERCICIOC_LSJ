module es.liernisarraoa.gestiondepersonasmodificado {
    requires javafx.controls;
    requires javafx.fxml;


    opens es.liernisarraoa.gestiondepersonasmodificado.Modelo to javafx.base;
    opens es.liernisarraoa.gestiondepersonasmodificado to javafx.fxml;
    exports es.liernisarraoa.gestiondepersonasmodificado;
    exports es.liernisarraoa.gestiondepersonasmodificado.Controlador;
    opens es.liernisarraoa.gestiondepersonasmodificado.Controlador to javafx.fxml;
}