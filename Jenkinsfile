pipeline {
    agent any 
    stages {
        stage('Compile and Clean') { 
            steps {
                script{
		    env.JAVA_HOME="/usr/lib/jvm/java-1.8.0-openjdk-1.8.0.302.b08-0.amzn2.0.1.x86_64"
                    env.M2_HOME="/opt/maven"
                    env.MAVEN_HOME="/opt/maven"
                    env.PATH="${M2_HOME}/bin:${PATH}"			
                    sh "env"			
                    sh "mvn clean compile"
                }
            }
        }

	    stage('Unittest'){
    		steps {
			    sh "mvn test"
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
