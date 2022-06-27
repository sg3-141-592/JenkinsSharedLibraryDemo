# Introduction
This repository contains an example of implementing new functionality using Jenkins file.

``` jenkins
@Library('pipeline-library-demo') _
import org.nordcloud.secretScan;

pipeline {
    agent any
    parameters {
        string(name: 'searchPath', defaultValue: '/etc', description: 'Directory to scan')
        string(name: 'searchTerms', defaultValue: 'root,password', description: 'List of problem terms to search for')
    }
    stages {
        stage('Secret Scanner') {
            steps {
                script {
                    def secretScanner = new secretScan()
                    ArrayList searchList = params.searchTerms.split(',')
                    secretScanner.scanDirectory(params.searchPath, searchList)
                }
            }
        }
        stage('Get CPU Statistics') {
            steps {
                getCPU()
            }
        }
    }
}
```