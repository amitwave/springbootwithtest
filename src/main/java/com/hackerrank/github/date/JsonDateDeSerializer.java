package com.hackerrank.github.date;


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

@JsonComponent
public class JsonDateDeSerializer extends JsonDeserializer<Timestamp> {

    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final SimpleDateFormat sdf1 = new SimpleDateFormat(DATE_FORMAT);


    @Override
    public Timestamp deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {


        try {
            String dateString = jsonParser.getText();
            if (dateString==null) {
                return null;
            } else {
                //return new Date(sdf1.parse(dateString).getTime()).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
                return Timestamp.from(new Date(sdf1.parse(dateString).getTime()).toInstant());
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}