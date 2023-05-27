#!/bin/bash
# shellcheck disable=SC1090

source ~/Documents/code/script/loading-animation.sh
source ~/Documents/code/script/color.sh

# Docker App 실행
open -a Docker

loading_animation_start "Waiting for Docker to start"
while ! docker info &> /dev/null ; do
  sleep 1
done
loading_animation_stop
echo "${GREEN}done.${NC}"

# -f docker-compose 파일 위치 설정
# -p Project Name 설정
# -d 백그라운드 실행
docker-compose -f ../docker/docker-compose.yml -p jwt up -d
