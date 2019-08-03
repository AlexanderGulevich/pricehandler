package basisFx;

import basisFx.appCore.menu.*;
import basisFx.appCore.settings.FontsStore;
import basisFx.appCore.utils.Registry;
import basisFx.presentation.*;
import basisFx.service.ServicePanelPriceOutput;
import basisFx.service.WindowServiceDatePicker;

import static basisFx.appCore.settings.FontsStore.*;

public class MainMenuSketch implements MenuSketch {

    private LeftAndTopBarDirector menuDirector=new LeftAndTopBarDirector();

    private double iconSize = 25d;

    public MainMenuSketch() {

        menuDirector.setComposite(LeftAndTopBarItemComposite.builder()
                .description("Загрузчик и проверка прайса \n")
                .fontsStore(FAWESOME5SOLID)
                .fxmlFileName("vbut.fxml")
                .fontSymbol("\uF039")
                .isActive(false)
                .fontSize(iconSize)
                .panelCreator( PriceLoader::new)
                .build()
        );

        menuDirector.setComposite(LeftAndTopBarItemComposite.builder()
                        .description("Выгрузка прайса и настройка параметров, шаблоны \n")
                        .fontsStore(FAWESOME5SOLID)
                        .panelCreator(PriceWritter::new)
                        .fxmlFileName("vbut.fxml")
                        .fontSymbol("\uF093")
                        .isActive(false)
                        .fontSize(iconSize)
                        .build()
        );



        menuDirector.setComposite(LeftAndTopBarItemComposite.builder()
                    .panelCreator( () -> new CommonPanel("\uF02A",FontsStore.FAWESOME5SOLID))
                    .callBack(WindowServiceDatePicker::closeIfExist)
                    .description("Графики")
                    .fontsStore(FAWESOME5SOLID)
                    .fxmlFileName("vbut.fxml")
                    .fontSymbol("\uF02A")
                    .fontSize(iconSize)
                    .build()
        ) ;


        menuDirector.setComposite(LeftAndTopBarItemComposite.builder()
                    .panelCreator( () -> new CommonPanel("\uF0CB",FontsStore.FAWESOME5SOLID))
                    .callBack(WindowServiceDatePicker::closeIfExist)
                    .description("Статистическая информация")
                    .fontsStore(FAWESOME5SOLID)
                    .fxmlFileName("vbut.fxml")
                    .fontSymbol("\uF0CB")
                    .fontSize(iconSize)
                    .build()
        ) ;
        menuDirector.setComposite(LeftAndTopBarItemComposite.builder()
                .panelCreator( () -> new CommonPanel("\uF085",FontsStore.FAWESOME5SOLID))
                .callBack(WindowServiceDatePicker::closeIfExist)
                .description("Статистическая информация")
                .fontsStore(FAWESOME5SOLID)
                .fxmlFileName("vbut.fxml")
                .fontSymbol("\uF085")
                .fontSize(iconSize)
                .build()
        ) ;
        menuDirector.setComposite(LeftAndTopBarItemComposite.builder()
                .panelCreator( () -> new CommonPanel("\uF085",FontsStore.FAWESOME5SOLID))
                .callBack(WindowServiceDatePicker::closeIfExist)
                .description("Статистическая информация")
                .fontsStore(FAWESOME5SOLID)
                .fxmlFileName("vbut.fxml")
                .fontSymbol("\uF085")
                .fontSize(iconSize)
                .build()
        ) ;



    }


    @Override
    public MenuComponent getMenuTree() {
        return menuDirector.getMenuTree();
    }
}
