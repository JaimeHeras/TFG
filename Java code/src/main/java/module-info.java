/* doesn't work with source level 1.8:
module com.mycompany.hola {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mycompany.hola to javafx.fxml;
    exports com.mycompany.hola;
}
*/
