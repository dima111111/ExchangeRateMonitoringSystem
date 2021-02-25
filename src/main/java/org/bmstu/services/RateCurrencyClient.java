package org.bmstu.services;

import org.bmstu.model.RateResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "rateCurrencyClient", url = "${rate.service.url}")
public interface RateCurrencyClient {

    @GetMapping("${rate.service.latest}" + "${rate.service.app_id}" +
            "&base=" + "${rate.service.base_cur}" +
            "&symbols={code}")
    RateResponse getDataLatest(@PathVariable String code);

    @GetMapping("${rate.service.historical_before}" + "{date}" + "${rate.service.historical_after}" +
            "${rate.service.app_id}" +
            "&base=" + "${rate.service.base_cur}" +
            "&symbols={code}")
    RateResponse getDataHistorical(@PathVariable String date, @PathVariable String code);
}