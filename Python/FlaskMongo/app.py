from flask import Flask
import logging as logger

logger.basicConfig(level="DEBUG")

app = Flask(__name__)

if __name__ == "__main__":
    logger.debug("Starting the application")
    from user import *
    app.run(
        host="0.0.0.0",
        port=80,
        debug=True,
    )
