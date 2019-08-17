package basisFx.service.price;

import basisFx.appCore.events.DirectoryChosserEvent;
import basisFx.appCore.grid.ButSizeEnum;
import basisFx.appCore.grid.CtrlPosEnum;
import basisFx.appCore.panelSets.SingleTableSet;
import basisFx.appCore.table.ColumnFabric;
import basisFx.appCore.utils.Coordinate;
import basisFx.appCore.utils.Registry;
import basisFx.domain.SleevePrice;
import basisFx.domain.price.Price;
import basisFx.domain.price.WritePrice;
import basisFx.service.ServicePanels;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;


public class ServicePanelPriceOutput extends ServicePanels {
    @FXML
    private JFXButton output;
    @FXML
    private JFXButton saveTamplate;
    @FXML
    private Label commonLabelName;
    @FXML
    private TextField textfield;
    @FXML
    private FlowPane flowpane;
    @FXML
    private JFXCheckBox checkAll;
    @FXML
    private JFXCheckBox addImage;
    @FXML
    private JFXCheckBox standatrCategory;
    @FXML
    private ComboBox tamplateCombobox;
    @FXML
    private AnchorPane panelAnchor;
//    @FXML
//    private AnchorPane tableAnchor_left;
//    @FXML
//    private AnchorPane tableAnchor_right;

    DirectoryChosserEvent directoryChosserEvent;

    @Override
    public void init() {


        currentWindow=Registry.mainWindow;

        directoryChosserEvent = new DirectoryChosserEvent();
        directoryChosserEvent.setEventToElement(output);
        directoryChosserEvent.setMediator(this);
        directoryChosserEvent.setCallBackTyped(() -> {
            Price price = (Price) Registry.dataExchanger.get("price");
            if (price == null) {
                Registry.windowFabric.infoWindow("Для того, чтобы сохранить обработанный прайс, его нужно сначала загрузить из остатков!");
                return false;
            }
            return true;
            }

        );


//
//        SingleTableSet.builder()
//                .aClass(SleevePrice.class)
//                .isSortable(false) .isEditable(true)
//                .currentWindow(currentWindow)
//                .bigTitle(null)
//                .littleTitle("Цена втулок")
//                .parentAnchor(tableAnchor_left)
//                .coordinate(new Coordinate(0d, 0d, 0d, 0d))
//                .ctrlPosEnum(CtrlPosEnum.CTRL_POS_TOP)
//                .butSizeEnum(ButSizeEnum.BUT_SIZE_LITTLE)
//                .addButEvent(null)
//                .delButEvent(null)
//                .column(
//                        ColumnFabric.doubleCol(
//                                "Цена","price",1d,true
//                        )
//                )
//
//                .build().configure();
//
//
//        SingleTableSet.builder()
//                .aClass(SleevePrice.class)
//                .isSortable(false) .isEditable(true)
//                .currentWindow(currentWindow)
//                .bigTitle(null)
//                .littleTitle("Цена втулок")
//                .parentAnchor(tableAnchor_right )
//                .coordinate(new Coordinate(0d, 0d, 0d, 0d))
//                .ctrlPosEnum(CtrlPosEnum.CTRL_POS_TOP)
//                .butSizeEnum(ButSizeEnum.BUT_SIZE_LITTLE)
//                .addButEvent(null)
//                .delButEvent(null)
//                .column(
//                        ColumnFabric.doubleCol(
//                                "Цена","price",1d,true
//                        )
//                )
//
//                .build().configure();










    }



    @Override
    public void commonLabelName(String name) {
        commonLabelName.setText(name);
    }


    public ServicePanelPriceOutput() {
        Registry.dataExchanger.put("priceWritter", this);
    }


    @Override
    public void inform(Object node) {
        if (node==output) {

            String path = directoryChosserEvent.getPath();
            Price price = (Price) Registry.dataExchanger.get("price");
            new WritePrice(price, path);


        }


    }

}