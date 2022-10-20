#!/bin/bash

RED="\033[1;31""m"
GREEN="\033[1;32""m"
YELLOW="\033[1;33""m"
#BLUE="\033[1;34""m"
PINK="\033[1;35""m"
NC="\033[0m"

CHECK_BRANCH="dev"

# 현재 브랜치 확인
echo "${GREEN} > check '$CHECK_BRANCH' branch${NC}"
CURRENT_BRANCH=$(git branch --show-current)

# 작업할 브랜치가 맞는지 검사
if [ "$CURRENT_BRANCH" = "$CHECK_BRANCH" ]
then
    echo "${GREEN} > current branch is '$CHECK_BRANCH'${NC}"
else
    echo "${RED} > current branch is not '$CHECK_BRANCH'. try again after checkout to '$CHECK_BRANCH' branch${NC}"
    exit
fi

echo "${GREEN} > git pull...${NC}"
git pull

# branch 이름 입력
DONE=0
while [ $DONE != 1 ]
do
    echo "${GREEN} > input jira issue key${NC}"
    echo "   ex)"
    echo "      CT2-1085"
    read -r  ISSUE_KEY

    echo ""
    echo "${GREEN} > input branch name${NC}"
    echo "   ex)"
    echo "      feat-product"
    echo "      refactor-product"
    echo "      fix-product"
    read -r SUBJECT

    BRANCH_NAME="${ISSUE_KEY}@${SUBJECT}"
    echo ""
    echo "${PINK} > your branch name is '${BRANCH_NAME}'${NC}"
    echo ""
    echo "${YELLOW} > is it your branch? press '1' if you not press 'any key'"
    read -r  DONE
done

# branch 생성 및 checkout
git checkout -b "$BRANCH_NAME"

echo "${GREEN} > upload created branch '$BRANCH_NAME'${NC}"
git push --set-upstream origin "$BRANCH_NAME"

echo "${GREEN} > done.${NC}"
