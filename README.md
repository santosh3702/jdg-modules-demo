jdg-modules-demo
================

This is a demo application that shows how to run JDG in EAP 6 using modules (work in progress)

To run this demo follow the steps below:

1. Download a ZIP of the project directly from the site or by using the following command

        $  curl https://codeload.github.com/tqvarnst/jdg-modules-demo/zip/master -o jdgdemo.zip

2. Download JBoss EAP 6.2 and JDG 6.3 Beta or later and place it under the installs dir
3. Run the init.sh script (you might have to change some of the settings in init.sh if you are using a later version of EAP or JDG)
4. Start JBoss EAP

        target/jboss-eap-6.3/bin/standalone.hs
        
5. Follow the instructions for [lab1](https://github.com/tqvarnst/jdg-modules-demo/blob/master/projects/todo/lab-guides/lab-guide1.md) to build and deploy a project using JBoss Data Grid in Library mode
