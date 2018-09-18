package tech.swisslegal.legalsnap.service.remote.google;

import com.google.cloud.translate.Detection;
import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import tech.swisslegal.legalsnap.service.remote.Translator;

@Component
@Slf4j
@Data
public class GoogleTranslatorWrapper implements Translator {

    @Override
    public String translateText(String text, String language) {

        if (text == null || text.isEmpty()) {
            return "n/a";
        }

        Translate translate = TranslateOptions.getDefaultInstance().getService();

        Detection source = translate.detect(text);

        String sourceLanguage = source.getLanguage();

        try {
            Translation translation = translate.translate(text, Translate.TranslateOption.sourceLanguage(sourceLanguage), Translate.TranslateOption.targetLanguage(language));
            return translation.getTranslatedText();
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return text;
    }

}