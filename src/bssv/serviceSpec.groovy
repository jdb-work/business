package bssv

import spock.lang.Specification

import static java.net.NetworkInterface.*

class serviceSpec extends Specification {

    def "fails service lookup when external"() {
        expect:
        for (ip in networkInterfaces) {
            println ip.getInetAddresses().nextElement()
        }
        false

    }



}

