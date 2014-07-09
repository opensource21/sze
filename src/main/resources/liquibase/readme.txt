Alle Skripte ausführen mit

liquibase --driver=org.h2.Driver  --classpath=$H2_JAR \
--url="jdbc:h2:$DATABASE;CIPHER=AES" --username=$USER --password="$PASSWORD" --changeLogFile=$MIG_DATEI \
update  \

Vorbereitet gibt es exporth2.sh und importh2.sh und liquibase-update.sh in ~/seu
Anschließend anonymisieren mit AnonymisierungsServiceImplIntegrationTest, wobei man den Testcode entfernen und die db.properties anpassen muss.


