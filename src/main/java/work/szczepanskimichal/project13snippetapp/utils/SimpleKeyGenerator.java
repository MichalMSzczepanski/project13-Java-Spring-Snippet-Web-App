package work.szczepanskimichal.project13snippetapp.utils;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

@Component
public class SimpleKeyGenerator implements KeyGenerator{

    @Override
    public String generateApiKey() {
        return RandomStringUtils.random(32, true, true);
    }

    @Override
    public String generateAccountKey() {
        return RandomStringUtils.random(16, true, true);
    }

}
