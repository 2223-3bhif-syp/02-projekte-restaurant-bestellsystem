module Bestellsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.apache.derby.client;
    requires org.apache.derby.commons;
    requires org.apache.derby.tools;
    requires org.mybatis;

    opens at.htl.bestellsystem.view to javafx.fxml;
    exports at.htl.bestellsystem.view;
    exports at.htl.bestellsystem.controller;
    exports at.htl.bestellsystem.entity;
}