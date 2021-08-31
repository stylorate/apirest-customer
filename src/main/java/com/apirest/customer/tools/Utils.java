package com.apirest.customer.tools;

import com.apirest.customer.controller.service.CustomerService;
import org.apache.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Utils {

    static Logger log = (Logger) Logger.getLogger(Utils.class);

    public static String calculatorDiff(Date date) {
        String pattern = "yyyy-MM-dd";
        String dateString = new SimpleDateFormat(pattern).format(date);
        int anio = Integer.parseInt(dateString.split("-")[0]);
        int mes = Integer.parseInt(dateString.split("-")[1]);
        int dia = Integer.parseInt(dateString.split("-")[2]);
        log.info("date format " + dateString);

        Calendar birth = Calendar.getInstance();
        birth.set(anio, mes - 1, dia);

        Calendar current = Calendar.getInstance();

        int diffYear = current.get(Calendar.YEAR) - birth.get(Calendar.YEAR);
        diffYear = (birth.get(Calendar.DAY_OF_YEAR) > current.get(Calendar.DAY_OF_YEAR))
                ? diffYear-- : diffYear;

        int diffMonth = current.get(Calendar.MONTH) >= birth.get(Calendar.MONTH)
                ? current.get(Calendar.MONTH) - birth.get(Calendar.MONTH)
                : 12 - birth.get(Calendar.MONTH) + current.get(Calendar.MONTH);

        int diffDays = (current.get(Calendar.DAY_OF_MONTH) > birth.get(Calendar.DAY_OF_MONTH))
                ? current.get(Calendar.DAY_OF_MONTH) - birth.get(Calendar.DAY_OF_MONTH)
                : birth.get(Calendar.DAY_OF_MONTH) - current.get(Calendar.DAY_OF_MONTH);

        String diffYearS = diffYear < 9 ? "0" + diffYear : diffYear + "";
        String diffMonthS = diffMonth < 9 ? "0" + diffMonth : diffMonth + "";
        String diffDaysS = --diffDays < 9 ? "0" + diffDays : diffDays + "";
        return diffYearS + "-" + diffMonthS + "-" + diffDaysS;
    }

    public static int calculatorAnios(Date date) {
        String pattern = "yyyy-MM-dd";
        String dateString = new SimpleDateFormat(pattern).format(date);
        int anio = Integer.parseInt(dateString.split("-")[0]);
        int mes = Integer.parseInt(dateString.split("-")[1]);
        int dia = Integer.parseInt(dateString.split("-")[2]);

        Calendar birth = Calendar.getInstance();
        birth.set(anio, mes - 1, dia);

        Calendar current = Calendar.getInstance();

        int diff = current.get(Calendar.YEAR) - birth.get(Calendar.YEAR);

        return (birth.get(Calendar.DAY_OF_YEAR) > current.get(Calendar.DAY_OF_YEAR))
                ? diff-- : diff;
    }
}
