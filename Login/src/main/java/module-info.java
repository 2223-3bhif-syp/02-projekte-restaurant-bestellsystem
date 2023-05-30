module at.htlleonding.login {
    requires javafx.controls;
    requires javafx.fxml;


    opens at.htlleonding.login to javafx.fxml;
    exports at.htlleonding.login;
}