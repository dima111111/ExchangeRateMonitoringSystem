package org.bmstu.services;

import org.bmstu.properties.RateCurrencyProperties;
import org.bmstu.model.RateResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "rateCurrencyClient", url = RateCurrencyProperties.OER_URL)
public interface RateCurrencyClient {

    @GetMapping(RateCurrencyProperties.LATEST + RateCurrencyProperties.APP_ID +
            "&base=" + RateCurrencyProperties.BASE_CUR +
            "&symbols={code}")
    RateResponse getDataLatest(@PathVariable String code);

    @GetMapping(RateCurrencyProperties.HISTORICAL_BEFORE + "{date}" + RateCurrencyProperties.HISTORICAL_AFTER +
            RateCurrencyProperties.APP_ID +
            "&base=" + RateCurrencyProperties.BASE_CUR +
            "&symbols={code}")
    RateResponse getDataHistorical(@PathVariable String date, @PathVariable String code);
}