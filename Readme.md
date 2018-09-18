LegalSnap Demo Webapp
--


```
 ____ ____ ____ ____ ____ ____ ____ ____ ____
||L |||e |||g |||a |||l |||S |||n |||a |||p ||
||__|||__|||__|||__|||__|||__|||__|||__|||__||
|/__\|/__\|/__\|/__\|/__\|/__\|/__\|/__\|/__\|

:: Spring Boot 2.0.5.RELEASE ::
:: https://github.com/SwissLegalTech/LegalSnap ::
```


1) Checkout:
git clone https://github.com/SwissLegalTech/LegalSnap

2) Build (Maven):
mvn clean install

3) Google api Key as json File:
Create one an save it to: /path.to.credentials.file

4) Startup:
java -jar legalsnap-0.0.1-SNAPSHOT.jar -DGOOGLE_APPLICATION_CREDENTIALS=[/path.to.credentials.file]

5) Demo:
https://legalsnap.emad.ch

6) Demofiles for Upload:
https://github.com/SwissLegalTech/LegalSnap/issues/1

7) IDE:
https://www.jetbrains.com/idea/download/


Java Libs and Frameworks
--
- Spring Boot 2
- Joinfaces with Primefaces 6 and JSF 2.2
- Lombook
- Google Translation API (Java Lib)
- Google OCR API (Java Lib)

Known Bugs
----------
- No responsive on mobile Browsers

