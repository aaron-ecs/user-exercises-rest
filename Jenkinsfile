node {
    def mvnHome = tool 'M3'
    def gitUrl = 'https://bitbucket.org/aaronmwilliams/'
    stage('Build') {
        git gitUrl + 'user-exercises-testing-library'
        sh './gradlew install'

        git gitUrl + 'swagger-java-client'
        sh './gradlew install'
    }
    stage('Unit & Integration Tests') {
        git gitUrl + 'user-exercises-rest'
        sh "'${mvnHome}/bin/mvn' test -Dtest=*UnitTest.java -Dserver.port=8282"
        sh "'${mvnHome}/bin/mvn' test -Dtest=*IntegrationTest.java -Dserver.port=8282"
    }
    stage('Rest-Assured Tests') {
        git gitUrl + 'user-exercises-restassured-testing'
        sh './gradlew clean build'
    }
    stage('Swagger Client Tests') {
        git gitUrl + 'user-exercises-swagger-testing'
        sh './gradlew clean build'
    }
}