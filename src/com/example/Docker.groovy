#!/usr/bin/env groovy
package com.example

class Docker implements Serializable {
    def script

    Docker (script) {
        this.script = script
    }
    def buildDockerImage(String imageName) {
        script.echo "Building the Docker image..."
            script.sh "docker build -t $imageName ."
    }

    def dockerLogin() {
        script.withCredentials([script.usernamePassword(credentialsId: 'nexus-docker-repo', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
            script.sh "echo $script.PASS | docker login -u $script.USER --password-stdin 207.154.204.232:8083"
        }
    }

    def dockerPush(String imageName) {
        script.sh "docker push $imageName"
    }
}