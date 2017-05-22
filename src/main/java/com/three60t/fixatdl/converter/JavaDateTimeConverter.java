package com.three60t.fixatdl.converter;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Optional;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import com.three60t.fixatdl.model.core.*;
import com.three60t.fixatdl.model.timezones.Timezone;

public class JavaDateTimeConverter implements TypeConverter<ZonedDateTime, ParameterT> {

	private ParameterT parameterT;
	private Timezone timezone;

	public JavaDateTimeConverter(ParameterT parameterT) {
		this.parameterT = parameterT;
		this.timezone = getLocalMktTz(parameterT);
	}

	/***
	 * 
	 * @param parameter
	 * @return
	 */
	private Timezone getLocalMktTz(ParameterT parameter) {
		if (parameter instanceof UTCTimestampT) {
			return ((UTCTimestampT) parameter).getLocalMktTz();
		} else if (parameter instanceof UTCTimeOnlyT) {
			return ((UTCTimeOnlyT) parameter).getLocalMktTz();
		}
		return null;
	}

	public ZonedDateTime convertParameterConstToComparable() {
		Object object = getConstFieldOfParam();
		if (object instanceof XMLGregorianCalendar) {
			return convertXMLGregorianCalenderToZonedDateTime((XMLGregorianCalendar) object, this.timezone);
		}
		return null;
	}

	public static ZonedDateTime convertXMLGregorianCalenderToZonedDateTime(XMLGregorianCalendar calendar, Timezone timezone) {
		shiftTimeZone(calendar, timezone);
		return ZonedDateTime.now().withYear(calendar.getYear())
				.withMonth(calendar.getMonth())
				.withDayOfMonth(calendar.getDay())
				.withHour(calendar.getHour())
				.withMinute(calendar.getMinute())
				.withSecond(calendar.getSecond())
				.withZoneSameLocal(convertTimezoneToDateTimeZone(timezone)
						.orElse(ZoneId.systemDefault()));
	}

	@Override
	public ParameterT getParameter() {
		return this.parameterT;
	}

	@Override
	public String convertParameterConstToFixWireValue() {
		ZonedDateTime zonedDateTime = convertParameterConstToComparable();
		if (zonedDateTime != null) {
			DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(getFormatString());
			return dateTimeFormatter
					.withZone(ZoneId.of("UTC"))
					.format(zonedDateTime);
		}
		return null;
	}

	private String getFormatString() {
		if (parameterT != null) {
			if (parameterT instanceof LocalMktDateT) {
				return "yyyyMMdd";
			} else if (parameterT instanceof MonthYearT) {
				return "yyyyMM";
			} else if (parameterT instanceof UTCDateOnlyT) {
				return "yyyyMMdd";
			} else if (parameterT instanceof UTCTimeOnlyT) {
				return "HH:mm:ss";
			} else if (parameterT instanceof UTCTimestampT) {
				return "yyyyMMdd-HH:mm:ss";
			} else if (parameterT instanceof TZTimeOnlyT) {
				return "HH:mm:ssZZ";
			} else if (parameterT instanceof TZTimestampT) {
				return "yyyyMMdd-HH:mm:ssZZ";
			}
		}
		return "yyyyMMdd-HH:mm:ss";
	}

	@Override
	public Object convertControlValueToParameterValue(Object value) {
		if (value instanceof ZonedDateTime) {
			ZonedDateTime zonedDateTime = (ZonedDateTime) value;
			try {
				return DatatypeFactory.newInstance()
						.newXMLGregorianCalendar(zonedDateTime.getYear(),
								zonedDateTime.getMonthValue(),
								zonedDateTime.getDayOfMonth(),
								zonedDateTime.getHour(),
								zonedDateTime.getMinute(),
								zonedDateTime.getSecond(),
								0,
								zonedDateTime.getOffset().getTotalSeconds() / 60);
			} catch (DatatypeConfigurationException e) {

			}

		}
		return null;
	}

	@Override
	public ZonedDateTime convertStringToControlValue(String aString) {
		return null;
	}

	public static void shiftTimeZone(final XMLGregorianCalendar calendar, final Timezone timezone) {
		// Check if calender is null, if so then nothing can be done
		Objects.requireNonNull(calendar);
		LocalDateTime now = LocalDateTime.now();
		ZonedDateTime zonedDateTime = ZonedDateTime.now();
		try {
			convertTimezoneToDateTimeZone(timezone)
					.ifPresent(zoneId -> calendar.setTimezone(zoneId.getRules().getOffset(now).getTotalSeconds() / 60));
			zonedDateTime = ZonedDateTime.now(ZoneId.of(timezone.value()));
		} catch (Exception e) {
		} finally {
			if (isFieldUndefined(calendar.getDay()))
				calendar.setDay(zonedDateTime.getDayOfMonth());
			if (isFieldUndefined(calendar.getMonth()))
				calendar.setMonth(zonedDateTime.getMonthValue());
			if (isFieldUndefined(calendar.getYear()))
				calendar.setYear(zonedDateTime.getYear());
			if (isFieldUndefined(calendar.getHour()))
				calendar.setHour(zonedDateTime.getHour());
			if (isFieldUndefined(calendar.getMinute()))
				calendar.setMinute(zonedDateTime.getMinute());
			if (isFieldUndefined(calendar.getSecond()))
				calendar.setSecond(zonedDateTime.getSecond());
		}
	}

	private static boolean isFieldUndefined(int fieldValue) {
		return fieldValue == DatatypeConstants.FIELD_UNDEFINED;

	}

	public static Optional<ZoneId> convertTimezoneToDateTimeZone(final Timezone timezone) {
		return timezone != null ? Optional.of(ZoneId.of(timezone.value())) : Optional.empty();
	}

}
