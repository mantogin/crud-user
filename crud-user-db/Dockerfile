FROM alpine:latest
WORKDIR /db
COPY db-scripts/ /db/
ENV PATH="/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin"
RUN apk update && apk add postgresql-client && rm -rf /var/cache/apk/*

