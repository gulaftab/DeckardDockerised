FROM debian:buster-slim AS compilator

LABEL maintainer "Build Container -- Deckard"

# mkdir the man/man1 directory due to Debian bug #863199
RUN apt-get update && \
    mkdir -p /usr/share/man/man1 && \
    apt-get install --yes --no-install-recommends \
      autoconf \
      automake \
      bubblewrap \
      bzip2 \
      cmake \
      curl \
      g++ \
      gcc \
      git \
      libc6-dev \
      make \
      openjdk-11-jdk-headless \
      patch \
      patchelf \
      pkg-config \
      python \
      unzip \
      xz-utils \
      bison\
      flex\
      zlib1g-dev && \
    rm -rf /var/lib/apt/lists/*

#create a symlink for python 
RUN ln -s /usr/bin/python2 /usr/bin/python & \
    ln -s /usr/bin/pip2 /usr/bin/pip

# Download the latest Infer master
RUN cd / && \
    git clone https://github.com/skyhover/Deckard.git

RUN cd /Deckard/src/main/ && ./build.sh


# map /data to host defined path ( config file and source data )
VOLUME /data

ENV PATH /Deckard/scripts/clonedetect:${PATH}

