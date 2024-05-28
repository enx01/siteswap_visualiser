module com.tiago.siteswap.visualiser.siteswap_visualiser {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.tiago.siteswap.visualiser.siteswap_visualiser to javafx.fxml;
    exports com.tiago.siteswap.visualiser.siteswap_visualiser;
    exports com.tiago.siteswap.visualiser.siteswap_visualiser.model;
    opens com.tiago.siteswap.visualiser.siteswap_visualiser.model to javafx.fxml;
    exports com.tiago.siteswap.visualiser.siteswap_visualiser.diagram;
    opens com.tiago.siteswap.visualiser.siteswap_visualiser.diagram to javafx.fxml;
}