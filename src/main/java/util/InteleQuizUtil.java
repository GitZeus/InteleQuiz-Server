package util;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class InteleQuizUtil {
    
    private static final boolean DEBUG_MODE = true;

    public static String formatData(Date data) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(data);
    }

    public static Double formatDecimal(Double numero) {
        DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(new Locale("pt-BR"));
        otherSymbols.setDecimalSeparator('.');
        otherSymbols.setGroupingSeparator(',');
        DecimalFormat df = new DecimalFormat("##0.00", otherSymbols);
        Double numeroFormatado = new Double(df.format(numero));
        return numeroFormatado;
    }

    public static void printExceptionLog(Exception e) {
        if(DEBUG_MODE){
            e.printStackTrace();
        }
    }
}
