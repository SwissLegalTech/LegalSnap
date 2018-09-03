#!groovy
/*
 * Copyright (C) Schweizerische Bundesbahnen SBB, 2018.
 *
 *  unter repoConfig bitte die Konfigurationsparameter fuer den Build anpassen, das Build Script
 *  selber befindet sich unter der Adresse hinter id@master
 *
 *  Doku: https://confluence.sbb.ch/todo
 */
library identifier: 'id@master',
        retriever: modernSCM(
                [$class       : 'GitSCMSource',
                 remote       : 'https://code-i.sbb.ch/scm/~u203244/esta-pipeline-jenkinslib.git'])

repoConfig = [

        mein                     : 'param 1',
        bsp                      : 'bsp 1',
        deployNightly            : 'true',
        openshiftDeploy          : 'true' ,         // true = release auf openshift ausloesen
        openshiftArtefaktServer  : 'n/a',
        openshiftImageNameTag    : '',              // '' = ohne SBB base Image (Bsp: alphine)

        sonarOFF                 : 'true',

]

libmain(repoConfig)
