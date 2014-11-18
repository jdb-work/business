package bssv.ops

import bssv.meta.Ctx
import groovy.util.logging.Log4j2
import wslite.soap.*

trait ServiceInvoker {

    def ctx = this as Ctx

    //@Log4j2
    def call = {

        assert ctx.@req != null

        try {
            def client = new SOAPClient(ctx.@wsdl as String)
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
                            'wsse:Username'(ctx.username)
                            'wsse:Password'(ctx.password)
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
        assert call != null
        return (ctx.resp = call)
    }
}