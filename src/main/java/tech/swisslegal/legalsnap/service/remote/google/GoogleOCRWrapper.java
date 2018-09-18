package tech.swisslegal.legalsnap.service.remote.google;

import com.google.cloud.vision.v1.*;
import com.google.protobuf.ByteString;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import tech.swisslegal.legalsnap.service.remote.OpticalCharacterRecognition;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
@Data
public class GoogleOCRWrapper implements OpticalCharacterRecognition {

    @Override
    public String recognizeText(byte[] file) {

        try {

            List<AnnotateImageRequest> requests = new ArrayList<>();

            ByteString imgBytes = null;

            imgBytes = ByteString.readFrom(new ByteArrayInputStream(file));


            Image img = Image.newBuilder().setContent(imgBytes).build();
            Feature feat = Feature.newBuilder().setType(Feature.Type.DOCUMENT_TEXT_DETECTION).build();
            AnnotateImageRequest request =
                    AnnotateImageRequest.newBuilder().addFeatures(feat).setImage(img).build();
            requests.add(request);

            try (ImageAnnotatorClient client = ImageAnnotatorClient.create()) {
                BatchAnnotateImagesResponse response = client.batchAnnotateImages(requests);
                List<AnnotateImageResponse> responses = response.getResponsesList();
                client.close();

                for (AnnotateImageResponse res : responses) {
                    if (res.hasError()) {
                        return res.getError().getMessage();
                    }

                    // For full list of available annotations, see http://g.co/cloud/vision/docs
                    TextAnnotation annotation = res.getFullTextAnnotation();
                    for (Page page : annotation.getPagesList()) {
                        String pageText = "";
                        for (Block block : page.getBlocksList()) {
                            String blockText = "";
                            for (Paragraph para : block.getParagraphsList()) {
                                String paraText = "";
                                for (Word word : para.getWordsList()) {
                                    String wordText = "";
                                    for (Symbol symbol : word.getSymbolsList()) {
                                        wordText = wordText + symbol.getText();
                                               }
                                    paraText = String.format("%s %s", paraText, wordText);
                                }
                                blockText = blockText + paraText;
                            }
                            pageText = pageText + blockText;
                        }
                    }
                    return annotation.getText();
                }
            }

        } catch (IOException e) {
            log.error(e.getMessage(),e);
        }
        return "";
    }

}