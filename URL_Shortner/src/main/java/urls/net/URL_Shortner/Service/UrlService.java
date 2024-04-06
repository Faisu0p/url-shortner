package urls.net.URL_Shortner.Service;

import urls.net.URL_Shortner.Model.UrlModel;
import urls.net.URL_Shortner.Repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class UrlService {

    @Autowired
    private UrlRepository urlRepository;

    public UrlModel shortenUrl(String originalUrl) {
        // Generate a short URL here (You can use any algorithm for this)
        String shortUrl = generateShortUrl(originalUrl);

        // Create a new UrlModel object
        UrlModel urlModel = new UrlModel();
        urlModel.setOriginalUrl(originalUrl);
        urlModel.setShortUrl(shortUrl);

        // Save the UrlModel object in the database
        return urlRepository.save(urlModel);
    }


    //Algorithm --> MD5 Hash
    private String generateShortUrl(String originalUrl) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(originalUrl.getBytes());
            byte[] digest = md.digest();
            BigInteger no = new BigInteger(1, digest);
            String hashText = no.toString(16);
            while (hashText.length() < 32) {
                hashText = "0" + hashText;
            }
            return hashText.substring(0, 7);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null; // Handle the exception as needed
        }
    }


    public UrlModel getOriginalUrl(String shortUrl) {
        return urlRepository.findByShortUrl(shortUrl);
    }

    public List<UrlModel> getAllUrls() {
        return urlRepository.findAll();
    }
}
