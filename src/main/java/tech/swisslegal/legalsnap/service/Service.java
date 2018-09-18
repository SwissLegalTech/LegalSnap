package tech.swisslegal.legalsnap.service;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import tech.swisslegal.legalsnap.model.KeyVal;
import tech.swisslegal.legalsnap.service.remote.OpticalCharacterRecognition;
import tech.swisslegal.legalsnap.service.remote.google.GoogleTranslatorWrapper;

import java.util.ArrayList;
import java.util.List;


/**
 * @author $Author: daniel.marthaler@sbb.ch $
 * @since 2018
 */
@Component
@Slf4j
@Data
public class Service {

    @Autowired
    private OpticalCharacterRecognition ocr;

    @Autowired
    private GoogleTranslatorWrapper translator;

    @Autowired
    private ResourceLoader resourceLoader;

    public String getText(byte[] file) {
        return ocr.recognizeText(file);
    }

    //@Cacheable(cacheNames = "translations", key = "{#in,#targt}")
    public String getTranslatedText(String in, String target) {
        return translator.translateText(in, target);
    }

    public List<KeyVal> loadKeyVals(String file) {
        Resource res = resourceLoader.getResource(file);
        List<KeyVal> ret = new ArrayList<>();
        try {
            String theString = IOUtils.toString(res.getInputStream(), "UTF-8");
            for (String in : theString.split("\n")) {
                KeyVal l = new KeyVal();
                l.setKey(in.split(",")[0]);
                l.setValue(in.split(",")[1]);
                ret.add(l);
            }

        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return ret;
    }

    @Cacheable(cacheNames = "translations", key = "{#in,#lang}")
    public String translateFromGerman(String in, String lang) {
        return translator.translateText(in, lang) + " ";
    }

}