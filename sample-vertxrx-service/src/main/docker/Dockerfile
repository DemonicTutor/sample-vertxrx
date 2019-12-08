FROM azul/zulu-openjdk-alpine:13

ARG DEPLOYABLE_ARTIFACT

ENV VERTICLE_FILE $DEPLOYABLE_ARTIFACT
ENV VERTICLE_HOME /usr/verticles

RUN addgroup --gid 1000 vertx \
    && adduser \
    --disabled-password \
    --gecos "" \
    --home $VERTICLE_HOME/ \
    --ingroup vertx \
    --no-create-home \
    --uid 1000 \
    vertx
USER vertx

COPY --chown=vertx:vertx ./maven/$VERTICLE_FILE $VERTICLE_HOME/

RUN chmod -R 550 $VERTICLE_HOME

WORKDIR $VERTICLE_HOME
ENTRYPOINT ["sh", "-c"]
CMD ["exec java -jar $VERTICLE_FILE"]

EXPOSE 8080 8443
