package com.three60t.fixatdl.ui.fx8.element;

import com.three60t.fixatdl.converter.ControlTTypeConverter;
import com.three60t.fixatdl.converter.TypeConverterFactory;
import com.three60t.fixatdl.model.core.EnumPairT;
import com.three60t.fixatdl.model.core.ParameterT;
import com.three60t.fixatdl.model.layout.ListItemT;
import com.three60t.fixatdl.model.layout.SliderT;
import com.three60t.fixatdl.ui.common.element.FixSliderUiElement;
import com.three60t.fixatdl.ui.fx8.FxUtils;
import com.three60t.fixatdl.ui.fx8.utils.ListStringConverter;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class FxFixSliderUiElement implements FixSliderUiElement<Pane, String> {

    private SliderT sliderT;
    private Slider slider;
    private ListStringConverter converter;
    private GridPane gridPane;
    private Label label;
    private ParameterT parameterT;

    private ObjectProperty<String> controlIdEmitter = new SimpleObjectProperty<>();

    private ControlTTypeConverter<?> controlTTypeConverter;

    @Override
    public Pane create() {
        this.gridPane = new GridPane();
        this.gridPane.getColumnConstraints().addAll(FxUtils.getOneColumnWidthForGridPane());

        this.controlTTypeConverter = TypeConverterFactory.createControlTypeConverter(sliderT, parameterT);

        this.label = new Label(this.sliderT.getLabel());

        this.gridPane.add(this.label, 0, 0);

        this.converter = new ListStringConverter(this.sliderT.getListItem().stream().map(ListItemT::getUiRep).collect(Collectors.toList()));

        this.slider = new Slider(0, this.sliderT.getListItem().size() - 1,
                this.converter.fromString(this.sliderT.getInitValue()));

        this.slider.setOnMouseClicked(event -> {
            setFieldValueToParameter(this.parameterT.getEnumPair().stream()
                            .filter(enumPairT -> enumPairT.getEnumID().equals(getValue()))
                            .map(EnumPairT::getWireValue).findFirst().orElse("0"),
                    this.parameterT);
            this.controlIdEmitter.setValue(this.sliderT.getID() + ":" + getValue());
            // TODO check it when dragged faster value change is not recognized and this is not called back
//            System.out.println("Slider Value : " + getValue());
//            System.out.println("Slider Enum value : "+this.parameterT.getEnumPair().stream()
//                    .filter(enumPairT -> enumPairT.getEnumID().equals(getValue()))
//                    .map(EnumPairT::getWireValue).findFirst().orElse("0"));
        });


        //Init of parameter
        setValue(getValue());

        this.gridPane.setPadding(new Insets(0, 30, 0, 30));

        this.slider.setLabelFormatter(this.converter);
        this.slider.setShowTickLabels(true);
        this.slider.setShowTickMarks(true);
        this.slider.setMajorTickUnit(1.0);

        this.slider.setMinWidth(this.converter.length());

        this.slider.setMinorTickCount(0);
        this.slider.setSnapToTicks(true);

        this.slider.setSnapToTicks(true);

        this.slider.setOrientation(Orientation.HORIZONTAL);

        this.gridPane.add(this.slider, 0, 1, 5, 1);
        return this.gridPane;
    }

    @Override
    public ObjectProperty<String> listenChange() {
        return this.controlIdEmitter;
    }

    @Override
    public String getValue() {
        return this.converter.toString(this.slider.getValue());
    }

    @Override
    public void setValue(String value) {
        double valueD = this.converter.fromString(value);
        this.slider.setValue(valueD);
        setFieldValueToParameter(parameterT
                .getEnumPair()
                .stream()
                .filter(enumPairT -> enumPairT.getEnumID()
                        .equals(valueD))
                .map(EnumPairT::getWireValue)
                .findFirst()
                .orElse("1"), parameterT);
        controlIdEmitter.setValue(sliderT.getID() + ":" + valueD);
    }

    @Override
    public void makeVisible(boolean visible) {
        this.gridPane.setVisible(visible);
    }

    @Override
    public void makeEnable(boolean enable) {
        this.gridPane.setDisable(!enable);
    }

    @Override
    public SliderT getControl() {
        return this.sliderT;
    }

    @Override
    public void setSlider(SliderT slider) {
        this.sliderT = slider;
    }

    @Override
    public void setParameters(List<ParameterT> parameterTList) {
        if (parameterTList != null && parameterTList.size() > 0)
            this.parameterT = parameterTList.get(0);
    }

    @Override
    public List<ParameterT> getParameters() {
        List<ParameterT> parameterTS = Collections.emptyList();
        parameterTS.add(this.parameterT);
        return parameterTS;
    }

    @Override
    public ControlTTypeConverter<?> getControlTTypeConverter() {
        return this.controlTTypeConverter;
    }
}
