package bssv.specs

import bssv.CustomerOps
import org.codehaus.groovy.runtime.GroovyCategorySupport
import spock.lang.Specification

import static org.codehaus.groovy.runtime.GroovyCategorySupport.use

class CustomerOpsSpec extends Specification {

    def "test address"() {
        given: def street
        when: GroovyCategorySupport.use(CustomerOps) { street = "main st".address() }
        then: street == null
    }

    def "test id"() {
        given: def entityId
        when: use(CustomerOps) { entityId = "".id() }
        then: entityId instanceof String
    }
}
