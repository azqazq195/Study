from flask_restful import Resource
import logging as logger
from .controller import *


class RoutesById(Resource):
    def get(self, id):
        logger.debug("Inside get method by id")
        return get_users(id)
        pass

    def put(self, id):
        logger.debug("Inside put method by id")
        return update_user(id)
        pass

    def delete(self, id):
        logger.debug("Inside delete method by id")
        return delete_user(id)
        pass
