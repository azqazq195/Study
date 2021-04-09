from flask_restful import Resource
import logging as logger


class TaskById(Resource):
    def get(self, taskId):
        logger.debug("Inside get method of task by id")
        return {"message": f"Inside get method of task by id. Task Id = {taskId}"}, 200
        pass

    def post(self, taskId):
        logger.debug("Inside post method")
        return {"message": "Inside post method"}, 200
        pass

    def put(self, taskId):
        logger.debug("Inside put method")
        return {"message": "Inside put method"}, 200
        pass

    def delete(self, taskId):
        logger.debug("Inside delete method")
        return {"message": "Inside delete method"}, 200
        pass
