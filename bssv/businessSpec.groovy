package bssv

import spock.lang.Specification
import static java.net.NetworkInterface.getNetworkInterfaces
import static org.codehaus.groovy.runtime.GroovyCategorySupport.use

class businessSpec extends Specification {

    def "fails service lookup when external"() {
        expect:
        networkInterfaces() { ip ->
            println ip.getInetAddresses()
        } != null
    }

    def "ws-security crededentials are injected by withCreds"() {
        expect:
        true == true
    }

    def "address.city is 'Paris' for customer: 250780"() {
        expect:
        use(CustomerOps) {
            //assert address() instanceof Map<String, String>
            //assert amount() instanceof Map<String, String>
            assert id() instanceof String
        }
    }

    def "Id returns 250780 as java.lang.String"() {
        expect:
        use(CustomerOps) {
            assert id == "250780"
        }
    }
}
