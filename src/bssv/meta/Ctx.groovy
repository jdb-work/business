package bssv.meta

import bssv.ops.ServiceInvoker

class Ctx implements ServiceInvoker {

    def abstract id
    def abstract wsdl
    def username = "mlewis"
    def password = "great2014"
    def abstract req
    def resp
}


