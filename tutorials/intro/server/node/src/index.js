
const express = require('express')
const cors = require('cors')
const TokenBuilder = require('@blindnet/jwt-node')

const app = express()
app.use(cors())

const port = 3000

const appId = '78f5fc15-5645-4f4f-8e1d-0792b7d89acd'
const key = 'EgPThokIzi0oGkOGPOuC3zA63/b39ZAefcbxpegoHog='

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
