package com.three60t.fixatdl.ui.fx8.element;

import java.time.MonthDay;
import java.time.Year;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;

import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.XMLGregorianCalendar;

import com.three60t.fixatdl.converter.JavaDateTimeConverter;
import com.three60t.fixatdl.converter.TypeConverter;
import com.three60t.fixatdl.converter.TypeConverterRepo;
import com.three60t.fixatdl.model.core.ParameterT;
import com.three60t.fixatdl.model.core.UTCDateOnlyT;
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

public class FxFixClockUiElement implements FixClockUiElement<Pane, ZonedDateTime> {

	private TimeSpinner timeSpinner;
	private DateSpinner dateSpinner;

	private GridPane gridPane;
	private ClockT clockT;
	private ParameterT parameterT;
	private ObjectProperty<String> controlIdEmitter = new SimpleObjectProperty<>();
	private int nextColumn = 0;

	private TypeConverter<?, ?> controlTTypeConverter;

	private boolean isInitializedOrHaveValue = false;

	@Override
	public Pane create() {
		if (this.clockT != null) {
			this.gridPane = new GridPane();

			this.controlTTypeConverter = TypeConverterRepo.createParameterTypeConverter(parameterT);

			if (!Utils.isEmptyString(this.clockT.getLabel())) {
				// this.gridPane.getColumnConstraints().addAll(FxUtils.getTwoColumnSameWidthForGridPane());
				this.gridPane.add(new Label(this.clockT.getLabel()), this.nextColumn++, 0);
			}

			this.gridPane.setHgap(3);

			//
			createClockByParameterType();
			initializeControl();
			setFieldValueToParameter(getValue(), parameterT);
			// this.dateSpinner = new DateSpinner(LocalDate.now());
			// this.gridPane.add(this.timeSpinner, this.nextColumn, 0);

			return this.gridPane;
		}
		return null;
	}

	@Override
	public void initializeControl() {
		// LOGIC clockT.getInitValueMode() if 0 then use clockT.getInitValue() otherwise use current time
		// if init value is supplied then have to use localMktTz
		if (this.clockT.getInitValue() != null && this.clockT.getLocalMktTz() != null) {
			// create joda Date time from xmlGregorianCalender with specified timezone
			ZonedDateTime initDateTime = JavaDateTimeConverter
					.convertXMLGregorianCalenderToZonedDateTime(this.clockT.getInitValue(),
							this.clockT.getLocalMktTz());
			// convert the specified date time to current time zone
			initDateTime = initDateTime.withZoneSameInstant(ZoneId.systemDefault());
			
			//initDateTime = initDateTime.toLocalDateTime().atZone(ZoneId.systemDefault());
			// initDateTime = initDateTime.toDateTime();
			// if init mode is 1 then adjust again
			if (this.clockT.getInitValueMode() == 1) {
				// if current time in current time zone is after initialized time converted to
				// current time zone then use current time zone value
				// DateTime currentTimeInSpecifiedTimeZone = new DateTime(DateTimeZone.forID(clockT.getLocalMktTz().value()));
				ZonedDateTime currentTime = ZonedDateTime.now();
				if (currentTime.isAfter(initDateTime)) {
					initDateTime = currentTime;
				}
			}
			setValue(initDateTime);
		} else {
			setValue(ZonedDateTime.now());
			isInitializedOrHaveValue = false;
		}
	}

	/***
	 *
	 */
	private void createClockByParameterType() {
		if (parameterT instanceof UTCTimestampT || parameterT instanceof UTCDateOnlyT) {
			this.dateSpinner = new DateSpinner(ZonedDateTime.now()
					.withYear(year(clockT.getInitValue()))
					.withMonth(month(clockT.getInitValue()))
					.withDayOfMonth(dayOfMonth(clockT.getInitValue())));

			this.dateSpinner.setOnMouseClicked(event -> {
				isInitializedOrHaveValue = true;
				setFieldValueToParameter(getValue(), parameterT);
			});
			this.gridPane.add(this.dateSpinner, this.nextColumn++, 0);
			if (parameterT instanceof UTCDateOnlyT)
				return;
		}
		this.timeSpinner = new TimeSpinner();
		this.timeSpinner.setOnMouseClicked(event -> {
			isInitializedOrHaveValue = true;
			setFieldValueToParameter(getValue(), parameterT);
		});
		this.gridPane.add(this.timeSpinner, this.nextColumn, 0);
	}

	private int year(XMLGregorianCalendar xmlGregorianCalendar) {
		return xmlGregorianCalendar == null ||
				xmlGregorianCalendar.getYear() == DatatypeConstants.FIELD_UNDEFINED ? Year.now().getValue() : xmlGregorianCalendar.getYear();
	}

	private int month(XMLGregorianCalendar xmlGregorianCalendar) {
		return xmlGregorianCalendar == null
				|| xmlGregorianCalendar.getMonth() == DatatypeConstants.FIELD_UNDEFINED ? MonthDay.now().getMonth().getValue()
						: xmlGregorianCalendar.getMonth();
	}

	private int dayOfMonth(XMLGregorianCalendar xmlGregorianCalendar) {
		return xmlGregorianCalendar == null
				|| xmlGregorianCalendar.getDay() == DatatypeConstants.FIELD_UNDEFINED ? MonthDay.now().getDayOfMonth() : xmlGregorianCalendar.getDay();
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

	/****
	 * if date part is absent then consider date as undefined
	 * if Time part is absent then set those field as undefined
	 * Rest of the setting must be handled at the time of Data
	 * type conversion
	 * 
	 * @return
	 */
	@Override
	public ZonedDateTime getValue() {
		if (!isInitializedOrHaveValue)
			return null;
		ZonedDateTime dateTime = ZonedDateTime.now();
		if (this.dateSpinner != null && this.dateSpinner.getValue() != null) {
			ZonedDateTime dateSpinnerDateTime = ZonedDateTime.from(this.dateSpinner.getValue());
			dateTime = dateTime.withDayOfMonth(dateSpinnerDateTime.getDayOfMonth())
					.withMonth(dateSpinnerDateTime.getMonth().getValue())
					.withYear(dateSpinnerDateTime.getYear());
		}
		if (this.timeSpinner != null && this.timeSpinner.getValue() != null) {
			ZonedDateTime timeSpinnerDateTime = ZonedDateTime.from(this.timeSpinner.getValue());
			dateTime = dateTime.withHour(timeSpinnerDateTime.getHour())
					.withMinute(timeSpinnerDateTime.getMinute())
					.withSecond(timeSpinnerDateTime.getSecond());
		}
		return dateTime;
	}

	@Override
	public void setValue(ZonedDateTime s) {
		if (this.timeSpinner != null)
			this.timeSpinner.setTime(s);
		if (this.dateSpinner != null)
			this.dateSpinner.setTime(s);
		setFieldValueToParameter(s, parameterT);
		isInitializedOrHaveValue = true;
	}

	@Override
	public void makeVisible(boolean visible) {
		gridPane.setVisible(visible);
	}

	@Override
	public void makeEnable(boolean enable) {
		gridPane.setDisable(!enable);
		if (enable)
			initializeControl();
	}

	@Override
	public TypeConverter<?, ?> getControlTTypeConverter() {
		return this.controlTTypeConverter;
	}
}
