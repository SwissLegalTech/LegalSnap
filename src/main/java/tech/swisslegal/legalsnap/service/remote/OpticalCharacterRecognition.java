package tech.swisslegal.legalsnap.service.remote;

public interface OpticalCharacterRecognition {
    String recognizeText(byte[] file);
}
