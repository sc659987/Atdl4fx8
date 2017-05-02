package com.three60t.fixatdl.ui.fx8.element;

import com.three60t.fixatdl.converter.ControlTTypeConverter;
import com.three60t.fixatdl.converter.TypeConverterFactory;
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

import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.XMLGregorianCalendar;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.SignStyle;
import java.time.temporal.ChronoField;
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

    private ControlTTypeConverter<?> controlTTypeConverter;

    @Override
    public Pane create() {
        if (this.clockT != null) {
            this.gridPane = new GridPane();

            this.controlTTypeConverter = TypeConverterFactory.createControlTypeConverter(clockT, parameterT);

            if (!Utils.isEmpty(this.clockT.getLabel()))
                this.gridPane.add(new Label(this.clockT.getLabel()), this.nextColumn++, 0);

            this.gridPane.setHgap(3);
            this.timeSpinner = new TimeSpinner();
            this.timeSpinner.setOnMouseClicked(event -> setFieldValueToParameter(getValue(), parameterT));


            // LOGIC clockT.getInitValueMode() if 0 then use clockT.getInitValue() otherwise use current time
            // if init value is supplied then have to use local Mkt Tz

            if (this.clockT.getInitValue() != null && this.clockT.getLocalMktTz() != null) {
                XMLGregorianCalendar xmlGregorianCalendar = this.clockT.getInitValue();

//                setValue(xmlGregorianCalendar.getHour() + ":"
//                        + xmlGregorianCalendar.getMinute() + ":"
//                        + xmlGregorianCalendar.getSecond());
            } else {
                setValue(getValue());
            }
            createByParameter();
            //this.dateSpinner = new DateSpinner(LocalDate.now());
            this.gridPane.add(this.timeSpinner, this.nextColumn, 0);

            return this.gridPane;
        }
        return null;
    }

    private void createByParameter() {
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
        return this.timeSpinner.getValue();
    }

    @Override
    public void setValue(DateTime s) {
        // TODO check parse DateTime
        //if (Utils.isNonEmpty(s))
//            timeSpinner.setTime(s.equals(NULL_VALUE) ? DateTime.now() : LocalTime.parse(s, ATDL_TIME_ONLY_FORMATTER));
        timeSpinner.setTime(DateTime.now());

        setFieldValueToParameter(getValue(), parameterT);
    }

    // TODO clean the code on 13-04-2017 after 12 PM
    private DateTimeFormatter ATDL_TIME_ONLY_FORMATTER = new DateTimeFormatterBuilder()
            .appendValue(java.time.temporal.ChronoField.HOUR_OF_DAY, 1, 2, SignStyle.NEVER)
            .appendLiteral(':')
            .appendValue(java.time.temporal.ChronoField.MINUTE_OF_HOUR, 1, 2, SignStyle.NEVER)
            .optionalStart()
            .appendLiteral(':')
            .appendValue(java.time.temporal.ChronoField.SECOND_OF_MINUTE, 1, 2, SignStyle.NEVER)
            .optionalStart()
            .appendFraction(java.time.temporal.ChronoField.NANO_OF_SECOND, 0, 9, true)
            .toFormatter();

    private DateTimeFormatter ATDL_DATE_ONLY_FORMATTER = new DateTimeFormatterBuilder()
            .appendValue(ChronoField.DAY_OF_MONTH, 1, 2, SignStyle.NEVER)
            .appendLiteral('.')
            .appendValue(ChronoField.MONTH_OF_YEAR, 1, 2, SignStyle.NEVER)
            .appendLiteral('.')
            .appendValue(ChronoField.YEAR, 1, 2, SignStyle.NEVER)
            .optionalStart().toFormatter();

    @Override
    public void makeVisible(boolean visible) {
        gridPane.setVisible(visible);
    }

    @Override
    public void makeEnable(boolean enable) {
        gridPane.setDisable(!enable);
    }


    @Override
    public ControlTTypeConverter<?> getControlTTypeConverter() {
        return this.controlTTypeConverter;
    }
}
