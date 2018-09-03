#!groovy
/*
 * Copyright (C) Schweizerische Bundesbahnen SBB, 2018.
 *
 */
library identifier: 'id@master',
        retriever: modernSCM(
                [$class       : 'GitSCMSource',
                 remote       : 'https://code.sbb.ch/scm/pt_cisi/config.git'])

repoConfig = [

        bitbucketProject         : 'PT_CISI',
        bitbucketRepo            : 'watchdog',             // eigenes Repo (In der Regel Repo = Artefakt = deploybare Einheit)

        deployNightly            : 'true',
        openshiftDeploy          : 'true' ,             // true = release auf openshift ausloesen
        openshiftArtefaktServer  : 'n/a',
        openshiftArtefaktClient  : 'n/a',
        openshiftArtefaktDB      : 'n/a',
        openshiftImageNameTag    : '', // '' = ohne SBB base Image (Bsp: alphine)

        sonarOFF                 : 'true',
  
        deployWebsphere          : 'false',               // true = Websphere Deployment ausloesen im Nightly
        deploySAF                : 'false'                // true = SAF Deployment ausloesen im Nightly
]

libmain(repoConfig)
