package hu.cubix.cloud.call.api;

import hu.cubix.cloud.call.model.BackappResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "backapp", url = "${back.url:http://localhost}", path = "/backapp")
public interface BackappApi {

    @GetMapping
    BackappResponse backapp(@RequestParam(required = false, name = "message") String message);
}
