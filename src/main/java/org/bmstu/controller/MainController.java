package org.bmstu.controller;

import org.bmstu.model.GifObject;
import org.bmstu.properties.GiphyProperties;
import org.bmstu.properties.RateCurrencyProperties;
import org.bmstu.services.GiphyClient;
import org.bmstu.services.RateCurrencyClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
public class MainController {

    @Autowired
    RateCurrencyClient serviceRateClient;

    @Autowired
    GiphyClient serviceGiphyClient;

    @RequestMapping("/rate/{id}")
    public String rate(@PathVariable("id") String id) {
        Double currentCourse = serviceRateClient.getDataLatest(id).getRates().get(id);
        Double previousCourse = serviceRateClient.getDataHistorical(getYesterdayDateString(), id).getRates().get(id);
        return "Текущий курс по отношению к базовой валюте " + RateCurrencyProperties.BASE_CUR + " валюты " + id + " составляет " + currentCourse + "\n" +
                "Вчерашний курс по отношению к базовой валюте " + RateCurrencyProperties.BASE_CUR + " валюты " + id + " составляет " + previousCourse ;
    }

    private static Date yesterday() {
        final Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        return cal.getTime();
    }

    private static String getYesterdayDateString() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        return dateFormat.format(yesterday());
    }

    @RequestMapping("/rate/history/{id}")
    public String rateHistory(@PathVariable("id") String id) {

        Double currentCourse = serviceRateClient.getDataLatest(id).getRates().get(id);
        Double previousCourse = serviceRateClient.getDataHistorical(getYesterdayDateString(), id).getRates().get(id);

        int randomNumber = (int) (Math.random() * GiphyProperties.MAX_GIF_COUNT);
        GifObject gif;
        if (currentCourse > previousCourse) {
            gif = serviceGiphyClient.searchGif(GiphyProperties.RICH, randomNumber).getGifObject();
        } else {
            gif = serviceGiphyClient.searchGif(GiphyProperties.BROKE, randomNumber).getGifObject();
        }
        return "<iframe src=\"" + gif.getUrl() + "\" width=\"800\" height=\"600\" frameBorder=\"0\" style=\"margin: 0 auto;display: block;\"></iframe>";
    }
}