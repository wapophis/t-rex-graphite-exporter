pipeline {
  agent {
    node {
      label 'java-docker-slave'
    }

  }
  stages {
    stage('Build') {
      steps {
        sh 'mvn clean compile'
      }
    }

    stage('Test') {
      steps {
        sh 'mvn install'
      }
    }

    stage('Reporting') {
      steps {
        junit '/coverage/**'
      }
    }

  }
}