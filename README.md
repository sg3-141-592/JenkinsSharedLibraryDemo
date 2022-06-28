# Introduction
This repository contains an example of implementing new functionality using Jenkins file.

You'll need to install the [Pipeline Utility Steps - Plugin](https://plugins.jenkins.io/pipeline-utility-steps/). We just this to get the `readYaml` function.

``` jenkins
@Library('pipeline-library-demo') _
import org.nordcloud.secretScan;

pipeline {
    agent any
    parameters {
        string(name: 'searchPath', defaultValue: '/etc', description: 'Directory to scan')
        string(name: 'searchTerms', defaultValue: 'root,password', description: 'List of problem terms to search for')
        string(name: 'environment', defaultValue: 'pre', description: 'Which environment are we using? dev, pre, prod')
        string(name: 'repositoryName', defaultValue: 'pre-12a0c9', description: 'Repository to build')
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
        stage('Validate Repository Name') {
            steps {
                validateName(params.environment, params.repositoryName)
            }
        }
    }
}
```