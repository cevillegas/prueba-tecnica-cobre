pipeline {
    agent any

    tools {
        maven 'Maven'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/cevillegas/prueba-tecnica-cobre.git'
            }
        }

        stage('Run Karate Tests') {
            steps {
                bat 'mvn clean test'
            }
        }

        stage('Publish JUnit Reports') {
            steps {
                junit '*/target/surefire-reports/.xml'
            }
        }

        stage('Publish Karate HTML Reports') {
            steps {
                publishHTML(target: [
                    reportDir: 'target/karate-reports',
                    reportFiles: 'karate-summary.html',
                    reportName: 'Karate Test Report'
                ])
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: '*/target/karate-reports/.html', allowEmptyArchive: true
        }
    }
}