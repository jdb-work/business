package bssv.ops

import spock.lang.Specification

class CustomerOpsSpec extends Specification {

    def "test address"() {
        given:
        def street
        when:
        use(CustomerOps) {
            street = "main st".address()
        }
        then:
        street == null
    }

    def "test id"() {
        given:
        def entityId
        when:
        use(CustomerOps) { entityId = "".id() }
        then:
        entityId instanceof String
    }
}
