from schema.user_schema import UserSchema


class UserRepository:
    def get(self):
        print("user repository get all")

    def get(self, id: int):
        print(f"user repository get ${id}")

    def create(self, user: UserSchema):
        print(f"create user ${user}")

    def update(self, id:int, user: UserSchema):
        print(f"update user ${user} at id: ${id}")

    def delete(self, id: int):
        print(f"delete user id: ${id}")

