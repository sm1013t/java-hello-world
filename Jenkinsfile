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
            // post {
            //     always {
            //         junit '**/target/surefire-reports/TEST-*.xml'
            //         archiveArtifacts(
            //             artifacts: '**/target/site/jacoco/**',
            //             allowEmptyArchive: true
            //         )
            //     }
            // }
        }

        stage('SonarCloud Analysis') {
            steps {
                script {
                    // Resolve the SonarScanner installation configured in Jenkins.
                    def scannerHome = tool 'SonarScanner8.1.0'

                    // Provide SonarCloud connection settings and run the scanner.
                    withSonarQubeEnv('SonarCloudServer') {
                        withEnv(["SONAR_SCANNER_HOME=${scannerHome}"]) {
                            sh '''
                             echo "BRANCH_NAME=$BRANCH_NAME"
                             echo "GIT_BRANCH=$GIT_BRANCH"
                             branch_name="${BRANCH_NAME:-${GIT_BRANCH#origin/}}"
                             if [ -z "$branch_name" ]; then
                                 echo "Unable to determine the current Git branch from BRANCH_NAME or GIT_BRANCH." >&2
                                 exit 1
                             fi
                             "$SONAR_SCANNER_HOME/bin/sonar-scanner" "-Dsonar.branch.name=$branch_name"
                          '''
                        }
                    }
                }
            }
        }
    }
}