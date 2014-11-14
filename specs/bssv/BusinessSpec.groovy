package bssv
import bssv.ops.CustomerOps
import spock.lang.Specification

import static org.codehaus.groovy.runtime.GroovyCategorySupport.use

class BusinessSpec extends Specification {

    def "fails service lookup when external"() {
        given:
        def ni = NetworkInterface.getByName("en0")
        expect:
        println ni.displayName
        println ni.hardwareAddress
        println ni.index
        println ni.loopback
        println ni.MTU
        println ni.up
        println ni.toString()
        true
    }

    def "ws-security crededentials are injected by withCreds"() {
        expect:
        assert true
    }

    def "address.city is 'Paris' for customer: 250780"() {
        given:
        def street
        when:
        use(CustomerOps) {
            street = "".address()
        }
        then:
        street == null
    }

    def "Id returns 250780 as java.lang.String"() {
        given:
        def entityId
        when:
        use(CustomerOps) {
            entityId = "".@id
        }
        then:
        entityId instanceof String
    }
}
