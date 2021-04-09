from flask_restful import Api

from app import app
from .Routes import Routes
from .RoutesById import RoutesById

restServer = Api(app)

restServer.add_resource(Routes, "/api/user")
restServer.add_resource(RoutesById, "/api/user/<id>")
