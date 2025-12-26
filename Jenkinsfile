pipeline {
    agent any

    stages {

        stage('Checkout') {
            steps {
                git 'https://github.com/Vuthy-Tourn/Rest-API.git'
            }
        }

        stage('Docker Build') {
            steps {
                sh 'docker build -t jenkins-spring-pipeline .'
            }
        }

        stage('Deploy') {
            steps {
                sh '''
                    docker stop springboot-cont || true
                    docker rm springboot-cont || true

                    docker run -d -p 8081:8080 \
                        --name springboot-cont \
                        jenkins-spring-pipeline
                '''
            }
        }
    }

    post {
        success {
            echo '✅ Spring Boot Docker Build & Deploy Successful'
        }
        failure {
            echo '❌ Spring Boot Pipeline Failed'
        }
    }
}
