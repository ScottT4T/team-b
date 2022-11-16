# team-b
Team B's App

How to startup this project.

1. Nginx (Proxy-pass app)

    Mac\
    To install nginx on mac please use homebrew. Enter in a terminal 'brew install nginx'.\
    This should install nginx onto your mac, then please locate the nginx.conf where this was installed using a terminal (e.g 'cd /opt/homebrew/etc/nginx/')\
    Once there proceed to copy everything from the nginx _example nginx.conf file from the project files into that mac-installed nginx.conf file.\
    Save and then use the ./runNginxMac.sh to run the proxy-pass.

    Windows\
    To use nginx on windows please use the ./runNginxWindows.sh shell script. It is all setup for you!

2. Run the backend\
    To run the backend use the convenient ./runBackend.sh shell script. which will run a java server from the BE .jar file provided from the BE service folder.\
    This will run the java server on port 3333

3. Install FE dependencies\
    Proceed to the 'frontend' folder and fire 'npm i' to install the react dependencies.

4. Run the FE\
    You can now run the react app by either firing 'npm start' in the frontend folder. Or use the ./runFrontend.sh shell script to initiate the react service.

5. Test your application\
    Go to 'http://localhost:80/'. You should be provided with some api buttons to test the api status and api response.