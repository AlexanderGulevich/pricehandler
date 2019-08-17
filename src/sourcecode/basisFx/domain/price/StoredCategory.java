package basisFx.domain.price;

import basisFx.appCore.activeRecord.ActiveRecord;
import javafx.beans.property.SimpleObjectProperty;

public class StoredCategory extends ActiveRecord {
    private static StoredCategory INSTANCE = new StoredCategory();
    public static StoredCategory getINSTANCE() {
        return INSTANCE;
    }


    private SimpleObjectProperty<String> name =new SimpleObjectProperty(this, "name", null);
    private SimpleObjectProperty<Integer> rank =new SimpleObjectProperty(this, "rank", null);


    public String getName() {
        return name.get();
    }

    public SimpleObjectProperty<String> nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public Integer getRank() {
        return rank.get();
    }

    public SimpleObjectProperty<Integer> rankProperty() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank.set(rank);
    }

    @Override
    public String toString() {
        return getName();
    }
}
