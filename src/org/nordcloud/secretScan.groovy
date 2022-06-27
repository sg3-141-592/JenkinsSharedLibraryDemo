#!/usr/bin/groovy
package org.nordcloud

import groovy.io.FileType
import java.io.FileNotFoundException

// Get list of files in directory, filtering out folders
@NonCPS
def listAllFiles(String dir) {
    def files = new File(dir)
    def list = []
    files.eachFile {
        try {
            if(!it.isDirectory()) {
                list.add(it)
            }
        } catch(FileNotFoundException e1) {
            println("No access to ${it.path}")
        }
    }
    return list
}

// Scan the contents of a directory against a keywords list for matches
def scanDirectory(String folder, ArrayList keywords) {
    def files = listAllFiles(folder)
    def problemFiles = []
    println("Number of files to scan: ${files.size()}")
    for(file in files) {
        for(searchTerm in keywords) {
            try {
                if(file.text.contains(searchTerm)) {
                    problemFiles.add(file.path)
                }
            } catch(FileNotFoundException e1) {
                println("No access to ${file.path}")
            }
        }
    }
    println("Number of files with issues: ${problemFiles.toSet().size()}")
    for(file in problemFiles.toSet()) {
        println(file)
    }
}