package util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class InteleQuizUtil {

    public static String formataData(Date data) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(data);
    }
}
