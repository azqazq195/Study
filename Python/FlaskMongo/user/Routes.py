from flask_restful import Resource
import logging as logger
from .controller import *


class Routes(Resource):
    def get(self):
        logger.debug("Inside get method")
        return get_some_users()
        pass

    def post(self):
        return create_user(request)
        pass
