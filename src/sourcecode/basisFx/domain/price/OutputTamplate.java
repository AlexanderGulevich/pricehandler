package basisFx.domain.price;

import basisFx.appCore.activeRecord.ActiveRecord;
import javafx.beans.property.SimpleObjectProperty;
import java.util.ArrayList;

public class OutputTamplate extends ActiveRecord {
    private static OutputTamplate INSTANCE = new OutputTamplate();

    private SimpleObjectProperty<String> name =new SimpleObjectProperty<>(this, "name", null);
    private SimpleObjectProperty<ArrayList<Integer>> storedCategory =new SimpleObjectProperty<>(this, "storedCategory", null);

    public static OutputTamplate getINSTANCE() {
        return INSTANCE;
    }
    @Override
    public String toString() {
        return null;
    }


    public String getName() {
        return name.get();
    }

    public SimpleObjectProperty<String> nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public ArrayList<Integer> getStoredCategory() {
        return storedCategory.get();
    }

    public SimpleObjectProperty<ArrayList<Integer>> storedCategoryProperty() {
        return storedCategory;
    }

    public void setStoredCategory(ArrayList<Integer> storedCategory) {
        this.storedCategory.set(storedCategory);
    }
}
