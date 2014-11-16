package bssv.ops

import bssv.contexts.*
import wslite.soap.*

class SvcInvoker {
    def SvcCtx ctx = new CustmrCtx(ctx.@_id) //"250780"
    def SOAPClient client = new SOAPClient("https://oakdbs01:8182/DV910/${ctx.@soapfn}?wsdl")
    def SOAPResponse response = client.send(sslTrustAllCerts = true) {
        envelopeAttributes (
        'xmlns:test': "http://test.cxf.grails.org/",
        'xmlns:orac': "http://oracle.e1.bssv.JP010020/",
        'xmlns:soapenv': "soapenv" )
        version SOAPVersion.V1_1
        header {
            'wsse:Security'(
            'soapenv:mustUnderstand': "1",
            'xmlns:wsse': "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd",
            'xmlns:wsu': "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd"
            ) {
                'wsse:UsernameToken' {
                    'wsse:Username' ( username )
                    'wsse:Password' (
                    Type: "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#PasswordText",
                    ctx.@password )
                }
            }
        }
        body {
            ctx.@req
        }
    }
    assert response != null

    return (ctx.@resp = response)
}

