FROM openjdk:11.0.8-slim-buster
COPY . /usr/src/review_service
WORKDIR /usr/src/review_service
CMD ["./gradlew", "bootRun"]