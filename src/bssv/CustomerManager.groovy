package bssv

import groovy.xml.DOMBuilder
import groovy.xml.dom.DOMCategory
import wslite.soap.*

class CustomerManager {

    String getCustomer(_id) {
        try {
            SOAPClient client = new SOAPClient('https://oakdbs01:8182/DV910/CustomerManager?wsdl')
            def response = client.send(sslTrustAllCerts:true, """
		    <soapenv:Envelope xmlns:orac="http://oracle.e1.bssv.JP010020/" xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/">
		    <soapenv:Header>
		        <wsse:Security xmlns:wsse="http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd"
		            xmlns="http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd"
		            xmlns:env="http://schemas.xmlsoap.org/soap/envelope/"
		            soapenv:mustUnderstand="1">
		            <wsse:UsernameToken>
		                <wsse:Username>mleXXXX</wsse:Username>
		                <wsse:Password>greXXXX</wsse:Password>
		            </wsse:UsernameToken>
		        </wsse:Security>
		    </soapenv:Header>
		    <soapenv:Body>
		        <orac:getCustomer>
		            <entity>
		                <entityId>${_id}</entityId>
		            </entity>
		        </orac:getCustomer>
		    </soapenv:Body>
		    </soapenv:Envelope>
		    """)
            assert response != null
			def address =  response.text.split("(?=<address>)|(?<=</address>)")[1]
			def reader  = new StringReader(address)
			def doc     = DOMBuilder.parse(reader)
			def records = doc.documentElement
			use(DOMCategory) {
				assert 11 == records.'*'.size()
				assert records.each{ el -> if (el.tagName == "city") return el.firstChild.nodeValue}
			}
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

