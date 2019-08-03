package basisFx.appCore.grid;

import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;

public class CtrlPosNON extends CtrlPosition {
    public CtrlPosNON() {
    }

    @Override
    public void organize(Label label, Button addBut, Button delBut, TableView tableView) {

        parentGridWrapper.addSpanNode(
                label,
                0, 0, 1, 1, HPos.LEFT, VPos.TOP, insets);
        parentGridWrapper.addSpanNode(
                tableView,
                0, 1, 1, 1, HPos.CENTER, VPos.TOP, insets);

    }

    @Override
    public void organize(Button addBut, Button delBut, TableView tableView) {

    }

    @Override
    public void organize(Label label, TableView tableView) {


        parentGridWrapper.addSpanNode(
                label,
                0, 0, 1, 1, HPos.LEFT, VPos.TOP, insets);
        parentGridWrapper.addSpanNode(
                tableView,
                0, 1, 1, 1, HPos.CENTER, VPos.TOP, insets);
    }

    @Override
    public void organize(TableView tableView) {


        parentGridWrapper.addSpanNode(
                tableView,
                0, 0, 1, 1, HPos.CENTER, VPos.TOP, insets);
    }
}