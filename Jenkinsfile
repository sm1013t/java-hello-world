pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build (skip tests)') {
            steps {
                sh '''
                 export JAVA_HOME=/opt/app/softwares/java/jdk-21.0.12
                 export PATH=/opt/app/softwares/maven-3.9.16/bin:$JAVA_HOME/bin:$PATH
                 mvn clean install -DskipTests
              '''
            }
        }

        stage('Run Unit Tests') {
            steps {
                sh '''
                 export JAVA_HOME=/opt/app/softwares/java/jdk-21.0.12
                 export PATH=/opt/app/softwares/maven-3.9.16/bin:$JAVA_HOME/bin:$PATH
                 mvn test
              '''
            }
            /*
            post {
                always {
                    junit '**/target/surefire-reports/TEST-*.xml'
                    archiveArtifacts(
                        artifacts: '**/target/site/jacoco/**',
                        allowEmptyArchive: true
                    )
                }
            }
            */
        }

        stage('SonarCloud Analysis') {
            steps {
                script {
                    // Resolve the SonarScanner installation configured in Jenkins.
                    def scannerHome = tool 'SonarScanner8.1.0'

                    // Provide the SonarCloud server URL and authentication settings.
                    withSonarQubeEnv('SonarCloudServer') {
                        // Run the scanner; it reads project settings from sonar-project.properties.
                        sh "${scannerHome}/bin/sonar-scanner"
                    }
                }
            }
        }
    }
}