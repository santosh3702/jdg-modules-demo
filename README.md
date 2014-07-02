jdg-modules-demo
================

This is a demo application that shows how to run JDG in EAP 6 using modules (work in progress)

To run this demo follow the steps below:

1. Download JBoss EAP 6.2 and JDG 6.3 Beta or later and place it under the installs dir
2. Run the init.sh script (you might have to change some of the settings in init.sh)
2. Start JBoss EAP

        target/jboss-eap-6.3/bin/standalone.hs
        
3. Make sure that you have JDG maven repository enabled (see JDG documentation)
4. Go to projects/simple-jdg-app

        cd projects/simple-jdg-app
        
5. Run maven

        mvn clean package
        
6. If everything builds find run the following to deploy the project

        mvn jboss-as:deploy
        
7. Open a browser to http://localhost:8080/simple-jdg-app/cache

If everything worked you should see a short list of names of baseball players. 
