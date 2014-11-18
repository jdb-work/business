package bssv
/*
//SAX html scanning/parsing with cyberneko
import org.cyberneko.html.parsers.SAXParser
def href = new XmlParser(new SAXParser()).parse("https://www.heroku.com/")
href.depthFirst().A['@href'].findAll{ if(it.endsWith(".com")) println it }
*/
/*
//Predicate example
abstract class Greeter {
    abstract String getName()
    void greet() {
        println "Hello, $name"
    }
}
interface Predicate<T> { boolean accept(T obj) }
boolean doFilter(String s) { s.contains('G') }
Predicate filter = this.&doFilter
assert filter.accept('Groovy') == true
Greeter greeter = GroovySystem.&getVersion
greeter.greet()


assert "GetCustomer" instanceof GString
*/
import groovy.xml.DOMBuilder
import groovy.xml.dom.DOMCategory
//@Grab(group='com.github.groovy-wslite', module='groovy-wslite', version='1.1.0')
import wslite.soap.*

System.setProperty("sun.security.ssl.allowUnsafeRenegotiation","false") //default "false"
System.setProperty("sun.security.ssl.allowLegacyHelloMessages","true") //default "true"
System.setProperty("jdk.tls.client.protocols","TLSv1") //removing ALL others
//System.setProperty("javax.net.debug","all")
//System.properties.each { k,v-> println "$k = $v" }

def client = new SOAPClient('https://oakdbs01:8182/DV910/CustomerManager?wsdl')
client.httpClient.sslTrustAllCerts = true
try {
    def id = 250780
    def username = "mlewis"
    def password = "great2014"
    SOAPResponse response = client.send {
        envelopeAttributes(
        //'xmlns:test': "http://test.cxf.grails.org/",
        'xmlns:soapenv': "soapenv",
        'xmlns:orac': "http://oracle.e1.bssv.JP010020/"
        )
        //version SOAPVersion.V1_1
        header {
            'wsse:Security'(
            //'soapenv:mustUnderstand': "1",
            'xmlns:wsse': "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd"
            //, 'xmlns:wsu': "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd"
            ) {
                'wsse:UsernameToken' {
                    'wsse:Username'(username)
                    'wsse:Password'(password)//'Type': "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#PasswordText",
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
    DOMCategory.globalTrimWhitespace
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
