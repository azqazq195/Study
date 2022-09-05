#!/bin/bash

RED="\033[1;31""m"
GREEN="\033[1;32""m"
YELLOW="\033[1;33""m"
BLUE="\033[1;34""m"
PINK="\033[1;35""m"
NC="\033[0m"

echo -e "${GREEN} > git pull...${NC}"

git pull origin master

echo -e "${GREEN} > done.${NC}"

echo -e "${GREEN} > commit comment: ${NC}"

read COMMIT
git add .
git commit -m "$COMMIT"

echo -e "${GREEN} > done.${NC}"

echo -e "${GREEN} > git push.. ${NC}"

git push origin master

echo -e "${GREEN} > done.${NC}"
