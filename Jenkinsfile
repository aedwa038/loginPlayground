pipeline {
     agent {
                //dockerfile true
                docker {
                    image 'aedwa038/builder:latest'
                    args '-v /root/.m2:/root/.m2'
                }
            }
    environment {
        registry = "aedwa038/login_db"
        registryCredential = 'dockerhub'
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
                    def dockerImage = docker.build(registry +":${env.BUILD_ID}", '-f .login-playground-database/Dockerfile .')
                    pipelineContext.dockerImage = dockerImage
                    dockerImage.push()
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