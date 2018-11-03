FROM postgres:11

ENV POSTGRES_USER login
ENV POSTGRES_PASSWORD world123
ENV POSTGRES_DB login-db
EXPOSE 5432

ADD scripts/*.sql /docker-entrypoint-initdb.d/

