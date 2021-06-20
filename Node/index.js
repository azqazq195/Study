const express = require('express');
const app = express()
const port = 9999

app.get('/', function (req, res) {
    res.send("hello world")
})

const router = express.Router()

router.use(function timelog(req, res, next) {
    console.log('Time: ', Date.now())
    next()
})

router.get('/', function (req, res) {
    res.send('Birds home page')
})

router.get('/about', function (req, res) {
    res.send('About birds')
})

module.exports = router

const birds = require('./birds')
app.use('/birds', birds)

app.listen(port, () => {
    console.log(`Example app listening at http://localhost:${port}`)
})
