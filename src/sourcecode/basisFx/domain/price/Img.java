package basisFx.domain.price;

import basisFx.appCore.activeRecord.ActiveRecord;
import basisFx.appCore.utils.Registry;
import basisFx.dataSource.Db;
import basisFx.domain.Packet;
import basisFx.domain.PacketSize;
import javafx.beans.property.SimpleObjectProperty;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.*;

public class Img extends ActiveRecord {
    private static Img INSTANCE = new Img();
    public static Img getINSTANCE() {
        return INSTANCE;
    }

    private SimpleObjectProperty<String> barcode =new SimpleObjectProperty<>(this, "barcode", null);

    private SimpleObjectProperty<InputStream> imgBig =new SimpleObjectProperty<>(this, "imgBig", null);
    private SimpleObjectProperty<InputStream> imgSmall =new SimpleObjectProperty<>(this, "imgSmall", null);

    public String getBarcode() {
        return barcode.get();
    }

    public SimpleObjectProperty<String> barcodeProperty() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode.set(barcode);
    }

    @Override
    public String toString() {
        return null;
    }

    public InputStream getImgBig() {
        return imgBig.get();
    }

    public SimpleObjectProperty<InputStream> imgBigProperty() {
        return imgBig;
    }

    public void setImgBig(InputStream imgBig) {
        this.imgBig.set(imgBig);
    }

    public InputStream getImgSmall() {
        return imgSmall.get();
    }

    public SimpleObjectProperty<InputStream> imgSmallProperty() {
        return imgSmall;
    }

    public void setImgSmall(InputStream imgSmall) {
        this.imgSmall.set(imgSmall);
    }

    public void setImgBig(FileInputStream imgBig) {
        this.imgBig.set(imgBig);
    }

    @Override
    public void insert()  {

        try {
            String expression = "INSERT INTO " + this.entityName
                    + "("
                    + " barcode,  "
                    + " imgBig,   "
                    + " imgSmall   "
                    + ") VALUES(?,?,?)";

            PreparedStatement pstmt = Db.connection.prepareStatement(expression);
            pstmt.setString(1, getBarcode());
            pstmt.setBlob(2, getImgBig());
            pstmt.setBlob(3, getImgSmall());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }


    public Img find(PriceItem priceItem)  {

        String barcode = priceItem.getBarcode();
        Img pojo=new Img() ;
        String expression="SELECT * FROM " +entityName+" WHERE barcode=?";

        try {
            PreparedStatement pstmt = Db.connection.prepareStatement(expression);
            pstmt.setString(1, barcode);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()){
                pojo.setId(rs.getInt("id"));
                pojo.setBarcode(barcode);
                pojo.setImgBig(rs.getBlob("imgBig").getBinaryStream());
                pojo.setImgSmall(rs.getBlob("imgSmall").getBinaryStream());
            }
        } catch (SQLException e) {
            Registry.windowFabric.infoWindow("Ошибка в загрузке картинки из БД\n\n"+e.getMessage());
        }
        return pojo;


    }
}
