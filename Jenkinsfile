pipeline {
  environment {
    registry = "aaronmwilliams/user-exercises-rest"
    registryCredential = 'dockerhub'
    dockerImage = ''
  }
  agent any
  stages {
    stage('Pull') {
      steps {
        checkout scm
      }
    }
    stage('Build') {
      steps {
        script {
          sh "docker build -t $registry ."
          dockerImage = docker.image("$registry")
        }
      }
    }
    stage('Test') {
      steps{
        script {
          sh "docker-compose up --exit-code-from tests"
        }
      }
    }
    stage('Publish Artifact to S3') {
      steps{
        script {
          sh 'rm -f artifact.zip'
          zip zipFile: 'artifact.zip', archive: false
          git 'https://github.com/aaron-ecs/deployment-scripts/'
          sh 'python3 publish_to_s3.py aaron-codedeploy-bucket user-exercises-rest/$GIT_BRANCH-$BUILD_NUMBER.zip'
        }
      }
    }
    stage('Push to Docker Hub') {
      steps{
        script {
          docker.withRegistry('', registryCredential) {
            dockerImage.push('$GIT_BRANCH-$BUILD_NUMBER')
          }
        }
      }
    }
    stage('Deploy to EC2') {
      steps{
        script {
          sh 'python3 deploy_to_ec2.py user-exercises-rest user-exercises-rest user-exercises-rest/$GIT_BRANCH-$BUILD_NUMBER.zip'
        }
      }
    }
    stage('Clean Up') {
      steps {
        sh 'rm -f artifact.zip'
        sh "docker rmi $registry"
      }
    }
  }
}