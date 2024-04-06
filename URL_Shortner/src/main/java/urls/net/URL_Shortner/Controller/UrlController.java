package urls.net.URL_Shortner.Controller;

import urls.net.URL_Shortner.Model.UrlModel;
import urls.net.URL_Shortner.Service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/urls")
public class UrlController {

    @Autowired
    private UrlService urlService;

    @PostMapping
    public UrlModel shortenUrl(@RequestBody Map<String, String> requestBody) {
        String originalUrl = requestBody.get("originalUrl");
        return urlService.shortenUrl(originalUrl);
    }


    @GetMapping("/{shortUrl}")
    public UrlModel getOriginalUrl(@PathVariable String shortUrl) {
        return urlService.getOriginalUrl(shortUrl);
    }

    @GetMapping
    public List<UrlModel> getAllUrls() {
        return urlService.getAllUrls();
    }
}
