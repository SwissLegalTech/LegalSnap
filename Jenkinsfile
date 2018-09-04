#!groovy
/*
 * Copyright (C) Schweizerische Bundesbahnen SBB, 2018.
 *
 *  Unter buildConf bitte die Konfigurationsparameter fuer den Build anpassen, die eigentliche
 *  Pipeline befindet sich im 'vars' unter pipeline@version
 *
 *  Doku: https://confluence.sbb.ch/todo
 *
 */
library identifier: 'pipeline@master',
        retriever: modernSCM(
                [$class       : 'GitSCMSource',
                 credentialsId: '99c49480-8063-4efb-a8bd-4fee70dd2fba', // fsbuild
                 remote       : 'https://fsbuild@code.sbb.ch/scm/~u203244/esta-pipeline-jenkinslib.git'])

buildConf = [

        mein                     : 'param 1',
        bsp                      : 'bsp 1',
        deployNightly            : 'true',
        openshiftDeploy          : 'true' ,         // true = release auf openshift ausloesen
        openshiftArtefaktServer  : 'n/a',
        openshiftImageNameTag    : '',              // '' = ohne SBB base Image (Bsp: alphine)

        sonarOFF                 : 'true',
        lockResource             : 'false'


]

mainPipeline(buildConf)
