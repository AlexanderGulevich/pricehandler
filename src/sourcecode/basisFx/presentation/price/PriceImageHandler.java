package basisFx.presentation.price;

import basisFx.appCore.DynamicContentPanel;
import basisFx.appCore.panelSets.Panel;


public class PriceImageHandler extends DynamicContentPanel {
    @Override
    protected void customDynamicElementsInit() {


        Panel.builder()
                .commonLabelName("Загрузка картинки для штихкода")
                .fxmlFileName("imageHadler")
                .parent(dynamicContentAnchorHolder)
                .build().configure();
    }

}
