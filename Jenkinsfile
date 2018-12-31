pipeline {

     agent {
    label 'docker' 
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
             agent {
                //dockerfile true
                label 'docker'
                docker {
                    image 'aedwa038/builder:latest'
                    args '-v /root/.m2:/root/.m2'
                }
            }
            steps {
                echo 'Building..'
                sh 'cd login-playground-app/ && mvn -Dmaven.test.failure.ignore=true install docker:build && cd ../'
            }
    }

        stage('Build Database') {
             agent {
                //dockerfile true
                docker {
                    label 'docker'
                    image 'aedwa038/builder:latest'
                    args '-v /root/.m2:/root/.m2'
                }
            }
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