package basisFx.domain.price;

import basisFx.appCore.activeRecord.ActiveRecord;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;


public class Price  {

    @Getter @Setter
    private ArrayList<PriceCategory> categoriesArrayList=new ArrayList<>();
    @Getter
    private ObservableList<ActiveRecord> allRecords= FXCollections.observableArrayList();

    @Getter @Setter
    private Date priceDate=null;
    @Getter @Setter
    private Double totalSumma=null;
    @Getter @Setter
    private String priceDateString=null;


    public void createCategory(String name,  ArrayList<PriceItem> categoryFilds){

        PriceCategory category=new PriceCategory();
        category.setFilds(categoryFilds);
        category.setName(name);
        categoriesArrayList.add(category);
        allRecords.addAll(categoryFilds);

    }



}