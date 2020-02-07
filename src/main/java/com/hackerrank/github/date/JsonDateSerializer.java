package com.hackerrank.github.date;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

@JsonComponent
public class JsonDateSerializer extends JsonSerializer<Timestamp> {
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final SimpleDateFormat sdf1 = new SimpleDateFormat(DATE_FORMAT);


    @Override
    public void serialize(Timestamp timestamp, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        String formattedDate = sdf1.format(timestamp);
        jsonGenerator.writeString(formattedDate);
    }


}