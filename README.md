# Cara menjalankan

cd ke webservice

mvn clean compile assembly:single

java -jar ./target/webservice-1.0-SNAPSHOT-jar-with-dependencies.jar

java --add-opens java.base/java.lang=ALL-UNNAMED --add-exports java.base/sun.nio.ch=ALL-UNNAMED --add-opens java.base/java.lang=ALL-UNNAMED --add-opens java.base/java.lang.reflect=ALL-UNNAMED --add-opens java.base/java.io=ALL-UNNAMED --add-exports jdk.unsupported/sun.misc=ALL-UNNAMED -jar ./target/webservice-1.0-SNAPSHOT-jar-with-dependencies.jar

# PENTING

Gunakan java SDK 1.8
