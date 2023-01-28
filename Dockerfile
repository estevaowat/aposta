FROM gradle:7.6.0-jdk17
VOLUME /tmp
WORKDIR /app
COPY . /app
EXPOSE 8000

# fetch dependencies
RUN chmod +x ./scripts/run.sh

# script which watches source file changes in background and executes bootRun
CMD ["sh", "./scripts/run.sh"]