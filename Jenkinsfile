pipeline {
    agent any 
    stages {
        stage('Compile and Clean') { 
            steps {
                script{
                    def mvnHome = tool name: 'maven-3', type: 'maven'
                    def mvnCMD = "${mvnHome}/bin/mvn"
                    sh "${mvnCMD} clean package"
                }
            }
        }

		stage('Unittest'){
		    steps {
			    sh "mvn test"
			}
			post {
		        always {
	    		    junit allowEmptyResults: true, testResults: 'target/surefire-reports'*.xml
	    		}
		    }
		}
	
	    stage('deploy') { 
	        steps {
	            sh "mvn package"
	        }
	    }
	
	    stage('Build Docker image'){
	        steps {
	            sh 'docker build -t  kmp15/people_registration_app:${BUILD_NUMBER} .'
	        }
	    }
	
	    stage('Docker Login'){            
	        steps {
	             withCredentials([string(credentialsId: 'DockerId', variable: 'Dockerpwd')]) {
	                sh "docker login -u kmp15 -p ${Dockerpwd}"
	            }
	        }                
	    }
	
	    stage('Docker Push'){
	        steps {
	            sh 'docker push kmp15/people_registration_app:${BUILD_NUMBER}'
	        }
	    }
	    
	    stage('Docker deploy'){
	        steps {
	           sh 'docker run -itd -p  8081:8081 kmp15/people_registration_app:${BUILD_NUMBER}'
	        }
	    }
	    
	    stage('Archving') { 
	        steps {
	             archiveArtifacts '**/target/*.jar'
	        }
	    }
    }
}
