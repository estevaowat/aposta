FROM eclipse-temurin:17
VOLUME /tmp
WORKDIR /app
COPY . /app
EXPOSE 8080

# fetch dependencies
RUN chmod +x ./scripts/run.sh

# script which watches source file changes in background and executes bootRun
CMD ["sh", "./scripts/run.sh"]