package basisFx;

import basisFx.appCore.elements.TableWrapper;
import basisFx.appCore.menu.MenuFabric;
import basisFx.appCore.settings.Settings;
import basisFx.appCore.settings.StylesPathes;
import basisFx.appCore.utils.*;
import basisFx.appCore.windows.ButtonsForStageThreeEntity;
import basisFx.appCore.windows.WindowAbstraction;
import basisFx.appCore.windows.WindowBuilder;
import basisFx.appCore.guiStructura.LeftAndTopMenuGUI;
import basisFx.appCore.windows.WindowFabric;
import basisFx.dataSource.DbFactory;
import javafx.stage.Stage;
//import org.scenicview.ScenicView;

import java.sql.SQLException;

public class App{

    public App(Stage primaryStage) throws ClassNotFoundException, SQLException {

        IconToPlatform.init(primaryStage);
        CSSHandler.init(StylesPathes.CUSTOM_1);
        WindowFabric.WindowUndecorated();
        DbFactory.createDbServer();
        FontHandler.getInstanse().loadFontToScene();

        Registry.windowFabric.mainWindow(primaryStage,
                WindowBuilder.newBuilder()
//                        .setButtonsForStage(new ButtonsForStageThreeEntity(LeftAndTopMenuGUI.Structura.titleAnchor.name()))
                        .setFxmlFileName("Main_v2")
                        .setParentAnchorNameForFXML(WindowAbstraction.DefaultPanelsNames.topVisibleAnchor.name())
                        .setWidth(Settings.WIDTH)
                        .setHeight(Settings.HEIGHT)
                        .setPanelCreator(null)
                        .setTitle(Settings.TITLE)
                        .setMessage(null)
                        .build()
                );

        MenuFabric.menuLeft( new MainMenuSketch());

//        ScenicView.show(Registry.mainWindow.getScene());

        ImgResize.mainr();

    }
    
}


