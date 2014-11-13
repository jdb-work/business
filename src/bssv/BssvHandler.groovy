package bssv

@Grab(group='com.github.groovy-wslite', module='groovy-wslite', version='1.1.0')
import wslite.soap.*

class BssvHandler {

    def call = { _id ->
        try {
            //todo: lift credentials
            def username = "mleXXXX"
            def password = "greaXXXX"
            //todo: lift service method
            def manager = "CustomerManager"
            SOAPClient client = new SOAPClient("https://oakdbs01:8182/DV910/${manager}?wsdl")
            def response = client.send(sslTrustAllCerts: true) {
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
                            entityId(_id)
                        }
                    }
                }
            }
            assert response != null
            return response
        }
        catch (SOAPFaultException sfe) {
            println sfe.message //fault
            println sfe.text    //envelope
            println sfe.httpResponse.statusCode
        }
        catch (SOAPClientException sce) {
            //indicates client error (i.e., 404 Not Found)
            println 'SCE: ' + sce.cause
        }
        catch (ArrayIndexOutOfBoundsException aioobe) {
            //todo: generalize error response
            return """
			<error>No address found for ${_id}</error>
			<address>
				<addressLine1></addressLine1>
				<addressLine2></addressLine2>
				<addressLine3></addressLine3>
				<addressLine4></addressLine4>
				<city></city>
				<countryCode></countryCode>
				<countyCode></countyCode>
				<mailingName></mailingName>
				<mailingNameSecondary></mailingNameSecondary>
				<postalCode></postalCode>
				<stateCode></stateCode>
			</address>
			"""
        }
    }
}