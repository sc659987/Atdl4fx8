package com.three60t.fixatdl.ui.fx8.customelement;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextFormatter;
import javafx.util.StringConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.SignStyle;

import static java.time.temporal.ChronoField.*;

/**
 * Created by sainik on 4/27/17.
 */
public class DateSpinner extends Spinner<LocalDate> {

    public enum Mode {
        DAY {
            @Override
            LocalDate increment(LocalDate date, int steps) {
                return date.plusDays(steps);
            }

            @Override
            void select(DateSpinner spinner) {
                int index = spinner.getEditor().getText().indexOf('.');
                spinner.getEditor().selectRange(0, index);

            }
        },
        MONTH {
            @Override
            LocalDate increment(LocalDate date, int steps) {
                return date.plusMonths(steps);
            }

            @Override
            void select(DateSpinner spinner) {
                int dayIndex = spinner.getEditor().getText().indexOf('.');
                int monthIndex = spinner.getEditor().getText().indexOf('.', dayIndex + 1);
                spinner.getEditor().selectRange(dayIndex + 1, monthIndex);
            }
        },
        YEAR {
            @Override
            LocalDate increment(LocalDate date, int steps) {
                return date.plusYears(steps);
            }

            @Override
            void select(DateSpinner spinner) {
                int index = spinner.getEditor().getText().lastIndexOf('.');
                spinner.getEditor().selectRange(index + 1, spinner.getEditor().getText().length());
            }
        };


        abstract LocalDate increment(LocalDate date, int steps);

        abstract void select(DateSpinner spinner);

        LocalDate decrement(LocalDate date, int steps) {
            return increment(date, -steps);
        }
    }


    DateTimeFormatter formatter = new DateTimeFormatterBuilder()
            .appendValue(DAY_OF_MONTH, 2)
            .appendLiteral('.')
            .appendValue(MONTH_OF_YEAR, 2)
            .appendLiteral('.')
            .appendValue(YEAR, 4, 4, SignStyle.NEVER)

            .toFormatter();


    StringConverter<LocalDate> localDateConverter = new StringConverter<LocalDate>() {
        @Override
        public String toString(LocalDate date) {
            return formatter.format(date);
        }

        @Override
        public LocalDate fromString(String string) {
            String[] tokens = string.split("\\.");
            int day = getIntField(tokens, 0);
            int month = getIntField(tokens, 1);
            int year = getIntField(tokens, 2);
            return LocalDate.of(year, month, day);

        }

        private int getIntField(String[] tokens, int index) {
            if (tokens.length <= index || tokens[index].isEmpty()) {
                return 0;
            }
            return Integer.parseInt(tokens[index]);
        }
    };

    TextFormatter<LocalDate> textFormatter = new TextFormatter<>(localDateConverter, LocalDate.now(), c -> {
        String newText = c.getControlNewText();
        if (newText.matches("[0-9]{0,2}.[0-9]{0,2}.[0-9]{4}")) {
            return c;
        }
        return null;
    });

    SpinnerValueFactory<LocalDate> valueFactory = new SpinnerValueFactory<LocalDate>() {

        {
            setConverter(localDateConverter);
            setValue(date);
        }

        @Override
        public void decrement(int steps) {
            setValue(mode.get().decrement(getValue(), steps));
            mode.get().select(DateSpinner.this);
        }

        @Override
        public void increment(int steps) {
            setValue(mode.get().increment(getValue(), steps));
            mode.get().select(DateSpinner.this);
        }

    };

    private LocalDate date;

    private final ObjectProperty<Mode> mode = new SimpleObjectProperty<Mode>(Mode.DAY);

    public ObjectProperty<DateSpinner.Mode> modeProperty() {
        return mode;
    }

    public final DateSpinner.Mode getMode() {
        return modeProperty().get();
    }

    public final void setMode(DateSpinner.Mode mode) {
        modeProperty().set(mode);
    }

    public void setTime(LocalDate date) {
        this.date = date;
        valueFactory.setValue(this.date);
    }


    public DateSpinner(LocalDate date) {
        setEditable(true);
        this.date = date;
        this.valueFactory.setValue(this.date);

        this.setValueFactory(valueFactory);
        this.getEditor().setTextFormatter(textFormatter);

        this.getEditor().addEventHandler(javafx.scene.input.InputEvent.ANY, e -> {
            int caretPos = this.getEditor().getCaretPosition();
            int dayIndex = this.getEditor().getText().indexOf('.');
            int monthIndex = this.getEditor().getText().indexOf('.', dayIndex + 1);
            if (caretPos <= dayIndex) {
                mode.set(Mode.DAY);
            } else if (caretPos <= monthIndex) {
                mode.set(Mode.MONTH);
            } else {
                mode.set(Mode.YEAR);
            }
        });

        mode.addListener((observable, oldValue, newValue) -> newValue.select(this));


    }


}
