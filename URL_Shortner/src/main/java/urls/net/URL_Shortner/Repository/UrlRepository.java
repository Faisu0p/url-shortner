package urls.net.URL_Shortner.Repository;

import urls.net.URL_Shortner.Model.UrlModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlRepository extends MongoRepository<UrlModel, String> {
    UrlModel findByShortUrl(String shortUrl);
}
