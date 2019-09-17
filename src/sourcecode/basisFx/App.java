package basisFx;

import basisFx.appCore.menu.MenuFabric;
import basisFx.appCore.settings.Settings;
import basisFx.appCore.settings.StylesPathes;
import basisFx.appCore.utils.*;
import basisFx.appCore.windows.WindowAbstraction;
import basisFx.appCore.windows.WindowBuilder;
import basisFx.appCore.windows.WindowFabric;
import basisFx.dataSource.DbFactory;
import javafx.stage.Stage;

import java.sql.Connection;
//import org.scenicview.ScenicView;



public class App{

    public App(Stage primaryStage)  {

        IconToPlatform.init(primaryStage);
        CSSHandler.init(StylesPathes.CUSTOM_1);
        WindowFabric.WindowUndecorated();
        FontHandler.getInstanse().loadFontToScene();


//        PropertiesUtils.setProperty("db_path","C:/komfdb/");
//        PropertiesUtils.setProperty("db_name","komdb");

        PropertiesUtils.run();
        PropertiesUtils.setProperty("db_name","pricedb");
        PropertiesUtils.setProperty("db_folder","omts");
        DbFactory.createDbServerHsql(new DbSchemaPrice());

        Registry.windowFabric.mainWindow(primaryStage,
                WindowBuilder.newBuilder()
//                        .setButtonsForStage(new ButtonsForStageThreeEntity(LeftAndTopMenuGUI.Structura.titleAnchor.name()))
                        .setFxmlFileName("Main_v2")
                        .setParentAnchorNameForFXML(WindowAbstraction.DefaultPanelsNames.topVisibleAnchor.name())
                        .setWidth(1250d)
                        .setHeight(Settings.HEIGHT)
                        .setPanelCreator(null)
                        .setTitle("ОБРАБОТЧИК ПРАЙСА")
                        .setMessage(null)
                        .build()
                );








        MenuFabric.menuLeft( new MainMenuSketch());

//        ScenicView.show(Registry.mainWindow.getScene());



    }


}


