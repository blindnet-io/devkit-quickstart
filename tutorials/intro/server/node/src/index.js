
const express = require('express')
const cors = require('cors')
const TokenBuilder = require('@blindnet/jwt-node')

const app = express()
app.use(cors())

const port = 3000

const appId = '<YOUR_APPLICATION_ID>'
const key = '<YOUR_APPLICATION_KEY>'

const tokenBuilder = TokenBuilder.init(appId, key)

app.get('/token/admin', async (req, res) => {
  const token = await tokenBuilder.app()
  res.send(token)
})

app.get('/token/user/:uid', async (req, res) => {
  const token = await tokenBuilder.user(req.params.uid)
  res.send(token)
})

app.listen(port, () => {
  console.log(`Listening on port ${port}`)
})
