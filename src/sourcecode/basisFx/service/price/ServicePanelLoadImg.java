package basisFx.service.price;

import basisFx.appCore.activeRecord.ActiveRecord;
import basisFx.appCore.events.DirectoryChosserEvent;
import basisFx.appCore.utils.ImgUtils;
import basisFx.appCore.utils.Registry;
import basisFx.domain.price.*;
import basisFx.service.ServicePanels;
import com.jfoenix.controls.JFXButton;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;


public class ServicePanelLoadImg extends ServicePanels {
    @FXML
    private JFXButton load;
    @FXML
    private Label commonLabelName;
    @FXML
    private Label number;
    @FXML
    private TextField textfield;

    DirectoryChosserEvent directoryChosserEvent;

    static String path;

    @FXML
    private AnchorPane panelAnchor;



    @Override
    public void init() {

        directoryChosserEvent = new DirectoryChosserEvent();
        directoryChosserEvent.setEventToElement(load);
        directoryChosserEvent.setMediator(this);
        number.setText("");
    }



    @Override
    public void commonLabelName(String name) {
        commonLabelName.setText(name);
    }


    public ServicePanelLoadImg() {
        Registry.dataExchanger.put("priceImgLoader", this);
    }


    @Override
    public void inform(Object node) {
        if (node == load) {
            path = directoryChosserEvent.getPath();
            textfield.setText(path);

            File dir = new File(path);
            File[] fls = dir.listFiles();
            List<File> files = Arrays.asList(fls);

            AtomicReference<Integer> numFiles= new AtomicReference<>(0);


            files.stream().forEach(file -> {
                if (file.isFile()) {
                    String name = file.getName();
                    int i = name.indexOf(".");
                    String ext = name.substring(i + 1);
                    String barcode = name.substring(0, i);

                    if (ext.equals("png") ||
                            (  ext.equals("PNG") ||
                            ext.equals("jpg") ||
                            ext.equals("JPG") ||
                            ext.equals("jpeg") ||
                            ext.equals("JPEG"))
                            && barcode.trim().length()==13
                            && checkIntNameParsing(barcode)
                    ) {

                        Img img1 = Img.getINSTANCE().find(barcode);
                        if (img1 == null) {
                            ByteArrayInputStream byteArrayInputStream160 = ImgUtils.resize(file, 130);
                            Img img = null;
                            img = new Img();
                            img.setBarcode(barcode);
                            img.setImgSmall(byteArrayInputStream160);
                            img.insert();

                            numFiles.set(numFiles.get() + 1);
                        }



                    }



                }
            });

            number.setText(numFiles.get().toString());

            Price price = (Price) Registry.dataExchanger.get("price");
            if (price != null) {
                price.setAllRecords(PriceItem.getINSTANCE().getAllFullData());
            }





        }
    }

    private boolean checkIntNameParsing(String barcode) {
        char[] charArray = barcode.trim().toCharArray();

        List<Character> chars = new ArrayList<>();
        for (int i = 0; i < charArray.length; i++) {
            chars.add(charArray[i]);
        }


        for (Character aChar : chars) {
            try {
                Integer integer = Integer.valueOf(aChar.toString());
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return true;
    }


}