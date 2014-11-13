

import groovy.xml.DOMBuilder
import groovy.xml.dom.DOMCategory
@Grab(group='com.github.groovy-wslite', module='groovy-wslite', version='1.1.0')
import wslite.soap.*

def client = new SOAPClient('https://oakdbs01:8182/DV910/CustomerManager?wsdl')
client.httpClient.sslTrustAllCerts = true
try {
    def id = 250780
    def username = "mleXXXX"
    def password = "greXXXX"
    SOAPResponse response = client.send {
        envelopeAttributes(
                'xmlns:test': "http://test.cxf.grails.org/",
                'xmlns:soapenv': "soapenv",
                'xmlns:orac': "http://oracle.e1.bssv.JP010020/")
        version SOAPVersion.V1_1
        header {
            'wsse:Security'(
                    'soapenv:mustUnderstand': "1",
                    'xmlns:wsse': "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd",
                    'xmlns:wsu': "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd") {
                'wsse:UsernameToken'('wsu:Id':"UsernameToken-13") {
                    'wsse:Username'(username)
                    'wsse:Password'('Type': "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#PasswordText", password)
                }
            }
        }
        body {
            'orac:getCustomer' {
                entity {
                    entityId(id)
                }
            }
        }
    }
    assert response != null
    def address = response.text.split("(?=<address>)|(?<=</address>)")[1]

    def reader  = new StringReader(address)
    def doc     = DOMBuilder.parse(reader)
    def records = doc.documentElement

    use(DOMCategory) {
        assert 11 == records.'*'.size()
        assert records.each{ el -> println el.firstChild.nodeValue}
    }
}
catch (SOAPFaultException sfe) {
    println sfe.message //fault
    println sfe.text    //envelope
    println sfe.httpResponse.statusCode
}
catch (SOAPClientException sce) {
    //indicates client error (i.e., 404 Not Found)
    println "SCE: " + sce.cause
}




