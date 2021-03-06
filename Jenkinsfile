pipeline {


    agent any
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
                sh 'cd login-playground-app/ && mvn -Dmaven.test.failure.ignore=true install docker:build && cd ../'
            }
    }

        stage('Build Database') {
            steps {
                 echo 'Build docker image'
                
                 script {
                    def dockerImage = docker.build(registry +":latest", '-f ./login-playground-database/Dockerfile .')
                   // pipelineContext.dockerImage = dockerImage
                   // dockerImage.push()
                }
            }
        }

        stage('Publish') {
            steps {
                withDockerRegistry([ credentialsId: "dockerhub", url: "" ]) {
          sh 'docker push aedwa038/login-playground:latest'
          sh 'docker push ' +registry +":latest"
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
                sh 'docker-compose down'
                sh 'docker-compose up -d'
            }
        }

        stage('Clean up') {
            steps {
                echo 'Deploying....'
                sh 'docker image '
                sh 'docker-compose up -d'
            }
        }
    }
}