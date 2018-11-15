package by.iba.student.reader;

import by.iba.student.common.Marks;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MarkLineMapper implements LineMapper<Marks> {
    @Override
    public Marks mapLine(String line) {
        String[] data = line.split(";");
        Marks mark = null;
        if (data.length > 6) {
            try {
                mark = new Marks(data[1], data[2], data[3], Double.valueOf(data[4]), new SimpleDateFormat("yyyy-MM-dd").parse(data[5]), data[6]);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else {
            try {
                mark = new Marks(data[1], data[2], data[3], Double.valueOf(data[4]), new SimpleDateFormat("yyyy-MM-dd").parse(data[5]), "");
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        mark.setId(Integer.valueOf(data[0]));
        return mark;
    }
}
