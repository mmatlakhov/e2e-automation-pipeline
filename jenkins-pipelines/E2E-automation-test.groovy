
pipeline {
    agent {
        docker {
            image 'maven:3-alpine'
            args '-v /root/.m2:/root/.m2'
        }
    }
    stages {

        stage('Git checkout') { // for display purposes
            git 'https://github.com/mmatlakhov/e2e-automation-pipeline.git'
        }
        stage('Smoke') {
            try {
                sh "mvn clean verify -Dtags='type:Smoke'"
            } catch (err) {

            } finally {
                publishHTML (target: [
                        reportDir: 'target/site/serenity',
                        reportFiles: 'index.html',
                        reportName: "Smoke tests report"
                ])
            }
        }
        stage('API') {
            try {
                sh "mvn clean verify -Dtags='type:API'"
            } catch (err) {

            } finally {
                publishHTML (target: [
                        reportDir: 'target/site/serenity',
                        reportFiles: 'index.html',
                        reportName: "API tests report"
                ])
            }
        }
        stage('Results') {
            junit '**/target/failsafe-reports/*.xml'
        }
    }
}
