package basisFx.service.price;

import basisFx.DbSchemaPrice;
import basisFx.appCore.events.DirectoryChosserEvent;
import basisFx.appCore.events.FileChooser;
import basisFx.appCore.utils.PropertiesUtils;
import basisFx.appCore.utils.Registry;
import basisFx.dataSource.DbFactory;
import basisFx.service.ServicePanels;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;


public class ServicePanelConfig extends ServicePanels {
    @FXML
    private JFXButton add;

    FileChooser fileChooser;

    DirectoryChosserEvent directoryChosserEvent;

    @Override
    public void init() {

        directoryChosserEvent = new DirectoryChosserEvent();
        directoryChosserEvent.setEventToElement(add);
        directoryChosserEvent.setMediator(this);

    }



    @Override
    public void commonLabelName(String name) {

    }


    public ServicePanelConfig() {
        Registry.dataExchanger.put("settings", this);
    }


    @Override
    public void inform(Object node) {
        if (node==add) {
            System.out.println(directoryChosserEvent.getPath());
            PropertiesUtils.setProperty("db_path",directoryChosserEvent.getPath());
            DbFactory.createDbServer(new DbSchemaPrice());


        }


    }

}