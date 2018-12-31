FROM 'ubuntu:16.04'
RUN apt-get update && apt-get install -y \
    git \
    default-jre \
    default-jdk \
    maven

RUN apt-get -y install docker 