#!/usr/bin/env groovy

def call(String imageName) {
    echo "Building the Docker image..."
    withCredentials([usernamePassword(credentialsId: 'nexus-docker-repo', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
        sh "docker build -t $imageName ."
        sh "echo $PASS | docker login -u $USER --password-stdin 207.154.204.232:8083"
        sh "docker push $imageName"
    }
}