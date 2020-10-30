#!groovy

//
// Spring Boot Project with Maven and Java
//
pipeline {
    agent {
        label 'maven'
    }

    options {
        skipDefaultCheckout() // Avoiding Git Checkout for DockerSlave
        disableConcurrentBuilds()
        timeout(time : 1, unit: 'HOURS')
        buildDiscarder(logRotator(numToKeepStr: '5'))
    }

    stages{
        stage ('Checkout from GIT') {
            steps {
                checkout scm
            }
        }

        stage ('Setup CI/CD Environment'){
            steps{

                script {

                    h1SetEnv('IMAGE_TAG', 'latest')
                    h1SetEnv('APP_NAME', 'SpringBootDemo')
                    h1SetEnv('CONFIG_LABEL', 'master')
                }
            }
        }

        stage ('Build Source') {
            //Remove once Jenkins gets updated with this limit applied to the Maven Slave Directly.Checkout
            environment {
                MAVEN_OPTS = '-Xmx256m' // Java Heap memory size -  limit set to 1/4 available memory
            }

            steps{
                    // mvn pass argLine parameter to limit heap memory for unit Tests
                    sh '''
                    mvn -B clean deploy -Dmaven.test.skip=false -DargLine="-Xmx256m"
                    '''

            }

        }

        stage ('Test') {
            // mvn pass argLine parameter to limit heap memory for unit Tests
            environment {
            MAVEN_OPTS = '-Xmx256m' // Java heap memory size - limit set to 1/4 available memory
            }

            steps {
            sh 'mvn -B test -DargLine="-Xmx256m"'

            stash 'app'
            }
        }

        //stage ('SonarQube Analysis'){
        //    steps {
        //    h1secSonarScanner('java','com.rocky.SpringBatchDemo','SpringBatchDemo', [coverage: 'target/jacoco.exec, target/jacoco-it.exec'])
        //    }
        //}
    }

    post {
        always {
            junit '**/target/surfire-reports/*.xml'
            print "All stages finished running"
        }

        success {
            script {
                print "Job Finished successfully"

            }
        }

        failure {
            emailext (
                to: '$DEFAULT_RECIPIENTS',
                subject: "Jenkins FAILED: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'",
                body: "<a href='${env.BUILD_URL}'>Fix!</a>",
                recipientProviders: [[$class: 'DevelopersRecipientProvider'],
                                    [$class: 'CulpritsRecipientProvider'],
                                    [$class: 'RequesterRecipientProvider'],
                                    [$class: 'FirstFailingBuildSuspectsRecipientProvider']])


        }
    }
}


