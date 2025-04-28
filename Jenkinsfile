pipeline {
    agent any

    stages {
        stage('Build with Maven') {
            steps {
                bat 'mvn clean install'
            }
        }

        stage('Build Docker Image') {
            steps {
                bat 'docker build -t studentapp .'
            }
        }

        stage('Run Docker Container') {
            steps {
                bat 'docker run -d -p 8085:8085 --name student-container studentapp'
            }
        }
    }
}