FROM 'ubuntu:16.04'
RUN apt-get update
RUN  apt-get -y install docker && default-jre && default-jdk && maven && git