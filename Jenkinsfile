pipeline {

    environment {
        registry = "aedwa038/login_db"
        registryCredential = 'dockerhub'
    }
    agent {
     dockerfile true
    }
    stages {
         stage ('Initialize') {
            steps {
                sh '''
                    echo "PATH = ${PATH}"
                    echo "M2_HOME = ${M2_HOME}"
                ''' 
            }
        }
        stage('Build App') {
            steps {
                echo 'Building..'
                sh 'cd login-playground-app/ && mvn -Dmaven.test.failure.ignore=true install && cd ../'
            }
        }

        stage('Build Database') {
            steps {
                 echo 'Build docker image'
                 script {
                    def dockerImage = docker.build("aedwa038/login_db:${env.BUILD_ID}", '-f .login-playground-database/Dockerfile .')
                    pipelineContext.dockerImage = dockerImage
                    dockerImage.push()
                }
                script {
                    docker.build(registry + ":$BUILD_NUMBER")
                }
            }
        }

        stage('Test') {
            steps {
                echo 'Testing..'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
    }
}