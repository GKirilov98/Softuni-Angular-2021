package softuni.angular.utils.joda;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

import java.io.IOException;

/**
 * Project: backend
 * Created by: GKirilov
 * On: 10/23/2021
 */
public class JodaTimeSerializer extends StdSerializer<DateTime> {

    private final DateTimeFormatter _dtf = ISODateTimeFormat.dateTime();

    public JodaTimeSerializer() {
        super(DateTime.class);
    }

    @Override
    public void serialize(DateTime value, JsonGenerator jsonGenerator, SerializerProvider provider)
            throws IOException {
        jsonGenerator.writeStartObject();
        if (value != null) {
            value = value.toDateTime(DateTimeZone.UTC);
            jsonGenerator.writeObjectField("date", null);
            jsonGenerator.writeObjectField("month", null);
            jsonGenerator.writeObjectField("year", null);
            jsonGenerator.writeObjectField("hours", null);
            jsonGenerator.writeObjectField("minutes", null);
            jsonGenerator.writeObjectField("seconds", null);
            jsonGenerator.writeObjectField("miliseconds", null);
            jsonGenerator.writeNumberField("date", value.getDayOfMonth());
            jsonGenerator.writeObjectField("month", value.getMonthOfYear());
            jsonGenerator.writeObjectField("year", value.getYear());
            jsonGenerator.writeObjectField("hours", value.getHourOfDay());
            jsonGenerator.writeObjectField("minutes", value.getMinuteOfHour());
            jsonGenerator.writeObjectField("seconds", value.getSecondOfMinute());
            jsonGenerator.writeObjectField("miliseconds", value.getMillisOfSecond());
            jsonGenerator.writeEndObject();
        }
    }
}