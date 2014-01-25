Alle Skripte ausführen mit

liquibase --driver=org.h2.Driver  --classpath=$H2_JAR \
--url="jdbc:h2:$DATABASE;CIPHER=AES" --username=$USER --password="$PASSWORD" --changeLogFile=$MIG_DATEI \
update  \
