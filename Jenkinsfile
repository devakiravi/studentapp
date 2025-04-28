pipeline {
    agent any
    stages {
        stage('Clone Repository') {
            steps {
                git 'https://github.com/devakiravi/devops.git'
            }
        }
        stage('Build') {
            steps {
                sh './mvn clean package'
            }
        }
        stage('Docker Build') {
            steps {
                sh 'docker build -t studentapp .'
            }
        }
        stage('Docker Run') {
            steps {
                sh 'docker stop student-container || true'
                sh 'docker rm student-container || true'
                sh 'docker run -d -p 8085:8085 --name student-container studentapp'
            }
        }
    }
 }