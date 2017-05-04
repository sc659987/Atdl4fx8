package com.three60t.fixatdl.ui.fx8.element;

import com.three60t.fixatdl.converter.DateTimeConverter;
import com.three60t.fixatdl.converter.TypeConverter;
import com.three60t.fixatdl.converter.TypeConverterRepo;
import com.three60t.fixatdl.model.core.ParameterT;
import com.three60t.fixatdl.model.core.UTCTimestampT;
import com.three60t.fixatdl.model.layout.ClockT;
import com.three60t.fixatdl.ui.common.element.FixClockUiElement;
import com.three60t.fixatdl.ui.fx8.customelement.DateSpinner;
import com.three60t.fixatdl.ui.fx8.customelement.TimeSpinner;
import com.three60t.fixatdl.utils.Utils;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.XMLGregorianCalendar;
import java.time.MonthDay;
import java.time.Year;
import java.util.Collections;
import java.util.List;

public class FxFixClockUiElement implements FixClockUiElement<Pane, DateTime> {

    private TimeSpinner timeSpinner;
    private DateSpinner dateSpinner;

    private GridPane gridPane;
    private ClockT clockT;
    private ParameterT parameterT;
    private ObjectProperty<String> controlIdEmitter = new SimpleObjectProperty<>();
    private int nextColumn = 0;

    private TypeConverter<?, ?> controlTTypeConverter;

    @Override
    public Pane create() {
        if (this.clockT != null) {
            this.gridPane = new GridPane();

            this.controlTTypeConverter = TypeConverterRepo.createParameterTypeConverter(parameterT);

            if (!Utils.isEmptyString(this.clockT.getLabel()))
                this.gridPane.add(new Label(this.clockT.getLabel()), this.nextColumn++, 0);

            this.gridPane.setHgap(3);
            this.timeSpinner = new TimeSpinner();
            this.timeSpinner.setOnMouseClicked(event -> setFieldValueToParameter(getValue(), parameterT));
            initializeControl();
            adjustClockByParameterType();
            //this.dateSpinner = new DateSpinner(LocalDate.now());
            this.gridPane.add(this.timeSpinner, this.nextColumn, 0);

            return this.gridPane;
        }
        return null;
    }

    @Override
    public void initializeControl() {
        // LOGIC clockT.getInitValueMode() if 0 then use clockT.getInitValue() otherwise use current time
        // if init value is supplied then have to use localMktTz
        if (this.clockT.getInitValue() != null && this.clockT.getLocalMktTz() != null) {
            DateTime dateTime = DateTimeConverter
                    .convertXMLGregorianCalendarToDateTime(this.clockT.getInitValue(),
                            this.clockT.getLocalMktTz());

            dateTime = dateTime.toDateTime(DateTimeZone.getDefault());
            if (this.clockT.getInitValueMode() == 1) {
                DateTime temp = new DateTime(DateTimeZone.forID(clockT.getLocalMktTz().value()));
                if (temp.isAfter(dateTime)) {
                    dateTime = temp;
                }
            }
            setValue(dateTime);
        } else {
            setValue(new DateTime());
        }
    }

    private void adjustClockByParameterType() {
        if (parameterT instanceof UTCTimestampT) {
            this.dateSpinner = new DateSpinner(new DateTime()
                    .withYear(year(clockT.getInitValue()))
                    .withMonthOfYear(month(clockT.getInitValue()))
                    .withDayOfMonth(dayOfMonth(clockT.getInitValue())));

            this.gridPane.add(this.dateSpinner, this.nextColumn++, 0);
        }
    }

    private int year(XMLGregorianCalendar xmlGregorianCalendar) {
        return xmlGregorianCalendar == null ||
                xmlGregorianCalendar.getYear() == DatatypeConstants.FIELD_UNDEFINED ?
                Year.now().getValue() : xmlGregorianCalendar.getYear();
    }

    private int month(XMLGregorianCalendar xmlGregorianCalendar) {
        return xmlGregorianCalendar == null
                || xmlGregorianCalendar.getMonth() == DatatypeConstants.FIELD_UNDEFINED ?
                MonthDay.now().getMonth().getValue() : xmlGregorianCalendar.getMonth();
    }

    private int dayOfMonth(XMLGregorianCalendar xmlGregorianCalendar) {
        return xmlGregorianCalendar == null
                || xmlGregorianCalendar.getDay() == DatatypeConstants.FIELD_UNDEFINED ?
                MonthDay.now().getDayOfMonth() : xmlGregorianCalendar.getDay();
    }

    @Override
    public void setClockT(ClockT clockT) {
        this.clockT = clockT;
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
    public ObjectProperty<String> listenChange() {
        return controlIdEmitter;
    }

    @Override
    public ClockT getControl() {
        return this.clockT;
    }

    @Override
    public DateTime getValue() {
        DateTime dateTime = this.timeSpinner.getValue();
        if (dateTime == null)
            dateTime = DateTime.now();
        if (dateSpinner != null) {
            DateTime dateTime1 = dateSpinner.getValue();
            if (dateTime1 == null)
                dateTime1 = DateTime.now();
            dateTime.withDayOfMonth(dateTime1.getDayOfMonth());
            dateTime.withMonthOfYear(dateTime1.getMonthOfYear());
            dateTime.withYear(dateTime1.getYear());
        }
        return dateTime;
    }

    @Override
    public void setValue(DateTime s) {
        timeSpinner.setTime(s);
        if (dateSpinner != null)
            dateSpinner.setTime(s);
        setFieldValueToParameter(s, parameterT);
    }

    @Override
    public void makeVisible(boolean visible) {
        gridPane.setVisible(visible);
        if (visible)
            setValue(getValue());
    }

    @Override
    public void makeEnable(boolean enable) {
        gridPane.setDisable(!enable);
        if (enable)
            setValue(getValue());
    }


    @Override
    public TypeConverter<?, ?> getControlTTypeConverter() {
        return this.controlTTypeConverter;
    }
}
