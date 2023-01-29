(sleep 60; ./gradlew build --continuous -PskipDownload=true -x Test)&
./gradlew bootRun -PskipDownload=true