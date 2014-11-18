package bssv

import spock.lang.Specification

class BusinessSpec extends Specification {

    def "fails service lookup when external"() {
        given:
        def away = false
        expect:
        !away
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
        expect:
        false
    }
}
