FROM quay.io/drsylent/cubix/block2/homework-base:java17

LABEL cubix.homework.owner="Sterbinszky Nora"

ENV CUBIX_HOMEWORK="S. Nori" APP_DEFAULT_MESSAGE=

#COPY target/ target
COPY target/*.jar app.jar

CMD [ "java", "-jar", "app.jar" ]
