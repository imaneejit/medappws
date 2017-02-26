Requirements:

1.Java: 1.8.0_121
2.Maven: 3.3.9
3.IDE

Steps:
1. Upon pull, perform "mvn clean install eclipse:clean eclipse:eclipse".
2. You can now import the project in your IDE.
3. To run, perform "mvn tomcat7:run". The URI is configured to /medappws/**
4. To create the javadoc, perform "mvn javadoc:javadoc". Open the html file "/target/site/apidocs/index.html".
