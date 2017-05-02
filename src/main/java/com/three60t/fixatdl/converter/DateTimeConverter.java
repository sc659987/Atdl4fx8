package com.three60t.fixatdl.converter;

import com.three60t.fixatdl.model.core.*;
import com.three60t.fixatdl.model.layout.ControlT;
import com.three60t.fixatdl.model.timezones.Timezone;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import javax.xml.datatype.XMLGregorianCalendar;

/**
 * Created by sainik on 01/05/17.
 */
public class DateTimeConverter implements ParameterTTypeConverter<DateTime, ParameterT>,
        ControlTTypeConverter<DateTime> {

    private ParameterT parameterT;
    private Timezone timezone = null;

    public DateTimeConverter(ParameterT parameterT) {
        this.parameterT = parameterT;
    }

    @Override
    public DateTime convertParameterConstToComparable() {
        Object aValue = getConstFieldOfParam();
        if (aValue instanceof DateTime) {
            return (DateTime) aValue;
        } else if (aValue instanceof XMLGregorianCalendar) {
            return convertXMLGregorianCalendarToDateTime(
                    (XMLGregorianCalendar) aValue,
                    getTimezone());
        } else {
            return null;
        }
    }

    @Override
    public ParameterT getParameter() {
        return this.parameterT;
    }

    @Override
    public String convertParameterConstToFixWireValue() {
        DateTime date = convertParameterConstToComparable();
        if (date != null) {
            DateTimeFormatter fmt = DateTimeFormat.forPattern(getFormatString());
            return fmt.withZone(DateTimeZone.UTC).print(date);
        } else {
            return null;
        }
    }

    @Override
    public Object convertFixWireValueToParameterConst(String aFixWireValue) {
        if (aFixWireValue != null) {
            String str = (String) aFixWireValue;
            String format = getFormatString();
            DateTimeFormatter fmt = DateTimeFormat.forPattern(format);
            try {
                if (getParameter() == null ||
                        getParameter() instanceof UTCTimeOnlyT ||
                        getParameter() instanceof UTCTimestampT) {
                    DateTime tempDateTime = fmt.withZone(DateTimeZone.UTC).parseDateTime(str);
                    return tempDateTime;
                } else {
                    return fmt.parseDateTime(str);
                }
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Unable to parse \"" + str + "\" with format \"" + format + "\"  Execption: " + e.getMessage());
            }
        }
        return null;
        // TODO Set to parameter
    }

    public Timezone getTimezone() {
        return this.timezone;
    }

    private String getFormatString() {
        if (getParameter() != null) {
            if (getParameter() instanceof LocalMktDateT) {
                return "yyyyMMdd";
            } else if (getParameter() instanceof MonthYearT) {
                return "yyyyMM";
            } else if (getParameter() instanceof UTCDateOnlyT) {
                return "yyyyMMdd";
            } else if (getParameter() instanceof UTCTimeOnlyT) {
                return "HH:mm:ss";
            } else if (getParameter() instanceof UTCTimestampT) {
                return "yyyyMMdd-HH:mm:ss";
            }
            // TODO: Uncomment when TZTimestamp / TZTimeOnly becomes available
            /*
             * else if (getParameter() instanceof TZTimeOnlyT) { return "HH:mm:ssZZ"; }
			 * else if (getParameter() instanceof TZTimestampT) { return
			 * "yyyyMMdd-HH:mm:ssZZ"; }
			 */
        }
        return "yyyyMMdd-HH:mm:ss";
    }


    public static DateTime convertXMLGregorianCalendarToDateTime(XMLGregorianCalendar aXMLGregorianCalendar, Timezone aTimezone) {
        // -- DateTime(int year, int monthOfYear, int dayOfMonth, int hourOfDay, int minuteOfHour, int secondOfMinute, int millisOfSecond) --
        int tempSubsecond = 0;
        if (aXMLGregorianCalendar.getFractionalSecond() != null) {
            tempSubsecond = aXMLGregorianCalendar.getFractionalSecond().intValue();
        }

        DateTimeZone tempDateTimeZone = convertTimezoneToDateTimeZone(aTimezone);
        if (tempDateTimeZone == null) {
            tempDateTimeZone = DateTimeZone.getDefault();
        }

        return new DateTime(aXMLGregorianCalendar.getYear(),
                aXMLGregorianCalendar.getMonth(),
                aXMLGregorianCalendar.getDay(),
                aXMLGregorianCalendar.getHour(),
                aXMLGregorianCalendar.getMinute(),
                aXMLGregorianCalendar.getSecond(),
                tempSubsecond,
                tempDateTimeZone);
    }


    public static DateTimeZone convertTimezoneToDateTimeZone(Timezone aTimezone) {
        if (aTimezone != null) {
            return DateTimeZone.forID(aTimezone.value());
        } else {
            return null;
        }
    }


    @Override
    public Object convertControlValueToParameterValue(Object aValue) {
        if (aValue instanceof XMLGregorianCalendar) {
            return convertXMLGregorianCalendarToDateTime((XMLGregorianCalendar) aValue, getTimezone());
        } else {
            return (DateTime) aValue;
        }
    }

    @Override
    public DateTime convertParameterValueToControlValue(Object aValue, ControlT aControl) {
        Object tempValue = adjustParameterValueForEnumRefValue(aValue, getParameter(), aControl);
        if (tempValue instanceof DateTime) {
            return (DateTime) tempValue;
        } else if (tempValue instanceof XMLGregorianCalendar) {
            return convertXMLGregorianCalendarToDateTime((XMLGregorianCalendar) tempValue, getTimezone());
        } else {
            return null;
        }
    }

    @Override
    public DateTime convertControlValueToControlComparable(Object aValue) {
        if (aValue instanceof DateTime) {
            return (DateTime) aValue;
        } else if (aValue instanceof XMLGregorianCalendar) {
            return convertXMLGregorianCalendarToDateTime((XMLGregorianCalendar) aValue, getTimezone());
        } else if (aValue instanceof String) {
            String str = (String) aValue;
            String format = getFormatString();
            DateTimeFormatter fmt = DateTimeFormat.forPattern(format);
            try {
                if ((getParameter() instanceof UTCTimeOnlyT) || (getParameter() instanceof UTCTimestampT)) {
                    DateTime tempDateTime = fmt.parseDateTime(str);
                    return tempDateTime.withZone(DateTimeZone.UTC);
                }

				/*
                 * else if (getParameter() instanceof TZTimestamp || getParameter() instanceof
				 * TZTimeOnlyT) { return fmt.withOffsetParsed().parseDateTime(str);
				 * }
				 */
                else {
                    return fmt.parseDateTime(str);
                }
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Unable to parse \"" + str + "\" with format \"" + format + "\"  Exception: " + e.getMessage());
            }
        } else {
            return null;
        }
    }

    @Override
    public DateTime convertStringToControlValue(String aString) {
        return convertControlValueToControlComparable(aString);
    }

}
