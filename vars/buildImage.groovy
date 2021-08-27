#!/usr/bin/env groovy

def call() {
    echo "Building the Docker image..."
    withCredentials([usernamePassword(credentialsId: 'nexus-docker-repo', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
        sh 'docker build -t 207.154.204.232:8083/java-maven-app:2.0 .'
        sh "echo $PASS | docker login -u $USER --password-stdin 207.154.204.232:8083"
        sh 'docker push 207.154.204.232:8083/java-maven-app:2.0'
    }
}