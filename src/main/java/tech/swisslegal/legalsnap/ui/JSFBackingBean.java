package tech.swisslegal.legalsnap.ui;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import tech.swisslegal.legalsnap.model.KeyVal;
import tech.swisslegal.legalsnap.service.Service;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope("session")
@Slf4j
@Data
public class JSFBackingBean {

    @Autowired
    private Service service;

    private boolean disclaimer = false;
    private boolean textOk = false;

    private String text;

    private UploadedFile file;

    private String language = "";

    public String getPage() {
        if (language.isEmpty()) {
            return "language";
        }
        if (!disclaimer) {
            return "disclaimer";
        }
        if (file == null) {
            return "upload";
        }
        return "process";
    }

    public List<KeyVal> getLanguages() {
        return service.loadKeyVals("googlelanguages.txt");
    }

    public void handleFileUpload(FileUploadEvent event) {
        file = event.getFile();
        ocr();
    }

    public String translate(String in) {
        if (language == null || language.isEmpty()) {
            return in;
        }
        return service.translateFromGerman(in, language);
    }

    public String getText() {
        return text;
    }

    public String getTextParsed() {

        if (!textOk) {
            return "";
        }
        String raw = getText();

        List<KeyVal> easyText = service.loadKeyVals("Simplified.txt");

        for (KeyVal rep : easyText) {
            if (raw.contains(rep.getKey())) {
                raw = raw.replace(rep.getKey(), "<b>" + rep.getValue() + "</b>");
            }
        }
        return raw;
    }

    public List<KeyVal> lookupKeywords() {

        List<KeyVal> ret = new ArrayList<>();

        String text = getText();
        for (KeyVal k : service.loadKeyVals("KeyStatement.txt")) {
            if (text.contains(k.getKey())) {
                ret.add(k);
            }
        }
        return ret;

    }

    public String getTextTranslatedParsed() {
        if (!textOk) {
            return "";
        }
        return service.getTranslatedText(getTextParsed(), language);
    }

    public void reset() {
        file = null;
        textOk = false;
        text = "";
        language = "";
    }

    public String getLan() {
        if (language == null || language.isEmpty()) {
            return "";
        }
        String lan = "";
        for (KeyVal in : service.loadKeyVals("googlelanguages.txt")) {
            if (in.getValue().equals(language)) {
                lan = in.getKey();
            }
        }
        return translate("Ausgewählte Sprache: ") + lan;
    }

    private void ocr() {
        text = service.getText(file.getContents());
        textOk = true;
    }

}