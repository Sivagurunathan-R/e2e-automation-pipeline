pipeline{

agent any

tools{

maven 'MAVEN_HOME'
jdk 'Java8'

}

stages{

stage('gir checkout'){

steps{
 git 'https://github.com/BushnevYuri/e2e-automation-pipeline.git'

}
}



stage('smoke'){

steps{

 try {
            bat "mvn clean verify -Dtags='type:Smoke'"
        } 
catch (err) {

        }
 finally {
            publishHTML (target: [
                    reportDir: 'target/site/serenity',
                    reportFiles: 'index.html',
                    reportName: "Smoke tests report"
            ])
        }


}
}


}
}
