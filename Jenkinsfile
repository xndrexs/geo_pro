pipeline {
  agent any
  stages {
    stage('clean') {
      steps {
        sh './gradlew clean'
      }
    }
    stage('build') {
      steps {
        sh './gradlew build'
      }
    }
    stage('doc') {
      steps {
        sh './gradlew javadoc'
      }
    }
    stage('publish') {
      steps {
        sh './gradlew publish'
      }
    }
  }
}