package com.example.skyfast_2_0.config;

import java.beans.PropertyEditorSupport;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimestampPropertyEditor extends PropertyEditorSupport {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (text == null || text.isEmpty()) {
            setValue(null);
        } else {
            LocalDateTime localDateTime = LocalDateTime.parse(text, FORMATTER);
            setValue(Timestamp.valueOf(localDateTime));
        }
    }

    @Override
    public String getAsText() {
        Timestamp value = (Timestamp) getValue();
        return (value != null ? FORMATTER.format(value.toLocalDateTime()) : "");
    }
}