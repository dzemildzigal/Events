cd ./EurekaService
call mvn -T 1C clean install
cd ../UserService
call mvn -T 1C clean install -Dmaven.test.skip=true
cd ../EventService
call mvn -T 1C clean install -Dmaven.test.skip=true
cd ../UserTicketService
call mvn -T 1C clean install -Dmaven.test.skip=true
cd ../ZuulGateway
call mvn -T 1C clean install -Dmaven.test.skip=true
cd ../SystemEvents
call mvn -T 1C clean install -Dmaven.test.skip=true
cd ../NotificationService
call mvn -T 1C clean install -Dmaven.test.skip=true
cd ../Events-Frontend/Events
call ng build 
cd ../../
call docker-compose up --build
pause 9999999999999999