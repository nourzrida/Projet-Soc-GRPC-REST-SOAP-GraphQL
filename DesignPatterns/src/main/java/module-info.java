module designpatterns {
    requires javafx.controls;
    requires javafx.fxml;

    exports view;
    exports controller;
    exports model;
    exports patterns.state;
    exports patterns.composite;
    exports patterns.decorator;
    exports patterns.factory;
    exports util;

    opens view to javafx.fxml;
}

