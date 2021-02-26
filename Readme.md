# Quick Start


```
docker pull gulaftab/deckard:latest
```


# Example Usage


```
cd era_bcb_sample
docker run -it -v$(pwd):/data 
```
now within docker shell
```
cd /data
deckard.sh
```


# Build the Image

  ```
  git clone https://github.com/gulaftab/DeckardDockerised.git

  cd DeckardDockerised

  docker build -t cdas/deckard .
  ```

