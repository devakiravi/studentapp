pipeline {
    agent any

    environment {
        DOCKER_IMAGE = "studentapp"
        CONTAINER_NAME = "studentcontainer1"
    }

    stages {
        stage('git checkout the code') {
            steps {
                checkout scm
            }
        }

        stage('maven build') {
            steps {
                bat "mvn clean package"
            }
        }

        stage('docker image creation') {
            steps {
                bat "docker build -t studentapp/${DOCKER_IMAGE}:${BUILD_NUMBER} ."
            }
        }

        stage('docker login') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'dockerhub-creds', usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
                    bat "echo %DOCKER_PASS% | docker login -u %DOCKER_USER% --password-stdin"
                }
            }
        }

        stage('docker push') {
            steps {
                bat "docker push studentapp/${DOCKER_IMAGE}:${BUILD_NUMBER}"
            }
        }

        stage('docker deploy') {
            steps {
                script {
                    try {
                        bat "docker stop ${CONTAINER_NAME}"
                        bat "docker rm ${CONTAINER_NAME}"
                    } catch (Exception e) {
                        echo "Container not running or doesn't exist. Skipping stop/remove."
                    }

                    bat "docker run -d --name ${CONTAINER_NAME} -p 8080:8080 studentapp/${DOCKER_IMAGE}:${BUILD_NUMBER}"
                }
            }
        }
    }

    post {
        success {
            emailext (
                subject : "Build Success So Please kindly check it !",
                body:"""<html>
<body>
<h1>Build Status : Success for ${JOB_NAME} for build ${currentBuild.number}</h1>
<h3>Please Verify all the APIs from the QA 😊</h3>
<h4>Info from Operation Team ❤️</h4>
</body>
</html> """,
               to:"morbiusgamingyt@gmail.com",
               from:"devakiravi226@gmail.com",
               replyTo: "devakiravi226@gmail.com",
               mimeType:"text/html"
            )
        }
    }
}