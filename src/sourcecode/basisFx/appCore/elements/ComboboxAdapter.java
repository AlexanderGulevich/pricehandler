package basisFx.appCore.elements;

import basisFx.appCore.activeRecord.ActiveRecord;
import basisFx.appCore.interfaces.Mediator;
import com.jfoenix.controls.JFXCheckBox;
import javafx.scene.control.ComboBox;
import lombok.Getter;

public class ComboboxAdapter {

    @Getter
    private ComboBox comboBox;
    private Mediator mediator;
    @Getter
    private ActiveRecord record;
    @Getter
    private ActiveRecord selected;

    public ComboboxAdapter(ComboBox comboBox, Mediator mediator, ActiveRecord record) {
        this.comboBox = comboBox;
        this.mediator = mediator;
        this.record = record;

        refresh();

        comboBox.setOnAction((e) -> {
            selected= (ActiveRecord) comboBox.getSelectionModel().getSelectedItem();
            mediator.inform(comboBox);
        });


    }

    public void choiceItem(ActiveRecord record){
        comboBox.getSelectionModel().select(record);
        selected= (ActiveRecord) comboBox.getSelectionModel().getSelectedItem();
        mediator.inform(comboBox);
    }

    public void refresh() {
        comboBox.setItems(record.getAll());
    }


}
