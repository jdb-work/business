package bssv.ops

import bssv.ctx.Customer
import wslite.soap.*

class SvcInvoker {

    //todo: inject this context
    def ctx = new Customer()

    def call = {
        try {
            def client = new SOAPClient(ctx.@addr as String)
            client.httpClient.sslTrustAllCerts = true
            def SOAPResponse response = client.send(sslTrustAllCerts:true) {
                envelopeAttributes(
                'xmlns:soapenv':"soapenv",
                'xmlns:orac':"http://oracle.e1.bssv.JP010020/")
                header {
                    'wsse:Security'(
                    'xmlns:wsse':"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd"
                    ) {
                        'wsse:UsernameToken' {
                            'wsse:Username'(ctx.@username)
                            'wsse:Password'(ctx.@password)
                        }
                    }
                }
                body {
                    ctx.@req
                }
            }
        }
        catch (SOAPFaultException sfe) {
            println sfe.message
            println sfe.text
            println sfe.httpResponse.statusCode
        }
        catch (SOAPClientException sce) {
            println "SCE: " + sce.cause
        }
        assert response != null
        return (ctx.@resp = response)
    }
}