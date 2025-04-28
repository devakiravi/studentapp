pipeline {
  agent any
  tools {
    maven "3.9.9"
  }
  environment {
    CONTAINER_NAME = "studentapplication"
  }
  stages {
    stage('Compile and Clean') {
      steps {
        // Run Maven on a Unix agent.

        sh "mvn clean compile"
      }
    }
    stage('deploy') {

      steps {
        sh "mvn package"
      }
    }
    stage('Build Docker image') {

          steps {
            echo "Iam studentapp"
            sh 'ls'
            sh 'docker build -t  studentapplication:${BUILD_NUMBER} .'
            //sh 'trivy image --exit-code 1 --severity HIGH,CRITICAL mentorbridge/stublab:${BUILD_NUMBER} > trivy-report.txt'
          }
    }
    stage('Docker Login') {

      steps {
        withCredentials([string(credentialsId: 'DockerSecuroId', variable: 'DockerSecuroPwd')]) {
          sh "docker login -u devakiravi -p Devops@java"
        }
      }
    }
    stage('Docker Push') {
      steps {
        sh 'docker push studentapplication:${BUILD_NUMBER}'
      }
    }
    stage('Docker deploy') {
      steps {
        script {
          // Stop and remove the existing container if it exists
          def containerExists = sh(script: "docker ps -aq -f name=${CONTAINER_NAME}", returnStdout: true).trim()
          if (containerExists) {
            echo "Stopping and removing existing container: ${CONTAINER_NAME}"
            sh "docker stop ${CONTAINER_NAME}"
            sh "docker rm ${CONTAINER_NAME}"
          } else {
            echo "No existing container found with name ${CONTAINER_NAME}"
          }
        }

        // Remove old image if it exists
                sh """
                    # Find the old image ID (excluding the latest build)
                    OLD_IMAGE_ID=\$(docker images -q studentapplication | tail -n +2)

                    if [ -n "\$OLD_IMAGE_ID" ]; then
                        echo "Removing old images..."
                        docker rmi -f \$OLD_IMAGE_ID || true
                    else
                        echo "No old images found."
                    fi
                """

        // Run a new container
        echo "Deploying new container: ${CONTAINER_NAME}"
        //sh 'docker run -itd -p  8085:8085 studentapplication:${BUILD_NUMBER}'
        sh 'docker run -d --name ${CONTAINER_NAME}  -p  8085:8085 studentapplication:${BUILD_NUMBER}'
      }
    }
    stage('Archving') {
      steps {
        archiveArtifacts '**/target/*.jar'
      }
    }
  }
}